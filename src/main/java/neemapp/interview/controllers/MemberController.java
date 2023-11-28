package neemapp.interview.controllers;

import neemapp.interview.data.*;
import neemapp.interview.repositories.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberRepository memberRep;
    private final CategoryRepository categoryRep;
    private final DeductibleRepository deductibleRep;
    private final PlanDeductibleRepository planDeductibleRep;
    //private final FamilyRepository familyRep;

    public MemberController(
            MemberRepository m, CategoryRepository c, DeductibleRepository d, PlanDeductibleRepository p
    ){
        this.memberRep = m;
        this.categoryRep = c;
        this.deductibleRep = d;
        this.planDeductibleRep = p;
    }

    @GetMapping("/{id}")
    Member getMember(@PathVariable Long id){
        Optional<Member> om = memberRep.findById(id);
        if(om.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "member not found");
        }
        Member m = om.get();
        Plan plan = m.getFamily().getPlan();
        Set<PlanDeductible> pds = planDeductibleRep.findAllByPlanId(plan.getId());
        for(PlanDeductible pd : pds){
            m.getAllDeductibles().put(pd.getCategory().getId(), new DisplayDeductible(pd.getCategory().getName(), pd.getIndividual(), false));
        }
        for(Deductible d: deductibleRep.findAllByMemberId(m.getId())){
            m.getAllDeductibles().put(d.getCategory().getId(), new DisplayDeductible(d.getCategory().getName(), d.getIndividual(), true));
        }
        return m;
    }


    @PostMapping("/deductible")
    ResponseEntity<Void> updateDeductible(@RequestParam Long memberId, @RequestParam Long categoryId, @RequestParam Double individual, @RequestParam Boolean isNonStandard){
        Member m = updateDeductibleInternal(memberId, categoryId, individual, isNonStandard);
        if(m.getIsHouseHold()){
            updateFamilyMembers(m, categoryId, individual, isNonStandard);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private Member updateDeductibleInternal(Long memId, Long catId, Double ind, Boolean nonS) throws ResponseStatusException{
        Optional<Member> m = memberRep.findById(memId);
        Optional<Category> c = categoryRep.findById(catId);
        if(m.isEmpty() || c.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "member or category not found");
        }
        if(nonS) {
            // changes are done at the member level (Deductible table)
            Optional<Deductible> d = deductibleRep.findById(new DeductibleId(memId, catId));
            Deductible dd = d.orElseGet(Deductible::new);
            dd.setMember(m.get());
            dd.setCategory(c.get());
            dd.setIndividual(ind);
            deductibleRep.save(dd);
        }else{
            // changes are done at plan level (PlanDeductible table)
            Member mem = m.get();
            Plan plan = mem.getFamily().getPlan();
            Optional<PlanDeductible> pd = planDeductibleRep.findById(new PlanDeductibleId(catId, plan.getId()));
            PlanDeductible pdd = pd.orElseGet(PlanDeductible::new);
            pdd.setCategory(c.get());
            pdd.setPlan(plan);
            pdd.setIndividual(ind);
            planDeductibleRep.save(pdd);
        }
        return m.get();
    }

    private void updateFamilyMembers(Member mem, Long catId, Double individual, Boolean isNonStandard){
        for(Member tmp : memberRep.findAllByFamilyId(mem.getFamily().getId())){
            if(!Objects.equals(tmp.getId(), mem.getId())){
                updateDeductibleInternal(tmp.getId(), catId, individual, isNonStandard);
            }
        }
    }
}

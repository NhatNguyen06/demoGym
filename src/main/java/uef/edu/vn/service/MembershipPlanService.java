/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.MembershipPlan;
import uef.edu.vn.repository.MembershipPlanRepository;

@Service
public class MembershipPlanService {

    private final MembershipPlanRepository membershipPlanRepository;

    @Autowired
    public MembershipPlanService(
            MembershipPlanRepository membershipPlanRepository) {

        this.membershipPlanRepository = membershipPlanRepository;
    }

    public MembershipPlan save(MembershipPlan plan) {

        membershipPlanRepository.save(plan);

        return plan;
    }

    public List<MembershipPlan> findAll() {

        return membershipPlanRepository.findAll();
    }

    public MembershipPlan findById(int id) {

        return membershipPlanRepository.findById(id);
    }

    public List<MembershipPlan> searchByName(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return findAll();
        }

        return membershipPlanRepository.searchByName(keyword);
    }

    public boolean update(MembershipPlan plan) {

        if (findById(plan.getId()) == null) {
            return false;
        }

        membershipPlanRepository.update(plan);

        return true;
    }

    public boolean delete(int id) {

        if (findById(id) == null) {
            return false;
        }

        membershipPlanRepository.delete(id);

        return true;
    }

    public long count() {

        return membershipPlanRepository.count();
    }
}

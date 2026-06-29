package uef.edu.vn.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.MembershipPlan;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.MembershipPlanService;

@Controller
@RequestMapping("/membership-plans")
public class MemberShipPlanController {

    private final MembershipPlanService membershipPlanService;

    @Autowired
    public MemberShipPlanController(
            MembershipPlanService membershipPlanService) {

        this.membershipPlanService = membershipPlanService;
    }

    @GetMapping

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String listPlans(
            Model model) {

        model.addAttribute(
                "plans",
                membershipPlanService.findAll()
        );

        return "plan/list";
    }

    @GetMapping("/detail/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String detailPlan(
            @PathVariable int id,
            Model model) {

        MembershipPlan plan
                = membershipPlanService.findById(
                        id
                );

        if (plan == null) {

            model.addAttribute(
                    "errorMessage",
                    "Membership Plan Not Found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "plan",
                plan
        );

        return "plan/detail";
    }

    @GetMapping("/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        })

    public String showAddForm(
            Model model) {

        model.addAttribute(
                "plan",
                new MembershipPlan()
        );

        model.addAttribute(
                "pageTitle",
                "Add Membership Plan"
        );

        return "plan/form";
    }

    @PostMapping("/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String addPlan(
            @Valid
            @ModelAttribute("plan") MembershipPlan plan,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Add Membership Plan"
            );

            return "plan/form";
        }

        membershipPlanService.save(
                plan
        );

        return "redirect:/membership-plans";
    }

    @GetMapping("/edit/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        })

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        MembershipPlan plan
                = membershipPlanService.findById(
                        id
                );

        if (plan == null) {

            model.addAttribute(
                    "errorMessage",
                    "Membership Plan Not Found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "plan",
                plan
        );

        model.addAttribute(
                "pageTitle",
                "Edit Membership Plan"
        );

        return "plan/form";
    }

    @PostMapping("/edit")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String updatePlan(
            @Valid
            @ModelAttribute("plan") MembershipPlan plan,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Edit Membership Plan"
            );

            return "plan/form";
        }

        membershipPlanService.update(
                plan
        );

        return "redirect:/membership-plans";
    }

    @GetMapping("/delete/{id}")

        @RoleRequired({
        Role.ADMIN,
        
    })

    public String deletePlan(
            @PathVariable int id) {

        membershipPlanService.delete(
                id
        );

        return "redirect:/membership-plans";
    }

}

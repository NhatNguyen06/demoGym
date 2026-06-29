package uef.edu.vn.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.MembershipPlan;
import uef.edu.vn.model.Role;
import uef.edu.vn.model.Subscription;
import uef.edu.vn.model.SubscriptionStatus;
import uef.edu.vn.service.MemberService;
import uef.edu.vn.service.MembershipPlanService;
import uef.edu.vn.service.SubscriptionService;

@Controller
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    private final MembershipPlanService membershipPlanService;

    private final MemberService memberService;

    @Autowired
    public SubscriptionController(
            SubscriptionService subscriptionService,
            MembershipPlanService membershipPlanService,
            MemberService memberService) {

        this.subscriptionService
                = subscriptionService;

        this.membershipPlanService
                = membershipPlanService;

        this.memberService
                = memberService;
    }

    @GetMapping

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String listSubscriptions(
            Model model) {

        subscriptionService
                .updateExpiredSubscriptions();

        model.addAttribute(
                "subscriptions",
                subscriptionService.findAll()
        );

        return "subscription/list";
    }

    @GetMapping("/detail/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        Role.MEMBER
        
    })

    public String detailSubscription(
            @PathVariable int id,
            Model model) {

        Subscription subscription
                = subscriptionService.findById(
                        id
                );

        if (subscription == null) {

            model.addAttribute(
                    "errorMessage",
                    "Subscription not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "subscription",
                subscription
        );

        return "subscription/detail";
    }

    @GetMapping("/register")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String showRegisterForm(
            Model model) {

        model.addAttribute(
                "subscription",
                new Subscription()
        );

        model.addAttribute(
                "plans",
                membershipPlanService.findAll()
        );

        model.addAttribute(
                "members",
                memberService.findAll()
        );

        return "subscription/register";
    }

    @PostMapping("/register")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String registerSubscription(
            @RequestParam int memberId,
            @RequestParam int planId,
            Model model) {

        MembershipPlan plan
                = membershipPlanService.findById(
                        planId
                );

        if (plan == null) {

            model.addAttribute(
                    "errorMessage",
                    "Membership Plan not found!"
            );

            return "error/404";
        }

        Subscription subscription
                = subscriptionService.register(
                        memberId,
                        plan
                );

        return "redirect:/subscriptions/detail/"
                + subscription.getId();
    }

    @GetMapping("/edit/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })
    public String showEditForm(
            @PathVariable int id,
            Model model) {

        Subscription subscription
                = subscriptionService.findById(
                        id
                );

        if (subscription == null) {

            return "error/404";
        }

        model.addAttribute(
                "subscription",
                subscription
        );

        model.addAttribute(
                "plans",
                membershipPlanService.findAll()
        );

        return "subscription/form";
    }

    @PostMapping("/edit")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String updateSubscription(
            @Valid
            @ModelAttribute("subscription") Subscription subscription,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "plans",
                    membershipPlanService.findAll()
            );

            return "subscription/form";
        }

        subscriptionService.update(
                subscription
        );

        return "redirect:/subscriptions";
    }

    @GetMapping("/renew/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String renewForm(
            @PathVariable int id,
            Model model) {

        Subscription subscription
                = subscriptionService.findById(
                        id
                );

        if (subscription == null) {

            return "error/404";
        }

        model.addAttribute(
                "subscription",
                subscription
        );

        return "subscription/renew";
    }

    @PostMapping("/renew")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String renewSubscription(
            @RequestParam int subscriptionId,
            @RequestParam int months) {

        subscriptionService.renew(
                subscriptionId,
                months
        );

        return "redirect:/subscriptions";
    }

    @GetMapping("/cancel/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String cancelSubscription(
            @PathVariable int id) {

        subscriptionService.cancel(
                id
        );

        return "redirect:/subscriptions";
    }

    @GetMapping("/delete/{id}")

        @RoleRequired({
        Role.ADMIN,
        
    })

    public String deleteSubscription(
            @PathVariable int id) {

        subscriptionService.delete(
                id
        );

        return "redirect:/subscriptions";
    }

    @GetMapping("/member/{memberId}")

        @RoleRequired({
        Role.ADMIN,
        
        Role.RECEPTIONIST,
        Role.MEMBER
    })

    public String subscriptionsByMember(
            @PathVariable int memberId,
            Model model) {

        model.addAttribute(
                "subscriptions",
                subscriptionService
                        .getSubscriptionsByMember(
                                memberId
                        )
        );

        model.addAttribute(
                "memberId",
                memberId
        );

        return "subscription/list";
    }

    @GetMapping("/active")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })
    public String activeSubscriptions(
            Model model) {

        model.addAttribute(
                "subscriptions",
                subscriptionService
                        .findActiveSubscriptions()
        );

        model.addAttribute(
                "pageTitle",
                "Active Subscriptions"
        );

        return "subscription/list";
    }

    @GetMapping("/expired")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String expiredSubscriptions(
            Model model) {

        model.addAttribute(
                "subscriptions",
                subscriptionService
                        .findExpiredSubscriptions()
        );

        model.addAttribute(
                "pageTitle",
                "Expired Subscriptions"
        );

        return "subscription/list";
    }

    @GetMapping("/status")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String subscriptionsByStatus(
            @RequestParam SubscriptionStatus status,
            Model model) {

        model.addAttribute(
                "subscriptions",
                subscriptionService
                        .findByStatus(
                                status
                        )
        );

        model.addAttribute(
                "selectedStatus",
                status
        );

        return "subscription/list";
    }

}

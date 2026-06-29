package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.service.ClassService;
import uef.edu.vn.service.EquipmentService;
import uef.edu.vn.service.MemberService;
import uef.edu.vn.service.MembershipPlanService;
import uef.edu.vn.service.PaymentService;
import uef.edu.vn.service.SubscriptionService;
import uef.edu.vn.service.TrainerService;

@Controller
@RequestMapping("/dashboard")

public class DashboardController {

    private final MemberService memberService;

    private final TrainerService trainerService;

    private final MembershipPlanService membershipPlanService;

    private final SubscriptionService subscriptionService;

    private final ClassService classService;

    private final PaymentService paymentService;

    private final EquipmentService equipmentService;

    @Autowired
    public DashboardController(
            MemberService memberService,
            TrainerService trainerService,
            MembershipPlanService membershipPlanService,
            SubscriptionService subscriptionService,
            ClassService classService,
            PaymentService paymentService,
            EquipmentService equipmentService) {

        this.memberService = memberService;

        this.trainerService = trainerService;

        this.membershipPlanService = membershipPlanService;

        this.subscriptionService = subscriptionService;

        this.classService = classService;

        this.paymentService = paymentService;

        this.equipmentService = equipmentService;
    }

    @GetMapping
//    @RoleRequired({
//        "ADMIN",
//        "MANAGER"
//    })
    public String dashboard(
            Model model) {

        model.addAttribute(
                "totalMembers",
                memberService.count()
        );

        model.addAttribute(
                "totalTrainers",
                trainerService.count()
        );

        model.addAttribute(
                "totalPlans",
                membershipPlanService.count()
        );

        model.addAttribute(
                "totalSubscriptions",
                subscriptionService.count()
        );

        model.addAttribute(
                "activeSubscriptions",
                subscriptionService
                        .countActiveSubscriptions()
        );

        model.addAttribute(
                "expiredSubscriptions",
                subscriptionService
                        .countExpiredSubscriptions()
        );

        model.addAttribute(
                "totalClasses",
                classService.totalClasses()
        );

        model.addAttribute(
                "totalSchedules",
                classService.totalSchedules()
        );

        model.addAttribute(
                "totalEquipments",
                equipmentService.count()
        );

        model.addAttribute(
                "totalRevenue",
                paymentService.totalRevenue()
        );
        model.addAttribute(
                "availableEquipments",
                equipmentService.countAvailable()
        );

        model.addAttribute(
                "maintenanceEquipments",
                equipmentService.countMaintenance()
        );

        model.addAttribute(
                "brokenEquipments",
                equipmentService.countBroken()
        );

        return "dashboard/index";
    }

}

package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.AttendanceService;
import uef.edu.vn.service.UserService;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    // Xem danh sách điểm danh
    @GetMapping

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        Role.TRAINER
    })

    public String list(
            Model model) {

        model.addAttribute(
                "attendances",
                attendanceService.findAll()
        );

        return "attendance/list";
    }

    // Hiển thị form checkin
    @GetMapping("/checkin")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String checkIn(
            Model model) {

        model.addAttribute(
                "members",
                userService.findAllMembers()
        );

        return "attendance/checkin";
    }

    // Xem lịch sử điểm danh của thành viên
    @GetMapping("/member/{memberId}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.MEMBER,
        Role.TRAINER
    })

    public String memberHistory(
            @PathVariable int memberId,
            Model model) {

        model.addAttribute(
                "attendances",
                attendanceService.findByMember(
                        memberId
                )
        );

        model.addAttribute(
                "memberId",
                memberId
        );

        return "attendance/history";
    }

    // Thực hiện checkin
    @PostMapping("/checkin")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        
    })

    public String checkIn(
            @RequestParam int memberId) {

        attendanceService.checkIn(
                memberId
        );

        return "redirect:/attendance";
    }

}

package uef.edu.vn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.EmailNotificationService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public NotificationController(
            EmailNotificationService emailNotificationService) {

        this.emailNotificationService = emailNotificationService;
    }

    
    @GetMapping("/send-expiry")
    @RoleRequired({Role.ADMIN})
    public String sendExpiryNotifications(Model model) {

        // Gọi service gửi mail, nhận về số email đã gửi thành công
        int sentCount = emailNotificationService.triggerManually();

        // Truyền kết quả ra view để hiển thị thông báo
        model.addAttribute(
                "message",
                "Đã gửi thành công " + sentCount + " email nhắc nhở hết hạn gói tập."
        );

        model.addAttribute("sentCount", sentCount);

        return "notification/result";
    }
}
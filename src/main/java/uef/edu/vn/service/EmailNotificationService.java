package uef.edu.vn.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.Member;
import uef.edu.vn.model.MembershipPlan;
import uef.edu.vn.model.Subscription;
import uef.edu.vn.repository.MemberRepository;
import uef.edu.vn.repository.MembershipPlanRepository;
import uef.edu.vn.repository.SubscriptionRepository;


@Service
public class EmailNotificationService {

    
    private static final int DAYS_BEFORE_EXPIRY = 7;

   
    private static final String SENDER_EMAIL = "your-gym-email@gmail.com";

    
    @Autowired(required = false)
    private JavaMailSender mailSender;

    
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MembershipPlanRepository membershipPlanRepository;

    
    @Scheduled(cron = "0 0 8 * * *")
    public void sendExpirationReminders() {

        System.out.println(">>> [EmailService] Bắt đầu kiểm tra gói tập sắp hết hạn: "
                + LocalDate.now());

    
        List<Subscription> expiringSubs
                = subscriptionRepository.findExpiringWithin(DAYS_BEFORE_EXPIRY);

        if (expiringSubs.isEmpty()) {
            System.out.println(">>> [EmailService] Không có gói tập nào sắp hết hạn.");
            return;
        }

        System.out.println(">>> [EmailService] Tìm thấy "
                + expiringSubs.size() + " gói tập sắp hết hạn.");

        
        int successCount = 0;
        int failCount = 0;

        for (Subscription sub : expiringSubs) {

           
            Member member = memberRepository.findById(sub.getMemberId());

      
            MembershipPlan plan = membershipPlanRepository.findById(sub.getPlanId());

           
            if (member == null || plan == null) {
                System.err.println(">>> [EmailService] Bỏ qua subscription id="
                        + sub.getId() + " (không tìm thấy member hoặc plan)");
                failCount++;
                continue;
            }

            
            boolean sent = sendReminderEmail(member, plan, sub);

            if (sent) {
                successCount++;
            } else {
                failCount++;
            }
        }

        System.out.println(">>> [EmailService] Hoàn tất. Thành công: "
                + successCount + " | Thất bại: " + failCount);
    }

    
    private boolean sendReminderEmail(
            Member member,
            MembershipPlan plan,
            Subscription sub) {

       
        String endDateFormatted = sub.getEndDate()
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        
        long daysLeft = LocalDate.now().until(sub.getEndDate()).getDays();

        
        String subject = "[GymMS] Nhắc nhở: Gói tập của bạn sắp hết hạn!";

        String body = buildEmailBody(
                member.getFullName(),
                plan.getName(),
                endDateFormatted,
                daysLeft
        );

        
        if (mailSender == null) {
            System.out.println(">>> [MOCK EMAIL] Luồng gửi mail đã chạy đúng!");
            System.out.println(">>> Gửi tới  : " + member.getEmail());
            System.out.println(">>> Tiêu đề  : " + subject);
            System.out.println(">>> Nội dung :");
            System.out.println(body);
            System.out.println(">>> -------------------------------------------");
            return true;
        }

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(SENDER_EMAIL);
            message.setTo(member.getEmail());
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            System.out.println(">>> [EmailService] Đã gửi email tới: "
                    + member.getEmail());
            return true;

        } catch (Exception e) {
            System.err.println(">>> [EmailService] Lỗi gửi mail tới "
                    + member.getEmail() + ": " + e.getMessage());
            return false;
        }
    }

    
    private String buildEmailBody(
            String memberName,
            String planName,
            String endDate,
            long daysLeft) {

        return """
                Xin chào %s,

                Chúng tôi muốn nhắc bạn rằng gói tập của bạn tại GymMS sắp hết hạn.

                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
                  Gói tập    : %s
                  Ngày hết hạn: %s
                  Còn lại    : %d ngày
                ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

                Để tiếp tục sử dụng dịch vụ, vui lòng gia hạn gói tập trước ngày hết hạn.

                Bạn có thể liên hệ lễ tân hoặc đăng nhập vào hệ thống để gia hạn gói tập.

                Trân trọng,
                Đội ngũ GymMS
                Hotline: 0909-XXX-XXX
                """.formatted(memberName, planName, endDate, daysLeft);
    }

    
    public int triggerManually() {

        System.out.println(">>> [EmailService] Admin kích hoạt gửi mail thủ công.");

        List<Subscription> expiringSubs
                = subscriptionRepository.findExpiringWithin(DAYS_BEFORE_EXPIRY);

        int successCount = 0;

        for (Subscription sub : expiringSubs) {

            Member member = memberRepository.findById(sub.getMemberId());
            MembershipPlan plan = membershipPlanRepository.findById(sub.getPlanId());

            if (member == null || plan == null) {
                continue;
            }

            if (sendReminderEmail(member, plan, sub)) {
                successCount++;
            }
        }

        return successCount;
    }
}
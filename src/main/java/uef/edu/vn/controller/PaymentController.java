package uef.edu.vn.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.Payment;
import uef.edu.vn.model.PaymentStatus;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.MemberService;
import uef.edu.vn.service.PaymentService;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import uef.edu.vn.service.ExcelExportService;
import uef.edu.vn.service.PdfExportService;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    private final MemberService memberService;

    private final ExcelExportService excelExportService;

    private final PdfExportService pdfExportService;

    @Autowired
    public PaymentController(
            PaymentService paymentService,
            MemberService memberService,
            ExcelExportService excelExportService,
            PdfExportService pdfExportService) {

        this.paymentService = paymentService;
        this.memberService = memberService;
        this.excelExportService = excelExportService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String listPayments(
            Model model) {

        model.addAttribute(
                "payments",
                paymentService.findAll()
        );

        model.addAttribute(
                "totalRevenue",
                paymentService.totalRevenue()
        );

        return "payment/list";
    }

    @GetMapping("/detail/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,
        Role.MEMBER
    })

    public String paymentDetail(
            @PathVariable int id,
            Model model) {

        Payment payment
                = paymentService.findById(
                        id
                );

        if (payment == null) {

            return "error/404";
        }

        model.addAttribute(
                "payment",
                payment
        );

        return "payment/detail";
    }

    @GetMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String showAddForm(
            Model model) {

        model.addAttribute(
                "payment",
                new Payment()
        );

        model.addAttribute(
                "members",
                memberService.findAll()
        );

        model.addAttribute(
                "statuses",
                PaymentStatus.values()
        );

        return "payment/form";
    }

    @PostMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String addPayment(
            @Valid
            @ModelAttribute("payment") Payment payment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "members",
                    memberService.findAll()
            );

            model.addAttribute(
                    "statuses",
                    PaymentStatus.values()
            );

            return "payment/form";
        }

        paymentService.save(
                payment
        );

        return "redirect:/payments";
    }

    @GetMapping("/edit/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        Payment payment
                = paymentService.findById(
                        id
                );

        if (payment == null) {

            return "error/404";
        }

        model.addAttribute(
                "payment",
                payment
        );

        model.addAttribute(
                "members",
                memberService.findAll()
        );

        model.addAttribute(
                "statuses",
                PaymentStatus.values()
        );

        return "payment/form";
    }

    @PostMapping("/edit")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String updatePayment(
            @Valid
            @ModelAttribute("payment") Payment payment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "members",
                    memberService.findAll()
            );

            model.addAttribute(
                    "statuses",
                    PaymentStatus.values()
            );

            return "payment/form";
        }

        paymentService.update(
                payment
        );

        return "redirect:/payments";
    }

    @GetMapping("/delete/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.RECEPTIONIST,})

    public String deletePayment(
            @PathVariable int id) {

        paymentService.delete(
                id
        );

        return "redirect:/payments";
    }

    @GetMapping("/receipt/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST,})

    public String receipt(
            @PathVariable int id,
            Model model) {

        Payment payment
                = paymentService.findById(
                        id
                );

        if (payment == null) {

            return "error/404";
        }

        model.addAttribute(
                "payment",
                payment
        );

        return "payment/receipt";
    }

    @GetMapping("/export/excel")
    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })
    public ResponseEntity<byte[]> exportExcel() throws IOException {

        List<Payment> payments = paymentService.findAll();

        byte[] excel = excelExportService.exportPayments(payments);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("payments.xlsx")
                        .build());

        headers.setContentType(
                MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(excel);
    }

    @GetMapping("/export/pdf")
    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })
    public ResponseEntity<byte[]> exportPdf() {

        List<Payment> payments = paymentService.findAll();

        byte[] pdf = pdfExportService.exportPayments(payments);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("payments.pdf")
                        .build());

        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdf);
    }

}

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
import org.springframework.web.bind.annotation.RequestParam;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.Member;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.MemberService;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import uef.edu.vn.service.ExcelExportService;
import uef.edu.vn.service.PdfExportService;

@Controller
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ExcelExportService excelExportService;
    private final PdfExportService pdfExportService;

    @Autowired
    public MemberController(
            MemberService memberService, ExcelExportService excelExportService, PdfExportService pdfExportService) {

        this.memberService = memberService;
        this.excelExportService = excelExportService;
        this.pdfExportService = pdfExportService;
    }

    @GetMapping

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String listMembers(
            Model model) {

        model.addAttribute(
                "members",
                memberService.findAll()
        );

        return "member/list";
    }

    @GetMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String showAddForm(
            Model model) {

        model.addAttribute(
                "member",
                new Member()
        );

        return "member/form";
    }

    @PostMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String addMember(
            @Valid
            @ModelAttribute("member") Member member,
            BindingResult result) {

        if (result.hasErrors()) {

            return "member/form";
        }

        memberService.save(
                member
        );

        return "redirect:/members";
    }

    @GetMapping("/edit/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        Member member
                = memberService.findById(
                        id
                );

        if (member == null) {

            return "error/404";
        }

        model.addAttribute(
                "member",
                member
        );

        return "member/form";
    }

    @PostMapping("/edit")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String updateMember(
            @Valid
            @ModelAttribute("member") Member member,
            BindingResult result) {

        if (result.hasErrors()) {

            return "member/form";
        }

        memberService.update(
                member
        );

        return "redirect:/members";
    }

    @GetMapping("/delete/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,})

    public String deleteMember(
            @PathVariable int id) {

        memberService.delete(
                id
        );

        return "redirect:/members";
    }

    @GetMapping("/search")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String search(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "members",
                memberService.searchByName(
                        keyword
                )
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        return "member/list";
    }

    @GetMapping("/detail/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })

    public String memberDetail(
            @PathVariable int id,
            Model model) {

        Member member
                = memberService.findById(
                        id
                );

        if (member == null) {

            model.addAttribute(
                    "errorMessage",
                    "Member not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "member",
                member
        );

        return "member/detail";
    }

    @GetMapping("/export/excel")
    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        Role.RECEPTIONIST
    })
    public ResponseEntity<byte[]> exportExcel() throws IOException {

        List<Member> members = memberService.findAll();

        byte[] excel = excelExportService.exportMembers(members);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("members.xlsx")
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

        List<Member> members = memberService.findAll();

        byte[] pdf = pdfExportService.exportMembers(members);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentDisposition(
                ContentDisposition
                        .attachment()
                        .filename("members.pdf")
                        .build());

        headers.setContentType(MediaType.APPLICATION_PDF);

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(pdf);
    }
}

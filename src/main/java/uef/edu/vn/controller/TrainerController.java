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
import uef.edu.vn.model.Role;
import uef.edu.vn.model.Trainer;
import uef.edu.vn.service.TrainerService;

@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private final TrainerService trainerService;

    @Autowired
    public TrainerController(
            TrainerService trainerService) {

        this.trainerService = trainerService;
    }

    @GetMapping

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String listTrainers(
            Model model) {

        model.addAttribute(
                "trainers",
                trainerService.findAll()
        );

        return "trainer/list";
    }

    @GetMapping("/detail/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String trainerDetail(
            @PathVariable int id,
            Model model) {

        Trainer trainer
                = trainerService.findById(
                        id
                );

        if (trainer == null) {

            model.addAttribute(
                    "errorMessage",
                    "Trainer not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "trainer",
                trainer
        );

        return "trainer/detail";
    }

    @GetMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String showAddForm(
            Model model) {

        model.addAttribute(
                "trainer",
                new Trainer()
        );

        model.addAttribute(
                "pageTitle",
                "Add Trainer"
        );

        return "trainer/form";
    }

    @PostMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String addTrainer(
            @Valid
            @ModelAttribute("trainer") Trainer trainer,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Add Trainer"
            );

            return "trainer/form";
        }

        trainerService.save(
                trainer
        );

        return "redirect:/trainers";
    }

    @GetMapping("/edit/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        Trainer trainer
                = trainerService.findById(
                        id
                );

        if (trainer == null) {

            model.addAttribute(
                    "errorMessage",
                    "Trainer not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "trainer",
                trainer
        );

        model.addAttribute(
                "pageTitle",
                "Edit Trainer"
        );

        return "trainer/form";
    }

    @PostMapping("/edit")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String updateTrainer(
            @Valid
            @ModelAttribute("trainer") Trainer trainer,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Edit Trainer"
            );

            return "trainer/form";
        }

        boolean updated
                = trainerService.update(
                        trainer
                );

        if (!updated) {

            model.addAttribute(
                    "errorMessage",
                    "Update failed!"
            );

            return "error/404";
        }

        return "redirect:/trainers";
    }

    @GetMapping("/delete/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })
    public String deleteTrainer(
            @PathVariable int id) {

        trainerService.delete(
                id
        );

        return "redirect:/trainers";
    }

    @GetMapping("/search")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String searchTrainer(
            @RequestParam(
                    required = false,
                    defaultValue = ""
            ) String keyword,
            Model model) {

        model.addAttribute(
                "trainers",
                trainerService.searchByName(
                        keyword
                )
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        return "trainer/list";
    }

    @GetMapping("/specialization")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String searchBySpecialization(
            @RequestParam String value,
            Model model) {

        model.addAttribute(
                "trainers",
                trainerService.searchBySpecialization(
                        value
                )
        );

        model.addAttribute(
                "specialization",
                value
        );

        return "trainer/list";
    }

}

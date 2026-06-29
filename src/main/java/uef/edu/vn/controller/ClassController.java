package uef.edu.vn.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.ClassSchedule;
import uef.edu.vn.model.GymClass;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.ClassService;
import uef.edu.vn.service.TrainerService;

@Controller
@RequestMapping("/classes")
public class ClassController {

    private final ClassService classService;

    private final TrainerService trainerService;

    @Autowired
    public ClassController(
            ClassService classService,
            TrainerService trainerService) {

        this.classService = classService;

        this.trainerService = trainerService;
    }

    @GetMapping

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String listClasses(
            Model model) {

        model.addAttribute(
                "classes",
                classService.findAllClasses()
        );

        return "classes/list";
    }

    @GetMapping("/detail/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String classDetail(
            @PathVariable int id,
            Model model) {

        GymClass gymClass
                = classService.findClassById(id);

        if (gymClass == null) {

            return "error/404";
        }

        model.addAttribute(
                "gymClass",
                gymClass
        );

        model.addAttribute(
                "schedules",
                classService.findSchedulesByClass(
                        id
                )
        );

        return "classes/detail";
    }

    @GetMapping("/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String showAddForm(
            Model model) {

        model.addAttribute(
                "gymClass",
                new GymClass()
        );

        model.addAttribute(
                "trainers",
                trainerService.findAll()
        );

        return "classes/form";
    }

    @PostMapping("/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String addClass(
            @Valid
            @ModelAttribute("gymClass") GymClass gymClass,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "trainers",
                    trainerService.findAll()
            );

            return "classes/form";
        }

        classService.saveClass(
                gymClass
        );

        return "redirect:/classes";
    }

    @GetMapping("/edit/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        GymClass gymClass
                = classService.findClassById(
                        id
                );

        if (gymClass == null) {

            return "error/404";
        }

        model.addAttribute(
                "gymClass",
                gymClass
        );

        model.addAttribute(
                "trainers",
                trainerService.findAll()
        );

        return "classes/form";
    }

    @PostMapping("/edit")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String updateClass(
            @Valid
            @ModelAttribute("gymClass") GymClass gymClass,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "trainers",
                    trainerService.findAll()
            );

            return "classes/form";
        }

        classService.updateClass(
                gymClass
        );

        return "redirect:/classes";
    }

    @GetMapping("/delete/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })
    public String deleteClass(
            @PathVariable int id) {

        classService.deleteClass(
                id
        );

        return "redirect:/classes";
    }

    @GetMapping("/search")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String searchClass(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "classes",
                classService.searchByName(
                        keyword
                )
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        return "classes/list";
    }

    @GetMapping("/trainer/{trainerId}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String classByTrainer(
            @PathVariable int trainerId,
            Model model) {

        model.addAttribute(
                "classes",
                classService.findByTrainer(
                        trainerId
                )
        );

        return "classes/list";
    }

    @GetMapping("/schedules")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String listSchedules(
            Model model) {

        model.addAttribute(
                "schedules",
                classService.findAllSchedules()
        );

        return "classes/schedule-list";
    }

    @GetMapping("/schedule/detail/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
        Role.TRAINER
    })

    public String scheduleDetail(
            @PathVariable int id,
            Model model) {

        ClassSchedule schedule
                = classService.findScheduleById(
                        id
                );

        if (schedule == null) {

            return "error/404";
        }

        model.addAttribute(
                "schedule",
                schedule
        );

        return "classes/schedule-detail";
    }

    @GetMapping("/schedule/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })
    public String showAddScheduleForm(
            Model model) {

        model.addAttribute(
                "schedule",
                new ClassSchedule()
        );

        model.addAttribute(
                "classes",
                classService.findAllClasses()
        );

        return "classes/schedule-form";
    }

    @PostMapping("/schedule/add")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String addSchedule(
            @Valid
            @ModelAttribute("schedule") ClassSchedule schedule,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "classes",
                    classService.findAllClasses()
            );

            return "classes/schedule-form";
        }

        classService.saveSchedule(
                schedule
        );

        return "redirect:/classes/schedules";
    }

    @GetMapping("/schedule/edit/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String showEditScheduleForm(
            @PathVariable int id,
            Model model) {

        ClassSchedule schedule
                = classService.findScheduleById(
                        id
                );

        if (schedule == null) {

            return "error/404";
        }

        model.addAttribute(
                "schedule",
                schedule
        );

        model.addAttribute(
                "classes",
                classService.findAllClasses()
        );

        return "classes/schedule-form";
    }

    @PostMapping("/schedule/edit")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String updateSchedule(
            @Valid
            @ModelAttribute("schedule") ClassSchedule schedule,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "classes",
                    classService.findAllClasses()
            );

            return "classes/schedule-form";
        }

        classService.updateSchedule(
                schedule
        );

        return "redirect:/classes/schedules";
    }

    @GetMapping("/schedule/delete/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String deleteSchedule(
            @PathVariable int id) {

        classService.deleteSchedule(
                id
        );

        return "redirect:/classes/schedules";
    }
}

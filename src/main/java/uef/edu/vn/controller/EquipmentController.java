package uef.edu.vn.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import uef.edu.vn.annotation.RoleRequired;
import uef.edu.vn.model.Equipment;
import uef.edu.vn.model.EquipmentStatus;
import uef.edu.vn.model.Role;
import uef.edu.vn.service.EquipmentService;

@Controller
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    @Autowired
    public EquipmentController(
            EquipmentService equipmentService) {

        this.equipmentService = equipmentService;
    }

    @GetMapping

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
    })

    public String listEquipment(
            Model model) {

        model.addAttribute(
                "equipments",
                equipmentService.findAll()
        );

        return "equipment/list";
    }

    @GetMapping("/detail/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,})

    public String equipmentDetail(
            @PathVariable int id,
            Model model) {

        Equipment equipment
                = equipmentService.findById(
                        id
                );

        if (equipment == null) {

            model.addAttribute(
                    "errorMessage",
                    "Equipment not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "equipment",
                equipment
        );

        return "equipment/detail";
    }

    @GetMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,})
    public String showAddForm(
            Model model) {

        model.addAttribute(
                "equipment",
                new Equipment()
        );

        model.addAttribute(
                "statuses",
                EquipmentStatus.values()
        );

        return "equipment/form";
    }

    @PostMapping("/add")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,})

    public String addEquipment(
            @Valid
            @ModelAttribute("equipment") Equipment equipment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "statuses",
                    EquipmentStatus.values()
            );

            return "equipment/form";
        }

        equipmentService.save(
                equipment
        );

        return "redirect:/equipment";
    }

    @GetMapping("/edit/{id}")

    @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,})

    public String showEditForm(
            @PathVariable int id,
            Model model) {

        Equipment equipment
                = equipmentService.findById(
                        id
                );

        if (equipment == null) {

            model.addAttribute(
                    "errorMessage",
                    "Equipment not found!"
            );

            return "error/404";
        }

        model.addAttribute(
                "equipment",
                equipment
        );

        model.addAttribute(
                "statuses",
                EquipmentStatus.values()
        );

        return "equipment/form";
    }

    @PostMapping("/edit")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String updateEquipment(
            @Valid
            @ModelAttribute("equipment") Equipment equipment,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {

            model.addAttribute(
                    "statuses",
                    EquipmentStatus.values()
            );

            return "equipment/form";
        }

        equipmentService.update(
                equipment
        );

        return "redirect:/equipment";
    }

    @GetMapping("/delete/{id}")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String deleteEquipment(
            @PathVariable int id) {

        equipmentService.delete(
                id
        );

        return "redirect:/equipment";
    }

    @GetMapping("/search")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String searchEquipment(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "equipments",
                equipmentService.search(
                        keyword
                )
        );

        model.addAttribute(
                "keyword",
                keyword
        );

        return "equipment/list";
    }

    @GetMapping("/status")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String equipmentByStatus(
            @RequestParam EquipmentStatus status,
            Model model) {

        model.addAttribute(
                "equipments",
                equipmentService.findByStatus(
                        status
                )
        );

        model.addAttribute(
                "selectedStatus",
                status
        );

        return "equipment/list";
    }

    @GetMapping("/low-stock")

        @RoleRequired({
        Role.ADMIN,
        Role.MANAGER,
        
    })

    public String lowStockEquipment(
            Model model) {

        model.addAttribute(
                "equipments",
                equipmentService.findLowStock()
        );

        return "equipment/list";
    }
}

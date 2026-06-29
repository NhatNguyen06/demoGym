/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.Equipment;
import uef.edu.vn.model.EquipmentStatus;

import uef.edu.vn.repository.EquipmentRepository;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment save(Equipment equipment) {

        equipmentRepository.save(equipment);

        return equipment;
    }

    public List<Equipment> findAll() {

        return equipmentRepository.findAll();
    }

    public Equipment findById(int id) {

        return equipmentRepository.findById(id);
    }

    public boolean update(Equipment equipment) {

        Equipment old = findById(equipment.getId());

        if (old == null) {
            return false;
        }

        equipmentRepository.update(equipment);

        return true;
    }

    public boolean delete(int id) {

        Equipment old = findById(id);

        if (old == null) {
            return false;
        }

        equipmentRepository.delete(id);

        return true;
    }

    public List<Equipment> findByStatus(
            EquipmentStatus status) {

        return equipmentRepository.findByStatus(status);
    }

    public List<Equipment> findLowStock() {

        return findAll().stream()
                .filter(e -> e.getQuantity() <= 5)
                .toList();
    }

    public List<Equipment> search(
            String keyword) {

        return equipmentRepository.searchByName(keyword);
    }

    public long count() {

        return equipmentRepository.count();
    }

    public long countAvailable() {
        return equipmentRepository.countByStatus(EquipmentStatus.AVAILABLE);
    }

    public long countMaintenance() {
        return equipmentRepository.countByStatus(EquipmentStatus.MAINTENANCE);
    }

    public long countBroken() {
        return equipmentRepository.countByStatus(EquipmentStatus.BROKEN);
    }
}

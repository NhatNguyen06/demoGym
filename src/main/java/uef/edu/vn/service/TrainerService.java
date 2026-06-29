/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uef.edu.vn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.Trainer;

import uef.edu.vn.repository.TrainerRepository;

@Service
public class TrainerService {

    private final TrainerRepository trainerRepository;

    @Autowired
    public TrainerService(
            TrainerRepository trainerRepository) {

        this.trainerRepository = trainerRepository;
    }

    /**
     * Lấy danh sách tất cả huấn luyện viên
     */
    public List<Trainer> findAll() {

        return trainerRepository.findAll();
    }

    /**
     * Tìm huấn luyện viên theo ID
     */
    public Trainer findById(int id) {

        return trainerRepository.findById(id);
    }

    /**
     * Thêm mới huấn luyện viên
     */
    public Trainer save(Trainer trainer) {

        trainerRepository.save(trainer);

        return trainer;
    }

    /**
     * Cập nhật thông tin huấn luyện viên
     */
    public boolean update(Trainer trainer) {

        Trainer oldTrainer = findById(
                trainer.getId()
        );

        if (oldTrainer == null) {
            return false;
        }

        trainerRepository.update(trainer);

        return true;
    }

    /**
     * Xóa huấn luyện viên
     */
    public boolean delete(int id) {

        Trainer trainer = findById(id);

        if (trainer == null) {
            return false;
        }

        trainerRepository.delete(id);

        return true;
    }

    /**
     * Tìm kiếm theo tên
     */
    public List<Trainer> searchByName(
            String keyword) {

        return trainerRepository.searchByName(
                keyword
        );
    }

    /**
     * Tìm kiếm theo chuyên môn
     */
    public List<Trainer> searchBySpecialization(
            String specialization) {

        return trainerRepository.findBySpecialization(
                specialization
        );
    }

    /**
     * Đếm tổng số huấn luyện viên
     */
    public long count() {

        return trainerRepository.count();
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.ClassSchedule;
import uef.edu.vn.model.GymClass;
import uef.edu.vn.repository.ClassScheduleRepository;
import uef.edu.vn.repository.GymClassRepository;

@Service
public class ClassService {

    @Autowired
    private GymClassRepository gymClassRepository;

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    // =========================
    // GYM CLASS
    // =========================
    public GymClass saveClass(GymClass gymClass) {

        gymClassRepository.save(gymClass);

        return gymClass;
    }

    public List<GymClass> findAllClasses() {

        return gymClassRepository.findAll();
    }

    public GymClass findClassById(int id) {

        return gymClassRepository.findById(id);
    }

    public boolean updateClass(GymClass gymClass) {

        if (findClassById(gymClass.getId()) == null) {
            return false;
        }

        gymClassRepository.update(gymClass);

        return true;
    }

    public boolean deleteClass(int id) {

        if (findClassById(id) == null) {
            return false;
        }

        gymClassRepository.delete(id);

        return true;
    }

    public List<GymClass> findByTrainer(int trainerId) {

        return gymClassRepository.findByTrainer(trainerId);
    }

    public List<GymClass> searchByName(String keyword) {

        return gymClassRepository.searchByName(keyword);
    }

    public List<GymClass> findClassByType(String type) {

        return gymClassRepository.findByType(type);
    }

    public long totalClasses() {

        return gymClassRepository.count();
    }

    public long countClassByTrainer(int trainerId) {

        return gymClassRepository.countByTrainer(trainerId);
    }

    // =========================
    // CLASS SCHEDULE
    // =========================
    public ClassSchedule saveSchedule(ClassSchedule schedule) {

        if (hasConflict(schedule)) {
            return null;
        }

        classScheduleRepository.save(schedule);

        return schedule;
    }

    public List<ClassSchedule> findAllSchedules() {

        return classScheduleRepository.findAll();
    }

    public ClassSchedule findScheduleById(int id) {

        return classScheduleRepository.findById(id);
    }

    public boolean updateSchedule(ClassSchedule schedule) {

        if (findScheduleById(schedule.getId()) == null) {
            return false;
        }

        if (hasConflict(schedule)) {
            return false;
        }

        classScheduleRepository.update(schedule);

        return true;
    }

    public boolean deleteSchedule(int id) {

        if (findScheduleById(id) == null) {
            return false;
        }

        classScheduleRepository.delete(id);

        return true;
    }

    public List<ClassSchedule> findSchedulesByClass(int classId) {

        return classScheduleRepository.findByClass(classId);
    }

    public List<ClassSchedule> findSchedulesByRoom(String room) {

        return classScheduleRepository.findByRoom(room);
    }

    public boolean hasConflict(ClassSchedule schedule) {

        return classScheduleRepository.hasConflict(schedule);
    }

    public long totalSchedules() {

        return classScheduleRepository.count();
    }
}

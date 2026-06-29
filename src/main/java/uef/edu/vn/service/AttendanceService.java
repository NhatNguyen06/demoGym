/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Attendance;
import uef.edu.vn.model.AttendanceStatus;
import uef.edu.vn.repository.AttendanceRepository;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceService(
            AttendanceRepository attendanceRepository) {

        this.attendanceRepository = attendanceRepository;
    }

    public void checkIn(int memberId) {

        if (attendanceRepository
                .existsTodayCheckIn(memberId)) {

            return;
        }

        Attendance attendance = new Attendance();

        attendance.setMemberId(memberId);

        attendance.setDate(
                LocalDate.now()
        );

        attendance.setCheckInTime(
                LocalTime.now()
        );

        attendance.setStatus(
                AttendanceStatus.PRESENT
        );

        attendanceRepository.save(attendance);
    }

    public List<Attendance> findAll() {

        return attendanceRepository.findAll();
    }

    public List<Attendance> findByMember(
            int memberId) {

        return attendanceRepository.findByMember(memberId);
    }

    public Attendance findById(int id) {

        return attendanceRepository.findById(id);
    }
}

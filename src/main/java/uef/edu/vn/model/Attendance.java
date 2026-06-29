/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author minhq
 */
public class Attendance {

    private int id;

    private int memberId;

    private LocalDate date;

    private LocalTime checkInTime;

    private AttendanceStatus status;

    public Attendance() {
    }

    public Attendance(
            int id,
            int memberId,
            LocalDate date,
            LocalTime checkInTime,
            AttendanceStatus status) {

        this.id = id;
        this.memberId = memberId;
        this.date = date;
        this.checkInTime = checkInTime;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.Attendance;
import uef.edu.vn.model.AttendanceStatus;

@Repository
public class AttendanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Attendance mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        Attendance attendance = new Attendance();

        attendance.setId(rs.getInt("id"));
        attendance.setMemberId(rs.getInt("member_id"));
        attendance.setDate(
                rs.getDate("attendance_date")
                        .toLocalDate()
        );

        attendance.setCheckInTime(
                rs.getTime("check_in_time")
                        .toLocalTime()
        );

        attendance.setStatus(
                AttendanceStatus.valueOf(
                        rs.getString("status")
                )
        );

        return attendance;
    }

    public List<Attendance> findAll() {

        String sql = """
                     SELECT *
                     FROM attendance
                     ORDER BY attendance_date DESC,
                              check_in_time DESC
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public Attendance findById(int id) {

        String sql = """
                     SELECT *
                     FROM attendance
                     WHERE id = ?
                     """;

        List<Attendance> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(Attendance attendance) {

        String sql = """
                     INSERT INTO attendance(
                         member_id,
                         attendance_date,
                         check_in_time,
                         status
                     )
                     VALUES (?, ?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                attendance.getMemberId(),
                attendance.getDate(),
                attendance.getCheckInTime(),
                attendance.getStatus().name()
        );
    }

    public List<Attendance> findByMember(
            int memberId) {

        String sql = """
                     SELECT *
                     FROM attendance
                     WHERE member_id = ?
                     ORDER BY attendance_date DESC,
                              check_in_time DESC
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                memberId
        );
    }

    public boolean existsTodayCheckIn(
            int memberId) {

        String sql = """
                     SELECT COUNT(*)
                     FROM attendance
                     WHERE member_id = ?
                     AND attendance_date = CURRENT_DATE()
                     """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                memberId
        );

        return count != null && count > 0;
    }
}

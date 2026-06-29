/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uef.edu.vn.model.ClassSchedule;

@Repository
public class ClassScheduleRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ClassSchedule mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        ClassSchedule schedule = new ClassSchedule();

        schedule.setId(rs.getInt("id"));
        schedule.setClassId(rs.getInt("class_id"));

        schedule.setDayOfWeek(
                DayOfWeek.valueOf(
                        rs.getString("day_of_week")
                )
        );

        schedule.setStartTime(
                rs.getTime("start_time")
                        .toLocalTime()
        );

        schedule.setEndTime(
                rs.getTime("end_time")
                        .toLocalTime()
        );

        schedule.setRoom(rs.getString("room"));

        return schedule;
    }

    public List<ClassSchedule> findAll() {

        String sql = "SELECT * FROM class_schedule";

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public ClassSchedule findById(int id) {

        String sql = """
                     SELECT *
                     FROM class_schedule
                     WHERE id = ?
                     """;

        List<ClassSchedule> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(ClassSchedule schedule) {

        String sql = """
                     INSERT INTO class_schedule(
                         class_id,
                         day_of_week,
                         start_time,
                         end_time,
                         room
                     )
                     VALUES (?, ?, ?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                schedule.getClassId(),
                schedule.getDayOfWeek().name(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getRoom()
        );
    }

    public void update(ClassSchedule schedule) {

        String sql = """
                     UPDATE class_schedule
                     SET class_id = ?,
                         day_of_week = ?,
                         start_time = ?,
                         end_time = ?,
                         room = ?
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                schedule.getClassId(),
                schedule.getDayOfWeek().name(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getRoom(),
                schedule.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                     DELETE FROM class_schedule
                     WHERE id = ?
                     """;

        jdbcTemplate.update(sql, id);
    }

    public List<ClassSchedule> findByClass(
            int classId) {

        String sql = """
                     SELECT *
                     FROM class_schedule
                     WHERE class_id = ?
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                classId
        );
    }

    public List<ClassSchedule> findByRoom(
            String room) {

        String sql = """
                     SELECT *
                     FROM class_schedule
                     WHERE LOWER(room) = LOWER(?)
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                room
        );
    }

    public boolean hasConflict(ClassSchedule schedule) {

        String sql = """
        SELECT COUNT(*)
        FROM class_schedule
        WHERE room = ?
          AND day_of_week = ?
          AND start_time < ?
          AND end_time > ?
        """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                schedule.getRoom(),
                schedule.getDayOfWeek().name(),
                schedule.getEndTime(),
                schedule.getStartTime()
        );

        return count != null && count > 0;
    }

    public long count() {

        String sql = "SELECT COUNT(*) FROM class_schedule";

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }
}

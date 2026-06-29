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

import uef.edu.vn.model.Trainer;

@Repository
public class TrainerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Mapping ResultSet -> Trainer
     */
    private Trainer mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        Trainer trainer = new Trainer();

        trainer.setId(
                rs.getInt("id")
        );

        trainer.setFullName(
                rs.getString("full_name")
        );

        trainer.setSpecialization(
                rs.getString("specialization")
        );

        trainer.setPhone(
                rs.getString("phone")
        );

        trainer.setSchedule(
                rs.getString("schedule")
        );

        return trainer;
    }

    /**
     * Lấy tất cả huấn luyện viên
     */
    public List<Trainer> findAll() {

        String sql = "SELECT * FROM trainers";

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    /**
     * Tìm theo ID
     */
    public Trainer findById(int id) {

        String sql = """
                     SELECT *
                     FROM trainers
                     WHERE id = ?
                     """;

        List<Trainer> trainers = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return trainers.isEmpty()
                ? null
                : trainers.get(0);
    }

    /**
     * Thêm mới
     */
    public void save(Trainer trainer) {

        String sql = """
                     INSERT INTO trainers
                     (full_name, specialization, phone, schedule)
                     VALUES (?, ?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                trainer.getFullName(),
                trainer.getSpecialization(),
                trainer.getPhone(),
                trainer.getSchedule()
        );
    }

    /**
     * Cập nhật
     */
    public void update(Trainer trainer) {

        String sql = """
                     UPDATE trainers
                     SET full_name = ?,
                         specialization = ?,
                         phone = ?,
                         schedule = ?
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                trainer.getFullName(),
                trainer.getSpecialization(),
                trainer.getPhone(),
                trainer.getSchedule(),
                trainer.getId()
        );
    }

    /**
     * Xóa
     */
    public void delete(int id) {

        String sql = """
                     DELETE FROM trainers
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                id
        );
    }

    /**
     * Tìm theo tên
     */
    public List<Trainer> searchByName(
            String keyword) {

        String sql = """
                     SELECT *
                     FROM trainers
                     WHERE full_name LIKE ?
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                "%" + keyword + "%"
        );
    }

    /**
     * Tìm theo chuyên môn
     */
    public List<Trainer> findBySpecialization(
            String specialization) {

        String sql = """
                     SELECT *
                     FROM trainers
                     WHERE specialization = ?
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                specialization
        );
    }

    /**
     * Đếm tổng số trainer
     */
    public long count() {

        String sql = """
                     SELECT COUNT(*)
                     FROM trainers
                     """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }
}

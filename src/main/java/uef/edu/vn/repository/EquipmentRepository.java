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

import uef.edu.vn.model.Equipment;

import uef.edu.vn.model.EquipmentStatus;

@Repository
public class EquipmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Equipment mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        Equipment equipment = new Equipment();

        equipment.setId(
                rs.getInt("id")
        );

        equipment.setName(
                rs.getString("name")
        );

        equipment.setQuantity(
                rs.getInt("quantity")
        );

        equipment.setStatus(
                EquipmentStatus.valueOf(
                        rs.getString("status")
                )
        );

        equipment.setPurchaseDate(
                rs.getDate("purchase_date")
                        .toLocalDate()
        );

        return equipment;
    }

    public List<Equipment> findAll() {

        String sql = """
                SELECT *
                FROM equipment
                ORDER BY id
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public Equipment findById(int id) {

        String sql = """
                SELECT *
                FROM equipment
                WHERE id = ?
                """;

        List<Equipment> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(Equipment equipment) {

        String sql = """
                INSERT INTO equipment
                (name, quantity, status, purchase_date)
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                equipment.getName(),
                equipment.getQuantity(),
                equipment.getStatus().name(),
                equipment.getPurchaseDate()
        );
    }

    public void update(Equipment equipment) {

        String sql = """
                UPDATE equipment
                SET name = ?,
                    quantity = ?,
                    status = ?,
                    purchase_date = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                equipment.getName(),
                equipment.getQuantity(),
                equipment.getStatus().name(),
                equipment.getPurchaseDate(),
                equipment.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                DELETE FROM equipment
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                id
        );
    }

    public List<Equipment> findByStatus(
            EquipmentStatus status) {

        String sql = """
                SELECT *
                FROM equipment
                WHERE status = ?
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                status.name()
        );
    }

    public List<Equipment> searchByName(
            String keyword) {

        String sql = """
                SELECT *
                FROM equipment
                WHERE LOWER(name) LIKE LOWER(?)
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                "%" + keyword + "%"
        );
    }

    public long count() {

        String sql = """
                SELECT COUNT(*)
                FROM equipment
                """;

        Long result = jdbcTemplate.queryForObject(
                sql,
                Long.class
        );

        return result == null
                ? 0
                : result;
    }

    public long countByStatus(EquipmentStatus status) {

        String sql = """
            SELECT COUNT(*)
            FROM equipment
            WHERE status = ?
            """;

        Long result = jdbcTemplate.queryForObject(
                sql,
                Long.class,
                status.name()
        );

        return result == null ? 0 : result;
    }
}

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

import uef.edu.vn.model.MembershipPlan;

@Repository
public class MembershipPlanRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private MembershipPlan mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        MembershipPlan plan = new MembershipPlan();

        plan.setId(
                rs.getInt("id")
        );

        plan.setName(
                rs.getString("name")
        );

        plan.setDurationMonths(
                rs.getInt("duration_months")
        );

        plan.setPrice(
                rs.getBigDecimal("price")
        );

        plan.setDescription(
                rs.getString("description")
        );

        return plan;
    }

    public List<MembershipPlan> findAll() {

        String sql = """
                SELECT *
                FROM membership_plan
                ORDER BY id
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public MembershipPlan findById(int id) {

        String sql = """
                SELECT *
                FROM membership_plan
                WHERE id = ?
                """;

        List<MembershipPlan> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(MembershipPlan plan) {

        String sql = """
                INSERT INTO membership_plan
                (
                    name,
                    duration_months,
                    price,
                    description
                )
                VALUES (?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                plan.getName(),
                plan.getDurationMonths(),
                plan.getPrice(),
                plan.getDescription()
        );
    }

    public void update(MembershipPlan plan) {

        String sql = """
                UPDATE membership_plan
                SET name = ?,
                    duration_months = ?,
                    price = ?,
                    description = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                plan.getName(),
                plan.getDurationMonths(),
                plan.getPrice(),
                plan.getDescription(),
                plan.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                DELETE FROM membership_plan
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public List<MembershipPlan> searchByName(
            String keyword) {

        String sql = """
                SELECT *
                FROM membership_plan
                WHERE LOWER(name) LIKE ?
                ORDER BY id
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                "%" + keyword.toLowerCase().trim() + "%"
        );
    }

    public long count() {

        String sql = """
                SELECT COUNT(*)
                FROM membership_plan
                """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }
}


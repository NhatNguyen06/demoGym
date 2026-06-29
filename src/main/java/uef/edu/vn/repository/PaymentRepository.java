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

import uef.edu.vn.model.Payment;
import uef.edu.vn.model.PaymentStatus;

@Repository
public class PaymentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Payment mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        Payment payment = new Payment();

        payment.setId(
                rs.getInt("id"));

        payment.setMemberId(
                rs.getInt("member_id"));

        payment.setAmount(
                rs.getBigDecimal("amount"));

        payment.setMethod(
                rs.getString("method"));

        payment.setStatus(
                PaymentStatus.valueOf(
                        rs.getString("status")));

        return payment;
    }

    public List<Payment> findAll() {

        String sql = """
                     SELECT *
                     FROM payments
                     ORDER BY id DESC
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public Payment findById(int id) {

        String sql = """
                     SELECT *
                     FROM payments
                     WHERE id = ?
                     """;

        List<Payment> results = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return results.isEmpty()
                ? null
                : results.get(0);
    }

    public List<Payment> findByMemberId(
            int memberId) {

        String sql = """
                     SELECT *
                     FROM payments
                     WHERE member_id = ?
                     ORDER BY id DESC
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                memberId
        );
    }

    public void save(Payment payment) {

        String sql = """
                     INSERT INTO payments
                     (member_id, amount, method, status)
                     VALUES (?, ?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                payment.getMemberId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus().name()
        );
    }

    public void update(Payment payment) {

        String sql = """
                     UPDATE payments
                     SET member_id = ?,
                         amount = ?,
                         method = ?,
                         status = ?
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                payment.getMemberId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus().name(),
                payment.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                     DELETE FROM payments
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                id
        );
    }

    public long count() {

        String sql = """
                     SELECT COUNT(*)
                     FROM payments
                     """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }

    public double totalRevenue() {

        String sql = """
                     SELECT COALESCE(SUM(amount), 0)
                     FROM payments
                     WHERE status = 'COMPLETED'
                     """;

        return jdbcTemplate.queryForObject(
                sql,
                Double.class
        );
    }
}

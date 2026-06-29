/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import uef.edu.vn.model.Subscription;

import uef.edu.vn.model.SubscriptionStatus;

@Repository
public class SubscriptionRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Subscription mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        Subscription subscription = new Subscription();

        subscription.setId(
                rs.getInt("id")
        );

        subscription.setMemberId(
                rs.getInt("member_id")
        );

        subscription.setPlanId(
                rs.getInt("plan_id")
        );

        subscription.setStartDate(
                rs.getDate("start_date").toLocalDate()
        );

        subscription.setEndDate(
                rs.getDate("end_date").toLocalDate()
        );

        subscription.setStatus(
                SubscriptionStatus.valueOf(
                        rs.getString("status")
                )
        );

        return subscription;
    }

    public List<Subscription> findAll() {

        String sql = """
                SELECT *
                FROM subscription
                ORDER BY id DESC
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public Subscription findById(int id) {

        String sql = """
                SELECT *
                FROM subscription
                WHERE id = ?
                """;

        List<Subscription> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(Subscription subscription) {

        String sql = """
                INSERT INTO subscription
                (
                    member_id,
                    plan_id,
                    start_date,
                    end_date,
                    status
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        jdbcTemplate.update(
                sql,
                subscription.getMemberId(),
                subscription.getPlanId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus().name()
        );
    }

    public void update(Subscription subscription) {

        String sql = """
                UPDATE subscription
                SET member_id = ?,
                    plan_id = ?,
                    start_date = ?,
                    end_date = ?,
                    status = ?
                WHERE id = ?
                """;

        jdbcTemplate.update(
                sql,
                subscription.getMemberId(),
                subscription.getPlanId(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getStatus().name(),
                subscription.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                DELETE FROM subscription
                WHERE id = ?
                """;

        jdbcTemplate.update(sql, id);
    }

    public List<Subscription> findByMemberId(
            int memberId) {

        String sql = """
                SELECT *
                FROM subscription
                WHERE member_id = ?
                ORDER BY start_date DESC
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                memberId
        );
    }

    public List<Subscription> findByStatus(
            SubscriptionStatus status) {

        String sql = """
                SELECT *
                FROM subscription
                WHERE status = ?
                ORDER BY end_date DESC
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                status.name()
        );
    }

    public List<Subscription> findNotCancelled() {

        String sql = """
                SELECT *
                FROM subscription
                WHERE status <> ?
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                SubscriptionStatus.CANCELLED.name()
        );
    }

    public long count() {

        String sql = """
                SELECT COUNT(*)
                FROM subscription
                """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }

    public long countByStatus(
            SubscriptionStatus status) {

        String sql = """
                SELECT COUNT(*)
                FROM subscription
                WHERE status = ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class,
                status.name()
        );
    }

    public long countExpired() {

        String sql = """
                SELECT COUNT(*)
                FROM subscription
                WHERE end_date < ?
                  AND status <> ?
                """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class,
                LocalDate.now(),
                SubscriptionStatus.CANCELLED.name()
        );
    }

    public List<Subscription> findExpiringWithin(int days) {

        String sql = """
                SELECT *
                FROM subscription
                WHERE status = ?
                  AND end_date >= CURDATE()
                  AND end_date <= DATE_ADD(CURDATE(), INTERVAL ? DAY)
                ORDER BY end_date ASC
                """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                SubscriptionStatus.ACTIVE.name(), // ? thứ nhất → 'ACTIVE'
                days // ? thứ hai  → 7
        );
    }
}

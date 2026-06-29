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
import uef.edu.vn.model.Gender;
import uef.edu.vn.model.Member;
import uef.edu.vn.model.MemberStatus;

/**
 *
 * @author minhq
 */
@Repository
public class MemberRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Member mapRow(ResultSet rs, int rowNum) throws SQLException {

        Member member = new Member();

        member.setId(rs.getInt("id"));
        member.setFullName(rs.getString("full_name"));
        member.setEmail(rs.getString("email"));
        member.setPhone(rs.getString("phone"));
        member.setDob(rs.getDate("dob").toLocalDate());
        member.setGender(Gender.valueOf(rs.getString("gender")));
        member.setJoinDate(rs.getDate("join_date").toLocalDate());
        member.setStatus(MemberStatus.valueOf(rs.getString("status")));
        return member;
    }

    public List<Member> findAll() {
        String sql = "SELECT * FROM members";
        return jdbcTemplate.query(sql, this::mapRow);
    }

    public Member findById(int id) {
        String sql = "SELECT * FROM members WHERE id = ?";
        List<Member> members = jdbcTemplate.query(sql, this::mapRow, id);
        return members.isEmpty() ? null : members.get(0);

    }

    public void save(Member member) {
        String sql = "INSERTS INTO members(full_name, email, phone, dob, gender, join_date, status) VALUES(?,?,?,?,?,?,?)";
        jdbcTemplate.update(
                sql,
                member.getFullName(),
                member.getEmail(),
                member.getPhone(),
                member.getDob(),
                member.getGender().name(),
                member.getJoinDate(),
                member.getStatus().name()
        );
    }

    public void update(Member member) {

        String sql = "UPDATE members SET full_name=?,email=?,phone=?,dob=?,gender=?,join_date=?,status=?WHERE id=?";

        jdbcTemplate.update(
                sql,
                member.getFullName(),
                member.getEmail(),
                member.getPhone(),
                member.getDob(),
                member.getGender().name(),
                member.getJoinDate(),
                member.getStatus().name(),
                member.getId()
        );
    }

    public void delete(int id) {
        String sql = "DELETE FROM members WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Member> searchByName(String keyword) {

        String sql = "SELECT * FROM members WHERE full_name LIKE ?";

        return jdbcTemplate.query(sql, this::mapRow, "%" + keyword + "%");
    }

    public long count() {

        String sql = "SELECT COUNT(*) FROM members";

        return jdbcTemplate.queryForObject(sql,Long.class);
    }
}

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

import uef.edu.vn.model.Role;

import uef.edu.vn.model.User;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Mapping ResultSet -> User
     */
    private User mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        User user = new User();

        user.setId(
                rs.getInt("id")
        );

        user.setUsername(
                rs.getString("username")
        );

        user.setPassword(
                rs.getString("password")
        );

        user.setRole(
                Role.valueOf(
                        rs.getString("role")
                )
        );

        return user;
    }

    /**
     * Lấy tất cả user
     */
    public List<User> findAll() {

        String sql = "SELECT * FROM users";

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    /**
     * Tìm theo ID
     */
    public User findById(int id) {

        String sql = """
                     SELECT *
                     FROM users
                     WHERE id = ?
                     """;

        List<User> users = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return users.isEmpty()
                ? null
                : users.get(0);
    }

    /**
     * Tìm theo username
     */
    public User findByUsername(
            String username) {

        String sql = """
                     SELECT *
                     FROM users
                     WHERE username = ?
                     """;

        List<User> users = jdbcTemplate.query(
                sql,
                this::mapRow,
                username
        );

        return users.isEmpty()
                ? null
                : users.get(0);
    }

    /**
     * Đăng nhập
     */
    public User login(
            String username,
            String password) {

        String sql = """
                     SELECT *
                     FROM users
                     WHERE username = ?
                     AND password = ?
                     """;

        List<User> users = jdbcTemplate.query(
                sql,
                this::mapRow,
                username,
                password
        );

        return users.isEmpty()
                ? null
                : users.get(0);
    }

    /**
     * Thêm user
     */
    public void save(User user) {

        String sql = """
                     INSERT INTO users
                     (username, password, role)
                     VALUES (?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getRole().name()
        );
    }

    /**
     * Cập nhật user
     */
    public void update(User user) {

        String sql = """
                     UPDATE users
                     SET username = ?,
                         password = ?,
                         role = ?
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                user.getUsername(),
                user.getPassword(),
                user.getRole().name(),
                user.getId()
        );
    }

    /**
     * Xóa user
     */
    public void delete(int id) {

        String sql = """
                     DELETE FROM users
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                id
        );
    }

    /**
     * Kiểm tra username đã tồn tại
     */
    public boolean existsByUsername(
            String username) {

        String sql = """
                     SELECT COUNT(*)
                     FROM users
                     WHERE username = ?
                     """;

        Integer count = jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                username
        );

        return count != null && count > 0;
    }

    /**
     * Đếm tổng số user
     */
    public long count() {

        String sql = """
                     SELECT COUNT(*)
                     FROM users
                     """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }
}


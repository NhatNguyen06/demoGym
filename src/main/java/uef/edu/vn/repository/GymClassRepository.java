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
import uef.edu.vn.model.GymClass;

@Repository
public class GymClassRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private GymClass mapRow(
            ResultSet rs,
            int rowNum) throws SQLException {

        GymClass gymClass = new GymClass();

        gymClass.setId(rs.getInt("id"));
        gymClass.setName(rs.getString("name"));
        gymClass.setTrainerId(rs.getInt("trainer_id"));
        gymClass.setCapacity(rs.getInt("capacity"));
        gymClass.setType(rs.getString("type"));

        return gymClass;
    }

    public List<GymClass> findAll() {

        String sql = "SELECT * FROM gym_class";

        return jdbcTemplate.query(
                sql,
                this::mapRow
        );
    }

    public GymClass findById(int id) {

        String sql = """
                     SELECT *
                     FROM gym_class
                     WHERE id = ?
                     """;

        List<GymClass> result = jdbcTemplate.query(
                sql,
                this::mapRow,
                id
        );

        return result.isEmpty()
                ? null
                : result.get(0);
    }

    public void save(GymClass gymClass) {

        String sql = """
                     INSERT INTO gym_class(
                         name,
                         trainer_id,
                         capacity,
                         type
                     )
                     VALUES (?, ?, ?, ?)
                     """;

        jdbcTemplate.update(
                sql,
                gymClass.getName(),
                gymClass.getTrainerId(),
                gymClass.getCapacity(),
                gymClass.getType()
        );
    }

    public void update(GymClass gymClass) {

        String sql = """
                     UPDATE gym_class
                     SET name = ?,
                         trainer_id = ?,
                         capacity = ?,
                         type = ?
                     WHERE id = ?
                     """;

        jdbcTemplate.update(
                sql,
                gymClass.getName(),
                gymClass.getTrainerId(),
                gymClass.getCapacity(),
                gymClass.getType(),
                gymClass.getId()
        );
    }

    public void delete(int id) {

        String sql = """
                     DELETE FROM gym_class
                     WHERE id = ?
                     """;

        jdbcTemplate.update(sql, id);
    }

    public List<GymClass> findByTrainer(int trainerId) {

        String sql = """
                     SELECT *
                     FROM gym_class
                     WHERE trainer_id = ?
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                trainerId
        );
    }

    public List<GymClass> searchByName(
            String keyword) {

        String sql = """
                     SELECT *
                     FROM gym_class
                     WHERE LOWER(name) LIKE LOWER(?)
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                "%" + keyword + "%"
        );
    }

    public List<GymClass> findByType(
            String type) {

        String sql = """
                     SELECT *
                     FROM gym_class
                     WHERE LOWER(type) = LOWER(?)
                     """;

        return jdbcTemplate.query(
                sql,
                this::mapRow,
                type
        );
    }

    public long count() {

        String sql = "SELECT COUNT(*) FROM gym_class";

        return jdbcTemplate.queryForObject(
                sql,
                Long.class
        );
    }

    public long countByTrainer(
            int trainerId) {

        String sql = """
                     SELECT COUNT(*)
                     FROM gym_class
                     WHERE trainer_id = ?
                     """;

        return jdbcTemplate.queryForObject(
                sql,
                Long.class,
                trainerId
        );
    }
}

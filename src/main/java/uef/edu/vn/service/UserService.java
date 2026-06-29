/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uef.edu.vn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.Role;
import uef.edu.vn.model.User;

import uef.edu.vn.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(
            UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /**
     * Lấy tất cả tài khoản
     */
    public List<User> findAll() {

        return userRepository.findAll();
    }

    /**
     * Tìm user theo ID
     */
    public User findById(int id) {

        return userRepository.findById(id);
    }

    /**
     * Tìm user theo username
     */
    public User findByUsername(
            String username) {

        return userRepository.findByUsername(
                username
        );
    }

    /**
     * Lấy danh sách member
     */
    public List<User> findAllMembers() {

        return userRepository.findAll()
                .stream()
                .filter(user
                        -> user.getRole() == Role.MEMBER)
                .toList();
    }

    /**
     * Đăng nhập
     */
    public User login(
            String username,
            String password) {

        return userRepository.login(
                username,
                password
        );
    }

    /**
     * Đăng ký tài khoản member
     */
    public boolean register(
            User user) {

        if (userRepository.existsByUsername(
                user.getUsername())) {

            return false;
        }

        user.setRole(Role.MEMBER);

        userRepository.save(user);

        return true;
    }

    /**
     * Cập nhật thông tin user
     */
    public boolean update(
            User user) {

        User oldUser = findById(
                user.getId()
        );

        if (oldUser == null) {
            return false;
        }

        userRepository.update(user);

        return true;
    }

    /**
     * Xóa user
     */
    public boolean delete(int id) {

        User user = findById(id);

        if (user == null) {
            return false;
        }

        userRepository.delete(id);

        return true;
    }

    /**
     * Đếm tổng số user
     */
    public long count() {

        return userRepository.count();
    }
}

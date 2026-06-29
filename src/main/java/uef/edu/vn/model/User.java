/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author minhq
 */
public class User {

    private int id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private Role role;

    public User() {
    }

    public User(
            int id,
            String username,
            String password,
            Role role) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(
            String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(
            String password) {

        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(
            Role role) {

        this.role = role;
    }
}

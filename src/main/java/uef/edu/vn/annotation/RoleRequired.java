/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.annotation;

import java.lang.annotation.*;
import uef.edu.vn.model.Role;

@Target(ElementType.METHOD)

@Retention(RetentionPolicy.RUNTIME)

public @interface RoleRequired {

    Role[] value();

}
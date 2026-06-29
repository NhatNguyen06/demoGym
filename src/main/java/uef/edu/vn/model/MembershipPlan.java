/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

/**
 *
 * @author minhq
 */
public class MembershipPlan {

    private int id;

    @NotBlank
    private String name;

    @Min(1)
    @Max(36)
    private int durationMonths;

    @DecimalMin("0.0")
    @NotNull(message="No empty")
    private BigDecimal price;

    @Size(max = 500)
    private String description;

    public MembershipPlan() {
    }

    public MembershipPlan(int id,
            String name,
            int durationMonths,
            BigDecimal price,
            String description) {

        this.id = id;
        this.name = name;
        this.durationMonths = durationMonths;
        this.price = price;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

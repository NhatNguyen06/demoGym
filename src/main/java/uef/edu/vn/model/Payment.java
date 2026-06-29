/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;


/**
 *
 * @author minhq
 */
public class Payment {
    
    private int id;

    @Positive(message = "Amount must be greater than 0")
    @NotNull(message ="No empty")
    private BigDecimal amount;


    @NotBlank(message="No empty")
    private String method;

    @NotNull(message="No empty")
    private PaymentStatus status;

    @Positive(message="No empty")
    private int memberId;

    public Payment() {
    }

    public Payment(int id,
            int memberId,
            BigDecimal amount,
           
            String method,
            PaymentStatus status) {

        this.id = id;
        this.memberId = memberId;
        this.amount = amount;
        
        this.method = method;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }



    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

}

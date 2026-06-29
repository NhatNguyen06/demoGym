/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uef.edu.vn.service;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uef.edu.vn.model.Payment;
import uef.edu.vn.repository.PaymentRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }

    public Payment findById(int id) {
        return paymentRepository.findById(id);
    }

    public Payment save(Payment payment) {

        paymentRepository.save(payment);

        return payment;
    }

    public boolean update(Payment payment) {

        Payment old = paymentRepository.findById(
                payment.getId()
        );

        if (old == null) {
            return false;
        }

        paymentRepository.update(payment);

        return true;
    }

    public boolean delete(int id) {

        Payment old = paymentRepository.findById(id);

        if (old == null) {
            return false;
        }

        paymentRepository.delete(id);

        return true;
    }

    public List<Payment> findByMemberId(int memberId) {

        return paymentRepository.findByMemberId(
                memberId
        );
    }

    public double totalRevenue() {

        return paymentRepository.totalRevenue();
    }
}

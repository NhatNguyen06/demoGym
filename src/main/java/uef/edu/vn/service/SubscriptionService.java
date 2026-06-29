/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uef.edu.vn.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uef.edu.vn.model.MembershipPlan;
import uef.edu.vn.model.Subscription;
import uef.edu.vn.model.SubscriptionStatus;

import uef.edu.vn.repository.SubscriptionRepository;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> findAll() {

        updateExpiredSubscriptions();

        return subscriptionRepository.findAll();
    }

    public Subscription findById(int id) {

        return subscriptionRepository.findById(id);
    }

    public Subscription register(
            int memberId,
            MembershipPlan plan) {

        LocalDate start = LocalDate.now();

        LocalDate end = start.plusMonths(
                plan.getDurationMonths()
        );

        Subscription subscription = new Subscription();

        subscription.setMemberId(memberId);

        subscription.setPlanId(
                plan.getId()
        );

        subscription.setStartDate(start);

        subscription.setEndDate(end);

        subscription.setStatus(
                SubscriptionStatus.ACTIVE
        );

        subscriptionRepository.save(
                subscription
        );

        return subscription;
    }

    public boolean isExpired(
            Subscription subscription) {

        return LocalDate.now().isAfter(
                subscription.getEndDate()
        );
    }

    public boolean cancel(int id) {

        Subscription subscription = findById(id);

        if (subscription == null) {
            return false;
        }

        subscription.setStatus(
                SubscriptionStatus.CANCELLED
        );

        subscriptionRepository.update(
                subscription
        );

        return true;
    }

    public boolean update(
            Subscription subscription) {

        if (findById(subscription.getId())
                == null) {

            return false;
        }

        subscriptionRepository.update(
                subscription
        );

        return true;
    }

    public boolean delete(int id) {

        if (findById(id) == null) {
            return false;
        }

        subscriptionRepository.delete(id);

        return true;
    }

    public List<Subscription> getSubscriptionsByMember(
            int memberId) {

        return subscriptionRepository.findByMemberId(
                memberId
        );
    }

    public List<Subscription> findByStatus(
            SubscriptionStatus status) {

        return subscriptionRepository.findByStatus(
                status
        );
    }

    public void updateExpiredSubscriptions() {

        List<Subscription> subscriptions
                = subscriptionRepository.findNotCancelled();

        for (Subscription subscription : subscriptions) {

            if (LocalDate.now().isAfter(
                    subscription.getEndDate())) {

                subscription.setStatus(
                        SubscriptionStatus.EXPIRED
                );

                subscriptionRepository.update(
                        subscription
                );
            }
        }
    }

    public List<Subscription> findActiveSubscriptions() {

        updateExpiredSubscriptions();

        return subscriptionRepository.findByStatus(
                SubscriptionStatus.ACTIVE
        );
    }

    public List<Subscription> findExpiredSubscriptions() {

        updateExpiredSubscriptions();

        return subscriptionRepository.findByStatus(
                SubscriptionStatus.EXPIRED
        );
    }

    public long count() {

        return subscriptionRepository.count();
    }

    public boolean renew(
            int subscriptionId,
            int months) {

        Subscription subscription
                = findById(subscriptionId);

        if (subscription == null) {
            return false;
        }

        subscription.setEndDate(
                subscription.getEndDate()
                        .plusMonths(months)
        );

        subscription.setStatus(
                SubscriptionStatus.ACTIVE
        );

        subscriptionRepository.update(
                subscription
        );

        return true;
    }

    public long countActiveSubscriptions() {

        updateExpiredSubscriptions();

        return subscriptionRepository.countByStatus(
                SubscriptionStatus.ACTIVE
        );
    }

    public long countExpiredSubscriptions() {

        updateExpiredSubscriptions();

        return subscriptionRepository.countByStatus(
                SubscriptionStatus.EXPIRED
        );
    }
}


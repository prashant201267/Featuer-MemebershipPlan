package com.feature.membershipPlan.service;

import com.feature.membershipPlan.dto.ApiResponse;
import com.feature.membershipPlan.dto.SubscriptionRequest;
import com.feature.membershipPlan.entity.MembershipPlan;
import com.feature.membershipPlan.entity.MembershipTier;
import com.feature.membershipPlan.entity.Subscription;
import com.feature.membershipPlan.exception.ResourceNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MembershipServiceImpl implements MembershipService {

    private final Map<Long, MembershipPlan> plans = new HashMap<>();
    private final Map<Long, MembershipTier> tiers = new HashMap<>();
    private final Map<Long, Subscription> subscriptions = new HashMap<>();

    @PostConstruct
    public void init() {
        tiers.put(1L, new MembershipTier(1L, "Silver"));
        tiers.put(2L, new MembershipTier(2L, "Gold"));
        tiers.put(3L, new MembershipTier(3L, "Platinum"));

        plans.put(1L, new MembershipPlan(1L, "Monthly", 99.0, List.of(1L, 2L, 3L)));
        plans.put(2L, new MembershipPlan(2L, "Quarterly", 249.0, List.of(1L, 3L)));
        plans.put(3L, new MembershipPlan(3L, "Yearly", 899.0, List.of(3L)));
    }

    @Override
    public List<MembershipPlan> getAllPlans() {
        return new ArrayList<>(plans.values());
    }

    @Override
    public List<MembershipTier> getAllTiers() {
        return new ArrayList<>(tiers.values());
    }

    @Override
    public List<MembershipTier> getTiersForPlan(Long planId) {
        MembershipPlan plan = plans.get(planId);
        if (plan == null) throw new ResourceNotFoundException("Plan not found");
        return plan.getTierIds().stream().map(tiers::get).collect(Collectors.toList());
    }

    @Override
    public ApiResponse subscribe(SubscriptionRequest request) {
        MembershipPlan plan = plans.get(request.getPlanId());
        MembershipTier tier = tiers.get(request.getTierId());
        if (plan == null || tier == null || !plan.getTierIds().contains(tier.getId())) {
            throw new ResourceNotFoundException("Invalid plan or tier");
        }

        LocalDate start = LocalDate.now();
        LocalDate expiry = calculateExpiry(plan.getName(), start);
        subscriptions.put(request.getUserId(), new Subscription(request.getUserId(), plan.getId(), tier.getId(), start, expiry));
        return new ApiResponse("Subscribed successfully", subscriptions.get(request.getUserId()));
    }

    @Override
    public ApiResponse upgrade(Long userId, Long newPlanId, Long newTierId) {
        return subscribe(new SubscriptionRequest(userId, newPlanId, newTierId));
    }

    @Override
    public ApiResponse downgrade(Long userId, Long newPlanId, Long newTierId) {
        return subscribe(new SubscriptionRequest(userId, newPlanId, newTierId));
    }

    @Override
    public ApiResponse cancel(Long userId) {
        Subscription removed = subscriptions.remove(userId);
        if (removed == null) throw new ResourceNotFoundException("No active subscription");
        return new ApiResponse("Subscription cancelled", removed);
    }

    @Override
    public Subscription getSubscription(Long userId) {
        Subscription sub = subscriptions.get(userId);
        if (sub == null) throw new ResourceNotFoundException("No active subscription");
        return sub;
    }

    private LocalDate calculateExpiry(String planName, LocalDate startDate) {
        return switch (planName.toLowerCase()) {
            //case "monthly" -> startDate.plusMonths(1);
            case "quarterly" -> startDate.plusMonths(3);
            case "yearly" -> startDate.plusYears(1);
            default -> startDate.plusMonths(1);
        };
    }
}
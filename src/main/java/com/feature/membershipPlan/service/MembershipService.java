package com.feature.membershipPlan.service;

import com.feature.membershipPlan.dto.ApiResponse;
import com.feature.membershipPlan.dto.SubscriptionRequest;
import com.feature.membershipPlan.entity.MembershipPlan;
import com.feature.membershipPlan.entity.MembershipTier;
import com.feature.membershipPlan.entity.Subscription;

import java.util.List;

public interface MembershipService {
    List<MembershipPlan> getAllPlans();
    List<MembershipTier> getAllTiers();
    List<MembershipTier> getTiersForPlan(Long planId);
    ApiResponse subscribe(SubscriptionRequest request);
    ApiResponse upgrade(Long userId, Long newPlanId, Long newTierId);
    ApiResponse downgrade(Long userId, Long newPlanId, Long newTierId);
    ApiResponse cancel(Long userId);
    Subscription getSubscription(Long userId);
}
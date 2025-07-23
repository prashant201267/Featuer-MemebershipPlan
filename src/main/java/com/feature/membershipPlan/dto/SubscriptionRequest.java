package com.feature.membershipPlan.dto;

import lombok.Data;

@Data
public class SubscriptionRequest {
    private Long userId;
    private Long planId;
    private Long tierId;

    public SubscriptionRequest(Long userId, Long newPlanId, Long newTierId) {
        this.userId = userId;
        this.planId = newPlanId;
        this.tierId = newTierId;
    }
}
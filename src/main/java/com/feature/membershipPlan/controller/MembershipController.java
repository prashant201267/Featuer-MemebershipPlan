package com.feature.membershipPlan.controller;

import com.feature.membershipPlan.dto.ApiResponse;
import com.feature.membershipPlan.dto.SubscriptionRequest;
import com.feature.membershipPlan.entity.MembershipPlan;
import com.feature.membershipPlan.entity.MembershipTier;
import com.feature.membershipPlan.entity.Subscription;
import com.feature.membershipPlan.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memberships")
@RequiredArgsConstructor
public class MembershipController {

    private final MembershipService membershipService;

    /**
     * Get all available membership plans.
     *
     * @return List of all membership plans.
     */
    @GetMapping("/plans")
    public List<MembershipPlan> getAllPlans() {
        return membershipService.getAllPlans();
    }

    /**
     * Get all available membership tiers across all plans.
     *
     * @return List of all membership tiers.
     */
    @GetMapping("/tiers")
    public List<MembershipTier> getAllTiers() {
        return membershipService.getAllTiers();
    }

    /**
     * Get all tiers for a specific membership plan.
     *
     * @param planId ID of the plan for which to fetch tiers.
     * @return List of tiers under the given plan.
     */
    @GetMapping("/plans/{planId}/tiers")
    public List<MembershipTier> getTiersForPlan(@PathVariable Long planId) {
        return membershipService.getTiersForPlan(planId);
    }

    /**
     * Subscribe a user to a specific membership tier.
     *
     * @param request Contains userId, planId, and tierId.
     * @return ApiResponse indicating success or failure.
     */
    @PostMapping("/subscribe")
    public ApiResponse subscribe(@RequestBody SubscriptionRequest request) {
        return membershipService.subscribe(request);
    }

    /**
     * Upgrade a user's current subscription to a higher tier or different plan.
     *
     * @param request Contains userId, new planId, and new tierId.
     * @return ApiResponse indicating success or failure.
     */
    @PostMapping("/upgrade")
    public ApiResponse upgrade(@RequestBody SubscriptionRequest request) {
        return membershipService.upgrade(request.getUserId(), request.getPlanId(), request.getTierId());
    }

    /**
     * Downgrade a user's subscription to a lower tier or different plan.
     *
     * @param request Contains userId, new planId, and new tierId.
     * @return ApiResponse indicating success or failure.
     */
    @PostMapping("/downgrade")
    public ApiResponse downgrade(@RequestBody SubscriptionRequest request) {
        return membershipService.downgrade(request.getUserId(), request.getPlanId(), request.getTierId());
    }

    /**
     * Cancel a user's active subscription.
     *
     * @param userId ID of the user whose subscription is to be canceled.
     * @return ApiResponse indicating success or failure.
     */
    @DeleteMapping("/cancel/{userId}")
    public ApiResponse cancel(@PathVariable Long userId) {
        return membershipService.cancel(userId);
    }

    /**
     * Retrieve the current subscription of a user.
     *
     * @param userId ID of the user.
     * @return Subscription details if available.
     */
    @GetMapping("/subscription/{userId}")
    public Subscription getSubscription(@PathVariable Long userId) {
        return membershipService.getSubscription(userId);
    }
}
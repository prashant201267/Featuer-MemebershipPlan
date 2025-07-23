package com.feature.membershipPlan.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    private Long userId;
    private Long planId;
    private Long tierId;
    private LocalDate startDate;
    private LocalDate expiryDate;
}
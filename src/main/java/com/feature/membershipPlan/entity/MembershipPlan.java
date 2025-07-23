package com.feature.membershipPlan.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipPlan {
    private Long id;
    private String name;
    private Double price;
    private List<Long> tierIds;
}

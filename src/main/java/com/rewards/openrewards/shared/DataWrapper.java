package com.rewards.openrewards.shared;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DataWrapper<T>{
    private T data;
}
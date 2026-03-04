package com.rewards.openrewards.shared;

public interface UseCase<I, O> {
    O execute(I input);
}

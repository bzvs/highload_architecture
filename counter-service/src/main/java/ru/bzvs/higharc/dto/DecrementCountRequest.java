package ru.bzvs.higharc.dto;

public record DecrementCountRequest(Long userId,
                                    Long countToDecrement) {
}

package ru.bzvs.higharc.service;

import ru.bzvs.higharc.dto.CounterDto;
import ru.bzvs.higharc.dto.DecrementCountRequest;
import ru.bzvs.higharc.dto.IncrementCountRequest;

import java.util.List;

public interface CounterService {
    CounterDto increment(IncrementCountRequest request);

    Long getCount();

    CounterDto decrement(DecrementCountRequest request);
}

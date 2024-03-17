package ru.bzvs.higharc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CounterDto {

    private Long id;

    private Long userId;

    private Long unreadMessageCount;

    private Instant updateDate;
}

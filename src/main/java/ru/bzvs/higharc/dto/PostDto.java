package ru.bzvs.higharc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto implements Serializable {

    Long id;

    Long authorId;

    String text;

    Instant createDate;
}

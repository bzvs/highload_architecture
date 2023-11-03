package ru.bzvs.higharc.dialog.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.springframework.data.tarantool.core.mapping.Field;
import org.springframework.data.tarantool.core.mapping.Tuple;

import java.time.Instant;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Tuple("message")
public class MessageEntity {

    @Id
    private Long id;

    @Field(name = "from_id")
    private Long from;

    @Field(name = "to_id")
    private Long to;

    private String text;

    private Instant createDate;
}

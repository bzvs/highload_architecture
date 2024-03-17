package ru.bzvs.higharc.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.bzvs.higharc.entity.MessageEntity;
import ru.bzvs.higharc.repository.MessageRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class MessageRepositoryImpl implements MessageRepository {

    private final NamedParameterJdbcTemplate masterTemplate;

    private static final String INSERT_QUERY = "insert into message values (nextval('message_id_seq'), :from_id, :to_id, :text)";
    private static final String SELECT_BY_TO_QUERY = """
            select * from message
            where to_id = :to_id
            and from_id = :from_id""";
    private static final String DELETE_QUERY = """
            delete from message
            where id = :id""";

    @Override
    public Long create(MessageEntity entity) {
        MapSqlParameterSource params = buildInsertEntityParams(entity);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        masterTemplate.update(INSERT_QUERY, params, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<MessageEntity> findByToAndFrom(Long toId, Long fromId) {
        return masterTemplate.query(SELECT_BY_TO_QUERY,
                new MapSqlParameterSource().addValue("to_id", toId)
                        .addValue("from_id", fromId),
                getMessageEntityRowMapper());
    }

    @Override
    public void delete(Long messageId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", messageId);
        masterTemplate.update(DELETE_QUERY, params);
    }

    private MapSqlParameterSource buildInsertEntityParams(MessageEntity entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("from_id", entity.getFrom());
        params.addValue("to_id", entity.getTo());
        params.addValue("text", entity.getText());

        return params;
    }

    private RowMapper<MessageEntity> getMessageEntityRowMapper() {
        return (rs, rowNum) -> {
            MessageEntity entity = new MessageEntity();
            entity.setId(rs.getLong("id"));
            entity.setFrom(rs.getLong("from_id"));
            entity.setTo(rs.getLong("to_id"));
            entity.setText(rs.getString("text"));
            Timestamp createDate = rs.getObject("create_date", Timestamp.class);
            entity.setCreateDate(createDate != null ? createDate.toInstant() : null);

            return entity;
        };
    }
}

//package ru.bzvs.higharc.repository.impl;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
//import org.springframework.stereotype.Repository;
//import ru.bzvs.higharc.entity.CounterEntity;
//import ru.bzvs.higharc.repository.CounterRepository;
//
//import java.sql.Timestamp;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Repository
//@RequiredArgsConstructor
//public class CounterRepositoryImpl implements CounterRepository {
//
//    private final NamedParameterJdbcTemplate masterTemplate;
//
//    private static final String INSERT_QUERY = "insert into counter values (nextval('counter_id_seq'), :userId, :unreadMessageCount, :updateDate)";
//    private static final String SELECT_BY_USER_ID_QUERY = """
//            select * from counter
//            where user_id = :userId""";
//
//    @Override
//    public Long create(CounterEntity entity) {
//        MapSqlParameterSource params = buildInsertEntityParams(entity);
//
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//        masterTemplate.update(INSERT_QUERY, params, keyHolder, new String[]{"id"});
//
//        return Objects.requireNonNull(keyHolder.getKey()).longValue();
//    }
//
//    @Override
//    public Optional<CounterEntity> findByUserId(Long userId) {
//        return Optional.ofNullable(masterTemplate.queryForObject(SELECT_BY_USER_ID_QUERY,
//                new MapSqlParameterSource().addValue("userId", userId),
//                getMessageEntityRowMapper()));
//    }
//
//    private MapSqlParameterSource buildInsertEntityParams(CounterEntity entity) {
//        MapSqlParameterSource params = new MapSqlParameterSource();
//        params.addValue("userId", entity.getUserId());
//        params.addValue("unreadMessageCount", entity.getUnreadMessageCount());
//        params.addValue("updateDate", entity.getUpdateDate());
//
//        return params;
//    }
//
//    private RowMapper<CounterEntity> getMessageEntityRowMapper() {
//        return (rs, rowNum) -> {
//            CounterEntity entity = new CounterEntity();
//            entity.setId(rs.getLong("id"));
//            entity.setUserId(rs.getLong("user_id"));
//            entity.setUnreadMessageCount(rs.getLong("unread_message_count"));
//            Timestamp updateDate = rs.getObject("update_date", Timestamp.class);
//            entity.setUpdateDate(updateDate != null ? updateDate.toInstant() : null);
//
//            return entity;
//        };
//    }
//}

package ru.bzvs.higharc.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.bzvs.higharc.entity.UserEntity;
import ru.bzvs.higharc.repository.UserRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private static final String INSERT_QUERY = "insert into user_info values (nextval('user_info_id_seq'), :first_name, :second_name, :age, :birthDate, :biography, :city, :password)";
    private static final String INSERT_FRIEND_QUERY = "insert into friend values (nextval('friend_id_seq'), :user_id, :friend_id)";
    private static final String DELETE_FRIEND_QUERY = "delete from friend where user_id = :user_id and friend_id = :friend_id";
    private static final String SELECT_QUERY = "select * from user_info where id = ";
    private static final String SELECT_FRIENDS_QUERY = """
            select ui.* from user_info ui
            join friend f on f.friend_id = ui.id
            where f.user_id = :user_id""";
    private static final String SELECT_SUBSCRIBERS_QUERY = """
            select ui.* from user_info ui
            join friend f on f.user_id = ui.id
            where f.friend_id = :friend_id""";

    private static final String SELECT_USERS_WITH_FRIENDS_QUERY = """
            select distinct ui.* from user_info ui
            join friend f on f.user_id = ui.id
            join post p on p.author_id = f.friend_id""";

    private final NamedParameterJdbcTemplate masterTemplate;

//    private final NamedParameterJdbcTemplate slaveTemplate;

    @Override
    public Long save(UserEntity entity) {
        MapSqlParameterSource params = buildInsertEntityParams(entity);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        masterTemplate.update(INSERT_QUERY, params, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Optional<UserEntity> findById(Long id) {
        return Optional.ofNullable(masterTemplate.queryForObject(SELECT_QUERY + id,
                new MapSqlParameterSource().addValue("id", id),
                getUserEntityRowMapper()));
    }

    @Override
    public List<UserEntity> findByFirstNameAndSecondName(String firstName, String secondName) {
        return masterTemplate.query("select * from user_info where first_name like '" + firstName +
                "%' and second_name like '" + secondName + "%'",
                getUserEntityRowMapper());
    }

    @Override
    public Long saveFriend(Long userId, Long friendId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("friend_id", friendId);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        masterTemplate.update(INSERT_FRIEND_QUERY, params, keyHolder, new String[]{"id"});

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public void deleteFriend(Long userId, Long friendId) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("friend_id", friendId);

        masterTemplate.update(DELETE_FRIEND_QUERY, params);
    }

    @Override
    public List<UserEntity> findFriends(Long userId) {
        return masterTemplate.query(SELECT_FRIENDS_QUERY,
                new MapSqlParameterSource().addValue("user_id", userId),
                getUserEntityRowMapper());
    }

    @Override
    public List<UserEntity> findSubscribers(Long userId) {
        return masterTemplate.query(SELECT_SUBSCRIBERS_QUERY,
                new MapSqlParameterSource().addValue("friend_id", userId),
                getUserEntityRowMapper());
    }

    @Override
    public List<UserEntity> findUsersWithFriendsAndPosts() {
        return masterTemplate.query(SELECT_USERS_WITH_FRIENDS_QUERY, getUserEntityRowMapper());
    }

    private RowMapper<UserEntity> getUserEntityRowMapper() {
        return (rs, rowNum) -> {
            UserEntity entity = new UserEntity();
            entity.setId(rs.getLong("id"));
            entity.setFirstName(rs.getString("first_name"));
            entity.setSecondName(rs.getString("second_name"));
            entity.setAge(rs.getInt("age"));
            Timestamp birthDate = rs.getObject("birth_date", Timestamp.class);
            entity.setBirthDate(birthDate != null ? birthDate.toInstant() : null);
            entity.setBiography(rs.getString("biography"));
            entity.setCity(rs.getString("city"));
            entity.setPassword(rs.getString("password"));

            return entity;
        };
    }

    private MapSqlParameterSource buildInsertEntityParams(UserEntity entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("first_name", entity.getFirstName());
        params.addValue("second_name", entity.getSecondName());
        params.addValue("age", entity.getAge());
        params.addValue("birthDate", Timestamp.from(entity.getBirthDate()));
        params.addValue("biography", entity.getBiography());
        params.addValue("city", entity.getCity());
        params.addValue("password", entity.getPassword());
        return params;
    }
}

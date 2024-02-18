package ru.bzvs.higharc.repository.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.bzvs.higharc.entity.PostEntity;
import ru.bzvs.higharc.repository.PostRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final NamedParameterJdbcTemplate masterTemplate;

    private static final String INSERT_QUERY = "insert into post values (nextval('post_id_seq'), :author_id, :text) returning id, author_id, text, create_date";
    private static final String UPDATE_QUERY = "update post set text = :text where id = :id";
    private static final String SELECT_QUERY = "select * from post where id = ";
    private static final String DELETE_QUERY = "delete from post where id = :id";

    private static final String SELECT_POSTS_BY_USER_QUERY = """
            select p.* from post p
            join friend f on p.author_id = f.friend_id
            where f.user_id = :user_id
            order by p.create_date desc
            offset :offset
            limit :limit
            """;

    @Override
    public PostEntity save(PostEntity entity) {
        MapSqlParameterSource params = buildInsertEntityParams(entity);

        return masterTemplate.queryForObject(INSERT_QUERY, params,  getPostEntityRowMapper());
    }

    @Override
    public PostEntity update(PostEntity entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", entity.getId());
        params.addValue("text", entity.getText());

        masterTemplate.update(UPDATE_QUERY, params);

        return entity;
    }

    @Override
    public void delete(Long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        masterTemplate.update(DELETE_QUERY, params);
    }

    @Override
    public Optional<PostEntity> findById(Long id) {
        return Optional.ofNullable(masterTemplate.queryForObject(SELECT_QUERY + id,
                new MapSqlParameterSource().addValue("id", id),
                getPostEntityRowMapper()));
    }

    @Override
    public List<PostEntity> getPostsByUserId(Long userId, int offset, int limit) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("user_id", userId);
        params.addValue("offset", offset);
        params.addValue("limit", limit);
        return masterTemplate.query(SELECT_POSTS_BY_USER_QUERY,
                params,
                getPostEntityRowMapper());
    }

    private MapSqlParameterSource buildInsertEntityParams(PostEntity entity) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("author_id", entity.getAuthorId());
        params.addValue("text", entity.getText());

        return params;
    }

    private RowMapper<PostEntity> getPostEntityRowMapper() {
        return (rs, rowNum) -> {
            PostEntity entity = new PostEntity();
            entity.setId(rs.getLong("id"));
            entity.setAuthorId(rs.getLong("author_id"));
            entity.setText(rs.getString("text"));
            Timestamp createDate = rs.getObject("create_date", Timestamp.class);
            entity.setCreateDate(createDate != null ? createDate.toInstant() : null);

            return entity;
        };
    }
}

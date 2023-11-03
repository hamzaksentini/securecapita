package hk.brothers.securecapita.rowmapper;

import hk.brothers.securecapita.domain.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Role.builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .permission(rs.getString("permission"))
                .build();
    }
}

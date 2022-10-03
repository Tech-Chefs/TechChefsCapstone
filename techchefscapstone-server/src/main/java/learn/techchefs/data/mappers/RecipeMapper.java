package learn.techchefs.data.mappers;

import learn.techchefs.models.Recipe;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipeMapper implements RowMapper <Recipe> {
    @Override
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(rs.getInt("id"));
        recipe.setUserId(rs.getInt("user_id"));
        recipe.setName(rs.getString("name"));
        recipe.setDescription(rs.getString("description"));
        return recipe;
    }
}

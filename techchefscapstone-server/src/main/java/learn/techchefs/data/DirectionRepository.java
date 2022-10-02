package learn.techchefs.data;

import learn.techchefs.models.Direction;

import java.util.List;

public interface DirectionRepository {
    List<Direction> findByRecipe(int recipeId);

    void addByRecipe(int recipeId, List<Direction> directions);

    boolean deleteByRecipe(int recipeId);

    boolean updateByRecipe(int recipeId, List<Direction> directions);
}

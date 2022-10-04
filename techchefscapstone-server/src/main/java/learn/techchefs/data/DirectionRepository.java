package learn.techchefs.data;

import java.util.List;

public interface DirectionRepository {
    List<String> findByRecipe(int recipeId);

    void addByRecipe(int recipeId, List<String> directions);

    boolean deleteByRecipe(int recipeId);

    boolean updateByRecipe(int recipeId, List<String> directions);
}

package learn.techchefs.data;

import learn.techchefs.models.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();

    Recipe findById(int id);

    Recipe add(Recipe recipe);

    boolean update(Recipe recipe);

    boolean deleteById(int recipeId);
}

package learn.techchefs.data;

import learn.techchefs.models.Ingredient;
import learn.techchefs.models.RecipeIngredient;

import java.util.List;
import java.util.Map;

public interface RecipeIngredientRepository {
    void setIngredients(Map<Integer, Ingredient> ingredients);

    List<RecipeIngredient> findByRecipe(int recipeId);

    void addByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients);

    boolean deleteByRecipe(int recipeId);

    boolean updateByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients);
}

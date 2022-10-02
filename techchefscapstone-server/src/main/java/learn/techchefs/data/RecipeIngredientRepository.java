package learn.techchefs.data;

import learn.techchefs.models.Ingredient;
import learn.techchefs.models.RecipeIngredient;
import learn.techchefs.models.Unit;

import java.util.List;
import java.util.Map;

public interface RecipeIngredientRepository {
    List <RecipeIngredient> findByRecipe(Map <Integer, Ingredient> ingredients, Map <Integer, Unit> units,
                                         int recipeId);

    void addByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients);

    boolean deleteByRecipe(int recipeId);

    boolean updateByRecipe(int recipeId, List<RecipeIngredient> recipeIngredients);
}

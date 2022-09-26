package learn.techchefs.data;

import learn.techchefs.models.Ingredient;

import java.util.List;

public interface IngredientRepository {
    List<Ingredient> findAll();

    List<Ingredient> findByCategory(int parentId);

    Ingredient findById(int id);

    Ingredient add(Ingredient ingredient);

    boolean update(Ingredient ingredient);

    boolean deleteById(int id);
}

package learn.techchefs.domain;

import learn.techchefs.data.*;
import learn.techchefs.models.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository repository;
    private final DirectionRepository directionRepository;
    private final UnitRepository unitRepository;
    private final UnitUnitRepository unitUnitRepository;
    private final RecipeIngredientRepository recipeIngredientRepository;
    private final IngredientRepository ingredientRepository;
    private final Map <Integer, Unit> units;
    private final Map<Unit, List <Measurement>> unitComparisons;
    private Map <Integer, Ingredient> ingredients;

    public RecipeService(RecipeRepository repository, DirectionRepository directionRepository,
                         UnitRepository unitRepository, UnitUnitRepository unitUnitRepository,
                         RecipeIngredientRepository recipeIngredientRepository,
                         IngredientRepository ingredientRepository) {
        this.repository = repository;
        this.directionRepository = directionRepository;
        this.unitRepository = unitRepository;
        this.unitUnitRepository = unitUnitRepository;
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.ingredientRepository = ingredientRepository;

        units = unitRepository.findAll().stream()
                .collect(Collectors.toMap(Unit::getId, unit -> unit));
        unitComparisons = unitUnitRepository.findAll(units);

        setIngredients();
    }

    public void setIngredients () {
        ingredients = ingredientRepository.findAll().stream()
                .collect(Collectors.toMap(Ingredient::getId, ingredient -> ingredient));
    }

    public List <Recipe> findAll () {
        List<Recipe> all = repository.findAll();
        for (Recipe recipe : all) setRecipeParams(recipe);
        return all;
    }

    public List <Recipe> findByIngredients (List <Ingredient> ingredients) {
        List <Recipe> recipes = findAll();
        for (Ingredient ingredient : ingredients) for (Recipe recipe : recipes) {
            boolean hasIngredient = false;
            for (RecipeIngredient recipeIngredient : recipe.getIngredients())
                if (recipeIngredient.getIngredient().equals(ingredient)) {
                    hasIngredient = true;
                    break;
                }
            if (!hasIngredient) recipes.remove(recipe);
        }
        return recipes;
    }

    public Result <Recipe> findById (int recipeId) {
        Result <Recipe> result = new Result<>();
        result.setPayload(repository.findById(recipeId));
        if (result.getPayload() == null)
            result.addMessage(String.format("Recipe #%d not found", recipeId), ResultType.NOT_FOUND);
        return result;
    }

    public Result <Recipe> add (Recipe recipe) {
        Result <Recipe> result = new Result<>();
        result.setPayload(repository.add(recipe));
        if (result.getPayload() == null) {
            result.addMessage(recipe.getName() + " already exists", ResultType.INVALID);
            return result;
        }
        recipeIngredientRepository.addByRecipe(recipe.getId(), recipe.getIngredients());
        directionRepository.addByRecipe(recipe.getId(), recipe.getDirections());
        return result;
    }

    public Result <List <RecipeIngredient>> updateIngredients (int recipeId, List <RecipeIngredient> recipeIngredients)
    {
        Result <List <RecipeIngredient>> result = new Result<>();
        result.setPayload(recipeIngredients);
        if (! recipeIngredientRepository.updateByRecipe(recipeId, recipeIngredients))
            result.addMessage(String.format("Ingredients for recipe #%d cannot be updated"), ResultType.INVALID);
        return result;
    }

    public Result <List <Direction>> updateDirections (int recipeId, List <Direction> directions) {
        Result <List <Direction>> result = new Result<>();
        result.setPayload(directions);
        if (! directionRepository.updateByRecipe(recipeId, directions))
            result.addMessage(String.format("Directions for recipe #%d cannot be updated"), ResultType.INVALID);
        return result;
    }

    public Result <Recipe> update (Recipe recipe) {
        Result <Recipe> result = new Result<>();
        result.setPayload(recipe);
        if (repository.findById(recipe.getId()) == null) {
            result.addMessage(String.format("Recipe #%d not found", recipe.getId()), ResultType.NOT_FOUND);
            return result;
        }
        if (! repository.update(recipe))
            result.addMessage(String.format("Recipe #%d cannot be updated", recipe.getId()), ResultType.INVALID);
        return result;
    }

    public Result <Recipe> delete (int recipeId) {
        Result <Recipe> result = new Result<>();
        result.setPayload(repository.findById(recipeId));
        if (result.getPayload() == null) {
            result.addMessage(String.format("Recipe #%d not found", recipeId), ResultType.NOT_FOUND);
            return result;
        }
        if (! recipeIngredientRepository.deleteByRecipe(recipeId)) {
            result.addMessage(String.format("Ingredients for recipe #%d cannot be deleted", recipeId), ResultType.INVALID);
            return result;
        }
        if (! directionRepository.deleteByRecipe(recipeId)) {
            result.addMessage(String.format("Directions for recipe #%d cannot be deleted", recipeId), ResultType.INVALID);
            return result;
        }
        if (! repository.deleteById(recipeId))
            result.addMessage(String.format("Recipe #%d cannot be deleted", recipeId), ResultType.INVALID);
        return result;
    }

    private void setRecipeParams (Recipe recipe) {
        List <RecipeIngredient> recipeIngredients =
                recipeIngredientRepository.findByRecipe(ingredients, units, recipe.getId());
        for (RecipeIngredient recipeIngredient : recipeIngredients) setAltMeasurements(recipeIngredient);
        recipe.setIngredients(recipeIngredients);
        recipe.setDirections(directionRepository.findByRecipe(recipe.getId()));
    }

    private void setAltMeasurements (RecipeIngredient recipeIngredient) {
        Map <Unit, Double> altMeasurements = new HashMap<>();
        Measurement measurement = recipeIngredient.getMeasurement();
        altMeasurements.put(measurement.getUnit(), measurement.getQuantity());
        Map<Unit, List <Measurement>> unitComparisons = unitUnitRepository.findByIngredient(units,
                recipeIngredient.getIngredient().getId());
        while (altMeasurements.size() < units.size()) {
            for (Map.Entry <Unit, Double> entry : altMeasurements.entrySet()) {
                double baseQuantity = entry.getValue();
                for (Measurement m : unitComparisons.get(entry.getKey())) {
                    Unit comparedUnit = m.getUnit();
                    if (!altMeasurements.containsKey(comparedUnit))
                        altMeasurements.put(comparedUnit, baseQuantity * m.getQuantity());
                }
            }
        }
    }
}

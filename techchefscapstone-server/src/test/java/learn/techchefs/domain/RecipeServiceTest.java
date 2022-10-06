package learn.techchefs.domain;

import learn.techchefs.data.*;
import learn.techchefs.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class RecipeServiceTest {
    @Autowired
    RecipeService service;

    @MockBean
    RecipeRepository repository;
    @MockBean
    DirectionRepository directionRepository;
    @MockBean
    UnitRepository unitRepository;
    @MockBean
    UnitUnitRepository unitUnitRepository;
    @MockBean
    RecipeIngredientRepository recipeIngredientRepository;
    @MockBean
    IngredientRepository ingredientRepository;

    List <Ingredient> ingredients = new ArrayList<>();
    List <Recipe> recipes = new ArrayList<>();

    List <Unit> units = new ArrayList<>();

    boolean hasSetUp = false;

    @BeforeEach
    void setUp (){
        if (!hasSetUp) {
            Unit unit = new Unit();
            unit.setId(1);
            unit.setAbbr("tsp");
            unit.setName("teaspoon");
            units.add(unit);
            unit = new Unit();
            unit.setId(2);
            unit.setAbbr("tbsp");
            unit.setName("tablespoon");
            units.add(unit);
            unit = new Unit();
            unit.setId(3);
            unit.setAbbr("floz");
            unit.setName("fluid ounce");
            units.add(unit);
            unit = new Unit();
            unit.setId(4);
            unit.setAbbr("cup");
            unit.setName("cup");
            units.add(unit);
            unit = new Unit();
            unit.setId(5);
            unit.setAbbr("pt");
            unit.setName("pint");
            units.add(unit);
            unit = new Unit();
            unit.setId(6);
            unit.setAbbr("qt");
            unit.setName("quart");
            units.add(unit);
            unit = new Unit();
            unit.setId(7);
            unit.setAbbr("gal");
            unit.setName("gallon");
            units.add(unit);
            unit = new Unit();
            unit.setId(8);
            unit.setAbbr("ml");
            unit.setName("milliliter");
            units.add(unit);
            unit = new Unit();
            unit.setId(9);
            unit.setAbbr("L");
            unit.setName("liter");
            units.add(unit);
            unit = new Unit();
            unit.setId(10);
            unit.setAbbr("g");
            unit.setName("gram");
            units.add(unit);
            unit = new Unit();
            unit.setId(11);
            unit.setAbbr("oz");
            unit.setName("ounce");
            units.add(unit);
            unit = new Unit();
            unit.setId(12);
            unit.setAbbr("kg");
            unit.setName("kilogram");
            units.add(unit);
            unit = new Unit();
            unit.setId(13);
            unit.setAbbr("lb");
            unit.setName("pound");
            units.add(unit);

            Ingredient ingredient = new Ingredient();
            ingredient.setId(1);
            ingredient.setName("Bread");
            ingredient.setContainsGluten(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(2);
            ingredient.setName("Peanut Butter");
            ingredient.setNutBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(3);
            ingredient.setName("Fruit Jelly");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(4);
            ingredient.setName("Lettuce");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(5);
            ingredient.setName("Tomato");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(6);
            ingredient.setName("Cucumber");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(7);
            ingredient.setName("Carrot");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(8);
            ingredient.setName("Ground Beef");
            ingredient.setIsMeat(true);
            ingredient.setAnimalBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(9);
            ingredient.setName("Peas");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(10);
            ingredient.setName("Potato");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(11);
            ingredient.setName("Onion");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(12);
            ingredient.setName("Beef Broth");
            ingredient.setAnimalBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(13);
            ingredient.setName("Butter");
            ingredient.setAnimalBased(true);
            ingredient.setContainsDairy(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(14);
            ingredient.setName("Worcestershire Sauce");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(15);
            ingredient.setName("Salt");
            ingredients.add(ingredient);

            Recipe recipe = new Recipe();

            List <RecipeIngredient> recipeIngredients = new ArrayList<>();
            RecipeIngredient recipeIngredient = new RecipeIngredient();
            Measurement measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(1));
            measurement.setUnit(units.get(1));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(2));
            measurement.setUnit(units.get(1));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(0));
            measurement.setUnit(units.get(10));
            measurement.setQuantity(3.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipe.setIngredients(recipeIngredients);

            List <Direction> directions = new ArrayList<>();
            Direction direction = new Direction();
            direction.setInstruction("Spread peanut butter on one slice of bread");
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Spread jelly on other slice of bread");
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Put slices of bread together");
            directions.add(direction);
            recipe.setDirections(directions);

            recipe.setId(1);
            recipe.setName("Peanut butter and jelly sandwich");
            recipe.setDescription("A lunchtime classic");
            recipes.add(recipe);

            recipe = new Recipe();

            recipeIngredients = new ArrayList<>();
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(3));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(3.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Chopped");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(4));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Wedged");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(5));
            measurement.setUnit(units.get(9));
            measurement.setQuantity(150.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Sliced");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(6));
            measurement.setUnit(units.get(9));
            measurement.setQuantity(75.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Grated");
            recipeIngredients.add(recipeIngredient);
            recipe.setIngredients(recipeIngredients);

            directions = new ArrayList<>();
            direction = new Direction();
            direction.setInstruction("Toss all ingredients in a big bowl");
            direction.setDurationMinutes(5);
            directions.add(direction);
            directions = new ArrayList<>();
            direction = new Direction();
            direction.setInstruction("Transfer to serving bowl and serve immediately");
            directions.add(direction);
            recipe.setDirections(directions);

            recipe.setId(2);
            recipe.setName("Garden Salad");
            recipe.setDescription("A healthy salad to share with friends");
            recipes.add(recipe);

            recipe = new Recipe();

            recipeIngredients = new ArrayList<>();
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(9));
            measurement.setUnit(units.get(12));
            measurement.setQuantity(2.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Peeled and quartered");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(14));
            measurement.setUnit(units.get(0));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(12));
            measurement.setUnit(units.get(1));
            measurement.setQuantity(8.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(10));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(1.5);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Chopped");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(6));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredient.setPreparation("Diced");
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(8));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(7));
            measurement.setUnit(units.get(12));
            measurement.setQuantity(1.5);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(11));
            measurement.setUnit(units.get(3));
            measurement.setQuantity(0.5);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipeIngredient = new RecipeIngredient();
            measurement = new Measurement();
            recipeIngredient.setIngredient(ingredients.get(13));
            measurement.setUnit(units.get(0));
            measurement.setQuantity(1.0);
            recipeIngredient.setMeasurement(measurement);
            recipeIngredients.add(recipeIngredient);
            recipe.setIngredients(recipeIngredients);

            directions = new ArrayList<>();
            direction = new Direction();
            direction.setInstruction("Boil the potatoes");
            direction.setDurationMinutes(20);
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Preheat oven to 400 F");
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Saute vegetables");
            direction.setDurationMinutes(10);
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Add ground beef, then worcestershire sauce and broth");
            direction.setDurationMinutes(10);
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Mash the cooked potatoes");
            direction.setDurationMinutes(5);
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Layer meat mixture and mashed potatoes in casserole dish");
            direction.setDurationMinutes(5);
            directions.add(direction);
            direction = new Direction();
            direction.setInstruction("Bake in oven");
            direction.setDurationMinutes(30);
            directions.add(direction);
            recipe.setDirections(directions);

            recipe.setId(3);
            recipe.setName("Shepherd's Pie");
            recipe.setDescription("An Irish Comfort Food");
            recipes.add(recipe);

            hasSetUp = true;
        }
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(recipes);
        assertEquals(3, service.findAll().size());
    }

    @Test
    void findByIngredients() {
        List <Ingredient> searchIngredients = List.of(ingredients.get(6));

    }

    @Test
    void findById() {
    }

    @Test
    void add() {
    }

    @Test
    void updateIngredients() {
    }

    @Test
    void updateDirections() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
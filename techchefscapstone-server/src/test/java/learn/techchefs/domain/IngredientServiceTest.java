package learn.techchefs.domain;

import learn.techchefs.data.IngredientRepository;
import learn.techchefs.models.Ingredient;
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
class IngredientServiceTest {
    @Autowired
    IngredientService service;

    @MockBean
    IngredientRepository repository;

    List <Ingredient> ingredients = new ArrayList<>();
    boolean hasSetup = false;

    @BeforeEach
    void setUp () {
        if (!hasSetup) {
            Ingredient ingredient = new Ingredient();
            ingredient.setId(1);
            ingredient.setName("Tomato");
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(2);
            ingredient.setName("Milk");
            ingredient.setContainsDairy(true);
            ingredient.setAnimalBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(3);
            ingredient.setName("Almonds");
            ingredient.setNutBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(4);
            ingredient.setName("chicken");
            ingredient.setIsMeat(true);
            ingredient.setAnimalBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(5);
            ingredient.setName("Salmon");
            ingredient.setIsMeat(true);
            ingredient.setIsFish(true);
            ingredient.setAnimalBased(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(6);
            ingredient.setName("Bread");
            ingredient.setContainsGluten(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(7);
            ingredient.setName("Egg");
            ingredient.setAnimalBased(true);
            ingredient.setContainsEgg(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(8);
            ingredient.setName("Soy Sauce");
            ingredient.setContainsGluten(true);
            ingredient.setContainsSoy(true);
            ingredients.add(ingredient);
            ingredient = new Ingredient();
            ingredient.setId(9);
            ingredient.setName("Roma Tomato");
            ingredient.setParentId(1);
            ingredients.add(ingredient);
            ingredient = ingredients.get(0);
            ingredient.setSubIngredients(List.of(ingredients.get(8)));
            ingredients.set(0, ingredient);
            hasSetup = true;
        }
    }

    @Test
    void shouldFindAll() {
        when(repository.findAll()).thenReturn(ingredients);
        assertEquals(ingredients, service.findAll());
    }

    @Test
    void shouldFilterShowOnlyDairy() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.SHOW_WITH, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(1, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(1));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutDairy() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.SHOW_WITHOUT, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(8, actual.size());
        Ingredient unexpected = ingredients.get(1);
        assertFalse(actual.contains(unexpected));
    }

    @Test
    void shouldFilterShowOnlyNuts() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.SHOW_WITH, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(1, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(2));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutNuts() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.SHOW_WITHOUT, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(8, actual.size());
        Ingredient unexpected = ingredients.get(2);
        assertFalse(actual.contains(unexpected));
    }

    @Test
    void shouldFilterShowOnlyMeat() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITH,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(2, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(3));
        expected.add(ingredients.get(4));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutMeat() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITHOUT,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(7, actual.size());
        assertFalse(actual.contains(ingredients.get(3)));
        assertFalse(actual.contains(ingredients.get(4)));
    }

    @Test
    void shouldFilterShowOnlyFish() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.SHOW_WITH, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(1, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(4));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutFish() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.SHOW_WITHOUT, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(8, actual.size());
        assertFalse(actual.contains(ingredients.get(4)));
    }

    @Test
    void shouldFilterShowOnlyAnimalBased() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.SHOW_WITH, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(4, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(1));
        expected.add(ingredients.get(3));
        expected.add(ingredients.get(4));
        expected.add(ingredients.get(6));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutAnimalBased() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.SHOW_WITHOUT, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(5, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(0));
        expected.add(ingredients.get(2));
        expected.add(ingredients.get(5));
        expected.add(ingredients.get(7));
        expected.add(ingredients.get(8));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowOnlyGluten() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITH, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(2, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(5));
        expected.add(ingredients.get(7));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutGluten() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITHOUT, FilterType.IGNORE,
                FilterType.IGNORE);
        assertEquals(7, actual.size());
        List <Ingredient> unexpected = new ArrayList<>();
        unexpected.add(ingredients.get(5));
        unexpected.add(ingredients.get(7));
        for (Ingredient ingredient : unexpected) assertFalse(actual.contains(ingredient));
    }

    @Test
    void shouldFilterShowOnlyEgg() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITH,
                FilterType.IGNORE);
        assertEquals(1, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(6));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutEgg() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.SHOW_WITHOUT,
                FilterType.IGNORE);
        assertEquals(8, actual.size());
        assertFalse(actual.contains(ingredients.get(6)));
    }

    @Test
    void shouldFilterShowOnlySoy() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.SHOW_WITH);
        assertEquals(1, actual.size());
        List <Ingredient> expected = new ArrayList<>();
        expected.add(ingredients.get(7));
        assertEquals(expected, actual);
    }

    @Test
    void shouldFilterShowWithoutSoy() {
        when(repository.findAll()).thenReturn(ingredients);
        List <Ingredient> actual = service.filter(FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE, FilterType.IGNORE,
                FilterType.SHOW_WITHOUT);
        assertEquals(8, actual.size());
        assertFalse(actual.contains(ingredients.get(7)));
    }

    @Test
    void shouldFindSubstitutions() {
        when(repository.findById(1)).thenReturn(ingredients.get(0));
        List <Ingredient> params = new ArrayList<>();
        params.add(ingredients.get(8));
        List <Ingredient> actual = service.findSubstitutions(params);
        assertEquals(2, actual.size());
        assertTrue(actual.contains(ingredients.get(0)));
        assertTrue(actual.contains(ingredients.get(8)));
    }

    @Test
    void shouldAdd() {
        Ingredient expected = new Ingredient();
        expected.setName("Lettuce");
        when(repository.add(expected)).thenReturn(expected);
        assertEquals(expected, service.add(expected).getPayload());
    }

    @Test
    void shouldNotAdd() {
        Ingredient unexpected = ingredients.get(0);
        when(repository.add(unexpected)).thenReturn(null);
        Result <Ingredient> result = service.add(unexpected);
        assertEquals(ResultType.INVALID, result.getResultType());
        assertEquals("Tomato cannot be added to the database", result.getMessages().get(0));
    }

    @Test
    void shouldUpdate() {
        Ingredient expected = ingredients.get(0);
        expected.setName("Lettuce");
        when(repository.findById(1)).thenReturn(expected);
        when(repository.update(expected)).thenReturn(true);
        assertEquals(ResultType.SUCCESS, service.update(expected).getResultType());
    }

    @Test
    void shouldNotUpdateIfNotFound() {
        Ingredient expected = new Ingredient();
        expected.setId(10);
        when(repository.findById(10)).thenReturn(null);
        Result <Ingredient> actual = service.update(expected);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
        assertEquals("Ingredient with id 10 not found", actual.getMessages().get(0));
    }

    @Test
    void shouldNotUpdateIfInvalid() {
        Ingredient expected = ingredients.get(0);
        expected.setName("Milk");
        when(repository.findById(1)).thenReturn(ingredients.get(0));
        when(repository.update(expected)).thenReturn(false);
        Result <Ingredient> actual = service.update(expected);
        assertEquals(ResultType.INVALID, actual.getResultType());
        assertEquals("Ingredient with id 1 cannot be updated", actual.getMessages().get(0));
    }

    @Test
    void shouldDelete() {
        when(repository.findById(1)).thenReturn(ingredients.get(0));
        when(repository.deleteById(1)).thenReturn(true);
        Result <Ingredient> actual = service.delete(1);
        assertEquals(ResultType.SUCCESS, actual.getResultType());
        assertEquals(ingredients.get(0), actual.getPayload());
    }

    @Test
    void shouldNotDeleteIfNotFound() {
        when(repository.findById(0)).thenReturn(null);
        Result <Ingredient> actual = service.delete(0);
        assertEquals(ResultType.NOT_FOUND, actual.getResultType());
        assertEquals("Ingredient with id 0 not found", actual.getMessages().get(0));
    }

    @Test
    void shouldNotDeleteIfInvalid() {
        when (repository.findById(1)).thenReturn(ingredients.get(0));
        when(repository.deleteById(1)).thenReturn(false);
        Result <Ingredient> actual = service.delete(1);
        assertEquals(ResultType.INVALID, actual.getResultType());
        assertEquals("Cannot delete Tomato", actual.getMessages().get(0));
    }
}
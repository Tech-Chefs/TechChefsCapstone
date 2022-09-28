package learn.techchefs.domain;

import learn.techchefs.data.IngredientRepository;
import learn.techchefs.models.Ingredient;
import org.springframework.web.client.HttpClientErrorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IngredientService {
    private final IngredientRepository repository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.repository = ingredientRepository;
    }

    public List <Ingredient> findAll () {
        return repository.findAll();
    }

    public List <Ingredient> filter (FilterType containsDairy, FilterType nutBased, FilterType meat, FilterType fish,
                                     FilterType animalBased, FilterType containsGluten, FilterType kosher,
                                     FilterType containsEgg, FilterType containsSoy) {
        return repository.findAll().stream()
                .filter(ingredient -> (containsDairy == FilterType.IGNORE ||
                        ((containsDairy == FilterType.SHOW_WITH) == (ingredient.isContainsDairy()))))
                .filter(ingredient -> (nutBased == FilterType.IGNORE ||
                        ((nutBased == FilterType.SHOW_WITH) == (ingredient.isNutBased()))))
                .filter(ingredient -> (meat == FilterType.IGNORE ||
                        ((meat == FilterType.SHOW_WITH) == (ingredient.isMeat()))))
                .filter(ingredient -> (fish == FilterType.IGNORE ||
                        ((fish == FilterType.SHOW_WITH) == (ingredient.isFish()))))
                .filter(ingredient -> (animalBased == FilterType.IGNORE ||
                        ((animalBased == FilterType.SHOW_WITH) == (ingredient.isAnimalBased()))))
                .filter(ingredient -> (containsGluten == FilterType.IGNORE ||
                        ((containsGluten == FilterType.SHOW_WITH) == (ingredient.getContainsGluten()))))
                .filter(ingredient -> (kosher == FilterType.IGNORE ||
                        ((kosher == FilterType.SHOW_WITH) == (ingredient.isKosher()))))
                .filter(ingredient -> (containsEgg == FilterType.IGNORE ||
                        ((containsEgg == FilterType.SHOW_WITH) == (ingredient.isContainsEgg()))))
                .filter(ingredient -> (containsSoy == FilterType.IGNORE ||
                        ((containsSoy == FilterType.SHOW_WITH) == (ingredient.isContainsSoy()))))
                .collect(Collectors.toList());
    }

    public List <Ingredient> findSubstitutions (List <Ingredient> recommended) {
        List <Ingredient> substitutions = new ArrayList<>();
        while (!recommended.isEmpty()) {
            Ingredient front = recommended.get(0);
            recommended.add(repository.findById(front.getParentId()));
            recommended.addAll(repository.findByCategory(front.getId()));
            substitutions.add(front);
            recommended.remove(0);
        }
        return substitutions;
    }

    public Result <Ingredient> add (Ingredient ingredient) {
        Result <Ingredient> result = new Result<>();
        result.setPayload(repository.add(ingredient));
        if (result.getPayload() == null) result.addMessage
                (ingredient.getName() + " cannot be added to the database", ResultType.INVALID);
        return result;
    }

    public Result <Ingredient> update (Ingredient ingredient) {
        Result <Ingredient> result = new Result<>();
        result.setPayload(ingredient);
        if (! repository.update(ingredient)) result.addMessage(String.format("Ingredient with id %d not found",
                ingredient.getId()), ResultType.NOT_FOUND);
        return result;
    }

    public Result <Ingredient> delete (int id) {
        Result <Ingredient> result = new Result<>();
        result.setPayload(repository.findById(id));
        if (result.getPayload() == null) {
            result.addMessage(String.format("Ingredient with id %d not found",id), ResultType.NOT_FOUND);
            return result;
        }
        if (!repository.deleteById(id))
            result.addMessage("Cannot delete " + result.getPayload().getName(), ResultType.INVALID);
        return result;
    }
}

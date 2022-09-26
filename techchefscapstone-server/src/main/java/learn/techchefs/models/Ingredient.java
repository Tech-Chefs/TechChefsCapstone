package learn.techchefs.models;

import java.util.ArrayList;
import java.util.List;

public class Ingredient {
    private int id;
    private String name;
    private int parentId;
    private List <Ingredient> subIngredients;
    private boolean containsDairy;
    private boolean nutBased;
    private boolean isKosher;
    private boolean containsEgg;
    private boolean  containsGluten;
    private boolean containsSoy;
    private boolean animalBased;
    private boolean isMeat;
    private boolean isFish;

    public Ingredient () {
        subIngredients = new ArrayList<>();
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public List<Ingredient> getSubIngredients() {
        return subIngredients;
    }

    public void addSubIngredient(Ingredient ingredient) {
        subIngredients.add(ingredient);
    }

    public void setSubIngredients (List <Ingredient> ingredients) {
        subIngredients = ingredients;
    }

    public boolean isKosher() {
        return isKosher;
    }

    public void setKosher(boolean kosher) {
        isKosher = kosher;
    }

    public boolean isContainsEgg() {
        return containsEgg;
    }

    public void setContainsEgg(boolean containsEgg) {
        this.containsEgg = containsEgg;
    }

    public boolean getContainsGluten() {
        return containsGluten;
    }

    public void setContainsGluten(boolean containsGluten) {
        this.containsGluten = containsGluten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isContainsDairy() {
        return containsDairy;
    }

    public void setContainsDairy(boolean containsDairy) {
        this.containsDairy = containsDairy;
    }

    public boolean isNutBased() {
        return nutBased;
    }

    public void setNutBased(boolean nutBased) {
        this.nutBased = nutBased;
    }

    public boolean getAnimalBased() {
        return animalBased;
    }

    public void setAnimalBased(boolean animalBased) {
        this.animalBased = animalBased;
    }

    public boolean isContainsGluten() {
        return containsGluten;
    }

    public boolean isContainsSoy() {
        return containsSoy;
    }

    public void setContainsSoy(boolean containsSoy) {
        this.containsSoy = containsSoy;
    }

    public boolean isAnimalBased() {
        return animalBased;
    }

    public boolean isMeat() {
        return isMeat;
    }

    public void setMeat(boolean meat) {
        isMeat = meat;
    }

    public boolean isFish() {
        return isFish;
    }

    public void setFish(boolean fish) {
        isFish = fish;
    }
}

package learn.techchefs.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Ingredient {
    private int id;
    private int userId;
    private String name;
    private int parentId;
    private List <Ingredient> subIngredients;
    private boolean containsDairy;
    private boolean nutBased;
    private boolean containsEgg;
    private boolean  containsGluten;
    private boolean containsSoy;
    private boolean animalBased;
    private boolean isMeat;
    private boolean isFish;

    public Ingredient () {
        subIngredients = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isContainsGluten() {
        return containsGluten;
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

    public boolean isContainsEgg() {
        return containsEgg;
    }

    public void setContainsEgg(boolean containsEgg) {
        this.containsEgg = containsEgg;
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

    public void setIsMeat(boolean meat) {
        isMeat = meat;
    }

    public boolean isFish() {
        return isFish;
    }

    public void setIsFish(boolean fish) {
        isFish = fish;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                ", subIngredients=" + subIngredients +
                ", containsDairy=" + containsDairy +
                ", nutBased=" + nutBased +
                ", containsEgg=" + containsEgg +
                ", containsGluten=" + containsGluten +
                ", containsSoy=" + containsSoy +
                ", animalBased=" + animalBased +
                ", isMeat=" + isMeat +
                ", isFish=" + isFish +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return id == that.id && parentId == that.parentId && containsDairy == that.containsDairy && nutBased == that.nutBased && containsEgg == that.containsEgg && containsGluten == that.containsGluten && containsSoy == that.containsSoy && animalBased == that.animalBased && isMeat == that.isMeat && isFish == that.isFish && name.equals(that.name) && Objects.equals(subIngredients, that.subIngredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, parentId, subIngredients, containsDairy, nutBased, containsEgg, containsGluten, containsSoy, animalBased, isMeat, isFish);
    }
}

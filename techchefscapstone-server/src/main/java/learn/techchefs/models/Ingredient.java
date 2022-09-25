package learn.techchefs.models;

public class Ingredient {
    private int id;
    private String name;
    private Ingredient parent;
    private boolean containsDairy;
    private boolean nutBased;
    private boolean isKosher;
    private boolean containsEgg;
    private boolean [] containsGluten; //00: gluten-free, 01: contains gluten (no soy), 11: contains soy
    private boolean [] animalBased; //00: vegan, 01: vegetarian, 10: meat, 11: fish

    public Ingredient () {}

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

    public boolean[] getContainsGluten() {
        return containsGluten;
    }

    public void setContainsGluten(boolean[] containsGluten) {
        this.containsGluten = containsGluten;
    }

    public Ingredient (int id) {
        this.id = id;
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

    public Ingredient getParent() {
        return parent;
    }

    public void setParent(Ingredient parent) {
        this.parent = parent;
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

    public boolean[] getAnimalBased() {
        return animalBased;
    }

    public void setAnimalBased(boolean[] animalBased) {
        this.animalBased = animalBased;
    }
}

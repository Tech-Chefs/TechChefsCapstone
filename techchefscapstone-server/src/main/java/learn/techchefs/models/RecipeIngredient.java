package learn.techchefs.models;

import java.util.List;
import java.util.Map;

public class RecipeIngredient {
    private Ingredient ingredient;
    private Measurement measurement;
    private boolean isOptional;
    private String preparation;
    private Map <Unit, Double> altMeasurements;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public String getPreparation() {
        return preparation;
    }

    public void setPreparation(String preparation) {
        this.preparation = preparation;
    }

    public Map<Unit, Double> getAltMeasurements() {
        return altMeasurements;
    }

    public void setAltMeasurements(Map<Unit, Double> altMeasurements) {
        this.altMeasurements = altMeasurements;
    }
}

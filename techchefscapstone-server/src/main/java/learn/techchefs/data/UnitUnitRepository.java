package learn.techchefs.data;

import learn.techchefs.models.Measurement;
import learn.techchefs.models.Unit;

import java.util.Map;

public interface UnitUnitRepository {
    Map<Unit, Measurement> findAll();

    Map<Unit, Measurement> findByIngredient(int ingredientId);

    boolean add (int ingredientId, Map.Entry<Unit, Measurement> entry);
}

package learn.techchefs.data;

import learn.techchefs.models.Measurement;
import learn.techchefs.models.Unit;

import java.util.List;
import java.util.Map;

public interface UnitUnitRepository {

    Map <Unit, List <Measurement>> findAll(Map<Integer, Unit> units);

    Map <Unit, List <Measurement>> findByIngredient(Map<Integer, Unit> units, int ingredientId);

    boolean add(int ingredientId, Map.Entry<Unit, Measurement> entry);
}

package learn.techchefs.data;

import learn.techchefs.models.Unit;

import java.util.List;

public interface UnitRepository {
    List<Unit> findAll();
}

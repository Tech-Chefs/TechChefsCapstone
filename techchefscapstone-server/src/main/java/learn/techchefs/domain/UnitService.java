package learn.techchefs.domain;

import learn.techchefs.data.UnitRepository;
import learn.techchefs.models.Unit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitService {
    final private UnitRepository repository;

    public UnitService(UnitRepository repository) {
        this.repository = repository;
    }

    public List<Unit> findAll () {
        return repository.findAll();
    }
}

package medicineapi.repository;

import com.example.medicineapi.model.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SymptomRepository extends JpaRepository<Symptom, Long> {
    Optional<Symptom> findByName(String name);
    List<Symptom> findByNameIn(List<String> names);
    List<Symptom> findByBodySystem(String bodySystem);
    List<medicineapi.repository.Symptom> findBySeverity(String severity);
}

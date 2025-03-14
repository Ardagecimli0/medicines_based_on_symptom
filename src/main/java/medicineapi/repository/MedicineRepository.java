package medicineapi.repository;

import medicineapi.model.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

    @Query("SELECT DISTINCT m FROM Medicine m JOIN m.treatableSymptoms s WHERE s IN :symptoms ORDER BY m.name")
    List<Medicine> findByTreatableSymptoms(@Param("symptoms") List<String> symptoms);

    @Query(value = "SELECT DISTINCT m.* FROM medicines m " +
            "JOIN medicine_treatable_symptoms mts ON m.id = mts.medicine_id " +
            "WHERE mts.symptom IN :symptoms " +
            "AND NOT EXISTS (SELECT 1 FROM medicine_contraindications mc " +
            "                WHERE mc.medicine_id = m.id AND mc.contraindication IN :contraindications)",
            nativeQuery = true)
    List<Medicine> findByTreatableSymptomsExcludingContraindications(
            @Param("symptoms") List<String> symptoms,
            @Param("contraindications") List<String> contraindications);

    @Query(value = "SELECT m.*, COUNT(mts.symptom) as symptom_count FROM medicines m " +
            "JOIN medicine_treatable_symptoms mts ON m.id = mts.medicine_id " +
            "WHERE mts.symptom IN :symptoms " +
            "GROUP BY m.id " +
            "ORDER BY symptom_count DESC",
            nativeQuery = true)
    List<Medicine> findByTreatableSymptomsOrderByMatchCount(@Param("symptoms") List<String> symptoms);
}
package medicineapi.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import medicineapi.model.Medicine;

import java.util.List;

@Data
public class SymptomRequest {
    @NotEmpty(message = "At least one symptom is required")
    private List<String> symptoms;

    private List<String> allergies;
    private List<String> existingConditions;
    private String ageGroup; // child, adult, elderly
    private String gender;
}


package medicineapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "active_ingredient")
    private String activeIngredient;

    @Column(name = "dosage_form")
    private String dosageForm;

    private String strength;
    private String manufacturer;

    @ElementCollection
    @CollectionTable(name = "medicine_treatable_symptoms",
            joinColumns = @JoinColumn(name = "medicine_id"))
    @Column(name = "symptom")
    private Set<String> treatableSymptoms;

    @ElementCollection
    @CollectionTable(name = "medicine_contraindications",
            joinColumns = @JoinColumn(name = "medicine_id"))
    @Column(name = "contraindication")
    private Set<String> contraindications;

    @ElementCollection
    @CollectionTable(name = "medicine_side_effects",
            joinColumns = @JoinColumn(name = "medicine_id"))
    @Column(name = "side_effect")
    private Set<String> sideEffects;

    @Column(name = "usage_instructions", columnDefinition = "TEXT")
    private String usageInstructions;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
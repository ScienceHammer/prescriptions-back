package app.core.entities;

import javax.persistence.*;

import app.core.enums.DoseFrequency;
import app.core.enums.DoseType;
import app.core.enums.Units;

@Entity
public class PrescribedMed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String activeSubstance;
	private DoseType doseType;
	private Integer doseAmount;
	private Double dosage;
	private Units dosageUnit;
	private DoseFrequency frequency;
	private Integer numberOfTakingDays;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinColumn(name = "prescription_id")
	private Prescription prescription;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActiveSubstance() {
		return activeSubstance;
	}

	public void setActiveSubstance(String activeSubstance) {
		this.activeSubstance = activeSubstance;
	}

	public DoseType getDoseType() {
		return doseType;
	}

	public void setDoseType(DoseType doseType) {
		this.doseType = doseType;
	}

	public Integer getDoseAmount() {
		return doseAmount;
	}

	public void setDoseAmount(Integer doseAmount) {
		this.doseAmount = doseAmount;
	}

	public Double getDosage() {
		return dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}

	public Units getDosageUnit() {
		return dosageUnit;
	}

	public void setDosageUnit(Units dosageUnit) {
		this.dosageUnit = dosageUnit;
	}

	public DoseFrequency getFrequency() {
		return frequency;
	}

	public void setFrequency(DoseFrequency frequency) {
		this.frequency = frequency;
	}

	public Integer getNumberOfTakingDays() {
		return numberOfTakingDays;
	}

	public void setNumberOfTakingDays(Integer numberOfTakingDays) {
		this.numberOfTakingDays = numberOfTakingDays;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}
	
	

}

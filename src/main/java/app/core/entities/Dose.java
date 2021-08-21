package app.core.entities;

import java.util.List;

import javax.persistence.*;

import app.core.enums.DoseFrequency;
import app.core.enums.DoseType;
import app.core.enums.Units;

@Entity
public class Dose {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private DoseType doseType;
	private Integer doseAmount;
	private Double dosage;
	private Units dosageUnit;
	private DoseFrequency frequency;
	private Integer numberOfTakingDays;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Medicine> medicines;

	@ManyToOne
	@JoinColumn(name = "medicine_id")
	private Medicine medicine;

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}

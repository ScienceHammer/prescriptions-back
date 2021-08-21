package app.core.entities;

import java.util.List;

import javax.persistence.*;

@Entity(name = "prescriptions")
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User doctor;
	@ManyToOne(cascade = CascadeType.ALL)
	private User patient;
	private String reason;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "prescription")
	private List<PrescripedMed> prescripedMeds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public List<PrescripedMed> getPrescripedMeds() {
		return prescripedMeds;
	}

	public void setPrescripedMeds(List<PrescripedMed> prescripedMeds) {
		this.prescripedMeds = prescripedMeds;
	}

	public User getDoctor() {
		return doctor;
	}

	public void setDoctor(User doctor) {
		this.doctor = doctor;
	}

	public User getPatient() {
		return patient;
	}

	public void setPatient(User patient) {
		this.patient = patient;
	}

}

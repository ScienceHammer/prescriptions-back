package app.core.entities;

import java.util.List;

import javax.persistence.*;

@Entity
public class Prescription {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User doctor;
	@ManyToOne(cascade = CascadeType.ALL)
	private User patient;
	private String reason;
	@OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<PrescribedMed> prescribedMeds;

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

	public List<PrescribedMed> getPrescribedMeds() {
		return prescribedMeds;
	}

	public void setPrescribedMeds(List<PrescribedMed> prescribedMeds) {
		this.prescribedMeds = prescribedMeds;
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

	@Override
	public String toString() {
		return "Prescription{" +
				"id=" + id +
				", doctor=" + doctor +
				", patient=" + patient +
				", reason='" + reason + '\'' +
				", prescribedMeds=" + prescribedMeds +
				'}';
	}
}

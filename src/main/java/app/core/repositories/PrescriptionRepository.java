package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
	
	List<Prescription> findByPatientId(Long patientId);

	List<Prescription> findByDoctorId(Long doctorId);

	List<Prescription> findByPatientIdOrDoctorId(Long patientId, Long doctorId);

}

package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Prescription;
import app.core.entities.User;
import app.core.repositories.PrescriptionRepository;
import app.core.repositories.UserRepository;

@Service
@Transactional
@Scope("prototype")
public class DoctorService {

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private UserRepository userRepository;

	public Prescription addPrescription(Prescription prescription) {
		if (prescription.getPatient().getId() == null) {
			prescription.getPatient().setActive(false);
			prescription.getPatient().setRegistered(false);
		} else {
			Optional<User> optionalUser = userRepository.findById(prescription.getPatient().getId());
			prescription.setPatient(optionalUser.get());
		}
		return prescriptionRepository.save(prescription);
	}

	public List<User> findUsersByUserIdNumber() {
		return userRepository.findAll();
	}

	public List<User> findUsersByFirstNameOrLastName(String firstName, String lastName) {
		return userRepository.findByFirstNameOrLastName(firstName, lastName);
	}

	public List<Prescription> getAllDoctorPrescriptions(Long doctorId) {
		return prescriptionRepository.findByDoctorId(doctorId);
	}

	public List<Prescription> getAllPatientPrescriptions(Long userId) {
		return prescriptionRepository.findByPatientId(userId);
	}

}

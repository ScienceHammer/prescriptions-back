package app.core.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.core.entities.Medicine;
import app.core.entities.User;
import app.core.repositories.MedicineRepository;
import app.core.repositories.UserRepository;

@Service
@Transactional
public class AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MedicineRepository medicineRepository;

	public Medicine addMedicine(Medicine medicine) {
		return medicineRepository.save(medicine);
	}

	public List<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}

	public String deleteMedicine(Long medicineId) {
		medicineRepository.deleteById(medicineId);
		return "Medicine with id " + medicineId + " is deleted!";
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public String deleteUser(Long userId) {
		userRepository.deleteById(userId);
		return "User with id " + userId + " is deleted!";
	}

}

package app.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}

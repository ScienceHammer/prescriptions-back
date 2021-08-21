package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByMailId(Long mailId);

}

package app.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Mail;

public interface MailRepository extends JpaRepository<Mail, Long> {

	List<Mail> findBySenderIdOrReceiverId(Long senderId, Long receiverId);

	List<Mail> findBySenderId(Long senderId);

	List<Mail> findByReceiverId(Long receiverId);

}

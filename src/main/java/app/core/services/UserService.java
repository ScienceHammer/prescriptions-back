package app.core.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import app.core.entities.Mail;
import app.core.entities.Message;
import app.core.entities.Prescription;
import app.core.entities.User;
import app.core.repositories.MailRepository;
import app.core.repositories.MessageRepository;
import app.core.repositories.PrescriptionRepository;
import app.core.repositories.UserRepository;

@Service
@Transactional
@Scope("prototype")
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PrescriptionRepository prescriptionRepository;

	@Autowired
	private MailRepository mailRepository;

	@Autowired
	private MessageRepository messageRepository;

	public List<Prescription> getAllPrescriptions(Long userId) {
		return prescriptionRepository.findByPatientIdOrDoctorId(userId, userId);
	}

	public List<Mail> getAllMails(Long userId) {
		return mailRepository.findBySenderIdOrReceiverId(userId, userId);
	}

	public List<Mail> getAllSentMails(Long userId) {
		return mailRepository.findBySenderId(userId);
	}

	public List<Mail> getAllRecivedMails(Long userId) {
		return mailRepository.findByReceiverId(userId);
	}

	public Mail sendMail(Long senderId, Mail mail, Long reciverId) throws Exception {
		Optional<User> sender = userRepository.findById(senderId);
		Optional<User> receiver = userRepository.findById(reciverId);
		if (sender.isPresent() && receiver.isPresent()) {
			mail.setSender(sender.get());
			mail.setReceiver(receiver.get());
			return mailRepository.save(mail);
		} else {
			throw new Exception("Sender Or Reciver Are Missing !!");
		}
	}

	public Message sendResponseMessage(Long mailId, Message message) throws Exception {
		Optional<Mail> optMail = mailRepository.findById(mailId);
		if (optMail.isPresent()) {
			Mail mail = optMail.get();
			mail.addMessage(message);
			return message;
		} else {
			throw new Exception("Mail Is Not Found !!");
		}
	}

	public List<Message> getAllMailMessages(Long mailId) {
		return messageRepository.findByMailId(mailId);
	}

}

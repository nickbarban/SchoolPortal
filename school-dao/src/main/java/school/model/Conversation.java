package school.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CONVERSATION")
@NamedQueries({
		@NamedQuery(name = "Conversation.INBOX_CONVERSATIONS", query = "SELECT c FROM Conversation c "
				+ "WHERE (c.receiverId = :receiver and c.isDeletedReceiver = false) "
				+ "OR (c.senderId = :receiver AND c.isAnswered = true AND c.isDeletedSender = false)"),
		@NamedQuery(name = "Conversation.SENT_CONVERSATIONS", query = "SELECT c from Conversation c "
				+ "where (c.senderId = :sender and c.isDeletedSender = false) "
				+ "or (c.receiverId = :sender and c.isAnswered = true and c.isDeletedReceiver = false)"),
		@NamedQuery(name = "Conversation.FIND_DATE_RECEIVER", query = "select max(m.dateTime) "
				+ "from Message m where m.conversationId = :conversation and m.isDeletedReceiver = false"),
		@NamedQuery(name = "Conversation.FIND_DATE_SENDER", query = "select max(m.dateTime) "
				+ "from Message m where m.conversationId = :conversation and m.isDeletedSender = false") })
public class Conversation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(nullable = false, name = "senderId")
	private User senderId;

	@ManyToOne
	@JoinColumn(nullable = false, name = "receiverId")
	private User receiverId;

	@OneToMany(mappedBy = "conversationId", cascade = CascadeType.ALL)
	private List<Message> messages;

	@Column(nullable = false, length = 100)
	private String subject;

	@Column(nullable = false)
	private boolean isAnswered;

	@Column(nullable = false)
	private boolean isDeletedReceiver;

	@Column(nullable = false)
	private boolean isDeletedSender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getSenderId() {
		return senderId;
	}

	public void setSenderId(User senderId) {
		this.senderId = senderId;
	}

	public User getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(User receiverId) {
		this.receiverId = receiverId;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isDeletedReceiver() {
		return isDeletedReceiver;
	}

	public void setDeletedReceiver(boolean isDeletedReceiver) {
		this.isDeletedReceiver = isDeletedReceiver;
	}

	public boolean isDeletedSender() {
		return isDeletedSender;
	}

	public void setDeletedSender(boolean isDeletedSender) {
		this.isDeletedSender = isDeletedSender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (isAnswered ? 1231 : 1237);
		result = prime * result + (isDeletedReceiver ? 1231 : 1237);
		result = prime * result + (isDeletedSender ? 1231 : 1237);
		result = prime * result
				+ ((messages == null) ? 0 : messages.hashCode());
		result = prime * result
				+ ((receiverId == null) ? 0 : receiverId.hashCode());
		result = prime * result
				+ ((senderId == null) ? 0 : senderId.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conversation other = (Conversation) obj;
		if (id != other.id)
			return false;
		if (isAnswered != other.isAnswered)
			return false;
		if (isDeletedReceiver != other.isDeletedReceiver)
			return false;
		if (isDeletedSender != other.isDeletedSender)
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (receiverId == null) {
			if (other.receiverId != null)
				return false;
		} else if (!receiverId.equals(other.receiverId))
			return false;
		if (senderId == null) {
			if (other.senderId != null)
				return false;
		} else if (!senderId.equals(other.senderId))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}
}
package proyecto.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Message.
 */
@Entity
@Table(name = "message")
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "attached")
    private byte[] attached;

    @Column(name = "attached_content_type")
    private String attachedContentType;

    @Column(name = "time")
    private ZonedDateTime time;

    @ManyToOne
    private User user;

    @ManyToOne
    private Conversation conversation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public Message text(String text) {
        this.text = text;
        return this;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getAttached() {
        return attached;
    }

    public Message attached(byte[] attached) {
        this.attached = attached;
        return this;
    }

    public void setAttached(byte[] attached) {
        this.attached = attached;
    }

    public String getAttachedContentType() {
        return attachedContentType;
    }

    public Message attachedContentType(String attachedContentType) {
        this.attachedContentType = attachedContentType;
        return this;
    }

    public void setAttachedContentType(String attachedContentType) {
        this.attachedContentType = attachedContentType;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public Message time(ZonedDateTime time) {
        this.time = time;
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public Message user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public Message conversation(Conversation conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Message message = (Message) o;
        if (message.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", text='" + text + "'" +
            ", attached='" + attached + "'" +
            ", attachedContentType='" + attachedContentType + "'" +
            ", time='" + time + "'" +
            '}';
    }
}

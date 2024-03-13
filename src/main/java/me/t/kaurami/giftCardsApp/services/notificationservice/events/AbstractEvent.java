package me.t.kaurami.giftCardsApp.services.notificationservice.events;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import me.t.kaurami.giftCardsApp.entities.User;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "event_type")
public abstract class AbstractEvent{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;

    @ManyToOne
    @JoinColumn(name = "user_event_source_id", nullable = false)
    @NotNull
    protected User eventFrom;

    @ManyToOne
    @JoinColumn(name = "user_event_target_id", nullable = false)
    @NotNull
    protected User eventFor;

    private boolean delivered = false;

    public AbstractEvent(User eventFrom, User eventFor) {
        this.eventFrom = eventFrom;
        this.eventFor = eventFor;
    }

    protected AbstractEvent() {
    }

    public User getEventFrom() {
        return eventFrom;
    }

    public User getEventFor() {
        return eventFor;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }
}

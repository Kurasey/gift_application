package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subscription_details")
public class SubscriptionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subscription_id")
    private Long id;
    @ManyToOne
    @NotNull(message = "{validation.nullUser}")
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @ManyToOne
    @NotNull(message = "{validation.nullUser}")
    @JoinColumn(name = "subscriber_id")
    private User subscriber;
    @Min(value = 0, message = "{validation.rate}")
    @Max(value = 10, message = "{validation.rate}")
    @Column(name = "available_min_rate")
    private int availableMinRate;
    @Min(value = 0, message = "{validation.rate}")
    @Max(value = 10, message = "{validation.rate}")
    @Column(name = "available_max_rate")
    private int availableMaxRate;

    @ManyToMany
    @JoinTable(name = "subscription_available_categories",
        joinColumns = @JoinColumn(name = "subscription_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> availableCategories;

    protected SubscriptionDetails() {
    }

    public SubscriptionDetails(User subscriber, User receiver, List<Category> availableCategories) {
        this.receiver = receiver;
        this.subscriber = subscriber;
        this.availableMinRate = 0;
        this.availableMaxRate = 0;
        this.availableCategories = availableCategories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSubscriber() {
        return subscriber;
    }

    public int getAvailableMinRate() {
        return availableMinRate;
    }

    public int getAvailableMaxRate() {
        return availableMaxRate;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setSubscriber(User subscriber) {
        this.subscriber = subscriber;
    }

    public void setAvailableMinRate(int availableMinRate) {
        this.availableMinRate = availableMinRate;
    }

    public void setAvailableMaxRate(int availableMaxRate) {
        this.availableMaxRate = availableMaxRate;
    }

    public List<Category> getAvailableCategories() {
        return availableCategories;
    }

    public void setAvailableCategories(List<Category> availableCategories) {
        this.availableCategories = availableCategories;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SubscribeDetails{");
        sb.append("id=").append(id);
        sb.append(", receiver=").append(receiver);
        sb.append(", subscriber=").append(subscriber);
        sb.append(", availableMinRate=").append(availableMinRate);
        sb.append(", availableMaxRate=").append(availableMaxRate);
        sb.append(", availableCategories=").append(availableCategories);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionDetails that = (SubscriptionDetails) o;
        return Objects.equals(receiver, that.receiver) && Objects.equals(subscriber, that.subscriber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiver, subscriber);
    }
}

package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Long id;

    @Size(min = 2, message = "{validation.descriptionSize}")
    private String description;
    private byte rate;
    private String commentary;
    @NotNull(message = "{validation.nullUser}")
    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    private boolean availableOnDefault;

    protected Category() {
    }

    public static Category withDescription(String description) {
        Category category = new Category();
        category.setDescription(description);
        return category;
    }

    public static Category withUser(User user) {
        Category category = new Category();
        category.user = user;
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte getRate() {
        return rate;
    }

    public void setRate(byte rate) {
        this.rate = rate;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isAvailableOnDefault() {
        return availableOnDefault;
    }

    public void setAvailableOnDefault(boolean availableOnDefault) {
        this.availableOnDefault = availableOnDefault;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", commentary='").append(commentary).append('\'');
        sb.append(", user=").append(user);
        sb.append(", availableOnDefault=").append(availableOnDefault);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(description, category.description) && Objects.equals(user, category.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, user);
    }
}

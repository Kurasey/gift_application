package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Objects;

@Entity
@Table(name ="gift_details")
public class GiftDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gift_id", updatable = false)
    private Long giftId;
    @ManyToOne
    @NotNull(message = "{validation.nullUser}")
    @JoinColumn(name = "user_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Size(min = 2, message = "{validation.descriptionSize}")
    private String description;
    private String articleNumber;
    private String link;
    private String commentary;
    private int rate;

    @ManyToMany
    @Size(min = 1, message = "{validation.categorySize}")
    @JoinTable(name = "gift_detail_categories",
            joinColumns = @JoinColumn(name = "gift_detail_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Category[] categories;

    public Long getGiftId() {
        return giftId;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    public String getArticleNumber() {
        return articleNumber;
    }

    public String getLink() {
        return link;
    }

    public String getCommentary() {
        return commentary;
    }

    public int getRate() {
        return rate;
    }

    public Category[] getCategories() {
        return categories;
    }

    public GiftDetail(String description) {
        this.description = description;
    }

    public GiftDetail(String description, String articleNumber, String link, String commentary, int rate) {
        this.description = description;
        this.articleNumber = articleNumber;
        this.link = link;
        this.commentary = commentary;
        this.rate = rate;
    }

    protected GiftDetail() {
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setCategories(Category... category){
        this.categories= category;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GiftDetail{");
        sb.append("giftId=").append(giftId);
        sb.append(", user=").append(user);
        sb.append(", description='").append(description).append('\'');
        sb.append(", articleNumber='").append(articleNumber).append('\'');
        sb.append(", link='").append(link).append('\'');
        sb.append(", commentary='").append(commentary).append('\'');
        sb.append(", rate=").append(rate);
        sb.append(", categories=").append(categories);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiftDetail giftCard = (GiftDetail) o;
        return Objects.equals(giftId, giftCard.giftId) && Objects.equals(user, giftCard.user) && Objects.equals(description, giftCard.description) && Objects.equals(articleNumber, giftCard.articleNumber) && Objects.equals(link, giftCard.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giftId, user, description, articleNumber, link);
    }
}

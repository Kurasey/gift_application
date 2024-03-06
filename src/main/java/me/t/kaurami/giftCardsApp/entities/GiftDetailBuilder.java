package me.t.kaurami.giftCardsApp.entities;

public class GiftDetailBuilder {

    private User user;

    private String description;
    private String articleNumber;
    private String link;
    private String commentary;
    private int rate;
    private Category[] categories;

    public GiftDetail build() {
        GiftDetail giftDetail = new GiftDetail();
        giftDetail.setUser(user);
        giftDetail.setDescription(description);
        giftDetail.setArticleNumber(articleNumber);
        giftDetail.setLink(link);
        giftDetail.setCommentary(commentary);
        giftDetail.setRate(rate);
        giftDetail.setCategories(categories);
        return giftDetail;
    }

    public GiftDetailBuilder description(String description) {
        this.description = description;
        return this;
    }
    public GiftDetailBuilder articleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
        return this;
    }
    public GiftDetailBuilder link(String link) {
        this.link = link;
        return this;
    }
    public GiftDetailBuilder commentary(String commentary) {
        this.commentary = commentary;
        return this;
    }
    public GiftDetailBuilder rate(int rate) {
        this.rate = rate;
        return this;
    }
    public GiftDetailBuilder user(User user) {
        this.user = user;
        return this;
    }

    public GiftDetailBuilder categories(Category... categories) {
        this.categories = categories;
        return this;
    }




}

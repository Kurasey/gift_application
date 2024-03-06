package me.t.kaurami.giftCardsApp.entities;

public class GiftDetailView {

    private GiftDetail giftDetail;
    private boolean booked;

    public GiftDetailView(GiftDetail giftDetail, boolean booked) {
        this.giftDetail = giftDetail;
        this.booked = booked;
    }

    public GiftDetail getGiftDetail() {
        return giftDetail;
    }

    public boolean isBooked() {
        return booked;
    }
}
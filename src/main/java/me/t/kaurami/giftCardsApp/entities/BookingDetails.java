package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "booking_details")
public class BookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_ig")
    private Long id;

    @OneToOne
    @JoinColumn(name = "gift_id")
    private GiftDetail giftDetail;

    @ManyToOne
    @JoinColumn(name = "user_booked_id")
    private User bookedBy;

    @ManyToOne
    @JoinColumn(name = "user_gift_id")
    private User giftUser;

    private LocalDate bookedAt;

    protected BookingDetails() {
    }

    public BookingDetails(GiftDetail giftDetail, User bookedBy, LocalDate bookedAt) {
        this.giftDetail = giftDetail;
        this.bookedBy = bookedBy;
        this.bookedAt = bookedAt;
        this.giftUser = giftDetail.getUser();
    }

    public Long getId() {
        return id;
    }

    public GiftDetail getGiftDetail() {
        return giftDetail;
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public LocalDate getBookedAt() {
        return bookedAt;
    }

    public User getGiftUser() {
        return giftUser;
    }
}

package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "favorite_gifts")
public class FavoriteGift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "favorite_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_id")
    private GiftDetail giftDetail;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private byte rate;
    private String Comment;
}

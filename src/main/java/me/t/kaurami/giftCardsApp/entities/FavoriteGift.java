package me.t.kaurami.giftCardsApp.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "favorite_gifts")
public class FavoriteGift {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "favorite_id", updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "gift_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private GiftDetail giftDetail;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    private byte rate;
    private String Comment;
}

package me.t.kaurami.giftCardsApp.repositories;

import jakarta.transaction.Transactional;
import me.t.kaurami.giftCardsApp.entities.User;
import me.t.kaurami.giftCardsApp.services.notificationservice.events.AbstractEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventRepository extends JpaRepository<AbstractEvent, Long> {

    public List<AbstractEvent> findByEventForAndDeliveredIsFalse(User eventFor);

    @Transactional
    @Modifying
    @Query("UPDATE AbstractEvent e SET e.delivered = true WHERE id = :deliveredId")
    public void setDelivered(@Param("deliveredId") Long deliveredId);
}

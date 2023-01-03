package me.ezra.core.domain.schedule;


import me.ezra.core.domain.schedule.domain.RequestStatus;
import me.ezra.core.domain.schedule.domain.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface ShareRepository extends JpaRepository<Share, Long> {

    @Query("SELECT s FROM Share s WHERE (s.fromUserId = ?1 OR s.toUserId = ?1) AND s.requestStatus = ?2 AND s.direction = ?3")
    List<Share> findAllByDirection(Long userId, RequestStatus accepted, Share.Direction biDirection);

    List<Share> findAllByToUserIdAndRequestStatusAndDirection(Long userId, RequestStatus requestStatus, Share.Direction direction);
}

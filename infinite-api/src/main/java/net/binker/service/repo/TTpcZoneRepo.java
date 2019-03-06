package net.binker.service.repo;

import net.binker.entity.model.TTpcZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTpcZoneRepo extends JpaRepository<TTpcZone, Long> {
    //已发布的话题
    @Query("from TTpcZone where topicId = ?1")
    public List<TTpcZone> findByTopicId(Long topicId);
}

package net.binker.service.repo;

import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcInfomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TAtcCommentRepo extends JpaRepository<TAtcComment, Long> {

    @Query("from TAtcComment where atcId = ?1")
    List<TAtcComment> findByAtcId(Long atcId);
}

package net.binker.service.repo;

import net.binker.entity.model.TAtcNote;
import net.binker.entity.model.TCstInfo;
import net.binker.entity.model.TTreInfomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTreInfomationRepo extends JpaRepository<TTreInfomation, Long> {

    @Query("from TTreInfomation where authorId = ?1")
    List<TTreInfomation> findByAuthorId(Long authorId);


}

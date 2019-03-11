package net.binker.service.repo;

import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTreDetailRepo extends JpaRepository<TTreDetail, Long> {

    @Query("from TTreDetail where myselfId = ?1 and treeId = ?2")
    List<TTreDetail> findByMIdAndTreeId(Long id, Long treeId);
}

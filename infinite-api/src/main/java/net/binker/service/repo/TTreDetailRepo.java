package net.binker.service.repo;

import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TTreDetailRepo extends JpaRepository<TTreDetail, Long> {

    @Query("from TTreDetail where myselfId = ?1 and treeId = ?2")
    List<TTreDetail> findByMIdAndTreeId(Long id, Long treeId);

    @Transactional
    @Modifying
    @Query("delete from TTreDetail where atcId = ?1 and type = ?2")
    Integer deleteByAtcIdAndType(Long atcId,Integer type);
}

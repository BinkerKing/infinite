package net.binker.service.repo;

import net.binker.entity.model.TTreInfomation;
import net.binker.entity.model.TTreStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TTreStructureRepo extends JpaRepository<TTreStructure, Long> {
    @Query("from TTreStructure where treeId = ?1")
    List<TTreStructure> findByTreeId(Long treeId);

}

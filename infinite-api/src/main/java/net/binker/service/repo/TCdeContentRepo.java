package net.binker.service.repo;

import java.util.List;

import net.binker.entity.model.TCdeContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TCdeContentRepo extends JpaRepository<TCdeContent, Long> {
	
	@Query("from TCdeContent where infoId = ?1")
	public List<TCdeContent> findByInfoId(Long codeId);
}

package net.binker.service.repo;

import java.util.List;

import net.binker.entity.model.TAtcNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TAtcNoteRepo extends JpaRepository<TAtcNote, Long> {
	
	@Query("from TAtcNote where atcId = ?1 and custId = ?2")
	List<TAtcNote> findByAtcIdAndCustId(Long atcId, Long custId);
}

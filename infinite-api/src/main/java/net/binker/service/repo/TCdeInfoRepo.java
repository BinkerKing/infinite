package net.binker.service.repo;

import java.util.List;

import net.binker.entity.model.TCdeInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TCdeInfoRepo extends JpaRepository<TCdeInfo, Long> {
	
	@Query("from TCdeInfo where custId = ?1")
	List<TCdeInfo> findByCustId(Long custId);
	
}

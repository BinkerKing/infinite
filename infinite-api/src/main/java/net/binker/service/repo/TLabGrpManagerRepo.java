package net.binker.service.repo;

import net.binker.entity.model.TLabGrpManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLabGrpManagerRepo extends JpaRepository<TLabGrpManager, Long> {
	

}

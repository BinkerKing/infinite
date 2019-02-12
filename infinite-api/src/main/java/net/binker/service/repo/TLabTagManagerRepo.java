package net.binker.service.repo;

import net.binker.entity.model.TLabTagManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TLabTagManagerRepo extends JpaRepository<TLabTagManager, Long> {
	

}

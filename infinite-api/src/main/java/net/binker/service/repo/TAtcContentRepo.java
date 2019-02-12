package net.binker.service.repo;

import net.binker.entity.model.TAtcContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAtcContentRepo extends JpaRepository<TAtcContent, Long> {
	

}

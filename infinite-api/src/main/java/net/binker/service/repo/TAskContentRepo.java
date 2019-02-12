package net.binker.service.repo;

import net.binker.entity.model.TAskContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAskContentRepo extends JpaRepository<TAskContent, Long> {
	

}

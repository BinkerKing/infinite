package net.binker.service.repo;

import net.binker.entity.model.TCstInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TCstInfoRepo extends JpaRepository<TCstInfo, Long> {
	

}

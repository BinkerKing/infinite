package net.binker.service.repo;

import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TTreDetailRepo extends JpaRepository<TTreDetail, Long> {
	

}

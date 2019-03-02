package net.binker.service.repo;

import net.binker.entity.model.TSysTmplinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSysTmplinfoRepo extends JpaRepository<TSysTmplinfo, Long> {
	

}

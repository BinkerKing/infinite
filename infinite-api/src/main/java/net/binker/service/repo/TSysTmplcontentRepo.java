package net.binker.service.repo;


import net.binker.entity.model.TSysTmplcontent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TSysTmplcontentRepo extends JpaRepository<TSysTmplcontent, Long> {
	

}

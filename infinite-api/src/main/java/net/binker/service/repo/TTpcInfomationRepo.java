package net.binker.service.repo;

import net.binker.entity.model.TTpcInfomation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TTpcInfomationRepo extends JpaRepository<TTpcInfomation, Long> {
	//已发布的话题
	@Query("from TTpcInfomation where publishStatus = '1'")
	public List<TTpcInfomation> findByPublish();


}

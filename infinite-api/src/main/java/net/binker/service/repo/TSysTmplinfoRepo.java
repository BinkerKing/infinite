package net.binker.service.repo;

import net.binker.entity.model.TSysTmplinfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TSysTmplinfoRepo extends JpaRepository<TSysTmplinfo, Long> {

    //发布的模版列表
    @Query("from TSysTmplinfo where publishStatus = 1")
    List<TSysTmplinfo> findByPublish();
}

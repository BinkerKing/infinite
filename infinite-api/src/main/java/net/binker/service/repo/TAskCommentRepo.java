package net.binker.service.repo;

import net.binker.entity.model.TAskComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TAskCommentRepo extends JpaRepository<TAskComment, Long> {

}
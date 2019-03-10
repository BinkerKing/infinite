package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "T_TRE_STRUCTURE")
public class TTreStructure {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("知识树ID")
	@Column(name = "tree_id")
	private Long treeId;

	//@Comment("父节点ID")
	@Column(name = "pid")
	private Long parentId;

	//@Comment("标题名")
	@Column(name = "title_info")
	private String name;

}
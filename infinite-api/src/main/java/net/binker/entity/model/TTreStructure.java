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

	//@Comment("是否打开")
	@Column(name = "open_flag")
	private Byte open;

	//@Comment("是否可以拖拽")
	@Column(name = "drag_flag")
	private Byte drag;

//	//@Comment("下面是否可以放子节点")
//	@Column(name = "dropInner")
//	private Byte dropInner;
//
//	//@Comment("是否可以作为根节点")
//	@Column(name = "dropRoot")
//	private Byte dropRoot;
//
//	//@Comment("禁止子节点移走")
//	@Column(name = "childOuter")
//	private Byte childOuter;
//
//	//@Comment("禁止子节点排序")
//	@Column(name = "childOrder")
//	private Byte childOrder;
}
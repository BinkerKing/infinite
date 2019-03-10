package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "T_TRE_DETAIL")
public class TTreDetail {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("标题")
	@Column(name = "title_info")
	private String titleInfo;

	//@Comment("创建时间")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;

	//@Comment("描述")
	@Column(name = "description")
	private String description;

	//@Comment("发表人ID")
	@Column(name = "author_id")
	private Long authorId;

	//@Comment("发表人")
	@Column(name = "authorName")
	private String authorName;

	//@Comment("分类")
	@Column(name = "classification")
	private String classification;

	//@Comment("发布状态 0：未发布 1:发布 ")
	@Column(name = "publish_status")
	private Byte publishStatus;

	//@Comment("文章状态  0-无效 1-有效")
	@Column(name = "valid_flag")
	private Byte validFlag;

	//@Comment("知识树ID")
	@Column(name = "tree_id")
	private Long treeId;

	//@Comment("当前节点ID")
	@Column(name = "lid")
	private Long lId;

	//@Comment("文章ID")
	@Column(name = "atcId")
	private Long atcId;

	//@Comment("文章类型")
	@Column(name = "type")
	private Long type;

}
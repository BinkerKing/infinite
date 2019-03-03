package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
//@Comment("话题空间表")
@Table(name = "T_TPC_ZONE")
public class TTpcZone {

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
	
	//@Comment("发表人ID")
	@Column(name = "author_id")
	private Long authorId;
	
	//@Comment("发表人")
	@Column(name = "authorName")
	private String authorName;
	
	//@Comment("分类 1-手记 2-猿问 3-代码")
	@Column(name = "classification")
	private String classification;
	
	//@Comment("信息ID")
	@Column(name = "info_id")
	private Long infoId;
	
	//@Comment("发布状态 0：未发布 1:发布 ")
	@Column(name = "publish_status")
	private Byte publishStatus;
	
	//@Comment("描述")
	@Column(name = "description")
	private String description;
	
	//正文内容
	@Transient
	private TAtcInfomation tAtcInfomation;

	@Transient
	private TAskInfomation tAskInfomation;

	@Transient
	private TCdeInfo tCdeInfo;
	
}
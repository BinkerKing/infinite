package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
//@Comment("话题信息表")
@Table(name = "T_TPC_INFOMATION")
public class TTpcInfomation {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("话题名称")
	@Column(name = "title_info")
	private String titleInfo;
	
	//@Comment("创建时间")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
	
	//@Comment("发表人ID")
	@Column(name = "author_id")
	private Long authorId;
	
	//@Comment("发起人")
	@Column(name = "authorName")
	private String authorName;
	
	//@Comment("参与人数")
	@Column(name = "join_count")
	private Integer joinCount;

	//@Comment("手记数")
	@Column(name = "atc_count")
	private Integer atcCount;

	//@Comment("猿问数")
	@Column(name = "ask_count")
	private Integer askCount;

	//@Comment("代码数")
	@Column(name = "code_count")
	private Integer codeCount;

	//@Comment("分类")
	@Column(name = "classification")
	private String classification;
	
	//@Comment("空间id")
	@Column(name = "zone_id")
	private Long zoneId;
	
	//@Comment("发布状态 0：未发布 1:发布 ")
	@Column(name = "publish_status")
	private Byte publishStatus;
	
	//@Comment("标星 0-未标星 1-标星")
	@Column(name = "star_flag")
	private Byte starFlag;
	
	//@Comment("话题状态  0-无效 1-有效")
	@Column(name = "valid_flag")
	private Byte validFlag;

	//@Comment("描述")
	@Column(name = "description")
	private String description;
	
	//话题空间列表
	@Transient
	private TTpcZone tTpcZone;
	
}
package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "T_SYS_TMPLINFO")
public class TSysTmplinfo {

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

	//@Comment("分类")
	@Column(name = "classification")
	private String classification;
	
	//@Comment("模板内容id")
	@Column(name = "content_id")
	private Long contentId;
	
	//@Comment("发布状态 0：未发布 1:发布 ")
	@Column(name = "publish_status")
	private Byte publishStatus;
	
	//@Comment("模板状态  0-无效 1-有效")
	@Column(name = "valid_flag")
	private Byte validFlag;

	//@Comment("描述")
	@Column(name = "description")
	private String description;
	
	//模板内容
	@Transient
	private TSysTmplcontent tSysTmplcontent;

}
package net.binker.entity.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Comment("代码信息表")
@Table(name = "T_CDE_INFO")
public class TCdeInfo {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("用户ID")
	@Column(name = "cust_id")
	private Long custId;

	//@Comment("内容ID")
	@Column(name = "content_id")
	private Long contentId;
	
	//@Comment("标题")
	@Column(name = "title")
	private String title;
	
	//@Comment("备注")
	@Column(name = "note")
	private String note;

	//@Comment("模式")
	@Column(name = "mode")
	private String mode;

	//@Comment("发布状态 0：未发布 1:发布 ")
	@Column(name = "publish_status")
	private Byte publishStatus;
	
	@Transient
	private TCdeContent tcdeContent;
	
}
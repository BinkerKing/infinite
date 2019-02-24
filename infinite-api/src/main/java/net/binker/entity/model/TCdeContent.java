package net.binker.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
//@Comment("代码内容表")
@Table(name = "T_CDE_CONTENT")
public class TCdeContent {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Lob
	//@Comment("内容")
	@Column(name = "content")
	private String content;
	
	//@Comment("代码格式")
	@Column(name = "mode")
	private String mode;
	
	//@Comment("备注")
	@Column(name = "note")
	private String note;
	
}
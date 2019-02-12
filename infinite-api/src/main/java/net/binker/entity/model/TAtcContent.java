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
//@Comment("手记内容表")
@Table(name = "T_ATC_CONTENT")
public class TAtcContent {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Lob
	//@Comment("正文HTML")
	@Column(name = "html_content")
	private String htmlContent;

	//@Comment("标题")
	@Column(name = "title_info")
	private String titleInfo;
	
	
}
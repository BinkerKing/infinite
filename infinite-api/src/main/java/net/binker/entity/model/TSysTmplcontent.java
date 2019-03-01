package net.binker.entity.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
//@Comment("模板内容表")
@Table(name = "T_SYS_TMPLCONTENT")
public class TSysTmplcontent {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Lob
	//@Comment("正文HTML")
	@Column(name = "html_content")
	private String htmlContent;
	
}
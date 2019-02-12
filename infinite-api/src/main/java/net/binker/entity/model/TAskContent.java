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
@Table(name = "T_ASK_CONTENT")
public class TAskContent {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Lob
	@Column(name = "html_content")
	private String htmlContent;

	@Column(name = "title_info")
	private String titleInfo;
	
	
}
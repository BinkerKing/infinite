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
//@Comment("手记笔记表")
@Table(name = "T_ATC_NOTE")
public class TAtcNote {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("用户ID")
	@Column(name = "cust_id")
	private Long custId;
	
	//@Comment("文章ID")
	@Column(name = "atc_id")
	private Long atcId;

	@Lob
	//@Comment("笔记内容")
	@Column(name = "note")
	private String note;
	
	//@Comment("笔记标题")
	@Column(name = "note_title")
	private String noteTitle;
	
}
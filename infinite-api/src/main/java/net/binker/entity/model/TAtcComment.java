package net.binker.entity.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
//@Comment("手记评论表")
@Table(name = "T_ATC_COMMENT")
public class TAtcComment {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	//@Comment("手记信息ID")
	@Column(name = "atc_id")
	private Long atcId;

	//@Comment("评论人ID")
	@Column(name = "author_id")
	private Long authorId;

	//@Comment("评论人姓名")
	@Column(name = "author_name")
	private String authorName;

	//@Comment("评论内容")
	@Lob
	@Column(name = "comment")
	private String comment;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	private Date createTime;
}
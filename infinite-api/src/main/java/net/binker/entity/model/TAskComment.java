package net.binker.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "T_ASK_COMMENT")
public class TAskComment {

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	@Column(name = "pet_name")
	private String petName;

	
	
	
	
}
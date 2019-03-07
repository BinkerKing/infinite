package net.binker.entity.model.temp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TableResult {

	private Integer total;
	
	private List<TableTest> rows;
	
}
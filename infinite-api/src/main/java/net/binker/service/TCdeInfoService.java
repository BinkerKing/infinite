package net.binker.service;

import java.util.List;

import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;

public interface TCdeInfoService {

	public List<TCdeInfo> getCodeList(Long custId);
	
	public TCdeInfo saveCode(TCdeInfo info);
	
	public String deleteCode(Long infoId);

	public TCdeInfo getCode(Long infoId);

}
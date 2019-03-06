package net.binker.service;

import java.util.List;

import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;

public interface CodeService {

	public List<TCdeInfo> getMyCodeList(Long custId);
	
	public TCdeInfo saveCode(TCdeInfo info);
	
	public String deleteCode(TCdeInfo inf);

	public TCdeInfo getCode(Long infoId);

}
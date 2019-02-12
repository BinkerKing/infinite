package net.binker.service;

import java.util.List;

import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;

public interface TCdeInfoService {

	public List<TCdeInfo> getCodeList(Long custId);
	
	public TCdeContent getContent(Long codeId);
	
	public TCdeInfo saveCode(TCdeInfo info);
	
	public TCdeContent saveContent(TCdeContent content);
	
	public String deleteContent(Long contentId);

}
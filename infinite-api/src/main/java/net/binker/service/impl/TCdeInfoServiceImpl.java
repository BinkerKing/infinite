package net.binker.service.impl;

import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;
import net.binker.service.TCdeInfoService;
import net.binker.service.repo.TCdeContentRepo;
import net.binker.service.repo.TCdeInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCdeInfoServiceImpl implements TCdeInfoService {

	@Autowired
	private TCdeInfoRepo tCdeInfoRepo;
	
	@Autowired
	private TCdeContentRepo tCdeContentRepo;
	
	@Override
	public List<TCdeInfo> getCodeList(Long custId) {
		List<TCdeInfo> tCdeInfoList = tCdeInfoRepo.findByCustId(custId);
		for(TCdeInfo info : tCdeInfoList){
			List<TCdeContent> contentList = tCdeContentRepo.findByInfoId(info.getId());
			info.setTcdeContentList(contentList);
		}
		return tCdeInfoList;
	}

	@Override
	public TCdeContent getContent(Long contentId) {
		TCdeContent content = tCdeContentRepo.findOne(contentId);
		return content;
	}

	@Override
	public TCdeInfo saveCode(TCdeInfo info) {
		info = tCdeInfoRepo.save(info);
		return info;
	}
	
	@Override
	public TCdeContent saveContent(TCdeContent content) {
		content = tCdeContentRepo.save(content);
		return content;
	}

	@Override
	public String deleteContent(Long contentId) {
		tCdeContentRepo.delete(contentId);
		return null;
	}

}

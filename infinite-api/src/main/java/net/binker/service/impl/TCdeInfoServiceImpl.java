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
		return tCdeInfoList;
	}

	@Override
	public TCdeInfo getCode(Long infoId) {
		TCdeInfo tCdeInfo = tCdeInfoRepo.findOne(infoId);
		TCdeContent tCdeContent = tCdeContentRepo.findOne(tCdeInfo.getContentId());
		tCdeInfo.setTcdeContent(tCdeContent);
		return tCdeInfo;
	}

	@Override
	public TCdeInfo saveCode(TCdeInfo info) {
		TCdeContent tCdeContent = tCdeContentRepo.save(info.getTcdeContent());
		info.setContentId(tCdeContent.getId());
		info = tCdeInfoRepo.save(info);
		return info;
	}

	@Override
	public String deleteCode(Long infoId) {
		TCdeInfo tCdeInfo = tCdeInfoRepo.findOne(infoId);
		tCdeContentRepo.delete(tCdeInfo.getContentId());
		tCdeInfoRepo.delete(infoId);
		return null;
	}

}

package net.binker.service.impl;

import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;
import net.binker.service.CodeService;
import net.binker.service.repo.TCdeContentRepo;
import net.binker.service.repo.TCdeInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {

	@Autowired
	private TCdeInfoRepo tCdeInfoRepo;
	
	@Autowired
	private TCdeContentRepo tCdeContentRepo;

	/**
	 * 功能：获取我的所有代码
	 * */
	@Override
	public List<TCdeInfo> getMyCodeList(Long custId) {
		List<TCdeInfo> tCdeInfoList = tCdeInfoRepo.findByCustId(custId);
		return tCdeInfoList;
	}

	/**
	 * 功能：获取代码信息+内容
	 * */
	@Override
	public TCdeInfo getCode(Long infoId) {
		TCdeInfo tCdeInfo = tCdeInfoRepo.findOne(infoId);
		TCdeContent tCdeContent = tCdeContentRepo.findOne(tCdeInfo.getContentId());
		tCdeInfo.setTcdeContent(tCdeContent);
		return tCdeInfo;
	}

	/**
	 * 功能：保存代码
	 * */
	@Override
	public TCdeInfo saveCode(TCdeInfo info) {
		TCdeContent tCdeContent = tCdeContentRepo.save(info.getTcdeContent());
		info.setContentId(tCdeContent.getId());
		info = tCdeInfoRepo.save(info);
		return info;
	}

	/**
	 * 功能：删除代码
	 * */
	@Override
	public String deleteCode(TCdeInfo info) {
		tCdeContentRepo.delete(info.getContentId());
		tCdeInfoRepo.delete(info.getId());
		return null;
	}

}

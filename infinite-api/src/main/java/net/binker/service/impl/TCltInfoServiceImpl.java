package net.binker.service.impl;

import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TCltInfo;
import net.binker.service.TCltInfoService;
import net.binker.service.repo.TAtcInfomationRepo;
import net.binker.service.repo.TCltInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TCltInfoServiceImpl implements TCltInfoService {

	@Autowired
	private TCltInfoRepo tCltInfoRepo;
	
	@Autowired
	private TAtcInfomationRepo tAtcInfomationRepo;
	
	@Override
	public String collect(String type,Long id,Long custId) {
		//获取文章信息
		TAtcInfomation atcInfo = tAtcInfomationRepo.findOne(id);
		//存入收藏表
		TCltInfo cltInfo = new TCltInfo();
		cltInfo.setAtcTitle(atcInfo.getTitleInfo());
		cltInfo.setAtcId(atcInfo.getId());
		cltInfo.setCustId(custId);
		cltInfo.setAtcContentId(atcInfo.getContentId());
		cltInfo.setType(type);
		tCltInfoRepo.save(cltInfo);
		//更新个人记录-收藏数+1
		
		return null;
	}

	@Override
	public List<TCltInfo> getMyCltList(Long custId) {
		List<TCltInfo> tCltInfoList = tCltInfoRepo.findByCustId(custId);
		return tCltInfoList;
	}

	@Override
	public TCltInfo getCollectInfo(String type, Long id, Long custId) {
		TCltInfo cltInfo = new TCltInfo();
		cltInfo = tCltInfoRepo.findInfo(type,id,custId);
		return cltInfo;
	}
	
}

package net.binker.service.impl;

import net.binker.config.Const;
import net.binker.entity.model.*;
import net.binker.service.TopicService;
import net.binker.service.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {
	
	@Autowired
	private TTpcInfomationRepo tTpcInfomationRepo;

	@Autowired
	private TTpcZoneRepo tTpcZoneRepo;

	@Override
	public List<TTpcInfomation> getPublishTopicList(){
		List<TTpcInfomation> topicList = tTpcInfomationRepo.findByPublish();
		return topicList;
	}

	@Override
	public List<TTpcZone> getTopicZoneList(Long infoId){
		List<TTpcZone> zoneList = tTpcZoneRepo.findByInfoId(infoId);
		return zoneList;
	}

	@Override
	public TTpcInfomation getTopicInfo(Long id){
		TTpcInfomation info = tTpcInfomationRepo.findOne(id);
		return info;
	}

	@Override
	public String submitTopic(TTpcInfomation lc){
		lc.setPublishStatus(Const.ATC_INFO_PUBLISH_STATUS_NO);
		tTpcInfomationRepo.save(lc);
		return null;
	}

	/*
	 *搜索结果方式1:最原始方案，在语句中用like搜索
	 * */
	@Override
	public List<TTpcInfomation> getSearchResult(String filter){
		List<TTpcInfomation> resultList = tTpcInfomationRepo.findSearchResult(filter);
		return resultList;
	}

}

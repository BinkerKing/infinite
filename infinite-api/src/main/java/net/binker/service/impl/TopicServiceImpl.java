package net.binker.service.impl;

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

}

package net.binker.service;

import net.binker.entity.model.*;

import java.util.List;

public interface TopicService {

	public List<TTpcInfomation> getPublishTopicList();

	public List<TTpcZone> getTopicZoneList(Long infoId);

	public TTpcInfomation getTopicInfo(Long id);

	public String submitTopic(TTpcInfomation lc);
	
}
package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TTpcInfomation;
import net.binker.entity.model.TTpcZone;
import net.binker.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TopicController")
public class TopicController {
	
	@Autowired
	public TopicService topicService;
	
	@RequestMapping(value = "/getPublishTopicList", method = RequestMethod.GET)
	public Resp getPublishTopicList(){
		List<TTpcInfomation> tTpcInfomationList = topicService.getPublishTopicList();
		return new Resp(tTpcInfomationList, CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getTopicZoneList", method = RequestMethod.GET)
	public Resp getTopicZoneList(@RequestParam("infoId") Long infoId){
		List<TTpcZone> zoneList = topicService.getTopicZoneList(infoId);
		return new Resp(zoneList, CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getTopicInfo", method = RequestMethod.GET)
	public Resp getTopicInfo(@RequestParam("id") Long id){
		TTpcInfomation info = topicService.getTopicInfo(id);
		return new Resp(info, CodeDef.SUCCESS);
	}
}

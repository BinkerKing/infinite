package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TAskInfomation;
import net.binker.service.TAskInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/MyAskController")
public class MyAskController {
	
	@Autowired
	public TAskInfomationService tAskInfomationService;
	
	@RequestMapping(value = "/getMyAskList", method = RequestMethod.POST)
	public Resp getMyAskList(@RequestBody SearchParam param){
		List<TAskInfomation> tAskInfomationList = tAskInfomationService.getMyAskList(param.getAuthorId(),param.getStatus(),param.getSearch(),param.getLable());
		return new Resp(tAskInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveAsk", method = RequestMethod.POST)
	public Resp saveAsk(@RequestBody TAskInfomation lc){
		tAskInfomationService.saveAsk(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveAskInfo", method = RequestMethod.POST)
	public Resp saveAskInfo(@RequestBody TAskInfomation lc){
		tAskInfomationService.saveAskInfo(lc);
		return new Resp(CodeDef.SUCCESS);
	}
}

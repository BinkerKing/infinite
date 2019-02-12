package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TAtcInfomation;
import net.binker.service.TAtcInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/AtcController")
public class AtcController {
	
	@Autowired
	public TAtcInfomationService tAtcInfomationService;
	
	@RequestMapping(value = "/getAtcView", method = RequestMethod.GET)
	public Resp getMyAtcView(@RequestParam("id") Long id){
		TAtcInfomation tAtcInfomation = tAtcInfomationService.getMyAtcView(id);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getAtcList", method = RequestMethod.POST)
	public Resp getAtcList(@RequestBody SearchParam param){
		List<TAtcInfomation> tAtcInfomationList = tAtcInfomationService.getAtcList(param.getStatus(),param.getSearch(),param.getLable());
		return new Resp(tAtcInfomationList,CodeDef.SUCCESS);
	}
	
}

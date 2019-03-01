package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.service.TSysTmplinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TmplController")
public class TmplController {
	
	@Autowired
	public TSysTmplinfoService tAtcInfomationService;
	
	@RequestMapping(value = "/getTmplList", method = RequestMethod.POST)
	public Resp getMyAtcList(@RequestBody SearchParam param){
		List<TSysTmplinfo> tAtcInfomationList = tAtcInfomationService.getMyAtcList(param.getAuthorId(),param.getStatus(),param.getSearch(),param.getLable());
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveTmpl", method = RequestMethod.POST)
	public Resp saveAtc(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.saveAtc(lc);
		return new Resp(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/deleteTmpl", method = RequestMethod.POST)
	public Resp deleteAtc(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.deleteAtc(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getOneTmpl", method = RequestMethod.GET)
	public Resp getMyAtcView(@RequestParam("id") Long id){
		TSysTmplinfo tAtcInfomation = tAtcInfomationService.getMyAtcView(id);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}

}

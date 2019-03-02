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
	public Resp getMyAtcList(){
		List<TSysTmplinfo> tAtcInfomationList = tAtcInfomationService.getTmplList();
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveTmpl", method = RequestMethod.POST)
	public Resp savTmpl(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.saveTmpl(lc);
		return new Resp(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/deleteTmpl", method = RequestMethod.POST)
	public Resp deleteTmpl(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.deleteTmpl(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getOneTmpl", method = RequestMethod.GET)
	public Resp getOneTmpl(@RequestParam("id") Long id){
		TSysTmplinfo tAtcInfomation = tAtcInfomationService.getOneTmpl(id);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}

}

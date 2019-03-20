package net.binker.web.controller;

import com.alibaba.fastjson.JSONObject;
import net.binker.config.CodeDef;
import net.binker.core.entity.PageQuery;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TSysTmplcontent;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.service.TSysTmplinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TmplController")
public class TmplController {
	
	@Autowired
	public TSysTmplinfoService tAtcInfomationService;
	
	@RequestMapping(value = "/getTmplList", method = RequestMethod.POST)
	public Resp getTmplList(){
		List<TSysTmplinfo> tAtcInfomationList = tAtcInfomationService.getTmplList();
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveTmpl", method = RequestMethod.POST)
	public Resp saveTmpl(@RequestBody TSysTmplinfo lc){
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

	@RequestMapping(value = "/getMore", method = RequestMethod.GET)
	public JSONObject test(@ModelAttribute PageQuery query, @ModelAttribute TSysTmplinfo lc) {
		Page<TSysTmplinfo> page = tAtcInfomationService.findAllPage(query,lc);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total",page.getTotalElements());
		jsonObject.put("rows",page.getContent());
		return jsonObject;
	}

	@RequestMapping(value = "/getPublishAtcTmpl", method = RequestMethod.GET)
	public Resp getPublishAtcTmpl(){
		List<TSysTmplinfo> tAtcInfomationList = tAtcInfomationService.getPublishAtcTmpl();
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getTmplContent", method = RequestMethod.GET)
	public Resp getTmplContent(@RequestParam("id") Long id){
		TSysTmplcontent tSysTmplcontent = tAtcInfomationService.getTmplContent(id);
		if(tSysTmplcontent == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tSysTmplcontent,CodeDef.SUCCESS);
	}

}

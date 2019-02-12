package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TCdeContent;
import net.binker.entity.model.TCdeInfo;
import net.binker.service.TCdeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/TCdeInfoController")
public class TCdeInfoController {
	
	@Autowired
	public TCdeInfoService tCdeInfoService;
	
	//获取所有CodeList
	@RequestMapping(value = "/getCodeList", method = RequestMethod.GET)
	public Resp getCodeList(@RequestParam("custId") Long custId){
		List<TCdeInfo> codeList = tCdeInfoService.getCodeList(custId);
		return new Resp(codeList, CodeDef.SUCCESS);
	}
	
	//获取Content
	@RequestMapping(value = "/getContent", method = RequestMethod.GET)
	public Resp getContent(@RequestParam("contentId") Long contentId){
		TCdeContent content = tCdeInfoService.getContent(contentId);
		return new Resp(content,CodeDef.SUCCESS);
	}
	
	//保存信息
	@RequestMapping(value = "/saveCode", method = RequestMethod.POST)
	public Resp saveCode(@RequestBody TCdeInfo lg){
		TCdeInfo info = tCdeInfoService.saveCode(lg);
		return new Resp(info,CodeDef.SUCCESS);
	}
	
	//保存信息
	@RequestMapping(value = "/saveContent", method = RequestMethod.POST)
	public Resp saveContent(@RequestBody TCdeContent lg){
		TCdeContent content = tCdeInfoService.saveContent(lg);
		return new Resp(content,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/deleteContent", method = RequestMethod.GET)
	public Resp deleteContent(@RequestParam("contentId") Long contentId){
		tCdeInfoService.deleteContent(contentId);
		return new Resp(CodeDef.SUCCESS);
	}
}

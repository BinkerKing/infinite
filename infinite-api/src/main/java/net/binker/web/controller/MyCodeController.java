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
@RequestMapping(value = "/MyCodeController")
public class MyCodeController {
	
	@Autowired
	public TCdeInfoService tCdeInfoService;
	
	//获取我的所有CodeList
	@RequestMapping(value = "/getCodeList", method = RequestMethod.GET)
	public Resp getCodeList(@RequestParam("custId") Long custId){
		List<TCdeInfo> codeList = tCdeInfoService.getCodeList(custId);
		return new Resp(codeList, CodeDef.SUCCESS);
	}
	
	//获取Code
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public Resp getContent(@RequestParam("infoId") Long infoId){
		TCdeInfo tCdeInfo = tCdeInfoService.getCode(infoId);
		return new Resp(tCdeInfo,CodeDef.SUCCESS);
	}
	
	//保存信息
	@RequestMapping(value = "/saveCode", method = RequestMethod.POST)
	public Resp saveCode(@RequestBody TCdeInfo lg){
		TCdeInfo info = tCdeInfoService.saveCode(lg);
		return new Resp(info,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/deleteContent", method = RequestMethod.GET)
	public Resp deleteContent(@RequestParam("infoId") Long infoId){
		tCdeInfoService.deleteCode(infoId);
		return new Resp(CodeDef.SUCCESS);
	}
}

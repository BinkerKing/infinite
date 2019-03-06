package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TCdeInfo;
import net.binker.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/CodeController")
public class CodeController {
	
	@Autowired
	public CodeService codeService;

	/**
	 * 参数：客户id
	 * 功能：获取我的所有代码
	 * */
	@RequestMapping(value = "/getMyCodeList", method = RequestMethod.GET)
	public Resp getMyCodeList(@RequestParam("custId") Long custId){
		List<TCdeInfo> codeList = codeService.getMyCodeList(custId);
		return new Resp(codeList, CodeDef.SUCCESS);
	}

	/**
	 * 参数：代码ID
	 * 功能：获取代码信息+内容
	 * */
	@RequestMapping(value = "/getCode", method = RequestMethod.GET)
	public Resp getContent(@RequestParam("infoId") Long infoId){
		TCdeInfo tCdeInfo = codeService.getCode(infoId);
		return new Resp(tCdeInfo,CodeDef.SUCCESS);
	}

	/**
	 * 参数：代码信息+内容
	 * 功能：保存代码
	 * */
	@RequestMapping(value = "/saveCode", method = RequestMethod.POST)
	public Resp saveCode(@RequestBody TCdeInfo lg){
		TCdeInfo info = codeService.saveCode(lg);
		return new Resp(info,CodeDef.SUCCESS);
	}

	/**
	 * 参数：代码信息
	 * 功能：删除代码
	 * */
	@RequestMapping(value = "/deleteCode", method = RequestMethod.POST)
	public Resp deleteCode(@RequestBody TCdeInfo lg){
		codeService.deleteCode(lg);
		return new Resp().setCode(CodeDef.SUCCESS);
	}
	
}

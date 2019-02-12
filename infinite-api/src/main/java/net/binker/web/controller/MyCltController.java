package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TCltInfo;
import net.binker.service.TCltInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/MyCltController")
public class MyCltController {
	
	@Autowired
	public TCltInfoService tCltInfoService;
	
	@RequestMapping(value = "/getMyAskList", method = RequestMethod.POST)
	public Resp getMyAskList(@RequestParam("custId") Long custId){
		List<TCltInfo> cltInfoList = tCltInfoService.getMyCltList(custId);
		return new Resp(cltInfoList,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/collect", method = RequestMethod.GET)
	public Resp collect(@RequestParam("type") String type,@RequestParam("id") Long id,@RequestParam("custId") Long custId){
		tCltInfoService.collect(type,id,custId);
		return new Resp().setCode(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getCollectInfo", method = RequestMethod.GET)
	public Resp getCollectInfo(@RequestParam("type") String type,@RequestParam("id") Long id,@RequestParam("custId") Long custId){
		TCltInfo cltInfo = tCltInfoService.getCollectInfo(type,id,custId);
		return new Resp(cltInfo).setCode(CodeDef.SUCCESS);
	}
}

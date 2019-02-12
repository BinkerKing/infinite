package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TLabGrpManager;
import net.binker.entity.model.TLabTagManager;
import net.binker.service.TLabManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/tLabManager")
public class TLabManagerController {
	
	@Autowired
	public TLabManagerService tLabManagerService;
	
	@RequestMapping(value = "/findTLabMap", method = RequestMethod.POST)
	public Resp findTLabMap(@RequestBody SearchParam param){
		Map<String, List<TLabTagManager>> tagManagerMap = tLabManagerService.findLabTagManagerMap();
		return new Resp(tagManagerMap,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveGrp", method = RequestMethod.POST)
	public Resp saveGrp(@RequestBody TLabGrpManager lg){
		tLabManagerService.saveGrp(lg);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveTag", method = RequestMethod.POST)
	public Resp saveTag(@RequestBody TLabTagManager lt){
		tLabManagerService.saveTag(lt);
		return new Resp(CodeDef.SUCCESS);
	}
}

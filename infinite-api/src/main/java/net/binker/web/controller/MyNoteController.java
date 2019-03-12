package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.*;
import net.binker.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/MyNoteController")
public class MyNoteController {
	
	@Autowired
	public TreeService treeService;

	@Autowired
	public AtcService atcService;

	@Autowired
	public AskService askService;

	@Autowired
	public CodeService codeService;
	
	@RequestMapping(value = "/getMyTreeInfomation", method = RequestMethod.GET)
	public Resp getMyTreeInfomation(@RequestParam("authorId") Long authorId){
		List<TTreInfomation> tTreInfomationList = treeService.getMyTreeInfomation(authorId);
		return new Resp(tTreInfomationList,CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getTreeStructure", method = RequestMethod.GET)
	public Resp getTreeStructure(@RequestParam("treeId") Long treeId){
		List<TTreStructure> tTreStuctureList = treeService.getTreeStructure(treeId);
		return new Resp(tTreStuctureList,CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/addTreeStructure", method = RequestMethod.POST)
	public Resp addTreeStructure(@RequestBody TTreStructure lc){
		TTreStructure structure = treeService.addTreeStructure(lc);
		return new Resp(structure,CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/updateStruct", method = RequestMethod.POST)
	public Resp updateStruct(@RequestBody TTreStructure lc){
		TTreStructure structure = treeService.updateStruct(lc);
		return new Resp(structure).setCode(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/deleteStruct", method = RequestMethod.GET)
	public Resp deleteStructruct(@RequestParam("id") Long id){
		treeService.deleteStruct(id);
		return new Resp().setCode(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getAtcList", method = RequestMethod.GET)
	public Resp getAtcList(@RequestParam("id") Long id,@RequestParam("treeId") Long treeId){
		List<TTreDetail> details = treeService.getAtcList(id,treeId);
		return new Resp(details).setCode(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getAtc", method = RequestMethod.GET)
	public Resp getAtc(@RequestParam("atcId") Long id,@RequestParam("type") Integer type){
		switch (type) {
			case 1 : //手记
				TAtcInfomation atc = atcService.getAtcView(id);
				return new Resp(atc).setCode(CodeDef.SUCCESS);
			case 2 : //猿问
				//TAskInfomation ask = askService.getAskView(id);
				//return new Resp(ask).setCode(CodeDef.SUCCESS);
				break;
			case 3 : //代码块
				TCdeInfo code = codeService.getCode(id);
				return new Resp(code).setCode(CodeDef.SUCCESS);
			default: break;
		}
		return new Resp().setCode(CodeDef.ERROR);
	}

}

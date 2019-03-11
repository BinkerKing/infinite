package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.*;
import net.binker.service.TCltInfoService;
import net.binker.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/MyNoteController")
public class MyNoteController {
	
	@Autowired
	public TreeService treeService;
	
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

}

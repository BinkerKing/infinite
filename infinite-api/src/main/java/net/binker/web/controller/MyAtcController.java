package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TAtcNote;
import net.binker.service.TAtcInfomationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/MyAtcController")
public class MyAtcController {
	
	@Autowired
	public TAtcInfomationService tAtcInfomationService;
	
	@RequestMapping(value = "/getMyAtcList", method = RequestMethod.POST)
	public Resp getMyAtcList(@RequestBody SearchParam param){
		List<TAtcInfomation> tAtcInfomationList = tAtcInfomationService.getMyAtcList(param.getAuthorId(),param.getStatus(),param.getSearch(),param.getLable());
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveAtc", method = RequestMethod.POST)
	public Resp saveAtc(@RequestBody TAtcInfomation lc){
		tAtcInfomationService.saveAtc(lc);
		return new Resp(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/deleteAtc", method = RequestMethod.POST)
	public Resp deleteAtc(@RequestBody TAtcInfomation lc){
		tAtcInfomationService.deleteAtc(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveAtcInfo", method = RequestMethod.POST)
	public Resp saveAtcInfo(@RequestBody TAtcInfomation lc){
		tAtcInfomationService.saveAtcInfo(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getMyAtcView", method = RequestMethod.GET)
	public Resp getMyAtcView(@RequestParam("id") Long id){
		TAtcInfomation tAtcInfomation = tAtcInfomationService.getMyAtcView(id);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/getCommentList", method = RequestMethod.GET)
	public Resp getCommentList(@RequestParam("atcId") Long atcId){
		List<TAtcComment> tAtcCommentList = tAtcInfomationService.getCommentList(atcId);
		return new Resp(tAtcCommentList,CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public Resp saveComment(@RequestBody TAtcComment lc){
		tAtcInfomationService.submitComment(lc);
		List<TAtcComment> tAtcCommentList = tAtcInfomationService.getCommentList(lc.getAtcId());
		return new Resp(tAtcCommentList).setCode(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveNote", method = RequestMethod.POST)
	public Resp saveNote(@RequestBody TAtcNote lc){
		tAtcInfomationService.saveNote(lc);
		return new Resp().setCode(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/udpateNotes", method = RequestMethod.POST)
	public Resp udpateNotes(@RequestBody List<TAtcNote> notes){
		tAtcInfomationService.updateNotes(notes);
		return new Resp().setCode(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
	public Resp deleteNote(@RequestParam("id") Long id){
		tAtcInfomationService.deleteNote(id);
		return new Resp().setCode(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getNotes", method = RequestMethod.GET)
	public Resp getNotes(@RequestParam("id") Long id,@RequestParam("custId") Long custId){
		List<TAtcNote> noteList = tAtcInfomationService.getNotes(id,custId);
		return new Resp(noteList).setCode(CodeDef.SUCCESS);
	}
}

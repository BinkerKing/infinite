package net.binker.web.controller;

import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TAtcNote;
import net.binker.service.AtcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/AtcController")
public class AtcController {
	
	@Autowired
	public AtcService atcService;

	/*******我的手记*********/
	/**
	 * 参数：无
	 * 功能：获取所有已发布手记列表
	 * */
	@RequestMapping(value = "/getMyAtcList", method = RequestMethod.GET)
	public Resp getAllShareAtcList(@RequestParam("authorId") Long authorId){
		List<TAtcInfomation> tAtcInfomationList = atcService.getMyAtcList(authorId);
		return new Resp(tAtcInfomationList,CodeDef.SUCCESS);
	}

	/*******共享手记*********/

	/**
	 * 参数：无
	 * 功能：获取所有已发布手记列表
	 * */
	@RequestMapping(value = "/getAllShareAtcList", method = RequestMethod.GET)
	public Resp getAllShareAtcList(){
		List<TAtcInfomation> tAtcInfomationList = atcService.getAllShareAtcList();
		return new Resp(tAtcInfomationList,CodeDef.SUCCESS);
	}

	/**
	 * 参数：手记信息ID
	 * 功能：通过手记信息ID，获取评论信息列表
	 * */
	@RequestMapping(value = "/getCommentList", method = RequestMethod.GET)
	public Resp getCommentList(@RequestParam("atcId") Long atcId){
		List<TAtcComment> tAtcCommentList = atcService.getCommentList(atcId);
		return new Resp(tAtcCommentList,CodeDef.SUCCESS);
	}

	/**
	 * 参数：评论信息
	 * 功能：保存评论信息
	 * */
	@RequestMapping(value = "/saveComment", method = RequestMethod.POST)
	public Resp saveComment(@RequestBody TAtcComment lc){
		atcService.submitComment(lc);
		List<TAtcComment> tAtcCommentList = atcService.getCommentList(lc.getAtcId());
		return new Resp(tAtcCommentList).setCode(CodeDef.SUCCESS);
	}

	/**
	 * 参数：搜索内容
	 * 功能：通过搜索内容，获取内容（全局搜索用）--用的like
	 * */
	@RequestMapping(value = "/getSearchResult", method = RequestMethod.GET)
	public Resp getSearchResult(@RequestParam("filter") String filter){
		List<TAtcInfomation> tAtcInfomationList = atcService.getSearchResult(filter);
		return new Resp(tAtcInfomationList,CodeDef.SUCCESS);
	}



	/*******通用*********/
	/**
	 * 参数：手记表ID
	 * 功能：通过ID获取手记信息
	 * */
	@RequestMapping(value = "/getAtcView", method = RequestMethod.GET)
	public Resp getAtcView(@RequestParam("atcId") Long atcId){
		TAtcInfomation tAtcInfomation = atcService.getAtcView(atcId);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}

	/**
	 * 参数：手记信息
	 * 功能：删除手记信息
	 * */
	@RequestMapping(value = "/deleteAtc", method = RequestMethod.POST)
	public Resp deleteAtc(@RequestBody TAtcInfomation lc){
		atcService.deleteAtc(lc);
		return new Resp(CodeDef.SUCCESS);
	}

	/**
	 * 参数：手记信息，不包含内容
	 * 功能：保存手记信息，不包含内容。
	 * */
	@RequestMapping(value = "/saveAtcInfo", method = RequestMethod.POST)
	public Resp saveAtcInfo(@RequestBody TAtcInfomation lc){
		atcService.saveAtcInfo(lc);
		return new Resp(CodeDef.SUCCESS);
	}


	/**
	 * 参数：手记信息，包含内容
	 * 功能：保存手记信息，包含内容
	 * */
	@RequestMapping(value = "/saveAtc", method = RequestMethod.POST)
	public Resp saveAtc(@RequestBody TAtcInfomation lc){
		TAtcInfomation info = atcService.saveAtc(lc);
		return new Resp(info,CodeDef.SUCCESS);
	}

	/**
	 * 参数：笔记信息
	 * 功能：保存笔记信息
	 * */
	@RequestMapping(value = "/saveNote", method = RequestMethod.POST)
	public Resp saveNote(@RequestBody TAtcNote lc){
		atcService.saveNote(lc);
		return new Resp().setCode(CodeDef.SUCCESS);
	}

	/**
	 * 参数：笔记列表
	 * 功能：保存笔记列表
	 * */
	@RequestMapping(value = "/udpateNotes", method = RequestMethod.POST)
	public Resp udpateNotes(@RequestBody List<TAtcNote> notes){
		atcService.updateNotes(notes);
		return new Resp().setCode(CodeDef.SUCCESS);
	}

	/**
	 * 参数：笔记ID
	 * 功能：删除笔记信息
	 * */
	@RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
	public Resp deleteNote(@RequestParam("id") Long id){
		atcService.deleteNote(id);
		return new Resp().setCode(CodeDef.SUCCESS);
	}

	/**
	 * 参数：文章ID+客户号
	 * 功能：获取该客户，该文章的笔记列表
	 * */
	@RequestMapping(value = "/getNotes", method = RequestMethod.GET)
	public Resp getNotes(@RequestParam("id") Long id,@RequestParam("custId") Long custId){
		List<TAtcNote> noteList = atcService.getNotes(id,custId);
		return new Resp(noteList).setCode(CodeDef.SUCCESS);
	}
	
}

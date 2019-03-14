package net.binker.service.impl;

import com.alibaba.fastjson.JSON;
import net.binker.config.Const;
import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcContent;
import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TAtcNote;
import net.binker.service.AtcService;
import net.binker.service.TreeService;
import net.binker.service.repo.TAtcCommentRepo;
import net.binker.service.repo.TAtcContentRepo;
import net.binker.service.repo.TAtcInfomationRepo;
import net.binker.service.repo.TAtcNoteRepo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AtcServiceImpl implements AtcService {
	
	@Autowired
	private TAtcInfomationRepo tAtcInfomationRepo;
	
	@Autowired
	private TAtcContentRepo tAtcContentRepo;

	@Autowired
	private TAtcCommentRepo tAtcCommentRepo;
	
	@Autowired
	private TAtcNoteRepo tAtcNoteRepo;

    @Autowired
    private TreeService treeService;

	/**
	 * 功能：获取所有我的文章列表
	 */
	@Override
	public List<TAtcInfomation> getMyAtcList(Long authorId) {
		return tAtcInfomationRepo.findByAuthorId(authorId);
	}

	/**
	 * 功能：保存手记信息和内容
	 */
	@Override
	public TAtcInfomation saveAtc(TAtcInfomation lc) {
		TAtcContent content = tAtcContentRepo.save(lc.getTatcContent());
		lc.setContentId(content.getId());
		lc.setCreateTime(new Date());
		lc.setValidFlag(Const.ATC_INFO_VALIDFLAG_YES);
		tAtcInfomationRepo.save(lc);
		return lc;
	}

	/**
	 * 功能：删除手记信息和内容
	 */
	@Override
	public String deleteAtc(TAtcInfomation lc) {
		tAtcInfomationRepo.delete(lc.getId());
		tAtcContentRepo.delete(lc.getContentId());
        treeService.deleteDetail(lc.getId(),1);
		return null;
	}

	/**
	 * 功能：单纯保存手记信息
	 */
	@Override
	public String saveAtcInfo(TAtcInfomation lc) {
		tAtcInfomationRepo.save(lc);
		return null;
	}

	/**
	 * 功能：获取所有已发布信息
	 */
	@Override
	public List<TAtcInfomation> getAllShareAtcList(){
		List<TAtcInfomation> tAtcInfomationList = tAtcInfomationRepo.findByPublish();
		return tAtcInfomationList;
	}

	/**
	 * 功能：获取文章下面所有评论
	 */
	@Override
	public List<TAtcComment> getCommentList(Long atcId){
		List<TAtcComment> tAtcCommentList = tAtcCommentRepo.findByAtcId(atcId);
		return tAtcCommentList;
	}

	/**
	 * 功能：提交评论
	 */
	@Override
	public String submitComment(TAtcComment lc){
		lc.setCreateTime(new Date());
		tAtcCommentRepo.save(lc);
		return null;
	}

	/**
	 * 功能：保存笔记
	 */
	@Override
	public String saveNote(TAtcNote note) {
		tAtcNoteRepo.save(note);
		return null;
	}

	/**
	 * 功能：删除笔记
	 */
	@Override
	public String deleteNote(Long id) {
		tAtcNoteRepo.delete(id);
		return null;
	}

	/**
	 * 功能：获取文章下，该用户的笔记列表
	 */
	@Override
	public List<TAtcNote> getNotes(Long id, Long custId) {
		return tAtcNoteRepo.findByAtcIdAndCustId(id,custId);
	}

	/**
	 * 功能：删除手记信息和内容
	 */
	@Override
	public String updateNotes(List<TAtcNote> notes) {
		tAtcNoteRepo.save(notes);
		return null;
	}

	/**
	 * 功能：搜索结果方式1:最原始方案，在语句中用like搜索
	 */
	@Override
	public List<TAtcInfomation> getSearchResult(String filter){
		List<TAtcInfomation> resultList = tAtcInfomationRepo.findSearchResult(filter);
		return resultList;
	}

	/**
	 * 功能：获取文章信息和内容
	 */
	@Override
	public TAtcInfomation getAtcView(Long id){
		TAtcInfomation info = tAtcInfomationRepo.findOne(id);
		info.setTatcContent(tAtcContentRepo.findOne(info.getContentId()));
		return info;
	}

	@Override
	public Long addEmptyAtc(TAtcInfomation info){
		TAtcContent content = new TAtcContent();
		content = tAtcContentRepo.save(content);
		info.setContentId(content.getId());
		info = tAtcInfomationRepo.save(info);
		return info.getId();
	}

}

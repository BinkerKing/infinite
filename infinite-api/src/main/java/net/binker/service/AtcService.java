package net.binker.service;

import java.util.List;

import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TAtcNote;

public interface AtcService {

	public List<TAtcInfomation> getMyAtcList(Long authorId);

	public String saveAtc(TAtcInfomation lc);

	public String deleteAtc(TAtcInfomation lc);
	
	public String saveAtcInfo(TAtcInfomation lc);
	
	public TAtcInfomation getAtcView(Long id);
	
	public List<TAtcInfomation> getAllShareAtcList();

	public List<TAtcComment> getCommentList(Long atcId);

	public String submitComment(TAtcComment lc);
	
	public String saveNote(TAtcNote note);
	
	public String deleteNote(Long id);
	
	public List<TAtcNote> getNotes(Long id, Long custId);
	
	public String updateNotes(List<TAtcNote> notes);

	public List<TAtcInfomation> getSearchResult(String filter);

	public Long addEmptyAtc(TAtcInfomation info);
	
}
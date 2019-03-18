package net.binker.service;

import net.binker.core.entity.PageQuery;
import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.entity.model.TAtcNote;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TSysTmplinfoService {

	public List<TSysTmplinfo> getTmplList();
	
	public String saveTmpl(TSysTmplinfo lc);

	public String deleteTmpl(TSysTmplinfo lc);
	
	public TSysTmplinfo getOneTmpl(Long id);

	public Page<TSysTmplinfo> findAllPage(PageQuery query,TSysTmplinfo tmplInfo);

}
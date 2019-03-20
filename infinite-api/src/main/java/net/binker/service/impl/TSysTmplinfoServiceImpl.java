package net.binker.service.impl;

import net.binker.core.entity.PageQuery;
import net.binker.entity.model.TSysTmplcontent;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.service.TSysTmplinfoService;
import net.binker.service.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TSysTmplinfoServiceImpl implements TSysTmplinfoService {
	
	@Autowired
	private TSysTmplinfoRepo tSysTmplinfoRepo;
	
	@Autowired
	private TSysTmplcontentRepo tSysTmplcontentRepo;

	@Override
	public List<TSysTmplinfo> getTmplList(){
		List<TSysTmplinfo> tmplinfolist = tSysTmplinfoRepo.findAll();
		return tmplinfolist;
	}

	@Override
	public String saveTmpl(TSysTmplinfo lc){
		TSysTmplcontent content = tSysTmplcontentRepo.save(lc.getTsysTmplcontent());
		lc.setContentId(content.getId());
		lc.setCreateTime(new Date());
		tSysTmplinfoRepo.save(lc);
		return null;
	}

	@Override
	public String deleteTmpl(TSysTmplinfo lc){
		tSysTmplinfoRepo.delete(lc.getId());
		tSysTmplcontentRepo.delete(lc.getContentId());
		return null;
	}

	@Override
	public TSysTmplinfo getOneTmpl(Long id){
		TSysTmplinfo info = tSysTmplinfoRepo.findOne(id);
		TSysTmplcontent content = tSysTmplcontentRepo.findOne(info.getContentId());
		info.setTsysTmplcontent(content);
		return info;
	}

	public Page<TSysTmplinfo> findAllPage(PageQuery query, TSysTmplinfo tmplInfo){
		PageRequest pageable = new PageRequest(query.getPindex(), query.getPcount(), query.getSortObj());
		Example<TSysTmplinfo> example = Example.of(tmplInfo, ExampleMatcher.matchingAll());
		Page<TSysTmplinfo> page = tSysTmplinfoRepo.findAll(example, pageable);
		return page;
	}

	public List<TSysTmplinfo> getPublishAtcTmpl(){
		return tSysTmplinfoRepo.findByPublish();
	}

	public TSysTmplcontent getTmplContent(Long id){
		return tSysTmplcontentRepo.findOne(id);
	}
}

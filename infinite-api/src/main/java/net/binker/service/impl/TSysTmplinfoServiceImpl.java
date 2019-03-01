package net.binker.service.impl;

import com.alibaba.fastjson.JSON;
import net.binker.config.Const;
import net.binker.entity.model.TAtcComment;
import net.binker.entity.model.TAtcContent;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.entity.model.TAtcNote;
import net.binker.service.TSysTmplinfoService;
import net.binker.service.repo.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TSysTmplinfoServiceImpl implements TSysTmplinfoService {
	
	@Autowired
	private TSysTmplinfoRepo tSysTmplinfoRepo;
	
	@Autowired
	private TSysTmplcontentRepo tSysTmplcontentRepo;
	

}

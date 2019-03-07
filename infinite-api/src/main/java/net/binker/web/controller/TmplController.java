package net.binker.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.binker.config.CodeDef;
import net.binker.core.entity.Resp;
import net.binker.entity.model.SearchParam;
import net.binker.entity.model.TSysTmplinfo;
import net.binker.entity.model.temp.TableResult;
import net.binker.entity.model.temp.TableTest;
import net.binker.service.TSysTmplinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/TmplController")
public class TmplController {
	
	@Autowired
	public TSysTmplinfoService tAtcInfomationService;
	
	@RequestMapping(value = "/getTmplList", method = RequestMethod.POST)
	public Resp getMyAtcList(){
		List<TSysTmplinfo> tAtcInfomationList = tAtcInfomationService.getTmplList();
		return new Resp(tAtcInfomationList, CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/saveTmpl", method = RequestMethod.POST)
	public Resp savTmpl(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.saveTmpl(lc);
		return new Resp(CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/deleteTmpl", method = RequestMethod.POST)
	public Resp deleteTmpl(@RequestBody TSysTmplinfo lc){
		tAtcInfomationService.deleteTmpl(lc);
		return new Resp(CodeDef.SUCCESS);
	}
	
	@RequestMapping(value = "/getOneTmpl", method = RequestMethod.GET)
	public Resp getOneTmpl(@RequestParam("id") Long id){
		TSysTmplinfo tAtcInfomation = tAtcInfomationService.getOneTmpl(id);
		if(tAtcInfomation == null)
			return new Resp().setCode(CodeDef.ERROR);
		return new Resp(tAtcInfomation,CodeDef.SUCCESS);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public JSONObject test(@RequestParam("limit") int limit, @RequestParam("offset") int offset)
	{
		JSONObject jsonObject = new JSONObject();
		List<TableTest> data = new ArrayList<TableTest>();
		TableTest test = new TableTest();
		test.setId(1L);
		test.setName("test1");
		test.setSex("man");
		data.add(test);
		Integer total = data.size();
		jsonObject.put("total",total);
		jsonObject.put("rows",data);
		return jsonObject;
	}

}

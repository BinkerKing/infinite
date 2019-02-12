package net.binker.service.impl;

import net.binker.entity.model.TLabGrpManager;
import net.binker.entity.model.TLabTagManager;
import net.binker.service.TLabManagerService;
import net.binker.service.repo.TLabGrpManagerRepo;
import net.binker.service.repo.TLabTagManagerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TLabManagerServiceImpl implements TLabManagerService {
	
	@Autowired
	private TLabGrpManagerRepo tLabGrpManagerRepo;
	
	@Autowired
	private TLabTagManagerRepo tLabTagManagerRepo;
	
	@Override
	public Map<String, List<TLabTagManager>> findLabTagManagerMap(){
		List<TLabGrpManager> tLabGrpManagers = tLabGrpManagerRepo.findAll();
		List<TLabTagManager> tLabTagManagers = tLabTagManagerRepo.findAll();
		Map<String, List<TLabTagManager>> tagsMap = new HashMap<String, List<TLabTagManager>>();
		if(tLabTagManagers.size() > 0){
			for (TLabTagManager tLabTagManager : tLabTagManagers) {
				List<TLabTagManager> list = null;
				Long groupId = tLabTagManager.getGroupId();
				String groupName = "";
				if(tLabGrpManagers.size()>0){
					for (TLabGrpManager tLabGrpManager : tLabGrpManagers) {
						if(groupId.equals(tLabGrpManager.getId())){
							groupName = tLabGrpManager.getGroupName();
							if(tagsMap.containsKey(groupName)){
								list = tagsMap.get(groupName);
								list.add(tLabTagManager);
							}else {
								list = new ArrayList<TLabTagManager>();
								list.add(tLabTagManager);
							}
						}
					}
				}
				tagsMap.put(groupName, list);
			}
			return tagsMap;
		}
		return null;
	}
	
	@Override
	public void saveGrp(TLabGrpManager lg) {
		tLabGrpManagerRepo.save(lg);
	}

	@Override
	public void saveTag(TLabTagManager lt) {
		tLabTagManagerRepo.save(lt);
	}

}

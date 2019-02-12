package net.binker.service;

import java.util.List;
import java.util.Map;

import net.binker.entity.model.TLabGrpManager;
import net.binker.entity.model.TLabTagManager;

public interface TLabManagerService {
	
	public Map<String, List<TLabTagManager>> findLabTagManagerMap();

	public void saveGrp(TLabGrpManager lg);
	
	public void saveTag(TLabTagManager lt);

}
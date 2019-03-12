package net.binker.service;

import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreInfomation;
import net.binker.entity.model.TTreStructure;

import java.util.List;

public interface TreeService {
    public List<TTreInfomation> getMyTreeInfomation(Long authorId);

    public List<TTreStructure> getTreeStructure(Long treeId);

    public TTreStructure addTreeStructure(TTreStructure lc);

    public TTreStructure updateStruct(TTreStructure lc);

    public String deleteStruct(Long id);

    public List<TTreDetail> getAtcList(Long id,Long treeId);

    public TTreDetail addDetail(TTreDetail lc);
}
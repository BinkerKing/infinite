package net.binker.service.impl;

import net.binker.entity.model.TAtcInfomation;
import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreInfomation;
import net.binker.entity.model.TTreStructure;
import net.binker.service.AtcService;
import net.binker.service.CustService;
import net.binker.service.TreeService;
import net.binker.service.repo.TTpcInfomationRepo;
import net.binker.service.repo.TTreDetailRepo;
import net.binker.service.repo.TTreInfomationRepo;
import net.binker.service.repo.TTreStructureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TTreInfomationRepo tTreInfomationRepo;

    @Autowired
    private TTreStructureRepo tTreStructureRepo;

    @Autowired
    private TTreDetailRepo tTreDetailRepo;

    @Autowired
    private AtcService atcService;

    @Override
    public List<TTreInfomation> getMyTreeInfomation(Long authorId){
        return tTreInfomationRepo.findByAuthorId(authorId);
    }

    @Override
    public List<TTreStructure> getTreeStructure(Long treeId){
        return tTreStructureRepo.findByTreeId(treeId);
    }

    @Override
    public List<TTreStructure> addTreeStructure(TTreStructure lc){
        lc = tTreStructureRepo.save(lc);

        List<TTreStructure> structureList = new ArrayList<TTreStructure>();
        structureList.add(lc);
        TTreStructure structure1 = new TTreStructure();
        structure1.setName("闲文");
        structure1.setOpen((byte)0);
        structure1.setParentId(lc.getId());
        structure1.setTreeId(lc.getTreeId());
        structure1.setDrag((byte)0);
        structure1 = tTreStructureRepo.save(structure1);
        structureList.add(structure1);

        return structureList;
    }

    @Override
    public TTreStructure updateStruct(TTreStructure lc){
        return tTreStructureRepo.save(lc);
    }

    @Override
    public String deleteStruct(Long id){
        tTreStructureRepo.delete(id);
        return null;
    }

    @Override
    public List<TTreDetail> getAtcList(Long id,Long treeId){
        List<TTreDetail> details =  tTreDetailRepo.findByMIdAndTreeId(id,treeId);
        return details;
    }

    @Override
    public TTreDetail addDetail(TTreDetail lc){
        TAtcInfomation info = new TAtcInfomation();
        info.setAuthorId(lc.getAuthorId());
        info.setAuthorName(lc.getAuthorName());
        info.setDescription(lc.getDescription());
        lc.setAtcId(atcService.addEmptyAtc(info));
        tTreDetailRepo.save(lc);
        return lc;
    }

    @Override
    public String deleteDetail(Long atcId,Integer type){
        tTreDetailRepo.deleteByAtcIdAndType(atcId,type);
        return null;
    }

    @Override
    public TTreDetail updateDetail(TTreDetail lc){
        return tTreDetailRepo.save(lc);
    }

    public String deleteDetail(TTreDetail lc){
        tTreDetailRepo.delete(lc.getId());
        return null;
    }
}

package net.binker.service.impl;

import net.binker.entity.model.TTreDetail;
import net.binker.entity.model.TTreInfomation;
import net.binker.entity.model.TTreStructure;
import net.binker.service.CustService;
import net.binker.service.TreeService;
import net.binker.service.repo.TTpcInfomationRepo;
import net.binker.service.repo.TTreDetailRepo;
import net.binker.service.repo.TTreInfomationRepo;
import net.binker.service.repo.TTreStructureRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreeServiceImpl implements TreeService {
    @Autowired
    private TTreInfomationRepo tTreInfomationRepo;

    @Autowired
    private TTreStructureRepo tTreStructureRepo;

    @Autowired
    private TTreDetailRepo tTreDetailRepo;

    @Override
    public List<TTreInfomation> getMyTreeInfomation(Long authorId){
        return tTreInfomationRepo.findByAuthorId(authorId);
    }

    @Override
    public List<TTreStructure> getTreeStructure(Long treeId){
        return tTreStructureRepo.findByTreeId(treeId);
    }

    @Override
    public TTreStructure addTreeStructure(TTreStructure lc){
        TTreStructure structure = tTreStructureRepo.save(lc);
        return structure;
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
}

package com.tensquare.spit.service;

import com.tensquare.common.util.IdWorker;
import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired

    private MongoTemplate mongoTemplate;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void save(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        //如果当前的添加的吐槽，有父节点，那么父节点的吐槽回复数要加1
        if(spit.getParentid()!=null && "".equals(spit.getParentid())){
            //说明是在别人的贴子下面做回复，不是新发的。所以它的回复数要加1
            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentid,int page,int size){
        Pageable pageable= PageRequest.of(page-1,size);
       return spitDao.findByParentid(parentid,pageable);
    }

    /**
     * 方式一：效率有问题
     * @param spitId
     * @return
     */
   /* public void thumbup(String spitId) {
        Spit spit=spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()==null?0:spit.getThumbup()+1);
        spitDao.save(spit);
    }*/

    /**
     * 方式二：使用原生mongo命令来实现自增
     * db.spit.update({"_id":"1"},{$inc:{thumbup: NumberInt(1)}})
     * @param spitId
     */
    public void thumbup(String spitId) {
        Query query=new Query();  //指定条件的
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }
}




























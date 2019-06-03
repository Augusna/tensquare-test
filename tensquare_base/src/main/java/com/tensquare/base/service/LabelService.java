package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import com.tensquare.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;

    /**
     * idWorker有红线，说明不再容器里面。
     * 在启动类上加@Bean就可以
     */
    @Autowired
    private IdWorker idWorker;


    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
        return labelDao.findById(id).get();
    }

    public void save(Label label){
        //使用分布式生成id
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }

    public void update(Label label){
        labelDao.save(label);
    }
    public  void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
       return labelDao.findByAuto(label);
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
      return labelDao.findByPage(label,page,size);
    }
}















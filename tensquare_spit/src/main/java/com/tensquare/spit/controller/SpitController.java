package com.tensquare.spit.controller;

import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/")
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @PostMapping
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @PostMapping("/{spitId}")
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @DeleteMapping("/{spitId}")
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }
    @GetMapping("/comment/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page,@PathVariable int size){
         Page<Spit> pagedata= spitService.findByParentId(parentid, page, size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(pagedata.getTotalElements(),pagedata.getContent()));
    }


    @GetMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //判断用户是否已经点赞，需要redis依赖；
        //判断当前用户是否已经点赞
        if(redisTemplate.opsForValue().get("thumbup_"+spitId)!=null){
            return new Result(true,StatusCode.REPERROR,"不能重复点赞");
        };
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+spitId,spitId);
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}














































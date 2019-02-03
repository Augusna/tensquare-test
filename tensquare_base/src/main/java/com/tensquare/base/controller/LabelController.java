package com.tensquare.base.controller;

import com.netflix.discovery.converters.Auto;
import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import com.tensquare.common.entity.PageResult;
import com.tensquare.common.entity.Result;
import com.tensquare.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable String labelId){
        System.out.println("获取头信息"+request.getHeader("Authorization"));
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"添加成功");
    }

    @PutMapping(value = "/{labelId}")
    public Result update(@RequestBody Label label,@PathVariable String labelId){
        label.setId(labelId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @DeleteMapping(value = "/{labelId}")
    public Result deleteById(@PathVariable String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    @PostMapping("/search")
   // public Result findSearch(@RequestBody Map map)
    public Result findSearch(@RequestBody Label label){  //JSON既可以转成对象，也可以转成Map
        List<Label> labelList= labelService.findSearch(label);
        return new Result(true, StatusCode.OK,"查询成功",labelList);
    }


    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
        Page<Label> pageData = labelService.pageQuery(label,page,size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<>(pageData.getTotalElements(),pageData.getContent()));
    }








}

package com.tensquare.qa.test;

import com.tensquare.qa.pojo.Problem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IPProjectTest {
    @RequestMapping("/retuser")
    public Problem returnProblem(){
        return new Problem("1","标题","content",null,null,"userid","昵称",0l,0l,0l,null,null,null);
    }
}

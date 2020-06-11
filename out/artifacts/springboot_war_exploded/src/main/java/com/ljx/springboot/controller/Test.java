package com.ljx.springboot.controller;

import com.ljx.springboot.model.Users;
import com.ljx.springboot.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Test {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/add")
    public ModelAndView insertUser(@RequestParam("uname") String uname, @RequestParam("uname") String upwd){
        Users users = new Users();
        users.setUname(uname);
        users.setUpwd(upwd);
        int id = usersService.insert(users);
        Map map = new HashMap();
        if (id>0){
            map.put("code",1);
            map.put("message","添加成功");
        }else {
            map.put("code",0);
            map.put("message","添加失败");
        }

        return new ModelAndView("log",map);
    }

}

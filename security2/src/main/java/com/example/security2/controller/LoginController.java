package com.example.security2.controller;

import com.example.security2.dto.RestResult;
import com.example.security2.filter.BeforeLoginFilter;
import com.example.security2.util.CommonUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/home")
public class LoginController {
    @RequestMapping(value="/login")
    public Object login(String name,String password){
        if ("sjy".equals(name) && "123456".equals(password)) {
            String uuid = CommonUtil.get32UUID();
            Object token = CommonUtil.addUser(name, uuid);
            return new RestResult(token);
        }else {
            return new RestResult("登陆失败");
        }

    }
}

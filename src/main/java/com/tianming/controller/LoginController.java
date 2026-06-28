package com.tianming.controller;

import com.tianming.pojo.Emp;
import com.tianming.pojo.LoginInfo;
import com.tianming.pojo.Result;
import com.tianming.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping
    public Result login(@RequestBody Emp emp)
    {
        log.info("用户登录,{}", emp);
        LoginInfo loginInfo = empService.login(emp);
        if(loginInfo != null)
        return Result.success(loginInfo);
        return Result.error("用户名或密码错误");
    }
}

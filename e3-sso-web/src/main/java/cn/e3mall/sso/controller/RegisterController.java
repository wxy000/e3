package cn.e3mall.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 王兴毅
 * @date 2018.08.11 11:02
 */
@Controller
public class RegisterController {

    @RequestMapping("/page/register")
    public String showRegister(){
        return "register";
    }
}

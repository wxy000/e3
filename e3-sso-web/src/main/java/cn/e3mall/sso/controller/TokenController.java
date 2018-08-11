package cn.e3mall.sso.controller;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.common.util.JsonUtils;
import cn.e3mall.sso.service.TokenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王兴毅
 * @date 2018.08.11 17:53
 */
@Controller
public class TokenController {

    @Autowired
    private TokenService tokenService;

    /*@RequestMapping("/user/token/{token}")
    @ResponseBody
    public String getUserByToken(@PathVariable String token, String callback){
        E3Result result = tokenService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)){
            return callback + "(" + JsonUtils.objectToJson(result) + ");";
        }
        return JsonUtils.objectToJson(result);
    }*/
    @RequestMapping("/user/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token, String callback){
        E3Result result = tokenService.getUserByToken(token);
        if (StringUtils.isNotBlank(callback)){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
        return result;
    }
}

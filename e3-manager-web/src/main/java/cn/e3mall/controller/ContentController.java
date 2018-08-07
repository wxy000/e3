package cn.e3mall.controller;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 王兴毅
 * @date 2018.08.06 21:04
 */
@Controller
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/content/save", method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContent(TbContent content){
        E3Result result = contentService.addContent(content);
        return result;
    }
}

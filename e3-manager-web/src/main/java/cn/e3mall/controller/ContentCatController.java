package cn.e3mall.controller;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.06 19:07
 */
@Controller
public class ContentCatController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/content/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(name = "id", defaultValue = "0") long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCatList(parentId);
        return list;
    }

    @RequestMapping(value = "/content/category/create", method = RequestMethod.POST)
    @ResponseBody
    public E3Result createContentCategory(Long parentId, String name){
        E3Result result = contentCategoryService.addContentCategory(parentId, name);
        return result;
    }
}

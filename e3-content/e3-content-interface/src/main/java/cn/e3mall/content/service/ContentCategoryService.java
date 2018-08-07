package cn.e3mall.content.service;

import cn.e3mall.common.pojo.EasyUITreeNode;
import cn.e3mall.common.util.E3Result;

import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.06 18:46
 */
public interface ContentCategoryService {
    List<EasyUITreeNode> getContentCatList(long parentId);
    E3Result addContentCategory(long parentId, String name);
}

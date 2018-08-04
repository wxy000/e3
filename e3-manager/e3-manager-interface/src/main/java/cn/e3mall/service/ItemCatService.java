package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.04 16:35
 */
public interface ItemCatService {
    List<EasyUITreeNode> getItemCatList(long parentId);
}

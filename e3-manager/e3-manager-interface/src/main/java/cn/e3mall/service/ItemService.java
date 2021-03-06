package cn.e3mall.service;

import cn.e3mall.common.pojo.EasyUIDataGridResult;
import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;

/**
 * @author 王兴毅
 * @date 2018.08.03 17:05
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    EasyUIDataGridResult getItemList(int page, int rows);
    E3Result addItem(TbItem tbItem, String desc);
    TbItemDesc getItemDescById(long itemId);
}

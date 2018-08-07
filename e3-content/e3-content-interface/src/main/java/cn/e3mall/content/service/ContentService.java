package cn.e3mall.content.service;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.pojo.TbContent;

import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.06 20:56
 */
public interface ContentService {
    E3Result addContent(TbContent content);
    List<TbContent> getContentListByCid(long cid);
}

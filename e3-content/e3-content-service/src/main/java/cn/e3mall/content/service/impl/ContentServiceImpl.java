package cn.e3mall.content.service.impl;

import cn.e3mall.common.util.E3Result;
import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 王兴毅
 * @date 2018.08.06 20:57
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Override
    public E3Result addContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);
        return E3Result.ok();
    }

    @Override
    public List<TbContent> getContentListByCid(long cid) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(cid);
        List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
        return list;
    }
}

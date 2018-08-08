package cn.e3mall.search.service;

import cn.e3mall.common.pojo.SearchResult;

/**
 * @author 王兴毅
 * @date 2018.08.08 10:11
 */
public interface SearchService {

    SearchResult search(String keyword, int page, int rows) throws Exception;
}

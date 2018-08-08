package cn.e3mall.search.dao;

import cn.e3mall.common.pojo.SearchItem;
import cn.e3mall.common.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 王兴毅
 * @date 2018.08.08 09:45
 */
@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResult searchResult(SolrQuery query) throws Exception{
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        long numFound = solrDocumentList.getNumFound();
        SearchResult result = new SearchResult();
        result.setRecordCount(numFound);
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<SearchItem> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : solrDocumentList){
            SearchItem item = new SearchItem();
            item.setId((String) solrDocument.get("id"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title;
            if (list != null && list.size() > 0){
                title = list.get(0);
            }else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            itemList.add(item);
        }
        result.setItemList(itemList);
        return result;
    }
}

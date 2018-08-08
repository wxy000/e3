package cn.e3mall.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author 王兴毅
 * @date 2018.08.07 19:50
 */
public class TestSolrj {

    @Test
    public void addDocument() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://39.108.111.94:8080/solr/collection1");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "doc01");
        document.addField("item_title", "测试商品01");
        document.addField("item_price", 1000);
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteDocument() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://39.108.111.94:8080/solr/collection1");
        solrServer.deleteById("doc01");
        //solrServer.deleteByQuery("id:doc01");
        solrServer.commit();
    }

    @Test
    public void queryIndex() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://39.108.111.94:8080/solr/collection1");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("*:*");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList documentList = response.getResults();
        System.out.println("总记录数：" + documentList.getNumFound());
        for (SolrDocument solrDocument : documentList){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
        }
    }

    @Test
    public void queryIndexFuza() throws Exception{
        SolrServer solrServer = new HttpSolrServer("http://39.108.111.94:8080/solr/collection1");
        SolrQuery query = new SolrQuery();
        //设置查询条件
        query.setQuery("手机");
        query.setStart(0);
        query.setRows(20);
        query.set("df", "item_title");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em>");
        query.setHighlightSimplePost("</em>");
        QueryResponse response = solrServer.query(query);
        SolrDocumentList documentList = response.getResults();
        System.out.println("总记录数：" + documentList.getNumFound());
        for (SolrDocument solrDocument : documentList){
            System.out.println(solrDocument.get("id"));
            Map<String, Map<String, List<String>>> highLighting = response.getHighlighting();
            List<String> list = highLighting.get(solrDocument.get("id")).get("item_title");
            String title;
            if (list != null && list.size() > 0){
                title = list.get(0);
            }else {
                title = (String) solrDocument.get("item_title");
            }
            System.out.println(title);
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
        }
    }
}

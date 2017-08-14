package com.itheima.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

/**
 * SOlrJ的
 * 添加
 * 修改
 * 删除
 * 查询
 * @author lx
 *
 */
public class SolrJDemo {

	
	//添加
	@Test
	public void testAdd() throws Exception {
		String baseURL = "http://localhost:8080/solr";
		SolrServer solrServer = new HttpSolrServer(baseURL);
		
		
		SolrInputDocument doc = new SolrInputDocument();
		doc.setField("id", 9);
		doc.setField("name", "赵丽颖");
		//添加
		solrServer.add(doc);
		solrServer.commit();
		
	}
	//删除
	@Test
	public void testDelete() throws Exception {
		String baseURL = "http://localhost:8080/solr";
		SolrServer solrServer = new HttpSolrServer(baseURL);

//		solrServer.deleteById("10");
		solrServer.deleteByQuery("name:我是林更新");
		solrServer.commit();
		
	}
	//查询  简单    
	@Test
	public void testQuery() throws Exception {
		String baseURL = "http://localhost:8080/solr";
		SolrServer solrServer = new HttpSolrServer(baseURL);
		//条件对象
		SolrQuery params = new SolrQuery();
		
		//条件  关键词 查询所有 
		params.set("q", "*:*");
		//执行查询
		QueryResponse response = solrServer.query(params);
		
		//结果集
		SolrDocumentList docs = response.getResults();
//		总条数
		long numFound = docs.getNumFound();
		System.out.println("总条数：" + numFound);
		
		for (SolrDocument doc : docs) {
			System.out.println("id:" + doc.get("id"));
			System.out.println("name:" + doc.get("name"));
		}
	}
}

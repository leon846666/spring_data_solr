package com.leon.test;

import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Crotch;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leon.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-solr.xml")
public class TestTemplate {
	
	@Autowired
	private SolrTemplate solrTemplate;
	
	@Test
	public void testAdd() {
		
			TbItem item=  new TbItem();
			
			item.setId(1l);
			item.setTitle("Huawei Mate10");
			item.setCategory("Smart Phone");
			item.setSeller("Huawei online store");
			item.setGoodsId(1l);
			item.setPrice(new BigDecimal(300.3));
			item.setImage("d:\\a.jpg");
			
			solrTemplate.saveBean(item);
			solrTemplate.commit();
			
	}
	
	
	@Test
	public void testUpdate() {
		
			TbItem item=  new TbItem();
			
			item.setId(1l);
			item.setTitle("Huawei Mate10");
			item.setCategory("Smart Phone");
			item.setSeller("Huawei online store");
			item.setGoodsId(3l);
			item.setPrice(new BigDecimal(300.3));
			item.setImage("d:\\a.jpg");
			
			solrTemplate.saveBean(item);
			solrTemplate.commit();
			
	}
	
	@Test
	public void findById(){
		TbItem item = solrTemplate.getById(1l, TbItem.class);
		System.out.println(item.getTitle());
		
	}

	
	@Test
	public void deleteById(){
		solrTemplate.deleteById("1");
		solrTemplate.commit();
		
	}
	
	@Test
	public void addList(){
		ArrayList list = new ArrayList();
		for (int i = 0; i < 100; i++) {
			TbItem item=  new TbItem();
			
			item.setId(i+1l);
			item.setTitle("Iphone"+i);
			item.setCategory("Smart Phone");
			item.setSeller("Apple store");
			item.setGoodsId(i+1l);
			item.setPrice(new BigDecimal(3000.3+i));
			item.setImage("d:\\a"+i+".jpg");
			
			list.add(item);
		}

		solrTemplate.saveBeans(list);
		solrTemplate.commit();	
		
	}
	
	@Test
	public void queryPagination(){
		//
		Query query= new SimpleQuery("*:*");
		
		ScoredPage<TbItem> pages = solrTemplate.queryForPage(query, TbItem.class);
		
		for (TbItem tbItem : pages.getContent()) {
			System.out.println(tbItem.getTitle());
			System.out.println(tbItem.getPrice());
		}
	} 
	
	@Test
	public void queryWithCriteria(){
		//
		Query query= new SimpleQuery("*:*");
		Criteria criteria=new Criteria("item_category").contains("Smart");
		criteria=criteria.and("item_title").contains("2");
		query.addCriteria(criteria);
		
		ScoredPage<TbItem> pages = solrTemplate.queryForPage(query, TbItem.class);
		
		for (TbItem tbItem : pages.getContent()) {
			System.out.println(tbItem.getTitle()+","+tbItem.getPrice());
		
		}
	} 
	
	@Test
	public void deleteAll(){
		Query query= new SimpleQuery("*:*");
		solrTemplate.delete(query);
		solrTemplate.commit();
	}
	
	
}

package com.leon.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leon.pojo.TbItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext-solr.xml")
public class TestTemplate {
	
	@Autowired
	private SolrTemplate sorlTemplate;
	
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
			
			sorlTemplate.saveBean(item);
			sorlTemplate.commit();
			
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
			
			sorlTemplate.saveBean(item);
			sorlTemplate.commit();
			
	}

}

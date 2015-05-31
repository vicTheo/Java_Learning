package compass.test;

import org.compass.core.Compass;
import org.compass.core.CompassHits;
import org.compass.core.CompassQuery;
import org.compass.core.CompassQueryBuilder;
import org.compass.core.CompassSession;
import org.compass.core.config.CompassConfiguration;
import org.junit.Test;

import compass.bean.Article;

public class compassTest {
	/*测试增 删 查 改
	 * 
	 * session.create(bean)无论id是否一样都会往索引库增加记录，id递增
	 * session。save(bean)如果id一样则会覆盖
	 */
    @Test
     //增
    public void testCreateIndex(){
    	CompassConfiguration compassConfiguration=new CompassConfiguration();
    	compassConfiguration.configure();
    	Compass compass=compassConfiguration.buildCompass();
    	CompassSession compassSession=compass.openSession();
    	Article article=new Article();
    	article.setId(2L);
    	article.setTitle("lucene");
    	article.setContent("lucene可以做很好的搜索引擎");
    	compassSession.create(article);
    	compassSession.close();
    	
    }
    @Test
    //查
   public void testSearchIndex(){
   	CompassConfiguration compassConfiguration=new CompassConfiguration();
   	compassConfiguration.configure();
   	Compass compass=compassConfiguration.buildCompass();
   	CompassSession compassSession=compass.openSession();
   	/**************************************/
   	//按通配符查询
   /*	CompassQueryBuilder queryBuilder=compass.queryBuilder();
   	CompassQuery query=queryBuilder.matchAll();
   	queryBuilder.wildcard("title", "l*");
   	query.attach(compassSession);
   	CompassHits hits=query.hits();*/
   	/**************************************/
   	CompassHits hits=compassSession.find("lucene");//此处为多字段查询
   	for(int i=0;i<hits.length();i++){
   		Article article=(Article) hits.data(i);
   		System.out.println(article.getContent());
   		System.out.println(article.getId());
   	}
   	compassSession.close();
   }
    @Test
    //删
    public void testDelete(){
    	CompassConfiguration compassConfiguration=new CompassConfiguration();
       	compassConfiguration.configure();
       	Compass compass=compassConfiguration.buildCompass();
       	CompassSession compassSession=compass.openSession();
       	Article article=compassSession.get(Article.class, 1L);
       	compassSession.delete(article);
       	compassSession.close();
    }
    @Test
    //改
    public void testUpdate(){
    	CompassConfiguration compassConfiguration=new CompassConfiguration();
       	compassConfiguration.configure();
       	Compass compass=compassConfiguration.buildCompass();
       	CompassSession compassSession=compass.openSession();
       	Article article=new Article();
    	article.setId(2L);
    	article.setTitle("lucene");
    	article.setContent("lucene可以做很好的搜索引擎");
    	compassSession.save(article);
    	compassSession.close();
    }
}

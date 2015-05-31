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
	/*������ ɾ �� ��
	 * 
	 * session.create(bean)����id�Ƿ�һ�����������������Ӽ�¼��id����
	 * session��save(bean)���idһ����Ḳ��
	 */
    @Test
     //��
    public void testCreateIndex(){
    	CompassConfiguration compassConfiguration=new CompassConfiguration();
    	compassConfiguration.configure();
    	Compass compass=compassConfiguration.buildCompass();
    	CompassSession compassSession=compass.openSession();
    	Article article=new Article();
    	article.setId(2L);
    	article.setTitle("lucene");
    	article.setContent("lucene�������ܺõ���������");
    	compassSession.create(article);
    	compassSession.close();
    	
    }
    @Test
    //��
   public void testSearchIndex(){
   	CompassConfiguration compassConfiguration=new CompassConfiguration();
   	compassConfiguration.configure();
   	Compass compass=compassConfiguration.buildCompass();
   	CompassSession compassSession=compass.openSession();
   	/**************************************/
   	//��ͨ�����ѯ
   /*	CompassQueryBuilder queryBuilder=compass.queryBuilder();
   	CompassQuery query=queryBuilder.matchAll();
   	queryBuilder.wildcard("title", "l*");
   	query.attach(compassSession);
   	CompassHits hits=query.hits();*/
   	/**************************************/
   	CompassHits hits=compassSession.find("lucene");//�˴�Ϊ���ֶβ�ѯ
   	for(int i=0;i<hits.length();i++){
   		Article article=(Article) hits.data(i);
   		System.out.println(article.getContent());
   		System.out.println(article.getId());
   	}
   	compassSession.close();
   }
    @Test
    //ɾ
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
    //��
    public void testUpdate(){
    	CompassConfiguration compassConfiguration=new CompassConfiguration();
       	compassConfiguration.configure();
       	Compass compass=compassConfiguration.buildCompass();
       	CompassSession compassSession=compass.openSession();
       	Article article=new Article();
    	article.setId(2L);
    	article.setTitle("lucene");
    	article.setContent("lucene�������ܺõ���������");
    	compassSession.save(article);
    	compassSession.close();
    }
}

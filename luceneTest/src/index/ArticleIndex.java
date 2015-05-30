package index;

import java.util.ArrayList;
import java.util.List;

import javabean.Article;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.util.Version;
import org.junit.Test;

import utils.DocumentUtiils;
import utils.LuceneUtils;

public class ArticleIndex {
	/**
	 * 1、索引库的增、删、改是由indexWriter来操作的
	 * 2、同一个时刻内，同一个索引库，只能允许一个indexWriter操作
	 * 3、当IndexWriter创建完成以后，indexwriter所指向的索引库就被占领了，只有当indexWriter.close时，才能释放锁的资源
	 * 4、当一个新的indexWriter想拥有索引库时，原来的indexWriter必须释放锁
	 * 5、只要索引库中存在write.lock文件，说明上锁了
	 * 6、indexWriter.close有两层含义：
	 *     *  关闭IO资源
	 *     *  释放锁
     */
	@Test
	public void testCreateIndex() throws Exception{
		Article article=new Article();
		article.setId(1L);
		article.setTitle("lucene可以做很好的搜索引擎");
		article.setContent("百度谷歌也是很强的搜索引擎");
		Document document=DocumentUtiils.article2documet(article);
		IndexWriter indexWriter=new IndexWriter(LuceneUtils.directory,LuceneUtils.analyzer,MaxFieldLength.LIMITED);
	    indexWriter.addDocument(document);
	    indexWriter.close();
	}
	
	@Test
	public void testSearchIndex() throws Exception{
		IndexSearcher indexSearcher=new IndexSearcher(LuceneUtils.directory);
		//多字段搜索
		QueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_30, new String[]{"title","content"}, LuceneUtils.analyzer);
		Query query=queryParser.parse("百度");
		TopDocs topDocs=indexSearcher.search(query, 1);
		ScoreDoc[] scoreDocs=topDocs.scoreDocs;
		List<Article> articleList=new ArrayList<Article>();
		for(ScoreDoc scoreDoc:scoreDocs){
			Document document=indexSearcher.doc(scoreDoc.doc);
			Article article=DocumentUtiils.document2article(document);
			articleList.add(article);
		}
		
		for(Article article:articleList){
			System.out.println(article.getId());
			System.out.println(article.getTitle());
			System.out.println(article.getContent());
		}
	}
	
	
	@Test
	public void testDelete() throws Exception{
		IndexWriter indexWriter=new IndexWriter(LuceneUtils.directory,LuceneUtils.analyzer, MaxFieldLength.LIMITED);
	    //indexWriter.deleteAll();//删除所有
		Term term=new Term("title","lucene");   //term为关键词对象
	   indexWriter.deleteDocuments(term);
	  indexWriter.close();
	
	}
	
	@Test
	//修改是先删除再创建
	public void testUpdate()throws Exception{
		IndexWriter indexWriter=new IndexWriter(LuceneUtils.directory,LuceneUtils.analyzer,MaxFieldLength.UNLIMITED);
		Article article=new Article();
		article.setId(1L);
		article.setTitle("可以做很好的搜索引擎");
		article.setContent("百度谷歌也是很强的搜索引擎");
		Document doc=DocumentUtiils.article2documet(article);
		Term term=new Term("title","lucene");
		indexWriter.updateDocument(term, doc);
		indexWriter.close();
	}
}

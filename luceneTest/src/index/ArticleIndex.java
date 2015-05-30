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

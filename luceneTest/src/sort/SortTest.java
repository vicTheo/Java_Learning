package sort;

import java.util.ArrayList;
import java.util.List;

import javabean.Article;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
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

public class SortTest {
	/**
	 * 相同的结构，相同的关键词 ，相关度得分一样
	 * 相同的结构，不同的关键词，相关度得分不一样，一般中文较高
	 *不同的结构，不同的关键词，出现次数越多，相关度得分越高
	 * document.setBoost(100);//可以按步长提高相关度得分
	 * @throws Exception
	 */
	@Test
	public void testCreateIndex() throws Exception{
		Article article=new Article();
		article.setId(101L);
		article.setTitle("lucene哈哈");
		article.setContent("百度谷歌也是很强的搜索引擎");
		Document document=DocumentUtiils.article2documet(article);
		IndexWriter indexWriter=new IndexWriter(LuceneUtils.directory,LuceneUtils.analyzer,MaxFieldLength.LIMITED);
	    document.setBoost(100);//可以按步长提高相关度得分
		indexWriter.addDocument(document);
	    indexWriter.close();
	}
	
	@Test
	public void testSearchIndex() throws Exception{
		IndexSearcher indexSearcher=new IndexSearcher(LuceneUtils.directory);
		//多字段搜索
		QueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_30, new String[]{"title","content"}, LuceneUtils.analyzer);
		Query query=queryParser.parse("lucene");
		TopDocs topDocs=indexSearcher.search(query, 25);
		ScoreDoc[] scoreDocs=topDocs.scoreDocs;
		List<Article> articleList=new ArrayList<Article>();
		for(ScoreDoc scoreDoc:scoreDocs){
			float score=scoreDoc.score;
			System.out.println(score);
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
	
}

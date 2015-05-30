package query;

import java.util.ArrayList;
import java.util.List;

import javabean.Article;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.MultiPhraseQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.PhraseQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.util.Version;
import org.junit.Test;

import utils.DocumentUtiils;
import utils.LuceneUtils;

public class QueryTest {
   
	/**
	 * 1关键字查询
	 * 2范围查询
	 * 3boolean查询
	 * 4查询所有文档
	 * 5通配符查询
	 * 6短语查询 
	 */
	public void showData(Query query) throws Exception{
		IndexSearcher indexSearcher=new IndexSearcher(LuceneUtils.directory);
	
		TopDocs topDocs=indexSearcher.search(query, 10);
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
	//关键字查询
	public void testTermQuer()throws Exception{
		Term term=new Term("title","lucene");
		Query query=new TermQuery(term);
		this.showData(query);
	}
	
	@Test
	//查询所有文档
	public void testMatchAllDocsQuery ()throws Exception{
		Query query=new MatchAllDocsQuery();
		this.showData(query);
	}
	@Test
	/*按通配符查询
	 *  ？表示但个字符
	 *  *表示多个字符
	 */
	public void testWildcardQuery()throws Exception{
		Term term=new Term("title","l?ce*");
		Query query=new WildcardQuery(term);
		this.showData(query);
	}
	
	@Test
	/*短语查询 
	 * 关键字必须针对同一属性
	 *  位置属性指的是分词后的位置（中华人民 中华 华人 人民）
	 */
	public void testPhraseQuery()throws Exception{
		Term term1=new Term("title","中华");
		Term term2=new Term("title","人民");
		PhraseQuery query=new PhraseQuery();
		query.add(term1,1);
		query.add(term2, 3);
		this.showData(query);
	}
	@Test
	//boolean查询
	public void testBooleanQuery()throws Exception{
		Term term1=new Term("title","中华");
		Term term2=new Term("title","人民");
		Term term3=new Term("title","中国人民");
		Query query1=new TermQuery(term1);
		Query query2=new TermQuery(term2);
		Query query3=new TermQuery(term3);
		
		BooleanQuery query=new BooleanQuery();
		query.add(query1, Occur.SHOULD);
		query.add(query2, Occur.SHOULD);
		query.add(query3, Occur.SHOULD);
		this.showData(query);
	}
	//范围查询
	@Test
	public void test() throws Exception{
	 Query query=NumericRangeQuery.newLongRange("id", 56L, 67L, true, true); 
	 this.showData(query);
	}
}

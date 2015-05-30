package highlighter;

import java.util.ArrayList;
import java.util.List;

import javabean.Article;

import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.junit.Test;

import utils.DocumentUtiils;
import utils.LuceneUtils;

public class HighLighterTest {
	@Test
	public void testHighLighter() throws Exception{
		IndexSearcher indexSearcher=new IndexSearcher(LuceneUtils.directory);
		//多字段搜索
		QueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_30, new String[]{"title","content"}, LuceneUtils.analyzer);
		Query query=queryParser.parse("lucene");
		/*****************************************************/
		//给关键字加上前缀和后缀
		Formatter formatter=new SimpleHTMLFormatter("<font color='red'>","/font>");
		//scorer封装了关键字
		Scorer scorer=new  QueryScorer(query);
		Highlighter highLighter=new Highlighter(formatter, scorer);
		//创建摘要
		Fragmenter framenter=new SimpleFragmenter(20);
		highLighter.setTextFragmenter(framenter);
		/*******************************************************/
		
		TopDocs topDocs=indexSearcher.search(query, 25);
		ScoreDoc[] scoreDocs=topDocs.scoreDocs;
		List<Article> articleList=new ArrayList<Article>();
		for(ScoreDoc scoreDoc:scoreDocs){
			Document document=indexSearcher.doc(scoreDoc.doc);
			Article article=DocumentUtiils.document2article(document);
			/*
			 * 分词器： 查找关键字
			 * 字段：哪个字段进行高亮
			 * 字段内容：把字段内容提取出来
			 */
			String title=highLighter.getBestFragment(LuceneUtils.analyzer,"title",document.get("title"));
			String content=highLighter.getBestFragment(LuceneUtils.analyzer,"content",document.get("content"));
			if(title!=null){article.setTitle(title);}
			if(content!=null){article.setContent(content);}
			
			articleList.add(article);
		}
		
		for(Article article:articleList){
			System.out.println(article.getId());
			System.out.println(article.getTitle());
			System.out.println(article.getContent());
		}
	}
	
}

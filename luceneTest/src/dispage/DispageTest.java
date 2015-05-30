package dispage;

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

public class DispageTest {
	@Test
	public void bathCreateIndex() throws Exception {
		IndexWriter indexWriter = new IndexWriter(LuceneUtils.directory,
				LuceneUtils.analyzer, MaxFieldLength.LIMITED);
		for (int i = 0; i <= 50; i++) {
			Article article = new Article();
			article.setId((long)i);
			article.setTitle("lucene可以做很好的搜索引擎");
			article.setContent("百度谷歌也是很强的搜索引擎");
			Document document = DocumentUtiils.article2documet(article);
			indexWriter.addDocument(document);
		}
		indexWriter.close();

	}
	
	public void dispage(int firstResult,int maxResult) throws Exception{
		IndexSearcher indexSearcher=new IndexSearcher(LuceneUtils.directory);
		QueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_30, new String[]{"title","content"}, LuceneUtils.analyzer);
		Query query=queryParser.parse("lucene");
		int a=25;
		TopDocs topDocs=indexSearcher.search(query, a);
		int totalHits=topDocs.totalHits;
		int count=Math.min(a,Math.min((maxResult+firstResult), totalHits));
		ScoreDoc[] scoreDocs=topDocs.scoreDocs;
		List<Article> articleList=new ArrayList<Article>();
		for(int i=firstResult;i<count;i++){
			Document document=indexSearcher.doc(scoreDocs[i].doc);
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
	public void test() throws Exception{
		this.dispage(0, 35);
	}
}

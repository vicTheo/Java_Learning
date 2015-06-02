package BBSAction;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Formatter;
import org.apache.lucene.search.highlight.Fragmenter;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.Scorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;

import utils.DocumentUtils;
import utils.LuceneConfig;
import utils.LuceneUtils;

import bean.Article;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ArticleAction extends ActionSupport implements ModelDriven<Article>{
	
	private Article article = new Article();
	private String queryString;//从页面上得到的关键字
	public String getQueryString() {
		return queryString;
	}
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}
	public Article getModel() {
		// TODO Auto-generated method stub
		return article;
	}
	public String listArticle() throws Exception{
		/*
		 * 查询所有的索引库中的数据
		 */
		List<Article> articleList = new ArrayList<Article>();
		if(LuceneConfig.directory!=null){
			IndexSearcher indexSearcher = new IndexSearcher(LuceneConfig.directory);
			Query query = new MatchAllDocsQuery();//查询索引库中所有的文档
			TopDocs topDocs = indexSearcher.search(query, 10);
			ScoreDoc[] scoreDocs = topDocs.scoreDocs;
			
			for(int i=0;i<scoreDocs.length;i++){
				Document document = indexSearcher.doc(scoreDocs[i].doc);
				Article article = DocumentUtils.documentToArticle(document);
				articleList.add(article);
			}
		}
		ActionContext.getContext().put("articleList", articleList);
		return "listTop";
	}
	
	public String saveArticle() throws Exception{
		IndexSearcher indexSearcher = new IndexSearcher(LuceneConfig.directory);
		Query query = new MatchAllDocsQuery();
		TopDocs topDocs = indexSearcher.search(query, 10);
		int i = topDocs.totalHits+1;
		IndexWriter indexWriter = LuceneUtils.getIndexWriter();
		article.setId(i);
		Document doc = DocumentUtils.articleToDocument(article);
		indexWriter.addDocument(doc);
		indexWriter.commit();
		return "toList";
	}
	
	public String queryById() throws Exception{
		IndexSearcher indexSearcher = new IndexSearcher(LuceneConfig.directory);
		Term term = new Term("id",article.getId().toString());
		Query query = new TermQuery(term);
		TopDocs topDocs = indexSearcher.search(query, 10);
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		List<Article> articleList = new ArrayList<Article>();
		for(int i=0;i<scoreDocs.length;i++){
			Document document = indexSearcher.doc(scoreDocs[i].doc);
			Article article = DocumentUtils.documentToArticle(document);
			articleList.add(article);
		}
		ActionContext.getContext().put("articleList", articleList);
		return "showResult";
	}
	
	public String search() throws Exception{
		IndexSearcher indexSearcher = new IndexSearcher(LuceneConfig.directory);
		QueryParser queryParser = new MultiFieldQueryParser(Version.LUCENE_30,new String[]{"title","content"},LuceneConfig.analyzer);
		Query query = queryParser.parse(this.queryString);
		TopDocs topDocs = indexSearcher.search(query, 10);
		/*************************************************************************************/
		Formatter formatter = new SimpleHTMLFormatter("<font color='red'>","</font>");
		//scorer告诉我们在什么地方进行高亮显示
		Scorer scorer = new QueryScorer(query);
		Highlighter highlighter = new Highlighter(formatter,scorer);
		Fragmenter fragmenter = new SimpleFragmenter(10);
		highlighter.setTextFragmenter(fragmenter);
		/*************************************************************************************/
		ScoreDoc[] scoreDocs = topDocs.scoreDocs;
		List<Article> articleList = new ArrayList<Article>();
		for(int i=0;i<scoreDocs.length;i++){
			Document document = indexSearcher.doc(scoreDocs[i].doc);
			Article article = DocumentUtils.documentToArticle(document);
			String highhighterContent = highlighter.getBestFragment(LuceneConfig.analyzer, "content", document.get("content"));
			String highhighterTitlte = highlighter.getBestFragment(LuceneConfig.analyzer, "title", document.get("title"));
			if(highhighterTitlte!=null){
				article.setTitle(highhighterTitlte);
			}
			if(highhighterContent!=null){
				article.setContent(highhighterContent);
			}
			articleList.add(article);
		}
		ActionContext.getContext().put("articleList", articleList);
		return "listTop";
	}
}

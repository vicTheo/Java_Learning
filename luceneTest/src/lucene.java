import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;


import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;
import org.junit.Test;


public class lucene {
	/**
	 * 1、把article对象存放在索引库中
	 * 2、根据关键词把对象从索引库中提取出来
	 * @throws IOException 
	 * @throws LockObtainFailedException 
	 * @throws CorruptIndexException 
	 */
	@Test
   public void testCreateIndex() throws CorruptIndexException, LockObtainFailedException, IOException{
	   /**
		 * 1、创建一个article对象，并且把信息存放进去
		 * 2、调用indexWriter的API把数据存放在索引库中
		 * 3、关闭indexWriter
		 */
	   //创建article
	   Article article = new Article();
	   article.setId(1L);
	   article.setTitle("lucene是好的搜索框架");
	   article.setContent("百度谷歌都是好的搜索引擎");
	   //创建索引库
	   Directory directory=FSDirectory.open(new File("./indexDir"));
	   //创建分词器
	   Analyzer analyzer= new StandardAnalyzer(Version.LUCENE_30);
	   
	   //创建indexwriter
         IndexWriter indexWriter = new IndexWriter(directory,analyzer, MaxFieldLength.UNLIMITED);	   
      //创建document
         Document document =new Document();
         Field idField=new Field("id",article.getId().toString(),Store.YES,Index.NOT_ANALYZED);
         Field titleField=new Field("title",article.getTitle(),Store.YES,Index.ANALYZED);
         Field contentField=new Field("content",article.getContent(),Store.YES,Index.ANALYZED);
         document.add(idField);
         document.add(titleField);
         document.add(contentField);
         //添加document到索引库
         indexWriter.addDocument(document);
         indexWriter.close();
   }
	//从lucene根据关键词检索
	@Test
	public void testSearchIndex() throws Exception{
		/**
		 * 1、创建一个 IndexSearch对象
		 * 2、调用search方法进行检索
		 * 3、输出内容
		 */
		//创建Directory 
		Directory directory=FSDirectory.open(new File("./indexDir"));
		//创建IndexSearcher
		IndexSearcher indexSearcher=new IndexSearcher(directory);
		//创建分词器
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_30);
		//创建query
		QueryParser queryParser=new QueryParser(Version.LUCENE_30, "title", analyzer);
		Query query=queryParser.parse("lucene");//关键词
		//搜索出前两条记录
		TopDocs topDocs=indexSearcher.search(query, 2);
		int count=topDocs.totalHits;  //根据关键词得出来总记录数
		ScoreDoc[] scoreDocs=topDocs.scoreDocs;
		List<Article> articleList=new ArrayList<Article>();
		for(ScoreDoc scoreDoc:scoreDocs){
			Float score=scoreDoc.score; //关键词得分
			int index=scoreDoc.doc;   //目录库中关键词索引
			
			Document document =indexSearcher.doc(index);
			//把document转换为article
			Article article=new Article();
			article.setId(Long.parseLong(document.get("id")));
			article.setTitle(document.get("title"));
			article.setContent(document.get("content"));
			articleList.add(article);
		}
		
		for(Article article:articleList){
			System.out.println(article.getId());
			System.out.println(article.getTitle());
			System.out.println(article.getContent());
		}
	}
}

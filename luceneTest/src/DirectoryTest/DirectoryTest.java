package DirectoryTest;

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
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import utils.DocumentUtiils;
import utils.LuceneUtils;

public class DirectoryTest {
  @Test
  //测试内存索引库
  public void testRamDirectory() throws Exception{
	  Directory ramDirectory=new RAMDirectory();
	  IndexWriter indexWriter=new IndexWriter(ramDirectory, LuceneUtils.analyzer, MaxFieldLength.LIMITED);
	  Article article =new Article();
	  article.setId(1L);
	  article.setTitle("lucene可以做搜索引擎");
	  article.setContent("百度谷歌");
	  Document document=DocumentUtiils.article2documet(article);
	  indexWriter.addDocument(document);
	 
	  indexWriter.close();
	  this.showData(ramDirectory);
  }
  
  private void showData(Directory directory) throws Exception{
	  IndexSearcher indexSearcher=new IndexSearcher(directory);
	  QueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_30, new String[]{"title","content"}, LuceneUtils.analyzer);
	  Query query=queryParser.parse("lucene");
	  TopDocs topDocs=indexSearcher.search(query, 2);
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
  /*将文件索引库的数据加载到内存索引库
   应用程序与内存索引库交互
   将内存索引库的数据写回文件索引库
   */
  public void testRamAndFile()throws Exception{
	  Directory ramDirectory=new RAMDirectory(LuceneUtils.directory);//将文件索引库的数据加载到内存索引库
	  IndexWriter fileIndexWriter=new IndexWriter(LuceneUtils.directory, LuceneUtils.analyzer,true, MaxFieldLength.LIMITED);
	  IndexWriter ramIndexWriter=new IndexWriter(ramDirectory,LuceneUtils.analyzer,MaxFieldLength.LIMITED);
	 //从内存索引库按关键字查询
	  System.out.println("从内存索引库读出的记录：");
	  this.showData(ramDirectory);
	  
	  //向内存索引库添加一条记录
	  Article article =new Article();
	  article.setId(2L);
	  article.setTitle(" lucene where there is a will");
	  article.setContent("there is a way!");
	  Document document=DocumentUtiils.article2documet(article);
	  ramIndexWriter.addDocument(document);
	  ramIndexWriter.close();
	  fileIndexWriter.addIndexesNoOptimize(ramDirectory);//把内存索引库数据写到文件索引库
	  System.out.println("从文件索引库读出的记录：");
	  fileIndexWriter.close();
	  this.showData(LuceneUtils.directory);
  }
}

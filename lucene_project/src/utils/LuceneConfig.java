package utils;



import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.apache.struts2.ServletActionContext;
import org.wltea.analyzer.lucene.IKAnalyzer;

import bean.Article;




public class LuceneConfig {
	public static Directory directory = null;
	public static Analyzer analyzer = null;
	static{
		try{
			//项目部署以后所在的绝对路径
			String bathPath = ServletActionContext.getServletContext().getRealPath("/");
			File file = new File(bathPath+"/indexDir");
			if(file.exists()){
				directory = FSDirectory.open(new File(bathPath+"/indexDir"));
			}else{//如果索引库不存在，则添加一个索引库
				Article article = new Article();
				article.setId(1);
				article.setTitle("lucene可以做搜索引擎");
				article.setContent("baidu,google都是比较好的搜索引擎");
				
				directory = FSDirectory.open(new File(bathPath+"/indexDir"));
				
				analyzer = new IKAnalyzer();
				
				IndexWriter indexWriter = new IndexWriter(directory,analyzer,MaxFieldLength.LIMITED);
			
				Document document = DocumentUtils.articleToDocument(article);
				
				indexWriter.addDocument(document);
				
				indexWriter.commit();
				
				indexWriter.close();
			}
			//directory = FSDirectory.open(new File("./indexDir"));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
		
	}
}

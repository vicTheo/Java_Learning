package utils;



import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.util.NumericUtils;

import bean.Article;



public class DocumentUtils {
	/**
	 * ��Articleת��ΪDocument
	 */
	public static Document articleToDocument(Article article){
		Document document = new Document();
		/*
		 * ˵��ʲô�����ʹ��Index.NOT_ANALYZED
		 */
		document.add(new Field("id",article.getId().toString(),
				Store.YES,Index.NOT_ANALYZED));
		document.add(new Field("title",article.getTitle(),Store.YES,Index.ANALYZED));
		document.add(new Field("content",article.getContent(),Store.YES,Index.ANALYZED));
		return document;
	}
	
	/**
	 * ��Documentת��ΪArticle
	 */
	public static Article documentToArticle(Document document){
		Article article = new Article();
		article.setId(Integer.parseInt(document.get("id")));
		article.setTitle(document.get("title"));
		article.setContent(document.get("content"));
		return article;
	}
}

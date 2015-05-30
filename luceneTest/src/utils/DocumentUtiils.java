package utils;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

import javabean.Article;

public class DocumentUtiils {
   public static Document article2documet(Article article){
	   Field idField=new Field("id",article.getId().toString(),Store.YES,Index.NOT_ANALYZED);
	   Field titleField=new Field("title",article.getTitle(),Store.YES,Index.ANALYZED);
	   Field contentField=new Field("content",article.getContent(),Store.YES,Index.ANALYZED);
     Document document=new Document();
     document.add(idField);
     document.add(titleField);
     document.add(contentField);
     return document;
   }
   
   public static Article document2article(Document document){
	   Article article=new Article();
	   article.setId(Long.parseLong(document.get("id")));
	   article.setTitle(document.get("title"));
	   article.setContent(document.get("content"));
	   return article;
   }
}

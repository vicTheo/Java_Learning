package utils;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.util.NumericUtils;

import javabean.Article;

public class DocumentUtiils {
   public static Document article2documet(Article article){
	    //NumericUtils.longToPrefixCoded(val)//此方法把id值转为二进制形式 以此来进行范围查询
	   Field idField=new Field("id",NumericUtils.longToPrefixCoded(article.getId()),Store.YES,Index.NOT_ANALYZED);
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
	   
	   article.setId(NumericUtils.prefixCodedToLong(document.get("id")));
	   article.setTitle(document.get("title"));
	   article.setContent(document.get("content"));
	   return article;
   }
}

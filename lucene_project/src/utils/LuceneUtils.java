package utils;



import org.apache.lucene.analysis.Analyzer;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;


public class LuceneUtils {
	private static IndexWriter indexWriter;
	static{
		Directory directory = LuceneConfig.directory;
		Analyzer analyzer = LuceneConfig.analyzer;
		try {
			if(indexWriter==null){
				indexWriter = new IndexWriter(directory,analyzer,MaxFieldLength.LIMITED);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		} 
	}
	
	public static void close(){
		if(indexWriter!=null){
			try {
				indexWriter.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			} 
		}else{
			System.out.println("indexWriter为null,不能关闭");
		}
		
	}
	
	public static IndexWriter getIndexWriter(){
		return indexWriter;
	}
}

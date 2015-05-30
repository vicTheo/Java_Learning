package optimizeTest;

import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.junit.Test;

import utils.LuceneUtils;

public class OptimizeTest {
   @Test
   public void optimizeTest() throws Exception{
	   IndexWriter indexWriter=new IndexWriter(LuceneUtils.directory,LuceneUtils.analyzer,MaxFieldLength.LIMITED);
	    indexWriter.optimize();
	    indexWriter.close(); 
	   
   }
}

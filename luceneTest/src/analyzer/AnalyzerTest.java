package analyzer;

import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class AnalyzerTest {
	@Test
	public void testStandardAnalyzer() throws Exception{
		Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_30);
		String text="中华人民共和国主席是习近平";
		this.testAnalyzer(analyzer, text);
		
	}
	
	@Test
	public void testChineseAnalyzer() throws Exception{
		Analyzer analyzer=new ChineseAnalyzer();
		String text="中华人民共和国主席是习近平";
		this.testAnalyzer(analyzer, text);
		
	}
	@Test
	public void testCJKAnalyzer() throws Exception{
		Analyzer analyzer=new CJKAnalyzer(Version.LUCENE_30);
		String text="中华人民共和国主席是习近平";
		this.testAnalyzer(analyzer, text);
		
	}
	@Test
	public void testIKAnalyzer() throws Exception{
		Analyzer analyzer=new IKAnalyzer();
		String text="中华人民";
		this.testAnalyzer(analyzer, text);
		
	}
	
	/**
	 * 经过该方法可以把分词后的结果输出
	 * @param analyzer
	 * @param text
	 * @throws Exception
	 */
	private void testAnalyzer(Analyzer analyzer,String text)throws Exception{
		TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
		tokenStream.addAttribute(TermAttribute.class);
		while(tokenStream.incrementToken()){
			TermAttribute termAttribute = tokenStream.getAttribute(TermAttribute.class);
			System.out.println(termAttribute.term());
		}
	}
}

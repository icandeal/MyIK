package org.wltea.analyzer.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuchunfan on 2016/12/27.
 */
public class SearchDemo {

//    public static String analyzeChinese(String input, boolean userSmart) throws IOException {
//        StringBuffer sb = new StringBuffer();
//        StringReader reader = new StringReader(input.trim());
//        IKSegmenter ikSeg = new IKSegmenter(reader, userSmart);
//        for (Lexeme lexeme = ikSeg.next(); lexeme != null; lexeme = ikSeg.next()) {
//            sb.append(lexeme.getLexemeText()).append(" ");
//        }
//        return sb.toString();
//    }
//
//    public static TokenStream convertSynonym(String input) throws IOException{
//        Map<String, String> filterArgs = new HashMap<>();
//        filterArgs.put("synonyms", "synonyms.txt");
//        filterArgs.put("expand", "true");
//        SynonymFilterFactory factory = new SynonymFilterFactory(filterArgs);
//        factory.inform(new ClasspathResourceLoader());
//        Analyzer whitespaceAnalyzer = new WhitespaceAnalyzer();
//        TokenStream ts = factory.create(whitespaceAnalyzer.tokenStream("someField", input));
//        return ts;
//    }
//
//    public static String displayTokens(TokenStream ts) throws IOException
//    {
//        StringBuffer sb = new StringBuffer();
//        CharTermAttribute termAttr = ts.addAttribute(CharTermAttribute.class);
//        ts.reset();
//        while (ts.incrementToken())
//        {
//            String token = termAttr.toString();
//            sb.append(token).append(" ");
//            System.out.print(token+"|");
////            System.out.print(offsetAttribute.startOffset() + "-" + offsetAttribute.endOffset() + "[" + token + "] ");
//        }
//        System.out.println();
//        ts.end();
//        ts.close();
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        String indexPath = "file:///E:/Project/IKAnalyzer/src/main/resources/bbb.json";
//        String input = "分为";
//        System.out.println("**********************");
//        try {
//            String result = displayTokens(convertSynonym(analyzeChinese(input, true)));
//			MyIndexer.createIndex(indexPath);
//            List<String> docs = MySearcher.searchIndex(result, indexPath);
//            for (String string : docs) {
//                System.out.println(string);
//            }
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (ParseException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}

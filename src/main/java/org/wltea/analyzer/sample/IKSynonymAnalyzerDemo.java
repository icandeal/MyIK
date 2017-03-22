package org.wltea.analyzer.sample;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;
import org.wltea.analyzer.lucene.IKSynonymAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuchunfan on 2016/12/26.
 */
public class IKSynonymAnalyzerDemo {

    public static void main(String[] args){
        String indexPath = "file:///E:/Project/IKAnalyzer/bbb";
        String input = "分为";

        try {
            IndexSearcher indexSearcher = null;
            IndexReader indexReader = DirectoryReader.open(FSDirectory.open(
                    Paths.get( URI.create(indexPath))
            ));
            indexSearcher = new IndexSearcher(indexReader);

            Analyzer analyzer = new IKSynonymAnalyzer(true);
            QueryParser queryParser = new QueryParser("title", analyzer);
            Query query = queryParser.parse(input);
            TopDocs td = indexSearcher.search(query, 10);
            for (int i = 0; i < td.totalHits; i++) {
                Document document = indexSearcher.doc(td.scoreDocs[i].doc);
                System.out.println(document.get("title"));
            }
            while(true){
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




        //构建IK分词器，使用smart分词模式
//        Analyzer analyzer = new IKSynonymAnalyzer(true);

//        //获取Lucene的TokenStream对象

//        TokenStream ts = null;
//        try {
//            ts = analyzer.tokenStream("myfield", new StringReader("这是一个中文分词的例子，你可以直接运行它！IKAnalyer can analysis english text too"));
//            //获取词元位置属性
//            OffsetAttribute offset = ts.addAttribute(OffsetAttribute.class);
//            //获取词元文本属性
//            CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);
//            //获取词元文本属性
//            TypeAttribute type = ts.addAttribute(TypeAttribute.class);
//
//
//            //重置TokenStream（重置StringReader）
//            ts.reset();
//            //迭代获取分词结果
//            while (ts.incrementToken()) {
//                System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | " + type.type());
//            }
//            //关闭TokenStream（关闭StringReader）
//            ts.end();   // Perform end-of-stream operations, e.g. set the final offset.
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            //释放TokenStream的所有资源
//            if(ts != null){
//                try {
//                    ts.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

    }
}

package org.wltea.analyzer.test;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Paths;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.wltea.analyzer.lucene.IKAnalyzer;
/**
 * Created by yuchunfan on 2016/12/27.
 */
public class MyIndexer {

    public static void createIndex(String indexPath) {
        try {
            Directory directory = FSDirectory.open(Paths.get(URI.create(indexPath)));

            Analyzer analyzer = new IKAnalyzer();

            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter indexWriter = new IndexWriter(directory, config);

            Document document1 = new Document();
            document1.add(new TextField("title","马云是个大资本家",Store.YES));
            indexWriter.addDocument(document1);

            Document document2 = new Document();
            document2.add(new TextField("title","王健林也是",Store.YES));
            indexWriter.addDocument(document2);

            Document document3 = new Document();
            document3.add(new TextField("title","高温慰问发票机流失率会计学肯定句诶",Store.YES));
            indexWriter.addDocument(document3);

            Document document4 = new Document();
            document4.add(new TextField("title","比尔而非噶不然把佛挡杀佛跟不上分公司不能用呢的散热色斑他听他说",Store.YES));
            indexWriter.addDocument(document4);

            indexWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createIndex("file:///E:/Project/IKAnalyzer/bbb");
    }
}
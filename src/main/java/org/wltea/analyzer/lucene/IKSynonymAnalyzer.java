package org.wltea.analyzer.lucene;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoader;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchunfan on 2016/12/26.
 */
public class IKSynonymAnalyzer extends Analyzer {
    private static Logger logger = Logger.getLogger(IKSynonymAnalyzer.class);

    private boolean useSmart;

    public boolean useSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    /**
     * IK分词器Lucene  Analyzer接口实现类
     *
     * 默认细粒度切分算法
     */
    public IKSynonymAnalyzer(){
        this(false);
        logger.warn("Init a default IKSynonymAnalyzer with useSmart = false");
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public IKSynonymAnalyzer(boolean useSmart){
        super();
        this.useSmart = useSmart;
        logger.warn("Init a new Boolean IKSynonymAnalyzer with useSmart = "+ this.useSmart);
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param args 参数配置
     */
    public IKSynonymAnalyzer(Map<String, String> args){
        super();
        String _arg = args.get("useSmart");
        this.useSmart = Boolean.parseBoolean(_arg);
        logger.warn("Init a new Map IKSynonymAnalyzer with useSmart = "+ this.useSmart);
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer token=new IKTokenizer(fieldName, true);
        Map paramsMap=new HashMap();
        paramsMap.put("synonyms", "synonyms.txt");
        logger.warn("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&"+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
        SynonymFilterFactory factory=new SynonymFilterFactory(paramsMap);
//        ResourceLoader loader = new ClasspathResourceLoader();
        try {
            ResourceLoader loader = new HDFSResourceLoader("hdfs://t45.test.etiantian.com:8020/tmp/data/");
            factory.inform(loader);
        } catch (IOException e) {
            logger.error(e);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new TokenStreamComponents(token, factory.create(token));
//        Tokenizer _IKTokenizer = new IKTokenizer(fieldName , this.useSmart());
//        return new TokenStreamComponents(_IKTokenizer);
    }
}

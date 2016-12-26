package org.wltea.analyzer.lucene;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.synonym.SynonymFilterFactory;
import org.apache.lucene.analysis.util.ClasspathResourceLoader;
import org.apache.lucene.analysis.util.FilesystemResourceLoader;
import org.apache.lucene.analysis.util.ResourceLoader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchunfan on 2016/12/26.
 */
public class IKSynonymAnalyzer extends Analyzer {
    private static Logger logger = Logger.getLogger(IKAnalyzer.class);

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
    }

    /**
     * IK分词器Lucene Analyzer接口实现类
     *
     * @param useSmart 当为true时，分词器进行智能切分
     */
    public IKSynonymAnalyzer(boolean useSmart){
        super();
        this.useSmart = useSmart;
        logger.warn("==========================================11"+ this.useSmart +"====================================");
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
        logger.warn("==========================================22"+ useSmart +"====================================");
    }

    /**
     * 重载Analyzer接口，构造分词组件
     */
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        Tokenizer token=new IKTokenizer(fieldName, true);
        Map paramsMap=new HashMap();
        paramsMap.put("synonyms", "synonyms.txt");
        SynonymFilterFactory factory=new SynonymFilterFactory(paramsMap);
        ResourceLoader loader = new ClasspathResourceLoader();
        try {
            factory.inform(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TokenStreamComponents(token, factory.create(token));
//        Tokenizer _IKTokenizer = new IKTokenizer(fieldName , this.useSmart());
//        return new TokenStreamComponents(_IKTokenizer);
    }
}

package org.wltea.analyzer.lucene;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;
import sun.rmi.runtime.Log;

import java.util.Map;

/**
 * Created by yuchunfan on 2016/12/23.
 */
public class IKTokenizerFactory extends TokenizerFactory {

    private static Logger logger = Logger.getLogger(IKTokenizerFactory.class);

    private boolean useSmart = false;

    public IKTokenizerFactory(Map<String, String> args) {
        super(args);
        logger.warn("$$$$$$$$$$$$$$$$$$  IKTokenizerFactory constructor $$$$$$$$$$$$$$$$$$");
        String _arg = args.get("useSmart");
        this.useSmart = Boolean.parseBoolean(_arg);
    }

    public Tokenizer create(AttributeFactory attributeFactory) {
        return new IKTokenizer(attributeFactory, useSmart);
    }

    public boolean isUseSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }
}

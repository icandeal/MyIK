package org.wltea.analyzer.sample;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.AttributeFactory;
import org.wltea.analyzer.lucene.IKTokenizer;
import org.wltea.analyzer.lucene.IKTokenizerFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuchunfan on 2016/12/26.
 */
public class IKTokenizerDemo {
    public static void main(String[] args){
        Map<String, String> params = new HashMap<String, String>();
        params.put("useSmart","true");
        IKTokenizerFactory tokenizerFactory = new IKTokenizerFactory(params);
        IKTokenizer ikTokenizer = (IKTokenizer) tokenizerFactory.create(AttributeFactory.DEFAULT_ATTRIBUTE_FACTORY);
        ikTokenizer.setReader(new StringReader("这是一个中文分词的例子，你可以直接运行它！IKAnalyer can analysis english text too"));

        OffsetAttribute offset = ikTokenizer.addAttribute(OffsetAttribute.class);
        //获取词元文本属性
        CharTermAttribute term = ikTokenizer.addAttribute(CharTermAttribute.class);
        //获取词元文本属性
        TypeAttribute type = ikTokenizer.addAttribute(TypeAttribute.class);
        try {
            ikTokenizer.reset();
            while (ikTokenizer.incrementToken()) {
                System.out.println(offset.startOffset() + " - " + offset.endOffset() + " : " + term.toString() + " | " + type.type());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

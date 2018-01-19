package training;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.BasicLineIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.tokenization.tokenizer.preprocessor.CommonPreprocessor;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Collection;

public class Launcher {

  private static Logger log = LoggerFactory.getLogger(Launcher.class);
  
  public static void main(String[] args) throws IOException {
    String filePath = args[0];

    log.info("Load & Vectorize Sentences....");
    // Strip white space before and after for each line
    SentenceIterator iter = new BasicLineIterator(filePath);
    
 // Split on white spaces in the line to get words
    TokenizerFactory t = new DefaultTokenizerFactory();
    t.setTokenPreProcessor(new CommonPreprocessor());
    
    log.info("Building model....");
    Word2Vec vec = new Word2Vec.Builder()
            .minWordFrequency(3) // 3
            .iterations(1)
            .layerSize(100)
            .seed(42)
            .windowSize(5)
            .iterate(iter)
            .tokenizerFactory(t)
            .build();

    log.info("Fitting Word2Vec model....");
    vec.fit();
    
    System.out.println("Save vectors....");
    
    WordVectorSerializer.writeWordVectors(vec, "modelWiki.txt");
    
    log.info("Closest Words:");
    Collection<String> lst = vec.wordsNearest("день", 10);
    System.out.println("10 Words closest to 'день': " + lst);
  }
}

package training;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

import java.io.IOException;
import java.util.Collection;

public class LauncherLoad {
  public static void main(String[] args) throws IOException {
    // File gModel = new File("/Developer/Vector Models/GoogleNews-vectors-negative300.bin.gz");
    Word2Vec vec = WordVectorSerializer.readWord2VecModel("C://Users//Ivan//Desktop//vectors.model1.bin.gz");
    
    // WordVectorSerializer.s
    // vec.fit();
    Collection<String> lst = vec.wordsNearest("дом", 10);
    System.out.println("10 Words closest to 'дом': " + lst);
    
    WordVectorSerializer.writeWordVectors(vec, "model123.txt");
  }
}

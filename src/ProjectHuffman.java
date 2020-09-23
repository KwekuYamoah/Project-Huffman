import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public interface ProjectHuffman {
    public HashMap<String,Integer> readfile(String inputFile) throws IOException;
    public void huffmanTree() throws IOException;
    public void printEncoded() throws IOException;
    public void printDecoded() throws IOException;
}
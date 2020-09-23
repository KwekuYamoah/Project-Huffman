import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class testProjectHuffman {
    public  static void main(String[] args) throws IOException {
        Huffman test = new Huffman ();
        test.inputFile = "Assignment_r.txt";
        test.inputFil2 = "Encoded.txt";
        System.out.println ("Welcome to Project Huffman\n" +
                                    "The goal of this to project is to read a text file\n" +
                                    "Encode the file using the principles proposed by David Huffman\n" +
                                    "Print the encodings of each character\n" +
                                    "Print the encoded file\n" +
                                    "And decode the encoded file to see if the same file is obtained to the console\n");

        System.out.println();
        System.out.println();

        /**
         * Reading a text file,
         * retuning the total count of each character
         * printing the results out in a formatted order
         */
        System.out.println ("Note: Character with empty strip and frequency\n denotes the empty string character ");
        System.out.println ("=====Printing out the items in the HashMap=====");
        System.out.println ("Character"+ "\t" + "   Frequency");
        System.out.println ("------------------------");
        HashMap<String, Integer> forPrint = test.readfile (test.inputFile);
        for (Map.Entry items : forPrint.entrySet ()) {
            System.out.println ("\t" + (String) items.getKey () + " \t\t|    " + (Integer) items.getValue ());
        }


        //Creating an instance of the hufQueue method
        PriorityQueue<Huffman.huffmanNode> arr= test.hufQueue();

        System.out.println();
        System.out.println();
        //Creating the huffmanTree for the test instance
        test.huffmanTree ();
        //Drawing the tree

        System.out.println ("====Printing the huffman tree in binary tree format====");
        test.drawTree();
        System.out.println();
        System.out.println();
        /**
         * Calling the huffmanCode method;
         * retuning the encodings of each character
         * printing the results out in a formatted order
         */
        System.out.println ("Note: Character with empty strip and encoding\n denotes the empty string character ");
        System.out.println ("=====Printing out the items in the encoded HashMap=====");
        System.out.println ("Character"+ "\t" + "   Encoding");
        System.out.println ("------------------------");
        HashMap<String, String> forCode = test.huffmanCode ();
        for (Map.Entry items : forCode.entrySet ()) {
            System.out.println ("\t" + (String) items.getKey () + " \t\t|    " + (String) items.getValue ());
        }

        System.out.println ();


        System.out.println ();
        //Clearing the encoded text file before writing to it
        test.clearFile ();
        //Writing to the encoded text file
        test.writeEncoded (test.inputFile);
        System.out.println ("=====Printing the encoded file to the console=====");
        test.printEncoded ();
        System.out.println ();
        System.out.println ();
        System.out.println ("=====Printing the decoded file to the console=====");
        test.printDecoded ();
        System.out.println();
        System.out.println();
        System.out.println ("Thank you for making it to the end of the program\n" +
                                    "We saw that the same file was printed back to the console\n" +
                                    "Therefore we have been able to encode and decode a text using huffman encoding.");

    }
}

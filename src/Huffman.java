import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Huffman implements ProjectHuffman {
    public String inputFile;
    //Instance variables
    private String file;
    public String[] words;
    private String encoded1 = "";
    private String decoded = "";
    public String inputFil2;


    //-----Beginning of Node Class---//
    // A class to represent the node of a Huffman Tree

    //Creating a root node and initializing it to null
    huffmanNode root = null;

    protected static class huffmanNode {
        private String data;
        private Integer freq;
        private huffmanNode left;
        private huffmanNode right;


        //default constructor
        huffmanNode() {
            data = null;
            left = null;
            right = null;
            freq = null;
        }

        //constructor

        huffmanNode(String data, Integer freq) {
            data = data;
            left = null;
            right = null;
            freq = freq;
        }

        //constructor
        huffmanNode(String d, huffmanNode lChild, huffmanNode rChild, Integer fre) {
            data = d;
            left = lChild;
            right = rChild;
            freq = fre;
        }

    }
    // ------End of Node Class-----//


    /**
     * Creating a comparator method
     * returns the lowest  between the two node frequencies
     */

    public class MyComparator implements Comparator<huffmanNode> {

        public int compare(huffmanNode left, huffmanNode right) {

            return (Integer) left.freq - (Integer) right.freq;
        }
    }

    /**
     * Single constructor for Huffman class
     */
    public Huffman() {
    }

    public HashMap<String, Integer> readfile(String inputFile) throws IOException {
        HashMap<String, Integer> mapStrings = new HashMap<> ();
        /**
         Creating a FileReader to read streams of characters
         */
        FileReader file;
        file = new FileReader (inputFile);

        /**
         Reads text from a character-input stream,
         buffering characters so as to provide for the
         efficient reading of characters, and lines.
         Because FileReader reads bytes from them file and converts them to characters
         and is returned which is sometimes in efficient
         */

        BufferedReader buffRead = new BufferedReader (file);
        String line = null;
        String data = "";

        /**Reading lines and spliting the data
         into an array of String characters
         */

        while ((line = buffRead.readLine ()) != null) {
            data = line;

            if (line != " ") {
                words = data.split ("");
            }
            /**
             * Loop through the array of characters
             * Insert it in a hasMap ith key-value nature
             */
            for (String i : words) {
                /**Character already belongs to map, increase value by 1*/

                if (mapStrings.containsKey (i)) {
                    mapStrings.replace (i, mapStrings.get (i) + 1);
                }
                /**
                 * Character is not in map
                 * insert character as key with value of 1
                 */
                else {
                    mapStrings.put (i, 1);
                }
            }
        }
        return mapStrings;
    }

    /**
     * Creating a PriorityQueue method,
     * which gives the smallest element the highest priority
     *
     * @return PriorityQueue of type Huffman node
     * @throws IOException
     */
    protected PriorityQueue<huffmanNode> hufQueue() throws IOException {
        PriorityQueue<huffmanNode> priorityQueue = new PriorityQueue<> (new MyComparator ());
        // Getting Items from the hash map
        HashMap<String, Integer> queue = this.readfile (inputFile);
        // Iterate through the HashMap
        // and create a single node using values and keys from hashmap
        //Store in a queue
        for (Map.Entry elements : queue.entrySet ()) {
            priorityQueue.add (new huffmanNode ((String) elements.getKey (), null, null, (Integer) elements.getValue ()));

        }
        return priorityQueue;
    }

    /**
     * A method which builds a binary tree,
     * using the huffman tree format
     *
     * @throws IOException
     */
    public void huffmanTree() throws IOException {
        PriorityQueue<huffmanNode> treeQueue = hufQueue ();
        while (treeQueue.size () > 1) {
            //first highest priority extracted
            huffmanNode left = treeQueue.poll ();

            //second highest priority extracted
            huffmanNode right = treeQueue.poll ();

            //Creating an internal node
            huffmanNode internalNode = new huffmanNode ();

            //Frequency of node is the sum of the frequencies of the left and right nodes
            internalNode.freq = (Integer) left.freq + (Integer) right.freq;
            // Data internal node holds is null from the constructor
            internalNode.data = "node";

            //Left child of internal node is extracted left node
            internalNode.left = left;

            //Right child of internal node is extracted right child
            internalNode.right = right;

            //Setting inter node as root of tree

            root = internalNode;

            //Inserting internal node back into priority queue
            treeQueue.add (internalNode);

        }

    }

    /**
     * Print tree code adopted from class
     * Author of Code: Dr Ayorkor Korsah
     * Code modified to suit the goal of Huffman Trees
     **/

    // A method to draw a tree with appropriate indentation
    public void drawTree() {
        if (root != null) {
            drawSubTree (root, 0); // call the recursive helper method
        } else {
            System.out.println ("The tree is empty");
        }
    }

    /**
     * A private recursive helper method to draw a subtree as an
     * indented list of the descendants of this node (including itself)
     * The indentLevel parameter just tells us how much to indent when printing
     */

    private void drawSubTree(huffmanNode curNode, int indentLevel) {
        /**The base case is that curNode is null, in which case we
         * don't want to do anything.
         * We only do something if curNode is
         * not null
         */
        if (curNode != null) {
            for (int i = 0; i < indentLevel; i++) {
                System.out.print ("\t");
            }
            System.out.println (curNode.data + " : " + curNode.freq);
            //Do drawSubTree of left child
            drawSubTree (curNode.left, indentLevel + 1);
            //Do drawSubTree of right child
            drawSubTree (curNode.right, indentLevel + 1);
        }
    }


    /**
     * Creating a recursive function to generate the
     * huffman encoding of each character
     */
    HashMap<String, String> youTire = new HashMap<> ();

    public HashMap<String, String> huffmanCode() {
        return huffmanCode (root, "");
    }

    /**
     * A method which generates the huffman code,
     * for each character
     *
     * @param sroot
     * @param store
     */
    private HashMap<String, String> huffmanCode(huffmanNode sroot, String store) {

        if (sroot != null) {
            if (sroot.right != null) {
                /**
                 * Checking left child to see if character is there
                 * Doing a left traversal and adding 0 to store
                 */
                huffmanCode (sroot.left, store + "0");
            }
            if (sroot.left != null) {
                /**
                 * Checking right child to see if character is there
                 * Doing a right traversal and adding 1 to store
                 */
                huffmanCode (sroot.right, store + "1");
            } else {
                /**Putting the character with corresponding
                 * bit encoding in  the map youTire
                 */
                youTire.put ((String) sroot.data, store);

            }
        }
        return youTire;
    }

    /**
     * method to clear the Encoded text file
     *
     * @throws IOException
     */
    public void clearFile() throws IOException {
        FileWriter fileWrite = new FileWriter (inputFil2, false);
        PrintWriter pWrite = new PrintWriter (fileWrite, false);
        pWrite.flush ();
        fileWrite.close ();
    }


    /**
     * A function to write the encoded file to a text file
     * called Encoded.txt
     *
     * @param inputFile
     * @throws IOException
     */
    public void writeEncoded(String inputFile) throws IOException {
        String encoded = "";
        /**
         Creating a FileReader to read streams of characters
         */
        FileReader file;
        file = new FileReader (inputFile);
        /**
         Reads text from a character-input stream,
         buffering characters so as to provide for the
         efficient reading of characters, and lines.
         Because FileReader reads bytes from them file and converts them to characters
         and is returned which is sometimes inefficient
         */
        BufferedReader buffRead = new BufferedReader (file);
        String line = null;
        String data = "";
        /**Reading lines and spliting the data
         into an array of String characters
         */
        while ((line = buffRead.readLine ()) != null) {
            data = line;

            if (line != "") {
                words = data.split ("");
            }
            /**
             * Loop through the array of characters
             * Loop through the hash map
             * if character at index i equals key in map
             * get the encoded value and add to the encoded string for that line read
             */

            for (int i = 0; i < words.length; i++) {
                for (Map.Entry items : youTire.entrySet ()) {
                    if (words[i].equals (items.getKey ())) {
                        encoded += items.getValue () + " ";

                    }
                }
            }


            /**
             * Creating a new file writer instance
             * Wrapping buffered writer around file read
             * to ensure efficient writing of encodings
             * create a newline to write characters to and writing the characters
             * The file is closed and encoded string is made empty again.
             */
            BufferedWriter inW = new BufferedWriter (new FileWriter (inputFil2, true));
            inW.flush ();
            inW.newLine ();
            inW.write (encoded);
            encoded = "";
            inW.close ();
        }
    }

    /**
     * A simple method to print the encoded file,
     * to the console
     *
     * @throws IOException
     */
    public void printEncoded() throws IOException {
        /**
         * Creating a new file reader instance
         * Wrapping buffered reader around file read
         * to ensure efficient reading of encodings
         */


        BufferedReader buffRead = new BufferedReader (new FileReader (inputFil2));
        String line = null;
        String data = "";
        /**Reading lines and spliting the data
         into an array of String encodings
         */
        while ((line = buffRead.readLine ()) != null) {
            data = line;

            String[] encodeArray = data.split (" ");
            for (int i = 0; i < encodeArray.length; i++) {
                decoded += encodeArray[i];
            }
            System.out.println (decoded);
            decoded = "";

        }
    }

    /**
     * A function which reads from Encoded.txt
     * prints the decoded results to the console
     *
     * @throws IOException
     */
    public void printDecoded() throws IOException {
        /**
         * Creating a new file reader instance
         * Wrapping buffered reader around file read
         * to ensure efficient reading of encodings
         */

        BufferedReader buffRead = new BufferedReader (new FileReader (inputFil2));
        String line = null;
        String data = "";
        /**Reading lines and spliting the data
         into an array of String encodings
         */
        while ((line = buffRead.readLine ()) != null) {
            data = line;

            String[] encodeArray = data.split (" ");

            /**
             * Loop through the array of string encodings
             * Loop through the hash map
             * if encoding at index i equals value in map
             * get the character value and add to the decoded string for that line read
             */
            for (int i = 0; i < encodeArray.length; i++) {
                for (Map.Entry items : youTire.entrySet ()) {
                    if (encodeArray[i].equals (items.getValue ())) {
                        decoded += items.getKey ();
                    }
                }
            }
            /**
             * Print the decoded sting to the console for that line
             * make decoded string empty again.
             */
            System.out.println (decoded);
            decoded = "";

        }
    }
}

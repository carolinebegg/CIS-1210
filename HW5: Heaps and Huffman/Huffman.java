import java.util.HashMap;
import java.util.Map;

/**
 * Implements construction, encoding, and decoding logic of the Huffman coding algorithm. Characters
 * not in the given seed or alphabet should not be compressible, and attempts to use those
 * characters should result in the throwing of an {@link IllegalArgumentException} if used in {@link
 * #compress(String)}.
 */
public class Huffman {

    /**
     * Constructs a {@code Huffman} instance from a seed string, from which to deduce the alphabet
     * and corresponding frequencies.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param seed the String from which to build the encoding
     * @throws IllegalArgumentException seed is null, seed is empty, or resulting alphabet only has
     *                                  1 character
     */
    BinaryMinHeapImpl<Integer, Node> heap = new BinaryMinHeapImpl<>();
    Map<Character, StringBuilder> encoding = new HashMap<>();
    Map<Character, Integer> frequencyMap;
    Node root;
    Integer inputLength = 0;
    Integer outputLength = 0;
    StringBuilder compressed = new StringBuilder();
    boolean compression = false;
    public Huffman() {
        heap = new BinaryMinHeapImpl<>();
        encoding = new HashMap<>();
    }
    public Huffman(String seed) {
        if (seed == null) {
            throw new IllegalArgumentException("seed is null");
        }
        if (seed.isEmpty()) {
            throw new IllegalArgumentException("seed is empty");
        }

        char[] string = seed.toCharArray();
        frequencyMap = new HashMap<>();

        for (char c : string) {
            if (!frequencyMap.containsKey(c)) {
                frequencyMap.put(c, 1);
            } else {
                frequencyMap.replace(c, frequencyMap.get(c) + 1);
            }
        }
        if (frequencyMap.size() <= 1) {
            throw new IllegalArgumentException("the alphabet has only 1 character");
        }

        for (Character c : frequencyMap.keySet()) {
            int freq = frequencyMap.get(c);
            heap.add(freq, new Node(c, freq));
        }

        while (heap.size() > 1) {
            Node left = heap.extractMin().value;
            Node right = heap.extractMin().value;
            int freq = left.freq + right.freq;
            Node parent = new Node(null, freq);
            parent.left = left;
            parent.right = right;
            heap.add(freq, parent);
        }
        root = heap.peek().value;
        encoder(root, new StringBuilder());
    }
    void encoder(Node curr, StringBuilder str) {
        if (curr.isLeaf()) {
            encoding.put(curr.encoded, new StringBuilder(str.toString()));
        } else {
            encoder(curr.left, str.append("0"));
            str.setLength(str.length() - 1);
            encoder(curr.right, str.append("1"));
            str.setLength(str.length() - 1);
        }
    }
    String getEncoding(Character c) {
        return encoding.get(c).toString();
    }

    /**
     * Constructs a {@code Huffman} instance from a frequency map of the input alphabet.
     * <p/>
     * Do NOT modify this constructor header.
     *
     * @param alphabet a frequency map for characters in the alphabet
     * @throws IllegalArgumentException if the alphabet is null, empty, has fewer than 2 characters,
     *                                  or has any non-positive frequencies
     */
    public Huffman(Map<Character, Integer> alphabet) {
        if (alphabet == null) {
            throw new IllegalArgumentException("alphabet is null");
        }
        if (alphabet.isEmpty()) {
            throw new IllegalArgumentException("alphabet is empty");
        }
        if (alphabet.size() < 2) {
            throw new IllegalArgumentException("alphabet has less than 2 characters");
        }
        for (Character character : alphabet.keySet()) {
            int freq = alphabet.get(character);
            if (alphabet.get(character) <= 0) {
                throw new IllegalArgumentException("alphabet contains negative frequency");
            }
            heap.add(freq, new Node(character, freq));
        }
        while (heap.size() > 1) {
            Node left = heap.extractMin().value;
            Node right = heap.extractMin().value;
            int freq = left.freq + right.freq;
            Node parent = new Node(null, freq);
            parent.left = left;
            parent.right = right;
            heap.add(freq, parent);
        }
        root = heap.peek().value;
        encoder(root, new StringBuilder());
    }

    /**
     * Compresses the input string.
     *
     * @param input the string to compress, can be the empty string
     * @return a string of ones and zeroes, representing the binary encoding of the inputted String.
     * @throws IllegalArgumentException if the input is null or if the input contains characters
     *                                  that are not compressible
     */
    public String compress(String input) {
        compressed = new StringBuilder();
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        if (input.isEmpty()) {
            return compressed.toString();
        }
        inputLength += input.length();

        int index = 0;
        while (index < input.length()) {
            if (!encoding.containsKey(input.charAt(index))) {
                throw new IllegalArgumentException("input contains invalid characters");
            }
            compressed.append(encoding.get(input.charAt(index)));
            index++;
        }
        compression = true;
        outputLength += compressed.length();
        return compressed.toString();
    }

    /**
     * Decompresses the input string.
     *
     * @param input the String of binary digits to decompress, given that it was generated by a
     *              matching instance of the same compression strategy
     * @return the decoded version of the compressed input string
     * @throws IllegalArgumentException if the input is null, or if the input contains characters
     *                                  that are NOT 0 or 1, or input contains a sequence of bits
     *                                  that is not decodable
     */
    public String decompress(String input) {
        if (input == null) {
            throw new IllegalArgumentException("input is null");
        }
        if (input.isEmpty()) {
            return "";
        }
        StringBuilder s = new StringBuilder();
        int index = 0;
        while (index < input.length()) {
            Node curr = root;
            while (!curr.isLeaf()) {
                if (index >= input.length()) {
                    throw new IllegalArgumentException("undecodable sequence of bits");
                }
                if (input.charAt(index) == '0') {
                    curr = curr.left;
                } else if (input.charAt(index) == '1') {
                    curr = curr.right;
                } else {
                    throw new IllegalArgumentException("encoding contains an invalid character");
                }
                index++;
            }
            s.append(curr.encoded);
        }
        return s.toString();
    }

    /**
     * Computes the compression ratio so far. This is the length of all output strings from {@link
     * #compress(String)} divided by the length of all input strings to {@link #compress(String)}.
     * Assume that each char in the input string is a 16 bit int.
     *
     * @return the ratio of the total output length to the total input length in bits
     * @throws IllegalStateException if no calls have been made to {@link #compress(String)} before
     *                               calling this method
     */
    public double compressionRatio() {
        if (compression) {
            return (double) outputLength / (inputLength * 16);
        } else {
            throw new IllegalStateException("no calls have been made to compress(String)");
        }
    }

    /**
     * Computes the expected encoding length of an arbitrary character in the alphabet based on the
     * objective function of the compression.
     * <p>
     * The expected encoding length is simply the sum of the length of the encoding of each
     * character multiplied by the probability that character occurs.
     *
     * @return the expected encoding length of an arbitrary character in the alphabet
     */
    public double expectedEncodingLength() {
        double sum = 0;
        int total = 0;
        for (Character c : frequencyMap.keySet()) {
            total += frequencyMap.get(c);
        }
        for (Character c : frequencyMap.keySet()) {
            double prob = (double) frequencyMap.get(c) / total ;
            sum += (prob * encoding.get(c).length());
        }
        return sum;
    }
    static class Node {
        Character encoded;
        Node left;
        Node right;
        Integer freq;
        Node(Character c, Integer f) {
            encoded = c;
            freq = f;
            left = null;
            right = null;
        }
        public boolean isLeaf() {
            return (left == null && right == null && encoded != null);
        }
    }
}

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class HuffmanTest {

    Huffman huffman;
    Huffman.Node node;
    Map<Character, Integer> alphabet;
    final double delta = 0.000001;

    /** Node Tests **/
    @Test
    public void testNode() {
        node = new Huffman.Node('c', 1);
        assertTrue(node.isLeaf());
        assertNull(node.left);
        assertNull(node.right);

        int f = node.freq;
        char c = node.encoded;
        assertEquals(1, f);
        assertEquals('c', c);
    }

    @Test
    public void testHuffman() {
        huffman = new Huffman();
        assertNotNull(huffman);
        assertTrue(huffman.heap.isEmpty());
        assertTrue(huffman.encoding.isEmpty());
    }

    /** Constructor #1 **/

    @Test(expected = IllegalArgumentException.class)
    public void testNullSeed() {
        Huffman huffman = new Huffman((String) null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEmptySeed() {
        Huffman huffman = new Huffman("");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testOneLetterSeed() {
        Huffman huffman = new Huffman("a");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testOneRepeatedLetterSeed() {
        Huffman huffman = new Huffman("aaaaaaaa");
    }

    @Test
    public void testTwoLetterSeed() {
        Huffman huffman = new Huffman("aab");
        assertEquals("1", huffman.getEncoding('a'));
        assertEquals("0", huffman.getEncoding('b'));
    }

    @Test
    public void testRepeatedSeed() {
        Huffman huffman = new Huffman("aaaaabbbbbbcccdddeffff");
        assertEquals("01", huffman.getEncoding('a'));
        assertEquals("10", huffman.getEncoding('b'));
        assertEquals("1111", huffman.getEncoding('c'));
        assertEquals("110", huffman.getEncoding('d'));
        assertEquals("1110", huffman.getEncoding('e'));
        assertEquals("00", huffman.getEncoding('f'));
    }

    @Test
    public void testRepeatedSeedSameFreq() {
        Huffman huffman = new Huffman("aaabbbccc");
        assertEquals("10", huffman.getEncoding('a'));
        assertEquals("11", huffman.getEncoding('c'));
        assertEquals("0", huffman.getEncoding('b'));
    }

    @Test
    public void testSeedSameFreq() {
        Huffman huffman = new Huffman("aaabbbccc");
        assertEquals("10", huffman.getEncoding('a'));
        assertEquals("11", huffman.getEncoding('c'));
        assertEquals("0", huffman.getEncoding('b'));
    }

    @Test
    public void testSeedSameFreqOneEach() {
        Huffman huffman = new Huffman("abc");
        assertEquals("10", huffman.getEncoding('a'));
        assertEquals("11", huffman.getEncoding('c'));
        assertEquals("0", huffman.getEncoding('b'));
    }

    @Test
    public void testSeedIncreasingFreq() {
        Huffman huffman = new Huffman("abbcccddddeeeee");
        assertEquals("010", huffman.getEncoding('a'));
        assertEquals("011", huffman.getEncoding('b'));
        assertEquals("00", huffman.getEncoding('c'));
        assertEquals("10", huffman.getEncoding('d'));
        assertEquals("11", huffman.getEncoding('e'));
    }

    /** Constructor #2 **/
    @Test(expected = IllegalArgumentException.class)
    public void testNullAlphabet() {
        Huffman huffman = new Huffman((Map<Character, Integer>) null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyAlphabet() {
        Huffman huffman = new Huffman(new HashMap<>());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAlphabetOfLengthOne() {
        alphabet = new HashMap<>();
        alphabet.put('a', 1);
        Huffman huffman = new Huffman(alphabet);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeFrequencies() {
        alphabet = new HashMap<>();
        alphabet.put('a', 1);
        alphabet.put('b', -1);
        Huffman huffman = new Huffman(alphabet);
    }

    @Test
    public void testNormalAlphabet() {
        alphabet = new HashMap<>();
        alphabet.put('a', 1);
        alphabet.put('b', 2);
        alphabet.put('c', 3);
        Huffman huffman = new Huffman(alphabet);
        assertEquals("10", huffman.getEncoding('a'));
        assertEquals("11", huffman.getEncoding('b'));
        assertEquals("0", huffman.getEncoding('c'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompressNull() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        huffman.compress(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCompressInvalidCharacters() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        huffman.compress("caroline");
    }

    @Test
    public void testCompressEmpty() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("", huffman.compress(""));
    }

    @Test
    public void testCompressNormal() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("111010", huffman.compress("cat"));
    }

    @Test
    public void testCompressCactus() {
        alphabet = new HashMap<>();
        alphabet.put('a', 3);
        alphabet.put('b', 5);
        alphabet.put('c', 1);
        alphabet.put('d', 2);
        alphabet.put('t', 2);
        alphabet.put('s', 3);
        alphabet.put('u', 1);
        huffman = new Huffman(alphabet);
        assertEquals("0110", huffman.getEncoding('c'));
        assertEquals("111", huffman.getEncoding('a'));
        assertEquals("110", huffman.getEncoding('t'));
        assertEquals("0111", huffman.getEncoding('u'));
        assertEquals("00", huffman.getEncoding('s'));
        assertEquals("01101110110110011100", huffman.compress("cactus"));
    }
    @Test
    public void testDecompressCactus() {
        alphabet = new HashMap<>();
        alphabet.put('a', 3);
        alphabet.put('b', 5);
        alphabet.put('c', 1);
        alphabet.put('d', 2);
        alphabet.put('t', 2);
        alphabet.put('s', 3);
        alphabet.put('u', 1);
        huffman = new Huffman(alphabet);
        assertEquals("cactus", huffman.decompress("01101110110110011100"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecompressNull() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        huffman.decompress(null);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDecompressInvalidSequence() {
        Huffman huffman = new Huffman("abc");
        huffman.decompress("101");
    }
    @Test
    public void testDecompressEmptyString() {
        huffman = new Huffman("aacctafctyaatta");
        assertEquals("", huffman.decompress(""));
    }
    @Test(expected = IllegalArgumentException.class)
    public void testDecompressInvalidCharacters() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        huffman.decompress("010!111");
    }
    @Test
    public void testDecompressCat() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("0", huffman.getEncoding('a'));
        assertEquals("10", huffman.getEncoding('t'));
        assertEquals("111", huffman.getEncoding('c'));
        assertEquals("1100", huffman.getEncoding('f'));
        assertEquals("1101", huffman.getEncoding('y'));
        assertEquals("cat", huffman.decompress("111010"));
    }
    @Test
    public void testCompressCat() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("0", huffman.getEncoding('a'));
        assertEquals("10", huffman.getEncoding('t'));
        assertEquals("111", huffman.getEncoding('c'));
        assertEquals("1100", huffman.getEncoding('f'));
        assertEquals("1101", huffman.getEncoding('y'));
        assertEquals("111010", huffman.compress("cat"));
    }
    @Test(expected = IllegalStateException.class)
    public void testCompressionRatioNotCompressed() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        huffman.compressionRatio();
    }
    @Test
    public void testCompressionRatioCat() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("111010", huffman.compress("cat"));
        assertEquals(0.125, huffman.compressionRatio(), delta);
    }
    @Test
    public void testCompressionRatioCatFact() {
        Huffman huffman = new Huffman("aacctafctyaatta");
        assertEquals("111010", huffman.compress("cat"));
        assertEquals("1100011110", huffman.compress("fact"));
        assertEquals((16.0 / 112.0), huffman.compressionRatio(), delta);
    }
    @Test
    public void testExpectedEncodingSame() {
        Huffman huffman = new Huffman("abcdabcdabcd");
        assertEquals("00", huffman.getEncoding('a'));
        assertEquals("11", huffman.getEncoding('b'));
        assertEquals("10", huffman.getEncoding('c'));
        assertEquals("01", huffman.getEncoding('d'));
        assertEquals(2, huffman.expectedEncodingLength(), delta);
    }

}

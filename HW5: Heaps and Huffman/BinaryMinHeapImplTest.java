import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class BinaryMinHeapImplTest {
    private BinaryMinHeap<Integer, String> minHeap;

    /*** SIZE TESTS ***/

    @Test
    public void testSizeEmpty() {
        minHeap = new BinaryMinHeapImpl<>();
        assertEquals(0, minHeap.size());
    }

    @Test
    public void testSizeIsOne() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "test");
        assertEquals(1, minHeap.size());
    }

    @Test
    public void testSizeThree() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "test one");
        minHeap.add(2, "test two");
        minHeap.add(3, "test three");
        assertEquals(3, minHeap.size());
    }

    /*** isEmpty() TESTS ***/

    @Test
    public void testIsEmptyTrue() {
        minHeap = new BinaryMinHeapImpl<>();
        assertTrue(minHeap.isEmpty());
    }
    @Test
    public void testIsEmptyFalse() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "test");
        assertFalse(minHeap.isEmpty());
    }

    /*** containsValue() TESTS ***/
    @Test
    public void testContainsEmptyFalse() {
        minHeap = new BinaryMinHeapImpl<>();
        assertFalse(minHeap.containsValue("A"));
    }
    @Test
    public void testContainsTrueSingleLetter() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        assertTrue(minHeap.containsValue("A"));
    }
    @Test
    public void testContainsTrueThreeLetters() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        minHeap.add(1, "B");
        minHeap.add(2, "C");
        assertTrue(minHeap.containsValue("B"));
    }
    @Test
    public void testContainsFalseThreeLetters() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        minHeap.add(1, "B");
        minHeap.add(2, "C");
        assertFalse(minHeap.containsValue("D"));
    }

    /*** ADD() + SWAP() TESTS ***/
    @Test(expected = IllegalArgumentException.class)
    public void testAddNullKey() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(null, "null key");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddValueInHeap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        minHeap.add(1, "A");
    }

    @Test
    public void testAddOneSwap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "B");
        minHeap.add(0, "A");
        assertEquals(2, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
        assertEquals("A", e0.value);
        assertEquals(1, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
        assertEquals("B", e1.value);
        assertEquals(0, minHeap.size());
    }

    @Test
    public void testAddTwoSwaps() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "B");
        minHeap.add(2, "C");
        minHeap.add(3, "D");
        minHeap.add(0, "A");
        assertEquals(4, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
        assertEquals("A", e0.value);
        assertEquals(3, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
        assertEquals("B", e1.value);
        assertEquals(2, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e2 = minHeap.extractMin();
        assertEquals(2, (int) e2.key);
        assertEquals("C", e2.value);
        assertEquals(1, minHeap.size());

        BinaryMinHeap.Entry<Integer, String> e3 = minHeap.extractMin();
        assertEquals(3, (int) e3.key);
        assertEquals("D", e3.value);
        assertEquals(0, minHeap.size());
    }

    /*** decreaseKey() TESTS ***/
    @Test(expected = IllegalArgumentException.class)
    public void testDecreaseKeyNullKey() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(2, "key");
        minHeap.decreaseKey("key", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecreaseKeyGreaterNewKey() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "key");
        minHeap.decreaseKey("key", 1);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDecreaseKeyValueNotInHeap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(2, "old key");
        minHeap.decreaseKey("new key", 1);
    }

    @Test
    public void testDecreaseKeyValidKeyOneElement() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(2, "key");
        minHeap.decreaseKey("key", 1);
        assertEquals(1,minHeap.size());
        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
    }

    @Test
    public void testDecreaseKeyValidKeyTwoKeys() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "key 0");
        minHeap.add(2, "key 1");
        minHeap.decreaseKey("key 1", 1);
        minHeap.extractMin();
        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
    }
    @Test
    public void testDecreaseKeyValidKeyThreeKeys() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "key 0");
        minHeap.add(1, "key 1");
        minHeap.add(3, "key 2");
        minHeap.decreaseKey("key 2", 2);
        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
        BinaryMinHeap.Entry<Integer, String> e2 = minHeap.extractMin();
        assertEquals(2, (int) e2.key);
    }

    @Test
    public void testDecreaseKeyValidKeyTwoKeysWithSwap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "key 1");
        minHeap.add(2, "key 0");
        minHeap.decreaseKey("key 0", 0);
        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
    }
    @Test
    public void testDecreaseKeyValidKeyMultiple() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(2, "key 0");
        minHeap.add(3, "key 1");
        minHeap.decreaseKey("key 1", 1);
        minHeap.decreaseKey("key 0", 0);
        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);

    }

    /*** PEEK() TESTS ***/
    @Test(expected = NoSuchElementException.class)
    public void testPeekNullHeap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.peek();
    }
    @Test
    public void testPeekOneElement() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        assertEquals("A", minHeap.peek().value);
    }

    @Test
    public void testPeekThreeElements() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "A");
        minHeap.add(1, "B");
        minHeap.add(2, "C");
        assertEquals("A", minHeap.peek().value);
    }
    @Test
    public void testPeekThreeElementsWithSwap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "B");
        minHeap.add(2, "C");
        minHeap.add(0, "A");
        assertEquals("A", minHeap.peek().value);
    }
    /*** extractMin() TESTS ***/
    @Test(expected = NoSuchElementException.class)
    public void testExtractMinValueNotInHeap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.extractMin();
    }

    @Test
    public void testExtractMinSingleElement() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "key");
        BinaryMinHeap.Entry<Integer, String> enter = minHeap.extractMin();
        assertEquals(0, (int) enter.key);
        assertEquals("key", enter.value);
    }

    @Test
    public void testExtractMinThreeElements() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(0, "key 0");
        minHeap.add(1, "key 1");
        minHeap.add(2, "key 2");
        BinaryMinHeap.Entry<Integer, String> enter = minHeap.extractMin();
        assertEquals(0, (int) enter.key);
        assertEquals("key 0", enter.value);
    }
    @Test
    public void testExtractMinThreeElementsWithSwap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "key 1");
        minHeap.add(2, "key 2");
        minHeap.add(0, "key 0");
        BinaryMinHeap.Entry<Integer, String> enter = minHeap.extractMin();
        assertEquals(0, (int) enter.key);
        assertEquals("key 0", enter.value);
    }
    @Test
    public void testExtractMinFourElementsWithSwap() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1, "key 1");
        minHeap.add(2, "key 2");
        minHeap.add(3, "key 3");
        minHeap.add(0, "key 0");

        BinaryMinHeap.Entry<Integer, String> e0 = minHeap.extractMin();
        assertEquals(0, (int) e0.key);
        assertEquals("key 0", e0.value);

        BinaryMinHeap.Entry<Integer, String> e1 = minHeap.extractMin();
        assertEquals(1, (int) e1.key);
        assertEquals("key 1", e1.value);

        BinaryMinHeap.Entry<Integer, String> e2 = minHeap.extractMin();
        assertEquals(2, (int) e2.key);
        assertEquals("key 2", e2.value);

        BinaryMinHeap.Entry<Integer, String> e3 = minHeap.extractMin();
        assertEquals(3, (int) e3.key);
        assertEquals("key 3", e3.value);
    }

    /*** VALUES() TESTS ***/
    @Test
    public void testValuesEmptySet() {
        minHeap = new BinaryMinHeapImpl<>();
        assertEquals(0,minHeap.values().size());
    }

    @Test
    public void testValuesSingleton() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1,"A");
        assertEquals(1,minHeap.values().size());
    }
    @Test
    public void testValuesTwoElements() {
        minHeap = new BinaryMinHeapImpl<>();
        minHeap.add(1,"A");
        minHeap.add(2,"B");
        assertEquals(2,minHeap.values().size());
    }

}

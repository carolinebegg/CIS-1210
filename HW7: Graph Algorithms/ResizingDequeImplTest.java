import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class ResizingDequeImplTest {
    private ResizingDequeImpl<Integer> deque;
    private ResizingDequeImpl<Integer> dequeOne;
    private ResizingDequeImpl<Integer> dequeTwo;
    private ResizingDequeImpl<Integer> dequeThree;
    private ResizingDequeImpl<Integer> dequeFour;
    private ResizingDequeImpl<Integer> dequeSixteen;

    @Before
    public void setup() {
        dequeOne = new ResizingDequeImpl<>();
        dequeOne.addFirst(1);

        dequeTwo = new ResizingDequeImpl<>();
        dequeTwo.addFirst(1);
        dequeTwo.addLast(2);

        dequeThree = new ResizingDequeImpl<>();
        dequeThree.addFirst(1);
        dequeThree.addLast(2);
        dequeThree.addLast(3);

        dequeFour = new ResizingDequeImpl<>();
        dequeFour.addFirst(1);
        dequeFour.addLast(2);
        dequeFour.addLast(3);
        dequeFour.addLast(4);

        dequeSixteen = new ResizingDequeImpl<>();
        dequeSixteen.addFirst(1);
        for (int i = 2; i < 17; i++) {
            dequeSixteen.addLast(i);
        }
    }

    /** NULL TESTS **/
    @Test
    public void testAddFirstNull() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(null);
        assertEquals(1, deque.size());
    }
    @Test
    public void testAddNull() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(null);
        assertEquals(1, deque.size());
    }
    @Test
    public void testPollFirstNull() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(null);
        deque.pollFirst();
        assertEquals(0, deque.size());
    }
    @Test
    public void testPollLastNull() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(null);
        deque.pollLast();
        assertEquals(0, deque.size());
    }
    @Test
    public void testPeekFirstNull() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(null);
        deque.peekFirst();
        assertEquals(1, deque.size());
    }
    @Test
    public void testPeekLastNull() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(null);
        deque.peekLast();
        assertEquals(1, deque.size());
    }
    @Test
    public void testLengthOfEmptyDeque() {
        deque = new ResizingDequeImpl<>();
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }
    @Test
    public void testSizeEmpty() {
        deque = new ResizingDequeImpl<>();
        assertEquals(0, deque.size());
    }

    /** Basic addFirst() Tests **/
    @Test
    public void testAddFirstOneElement() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(1, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(1, head);
        assertEquals(1, tail);
    }

    @Test
    public void testAddFirstTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(2, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(2, head);
        assertEquals(1, tail);
    }

    @Test
    public void testAddFirstThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertEquals(2, actual[0]);
        assertEquals(1, actual[1]);
        assertNull(actual[2]);
        assertEquals(3, actual[3]);
        assertEquals(3, head);
        assertEquals(1, tail);
    }

    @Test
    public void testAddFirstFourElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(4, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertEquals(2, actual[0]);
        assertEquals(1, actual[1]);
        assertEquals(4, actual[2]);
        assertEquals(3, actual[3]);
        assertEquals(4, head);
        assertEquals(1, tail);
    }

    /** Basic addLast() Tests **/
    @Test
    public void testAddLastOneElement() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(1, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(1, head);
        assertEquals(1, tail);
    }
    @Test
    public void testAddLastTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(2, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(1, head);
        assertEquals(2, tail);
    }
    @Test
    public void testAddLastThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(3, actual[2]);
        assertNull(actual[3]);
        assertEquals(1, head);
        assertEquals(3, tail);
    }
    @Test
    public void testAddLastFourElements() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(4, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(3, actual[2]);
        assertEquals(4, actual[3]);
        assertEquals(1, head);
        assertEquals(4, tail);
    }

    /** Combination addFirst() and addLast() Tests **/
    @Test
    public void testAddFirstAddLastTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(1, head);
        assertEquals(2, tail);
    }
    @Test
    public void testAddLastAddFirstTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addFirst(2);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(2, head);
        assertEquals(1, tail);
    }

    @Test
    public void testAddFirstResizingThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(3);
        deque.addFirst(7);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(1, actual[0]);
        assertEquals(3, actual[1]);
        assertNull(actual[2]);
        assertEquals(7, actual[3]);
        assertEquals(7, head);
        assertEquals(3, tail);
    }
    @Test
    public void testAddLastFourElementsHeadAtZero() {
        dequeFour.addLast(9);

        Object[] actual = (Object[]) dequeFour.getArray();
        int head = dequeFour.peekFirst();
        int tail = dequeFour.peekLast();

        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(3, actual[2]);
        assertEquals(4, actual[3]);
        assertEquals(9, actual[4]);
        assertNull(actual[5]);
        assertNull(actual[6]);
        assertNull(actual[7]);
        assertEquals(1, head);
        assertEquals(9, tail);
    }

    /** Basic pollFirst() Tests **/
    @Test(expected = NoSuchElementException.class)
    public void testPollFirstEmptyDeque() {
        deque = new ResizingDequeImpl<>();
        deque.pollFirst();
    }

    @Test
    public void testPollFirstOneElement() {
        int e = dequeOne.pollFirst();

        Object[] actual = (Object[]) dequeOne.getArray();

        assertEquals(1, e);
        assertEquals(0, dequeOne.size());
        assertEquals(2,  ((Object[]) dequeOne.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
    }

    @Test
    public void testPollFirstTwoElements() {
        int e1 = dequeTwo.pollFirst();

        Object[] actual = (Object[]) dequeTwo.getArray();
        int head = dequeTwo.peekFirst();
        int tail = dequeTwo.peekLast();

        assertEquals(1, e1);
        assertEquals(1, dequeTwo.size());
        assertEquals(2,  ((Object[]) dequeTwo.getArray()).length);
        assertNull(actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(2, head);
        assertEquals(2, tail);
    }
    @Test
    public void testPollFirstPollFirst() {
        int e1 = dequeTwo.pollFirst();
        int e2 = dequeTwo.pollFirst();

        Object[] actual = (Object[]) dequeTwo.getArray();

        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(0, dequeTwo.size());
        assertEquals(2,  ((Object[]) dequeTwo.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
    }

    @Test
    public void testPollFirstThreeElements() {
        int e1 = dequeThree.pollFirst();
        int e2 = dequeThree.pollFirst();

        Object[] actual = (Object[]) dequeThree.getArray();
        int head = dequeThree.peekFirst();
        int tail = dequeThree.peekLast();

        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(1, dequeThree.size());
        assertEquals(4,  ((Object[]) dequeThree.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
        assertEquals(3, actual[2]);
        assertEquals(3, head);
        assertEquals(3, tail);
    }

    @Test
    public void testPollFirstFourElementsResizing() {
        dequeFour.pollFirst();
        dequeFour.pollFirst();
        dequeFour.pollFirst();
        dequeFour.pollFirst();

        Object[] actual = (Object[]) dequeFour.getArray();

        assertEquals(0, dequeFour.size());
        assertEquals(2,  ((Object[]) dequeFour.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
    }

    /** Basic pollLast() Tests **/
    @Test(expected = NoSuchElementException.class)
    public void testPollLastEmptyDeque() {
        deque = new ResizingDequeImpl<>();
        deque.pollLast();
    }

    @Test
    public void testPollLastOneElement() {
        int e = dequeOne.pollLast();

        Object[] actual = (Object[]) dequeOne.getArray();

        assertEquals(1, e);
        assertEquals(0, dequeOne.size());
        assertEquals(2,  ((Object[]) dequeOne.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
    }
    @Test
    public void testPollLastTwoElement() {
        int e2 = dequeTwo.pollLast();

        Object[] actual = (Object[]) dequeTwo.getArray();
        int head = dequeTwo.peekFirst();
        int tail = dequeTwo.peekLast();

        assertEquals(2, e2);
        assertEquals(1, dequeTwo.size());
        assertEquals(2,  ((Object[]) dequeTwo.getArray()).length);
        assertEquals(1, actual[0]);
        assertNull(actual[1]);
        assertEquals(1, head);
        assertEquals(1, tail);
    }
    @Test
    public void testPollLastPollLast() {
        int e2 = dequeTwo.pollLast();
        int e1 = dequeTwo.pollLast();

        Object[] actual = (Object[]) dequeTwo.getArray();

        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(0, dequeTwo.size());
        assertEquals(2,  ((Object[]) dequeTwo.getArray()).length);
        assertNull(actual[0]);
        assertNull(actual[1]);
    }
    @Test
    public void testPollLastThreeElement() {
        int e3 = dequeThree.pollLast();
        int e2 = dequeThree.pollLast();

        int head = dequeThree.peekFirst();
        int tail = dequeThree.peekLast();

        assertEquals(2, e2);
        assertEquals(3, e3);
        assertEquals(1, dequeThree.size());
        assertEquals(4,  ((Object[]) dequeThree.getArray()).length);
        assertEquals(1, head);
        assertEquals(1, tail);
    }
    @Test
    public void testPollTailIsEnd() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        int e1 = deque.pollLast();
        int e2 = deque.pollLast();
        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(0, deque.size());
        assertEquals(2,  ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testPollLastFourElement() {
        int e4 = dequeFour.pollLast();
        int e3 = dequeFour.pollLast();
        int e2 = dequeFour.pollLast();
        int e1 = dequeFour.pollLast();

        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(3, e3);
        assertEquals(4, e4);
        assertEquals(0, dequeFour.size());
        assertEquals(2,  ((Object[]) dequeFour.getArray()).length);
    }

    /** PEEK FIRST TESTS **/
    @Test(expected = NoSuchElementException.class)
    public void testPeekFirstEmptyDeque() {
        deque = new ResizingDequeImpl<>();
        deque.peekFirst();
    }

    @Test
    public void testPeekFirstOneElementAddFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        int e = deque.peekFirst();
        assertEquals(1, e);
    }

    @Test
    public void testPeekFirstOneElementAddLast() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        int e = deque.peekFirst();
        assertEquals(1, e);
    }

    @Test
    public void testPeekFirstTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        int e = deque.peekFirst();
        assertEquals(2, e);
    }

    @Test
    public void testPeekFirstThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        int e = deque.peekFirst();
        assertEquals(3, e);
    }

    @Test
    public void testPeekFirstFourElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        int e = deque.peekFirst();
        assertEquals(4, e);
    }
    @Test
    public void testPeekFirstMixed() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        int e = deque.peekFirst();
        assertEquals(5, e);
    }

    /** PEEK LAST TESTS **/
    @Test(expected = NoSuchElementException.class)
    public void testPeekLastEmptyDeque() {
        deque = new ResizingDequeImpl<>();
        deque.peekLast();
    }

    @Test
    public void testPeekLastOneElementAddFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        int e = deque.peekLast();
        assertEquals(1, e);
    }

    @Test
    public void testPeekLastOneElementAddLast() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        int e = deque.peekLast();
        assertEquals(1, e);
    }

    @Test
    public void testPeekLastTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        int e = deque.peekLast();
        assertEquals(1, e);
    }

    @Test
    public void testPeekLastThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        int e = deque.peekLast();
        assertEquals(1, e);
    }

    @Test
    public void testPeekLastFourElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        int e = deque.peekLast();
        assertEquals(1, e);
    }
    @Test
    public void testPeekLastMixed() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        int e = deque.peekLast();
        assertEquals(6, e);
    }

    /** COMBINATION TESTS **/
    @Test
    public void testAddFirst7() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(3);
        deque.addFirst(7);

        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1, actual[0]);
        assertEquals(3, actual[1]);
        assertNull(actual[2]);
        assertEquals(7, actual[3]);

        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddLast9NormalIndex() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(4);
        deque.addLast(3);

        deque.addLast(9);

        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertEquals(4, actual[2]);
        assertEquals(3, actual[3]);
        assertEquals(9, actual[4]);
        assertNull(actual[5]);
        assertNull(actual[6]);
        assertNull(actual[7]);

        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testExampleAddLast9ShiftedIndex() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(0);
        deque.addLast(0);
        deque.addLast(4);
        deque.addLast(3);
        deque.pollFirst();
        deque.pollFirst();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(9);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(2, head);
        assertEquals(9, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(2, actual[0]);
        assertEquals(4, actual[1]);
        assertEquals(3, actual[2]);
        assertEquals(1, actual[3]);
        assertEquals(9, actual[4]);
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testExampleAddLast5() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(0);
        deque.addLast(0);
        deque.addLast(0);
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.addLast(5);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(5, actual[0]);
        assertNull(actual[1]);
        assertNull(actual[2]);
        assertNull(actual[3]);
        assertEquals(1, actual[4]);
        assertEquals(2, actual[5]);
        assertEquals(3, actual[6]);
        assertEquals(4, actual[7]);
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testExampleAddFirst5() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(0);
        deque.addLast(0);
        deque.addLast(0);
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.addFirst(5);
        Object[] actual = (Object[]) deque.getArray();
        assertNull(actual[0]);
        assertNull(actual[1]);
        assertNull(actual[2]);
        assertEquals(5, actual[3]);
        assertEquals(1, actual[4]);
        assertEquals(2, actual[5]);
        assertEquals(3, actual[6]);
        assertEquals(4, actual[7]);
        assertEquals(5, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testExampleAddFirst3() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(0);
        deque.pollLast();
        deque.addFirst(3);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1, actual[0]);
        assertEquals(2, actual[1]);
        assertNull(actual[2]);
        assertEquals(3, actual[3]);
        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);
    }

    /** LARGER ARRAY TEST **/
    @Test
    public void testEightElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        assertEquals(8, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);

        deque.pollLast();
        deque.pollFirst();
        deque.pollLast();
        deque.pollFirst();
        assertEquals(4, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);

        deque.pollLast();
        deque.pollFirst();
        assertEquals(2, deque.size());
        assertEquals(8, ((Object[]) deque.getArray()).length);

        deque.pollLast();
        assertEquals(1, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);

        deque.pollFirst();
        assertEquals(0, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddFirstPollFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.pollFirst();

        Object[] actual = (Object[]) deque.getArray();
        assertNull(actual[0]);
        assertNull(actual[1]);

        assertEquals(0, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddLastPollFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.pollFirst();

        Object[] actual = (Object[]) deque.getArray();
        assertNull(actual[0]);
        assertNull(actual[1]);

        assertEquals(0, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddFirstPollLast() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.pollLast();

        Object[] actual = (Object[]) deque.getArray();
        assertNull(actual[0]);
        assertNull(actual[1]);

        assertEquals(0, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddLastPollLast() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.pollLast();

        Object[] actual = (Object[]) deque.getArray();
        assertNull(actual[0]);
        assertNull(actual[1]);

        assertEquals(0, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);
    }

    @Test
    public void testAddRemoveFourthElement() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, deque.size());
        assertEquals(2, ((Object[]) deque.getArray()).length);

        deque.addFirst(3);
        deque.addFirst(4);
        assertEquals(4, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);

        deque.pollLast();
        assertEquals(3, deque.size());
        assertEquals(4, ((Object[]) deque.getArray()).length);

        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(4, head);
        assertEquals(2, tail);
    }

    /** RESIZING UP TEST **/
    @Test
    public void testResizeUpManual() {
        deque = new ResizingDequeImpl<>();
        assertEquals(2, ((Object[]) deque.getArray()).length);
        deque.resizeUp();
        assertEquals(4, ((Object[]) deque.getArray()).length);
        deque.resizeUp();
        assertEquals(8, ((Object[]) deque.getArray()).length);
        deque.resizeUp();
        assertEquals(16, ((Object[]) deque.getArray()).length);
    }

    /** RESIZING UP WITH ADD LAST AND ADD FIRST TEST **/
    @Test
    public void testAddFirstAddLastAddLastAddLast() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(1, head);
        assertEquals(4, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1,actual[0]);
        assertEquals(2,actual[1]);
        assertEquals(3,actual[2]);
        assertEquals(4,actual[3]);
    }
    @Test
    public void testAddFirstAddLastAddLastAddFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(4, head);
        assertEquals(3, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1,actual[0]);
        assertEquals(2,actual[1]);
        assertEquals(3,actual[2]);
        assertEquals(4,actual[3]);
    }
    @Test
    public void testAddFirstAddLastAddLastAddFirstAddFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addFirst(5);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(5, head);
        assertEquals(3, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(4,actual[0]);
        assertEquals(1,actual[1]);
        assertEquals(2,actual[2]);
        assertEquals(3,actual[3]);
        assertEquals(5,actual[7]);
    }
    @Test
    public void testAddFirstAddLastAddLastAddFirstAddLast() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addLast(5);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(4, head);
        assertEquals(5, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(4,actual[0]);
        assertEquals(1,actual[1]);
        assertEquals(2,actual[2]);
        assertEquals(3,actual[3]);
        assertEquals(5,actual[4]);
    }
    @Test
    public void testAddLastAddFirstAddLastAddFirstAddFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addFirst(5);

        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(5, head);
        assertEquals(3, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(4,actual[0]);
        assertEquals(2,actual[1]);
        assertEquals(1,actual[2]);
        assertEquals(3,actual[3]);
        assertEquals(5,actual[7]);
    }
    @Test
    public void testAddLastAddFirstAddLastAddFirstAddLast() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(1);
        deque.addFirst(2);
        deque.addLast(3);
        deque.addFirst(4);
        deque.addLast(5);
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(4, head);
        assertEquals(5, tail);
        Object[] actual = (Object[]) deque.getArray();
        assertEquals(4,actual[0]);
        assertEquals(2,actual[1]);
        assertEquals(1,actual[2]);
        assertEquals(3,actual[3]);
        assertEquals(5,actual[4]);
    }

    /** RESIZING DOWN WITH POLL FIRST AND POLL LAST TESTS **/
    @Test
    public void test16Elements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.addLast(11);
        deque.addLast(12);
        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.addLast(16);

        Object[] actual = (Object[]) deque.getArray();
        assertEquals(1,actual[0]);
        assertEquals(2,actual[1]);
        assertEquals(3,actual[2]);
    }
    @Test
    public void testEightElementsAddFirstAddLastPollFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        int e7 = deque.pollFirst();
        int e5 = deque.pollFirst();
        int e3 = deque.pollFirst();
        int e1 = deque.pollFirst();
        int e2 = deque.pollFirst();
        int e4 = deque.pollFirst();
        int e6 = deque.pollFirst();
        assertEquals(1,deque.size());
        assertEquals(4,((Object[]) deque.getArray()).length);
        int e8 = deque.pollFirst();
        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(3, e3);
        assertEquals(4, e4);
        assertEquals(5, e5);
        assertEquals(6, e6);
        assertEquals(7, e7);
        assertEquals(8, e8);
    }
    @Test
    public void testEightElementsAddFirstAddLastPollLast() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addFirst(5);
        deque.addLast(6);
        deque.addFirst(7);
        deque.addLast(8);
        int e8 = deque.pollLast();
        int e6 = deque.pollLast();
        int e4 = deque.pollLast();
        int e2 = deque.pollLast();
        int e1 = deque.pollLast();
        int e3 = deque.pollLast();
        int e5 = deque.pollLast();
        assertEquals(1,deque.size());
        assertEquals(4,((Object[]) deque.getArray()).length);
        int e7 = deque.pollLast();
        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(3, e3);
        assertEquals(4, e4);
        assertEquals(5, e5);
        assertEquals(6, e6);
        assertEquals(7, e7);
        assertEquals(8, e8);
    }
    @Test
    public void testEightElementsPollLastTailLessThanHead() {
        deque = new ResizingDequeImpl<>();
        deque.addLast(8);
        deque.addFirst(4);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(6);
        deque.addFirst(3);
        deque.addFirst(5);
        deque.addFirst(7);

        int e6 = deque.pollLast();
        int e8 = deque.pollLast();
        int e4 = deque.pollLast();
        int e2 = deque.pollLast();

        int e1 = deque.pollLast();
        int e3 = deque.pollLast();
        int e5 = deque.pollLast();
        assertEquals(1,deque.size());
        assertEquals(4,((Object[]) deque.getArray()).length);
        int e7 = deque.pollLast();
        assertEquals(1, e1);
        assertEquals(2, e2);
        assertEquals(3, e3);
        assertEquals(4, e4);
        assertEquals(5, e5);
        assertEquals(6, e6);
        assertEquals(7, e7);
        assertEquals(8, e8);
    }
    @Test
    public void testIslandDebugging() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(2);
        deque.addLast(3);

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(2,deque.size());
        assertEquals(2,(actual.length));
        assertEquals(2,(actual[0]));
        assertEquals(3,(actual[1]));
        assertEquals(2,(head));
        assertEquals(3,(tail));

        deque.pollFirst();

        actual = (Object[]) deque.getArray();
        head = deque.peekFirst();
        tail = deque.peekLast();
        assertEquals(1,deque.size());
        assertEquals(2,(actual.length));
        assertNull(actual[0]);
        assertEquals(3,(actual[1]));
        assertEquals(3,(head));
        assertEquals(3,(tail));

        deque.pollFirst();

        actual = (Object[]) deque.getArray();
        assertEquals(0,deque.size());
        assertEquals(2,(actual.length));
        assertNull(actual[0]);
    }
    @Test
    public void testResizeDownTailLessThanHead() {
        deque = dequeSixteen;
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();
        dequeSixteen.addLast(1);
        dequeSixteen.pollFirst();
        dequeSixteen.pollFirst();

        Object[] actual = (Object[]) deque.getArray();
        int head = deque.peekFirst();
        int tail = deque.peekLast();
        assertEquals(8,actual.length);
        assertEquals(3,deque.size());
        assertEquals(15,head);
        assertEquals(1,tail);
        assertEquals(15,actual[0]);
        assertEquals(16,actual[1]);
        assertEquals(1,actual[2]);
        assertNull(actual[4]);
        assertNull(actual[5]);
        assertNull(actual[6]);
        assertNull(actual[7]);
    }
    @Test
    public void testSixteenPollFirst() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.addLast(11);
        deque.addLast(12);
        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollLast();
        deque.pollLast();
        deque.pollLast();

        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(9,head);
        assertEquals(12,tail);

        deque.pollFirst();
        Object[] actual = (Object[]) deque.getArray();
        head = deque.peekFirst();
        tail = deque.peekLast();

        assertEquals(10,actual[0]);
        assertEquals(11,actual[1]);
        assertEquals(12,actual[2]);
        assertEquals(10,head);
        assertEquals(12,tail);
    }
    @Test
    public void testSixteenPollLast() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(0);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.addLast(6);
        deque.addLast(7);
        deque.addLast(8);
        deque.addLast(9);
        deque.addLast(10);
        deque.addLast(11);
        deque.addLast(12);
        deque.addLast(13);
        deque.addLast(14);
        deque.addLast(15);
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollFirst();
        deque.pollLast();
        deque.pollLast();
        deque.pollLast();

        int head = deque.peekFirst();
        int tail = deque.peekLast();

        assertEquals(9,head);
        assertEquals(12,tail);

        deque.pollLast();
        Object[] actual = (Object[]) deque.getArray();
        head = deque.peekFirst();
        tail = deque.peekLast();

        assertEquals(9,actual[0]);
        assertEquals(10,actual[1]);
        assertEquals(11,actual[2]);
        assertEquals(9,head);
        assertEquals(11,tail);
    }

    /** ITERATOR TESTS **/
    @Test
    public void testIteratorEmpty() {
        deque = new ResizingDequeImpl<>();
        Iterator<Integer> iter = deque.iterator();
        assertFalse(iter.hasNext());
    }
    @Test
    public void testIteratorOneElement() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        Iterator<Integer> iter = deque.iterator();
        int first = iter.next();
        assertEquals(1, first);
        assertFalse(iter.hasNext());
    }

    @Test
    public void testIteratorFourElementsInOrder() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);

        Iterator<Integer> iter = deque.iterator();
        assertTrue(iter.hasNext());
        int e1 = iter.next();
        assertEquals(1, e1);
        assertTrue(iter.hasNext());
        int e2 = iter.next();
        assertEquals(2, e2);
        assertTrue(iter.hasNext());
        int e3 = iter.next();
        assertEquals(3, e3);
        assertTrue(iter.hasNext());
        int e4 = iter.next();
        assertEquals(4, e4);
        assertFalse(iter.hasNext());
    }
    @Test
    public void testIteratorTwoElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(1);
        deque.addLast(2);
        Iterator<Integer> iter = deque.iterator();
        int e1 = iter.next();
        assertTrue(iter.hasNext());
        int e2 = iter.next();
        assertFalse(iter.hasNext());
        assertEquals(1, e1);
        assertEquals(2, e2);
    }
    @Test
    public void testIteratorThreeElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        Iterator<Integer> iter = deque.iterator();
        int e1 = iter.next();
        assertEquals(1, e1);
        assertTrue(iter.hasNext());
        int e2 = iter.next();
        assertEquals(2, e2);
        assertTrue(iter.hasNext());
        int e3 = iter.next();
        assertEquals(3, e3);
        assertFalse(iter.hasNext());
    }
    @Test
    public void testIteratorFourElements() {
        deque = new ResizingDequeImpl<>();
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);

        Iterator<Integer> iter = deque.iterator();
        int first = iter.next();
        assertEquals(1, first);
        assertTrue(iter.hasNext());
        int second = iter.next();
        assertEquals(2, second);
        assertTrue(iter.hasNext());
        int third = iter.next();
        assertEquals(3, third);
        assertTrue(iter.hasNext());
        int fourth = iter.next();
        assertEquals(4, fourth);
        assertFalse(iter.hasNext());
    }
}

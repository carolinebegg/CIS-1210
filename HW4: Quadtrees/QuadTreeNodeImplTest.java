import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuadTreeNodeImplTest {

    private int[][] basic4x4;
    private int[][] test;

    @Before
    public void setUpTestImage() {
        basic4x4 = new int[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5},
        };
    }

    @Before
    public void setUpTest() {
        test = new int[][] {
                {1, 0},
                {0, 1},
        };
    }

    /*** NULL ARRAY TEST ***/
    @Test(expected = IllegalArgumentException.class)
    public void testNullArray() {
        QuadTreeNodeImpl.buildFromIntArray(null);
    }

    /*** EMPTY ARRAY TEST ***/
    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArray() {
        QuadTreeNodeImpl.buildFromIntArray(empty);
    }
    int[][] empty = {};

    /*** POWER OF 2 TESTS ***/
    @Test(expected = IllegalArgumentException.class)
    public void test3X3Array() {
        QuadTreeNodeImpl.buildFromIntArray(array3x3);
    }
    int[][] array3x3 = new int[][] {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0},
    };

    @Test(expected = IllegalArgumentException.class)
    public void test6X6Array() {
        QuadTreeNodeImpl.buildFromIntArray(array6x6);
    }
    int[][] array6x6 = new int[][] {
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
    };

    /*** JAGGED ARRAY TESTS ***/
    @Test(expected = IllegalArgumentException.class)
    public void testJaggedArrayOne() {
        QuadTreeNodeImpl.buildFromIntArray(jaggedOne);
    }
    int[][] jaggedOne = new int[][] {
            {0, 1, 2, 3, 4},
            {4, 5, 6, 7},
            {8, 9, 0, 1},
            {2, 3, 4, 5},
    };

    @Test(expected = IllegalArgumentException.class)
    public void testJaggedArrayTwo() {
        QuadTreeNodeImpl.buildFromIntArray(jaggedTwo);
    }
    int[][] jaggedTwo = new int[][] {
            {0, 1, 2, 3, 4},
            {4, 5, 6, 7},
            {8, 9, 0, 1, 1},
            {2, 3, 4, 5},
            {2, 3, 4, 5, 5},
    };

    /*** NON-SQUARE ARRAY TESTS ***/
    @Test(expected = IllegalArgumentException.class)
    public void testHorizontalRectangle() {
        QuadTreeNodeImpl.buildFromIntArray(horizontalRectangle);
    }
    int[][] horizontalRectangle = new int[][] {
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
            {0, 1, 2, 3, 4},
    };
    @Test(expected = IllegalArgumentException.class)
    public void testVerticalRectangle() {
        QuadTreeNodeImpl.buildFromIntArray(verticalRectangle);
    }
    int[][] verticalRectangle = new int[][] {
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2},
            {0, 1, 2},
    };

    @Test
    public void testBuildTree() {
        int[][] basic4x4 = new int[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 0, 1},
                {2, 3, 4, 5},
        };
        QuadTreeNode testTree = QuadTreeNodeImpl.buildFromIntArray(basic4x4);
    }

    /*** IS LEAF TESTS ***/
    @Test
    public void testIsLeafTrue1x1() {
        int[][] oneColor = new int[][] {
                {0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(oneColor);
        assertTrue(tree.isLeaf());
    }
    @Test
    public void testIsLeafTrue4x4() {
        int[][] oneColor = new int[][] {
                {0, 0},
                {0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(oneColor);
        assertTrue(tree.isLeaf());
    }
    @Test
    public void testIsLeafFalse4x4() {
        int[][] oneColor = new int[][] {
                {0, 1},
                {1, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(oneColor);
        assertFalse(tree.isLeaf());
    }

    @Test
    public void testIsLeafTrueOneColor() {
        int[][] oneColor = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(oneColor);
        assertTrue(tree.isLeaf());
    }

    @Test
    public void testIsLeafFalseTwoColors() {
        int[][] two = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(two);
        assertFalse(tree.isLeaf());
    }

    @Test
    public void testIsLeafFalseThreeColors() {
        int[][] three = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 2, 2},
                {1, 1, 2, 2},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(three);
        assertFalse(tree.isLeaf());
    }

    /*** GET SIZE TESTS ***/
    @Test
    public void testGetSizeOneNode() {
        int[][] one = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(1, tree.getSize());
    }

    @Test
    public void testGetSizeFiveNodes() {
        int[][] five = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(five);
        assertEquals(5, tree.getSize());
    }

    @Test
    public void testGetSizeThirteenNodes() {
        int[][] thirteen = new int[][] {
                {0, 0, 1, 1},
                {2, 2, 2, 2},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(thirteen);
        assertEquals(13, tree.getSize());
    }
    @Test
    public void testGetSizeSixteenNodes() {
        int[][] twentyOne = new int[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 16},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(twentyOne);
        assertEquals(21, tree.getSize());
    }
    @Test
    public void testGetSizeOneNode1x1() {
        int[][] thirteen = new int[][] {
                {0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(thirteen);
        assertEquals(1, tree.getSize());
    }
    @Test
    public void testGetSizeOneNode4x4() {
        int[][] thirteen = new int[][] {
                {0, 0},
                {0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(thirteen);
        assertEquals(1, tree.getSize());
    }
    @Test
    public void testGetSizeFiveNodes4x4() {
        int[][] thirteen = new int[][] {
                {0, 1},
                {1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(thirteen);
        assertEquals(5, tree.getSize());
    }

    /*** GET DIMENSION TESTS ***/
    @Test
    public void testGetDimension1x1() {
        int[][] one = new int[][] {
                {2}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(1, tree.getDimension());
    }
    @Test
    public void testGetDimension4x4() {
        int[][] one = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(4, tree.getDimension());
    }
    @Test
    public void testGetDimension16x16() {
        int[][] one = new int[][] {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(16, tree.getDimension());
    }

    /*** COMPRESSION RATIO TESTS ***/
    @Test
    public void testCompressionRatioOneNode() {
        int[][] one = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(0.0625, tree.getCompressionRatio(), 10 ^ -6);
    }
    @Test
    public void testCompressionRatioFiveNodes() {
        int[][] five = new int[][] {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(five);
        assertEquals(0.3125, tree.getCompressionRatio(), 10 ^ -6);
    }
    @Test
    public void testCompressionRatioThirteenNodes() {
        int[][] thirteen = new int[][] {
                {0, 0, 1, 1},
                {2, 2, 2, 2},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(thirteen);
        assertEquals(0.8125,tree.getCompressionRatio(), 10 ^ -6);
    }

    /*** DECOMPRESSION TESTS ***/
    @Test
    public void testDecompressionOneNode1x1() {
        int[][] original = new int[][] {
                {1}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(1, tree.getSize());
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionOneNode4x4() {
        int[][] original = new int[][] {
                {1, 1},
                {1, 1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(1, tree.getSize());
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionFiveNodes4x4() {
        int[][] original = new int[][] {
                {1, 0},
                {1, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(5, tree.getSize());
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionOneNode() {
        int[][] original = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionHalfColored() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(5, tree.getSize());
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionZigZag() {
        int[][] original = new int[][] {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionAllDifferent2x2() {
        int[][] original = new int[][] {
                {0, 1},
                {2, 3},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    @Test
    public void testDecompressionAllDifferent4x4() {
        int[][] original = new int[][] {
                {0, 1, 2, 3},
                {4, 5, 6, 7},
                {8, 9, 10, 11},
                {12, 13, 14, 16},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        int[][] modified = tree.decompress();
        assertArrayEquals(original, modified);
    }

    /*** GET COLOR TESTS ***/

    @Test(expected = IllegalArgumentException.class)
    public void testGetColorXOutOfBoundsTooHigh() {
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(test);
        tree.getColor(2,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetColorXOutOfBoundsTooLow() {
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(test);
        tree.getColor(-1,0);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testGetColorYOutOfBoundsTooHigh() {
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(test);
        tree.getColor(0,2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetColorYOutOfBoundsTooLow() {
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(test);
        tree.getColor(0,-1);
    }
    @Test
    public void testGetColorUpperLeftCorner2x2() {
        int[][] t = new int[][] {
                {1, 2},
                {3, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(1, tree.getColor(0,0));
    }
    @Test
    public void testGetColorUpperRightCorner2x2() {
        int[][] t = new int[][] {
                {1, 2},
                {3, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(2, tree.getColor(1,0));
    }
    @Test
    public void testGetColorLowerLeftCorner2x2() {
        int[][] t = new int[][] {
                {1, 2},
                {3, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(3, tree.getColor(0,1));
    }
    @Test
    public void testGetColorLowerRightCorner2x2() {
        int[][] t = new int[][] {
                {1, 2},
                {3, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(4, tree.getColor(1,1));
    }

    @Test
    public void testGetColorUpperLeftLeaf4x4() {
        int[][] t = new int[][] {
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {3, 3, 2, 2},
                {3, 3, 2, 2},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(1, tree.getColor(0,0));
        assertEquals(1, tree.getColor(0,1));
        assertEquals(1, tree.getColor(1,0));
        assertEquals(1, tree.getColor(1,1));
    }

    @Test
    public void testGetColorUpperRightLeaf4x4() {
        int[][] t = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {3, 3, 2, 2},
                {3, 3, 2, 2},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(0, tree.getColor(2, 0));
        assertEquals(0, tree.getColor(2, 1));
        assertEquals(0, tree.getColor(3, 0));
        assertEquals(0, tree.getColor(3, 1));
    }

    @Test
    public void testGetColorLowerLeftLeaf4x4() {
        int[][] t = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {3, 3, 2, 2},
                {3, 3, 2, 2},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(3, tree.getColor(0,2));
        assertEquals(3, tree.getColor(0,3));
        assertEquals(3, tree.getColor(1,2));
        assertEquals(3, tree.getColor(1,3));
    }
    @Test
    public void testGetColorLowerRightLeaf4x4() {
        int[][] t = new int[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {3, 3, 2, 2},
                {3, 3, 2, 2},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(2, tree.getColor(2,2));
        assertEquals(2, tree.getColor(2,3));
        assertEquals(2, tree.getColor(3,2));
        assertEquals(2, tree.getColor(3,3));
    }

    @Test
    public void testGetColorUpperLeftCorner4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(1, tree.getColor(0,0));
    }
    @Test
    public void testGetColorUpperRightCorner4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(2, tree.getColor(3,0));
    }
    @Test
    public void testGetColorBottomLeftCorner4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(3, tree.getColor(0,3));
    }
    @Test
    public void testGetColorBottomRightCorner4x4() {
        int[][] one = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(one);
        assertEquals(4, tree.getColor(3,3));
    }
    @Test
    public void testGetColorCenterTLeft4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(5, tree.getColor(1,1));
    }
    @Test
    public void testGetColorCenterTRight4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(6, tree.getColor(2,1));
    }
    @Test
    public void testGetColorCenterBLeft4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(7, tree.getColor(1,2));
    }
    @Test
    public void testGetColorCenterBRight4x4() {
        int[][] t = new int[][] {
                {1, -1, -2, 2},
                {-3, 5, 6, -5},
                {-4, 7, 8, -6},
                {3, -7, -8, 4},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(t);
        assertEquals(8, tree.getColor(2,2));
    }

    /*** SET COLOR TESTS ***/

    @Test
    public void testSetColor1x1Same() {
        int[][] original = new int[][] {
                {1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.setColor(0,0,1);
        assertEquals(1, tree.getColor(0,0));
    }
    @Test
    public void testSetColor1x1Different() {
        int[][] original = new int[][] {
                {1},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.setColor(0,0,2);
        assertEquals(2, tree.getColor(0,0));
    }
    @Test
    public void testSetColor2x2LeavesNoChange() {
        int[][] original = new int[][] {
                {0, 1},
                {2, 3},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(5, tree.getSize());
        tree.setColor(0,0,4);
        tree.setColor(1,0,5);
        tree.setColor(0,1,6);
        tree.setColor(1,1,7);
        assertEquals(4, tree.getColor(0,0));
        assertEquals(5, tree.getColor(1,0));
        assertEquals(6, tree.getColor(0,1));
        assertEquals(7, tree.getColor(1,1));
        assertEquals(5, tree.getSize());
    }
    @Test
    public void testSetColor4x4LeavesNoChange() {
        int[][] original = new int[][] {
                {1, 1, 2, 1},
                {1, 1, 2, 2},
                {0, 1, 1, 1},
                {1, 0, 1, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(17, tree.getSize());
        tree.setColor(0,2,1);
        tree.setColor(3,0,5);
        assertEquals(1, tree.getColor(0,2));
        assertEquals(5, tree.getColor(3,0));
        assertEquals(17, tree.getSize());
    }
    @Test
    public void testSetColor2x2LeavesToLeafOne() {
        int[][] original = new int[][] {
                {0, 0},
                {0, 3},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(5, tree.getSize());
        tree.setColor(1,1,0);
        assertEquals(0, tree.getColor(0,0));
        assertEquals(0, tree.getColor(1,0));
        assertEquals(0, tree.getColor(0,1));
        assertEquals(0, tree.getColor(1,1));
        assertEquals(1, tree.getSize());
    }

    @Test
    public void testSetColor2x2LeavesToLeaf() {
        int[][] original = new int[][] {
                {0, 1},
                {2, 3},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(5, tree.getSize());
        tree.setColor(0,0,5);
        tree.setColor(1,0,5);
        tree.setColor(0,1,5);
        tree.setColor(1,1,5);
        assertEquals(5, tree.getColor(0,0));
        assertEquals(5, tree.getColor(1,0));
        assertEquals(5, tree.getColor(0,1));
        assertEquals(5, tree.getColor(1,1));
        assertEquals(1, tree.getSize());
    }
    @Test
    public void testSetColor2x2LeafToLeaves() {
        int[][] original = new int[][] {
                {0, 0},
                {0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(1, tree.getSize());
        tree.setColor(0,0,1);
        assertEquals(1, tree.getColor(0,0));
        assertEquals(0, tree.getColor(1,0));
        assertEquals(0, tree.getColor(0,1));
        assertEquals(0, tree.getColor(1,1));
        assertEquals(5, tree.getSize());
    }
    @Test
    public void testSetColorOneLeafSameColor() {
        int[][] original = new int[][] {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        assertEquals(1, tree.getSize());
        tree.setColor(0,0,1);
        assertEquals(1, tree.getColor(0,0));
        assertEquals(9, tree.getSize());
    }

    @Test
    public void testSetColorOneLeafDifferentColor() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.setColor(3,0,0);
        int[][] modified = tree.decompress();
        int[][] expected = new int[][] {
                {0, 0, 1, 0},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        assertArrayEquals(expected, modified);
    }

    /*** GET QUADRANT TESTS ***/

    @Test
    public void testGetQuadrantTopLeft() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT);
    }
    @Test
    public void testGetQuadrantTopRight() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.getQuadrant(QuadTreeNode.QuadName.TOP_RIGHT);
    }
    @Test
    public void testGetQuadrantBottomLeft() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.getQuadrant(QuadTreeNode.QuadName.BOTTOM_LEFT);
    }
    @Test
    public void testGetQuadrantBottomRight() {
        int[][] original = new int[][] {
                {0, 0, 1, 1},
                {0, 0, 1, 1},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
        };
        QuadTreeNode tree = QuadTreeNodeImpl.buildFromIntArray(original);
        tree.getQuadrant(QuadTreeNode.QuadName.BOTTOM_RIGHT);
    }
}

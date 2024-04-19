// CIS 121, QuadTree

public class QuadTreeNodeImpl implements QuadTreeNode {
    /**
     * ! Do not delete this method !
     * Please implement your logic inside this method without modifying the signature
     * of this method, or else your code won't compile.
     * <p/>
     * As always, if you want to create another method, make sure it is not public.
     *
     * @param image image to put into the tree
     * @return the newly build QuadTreeNode instance which stores the compressed image
     * @throws IllegalArgumentException if image is null
     * @throws IllegalArgumentException if image is empty
     * @throws IllegalArgumentException if image.length is not a power of 2
     * @throws IllegalArgumentException if image, the 2d-array, is not a perfect square
     */

    private Integer color; // color of the leaf
    private int length; // length of the image
    private int nX; // top left x coordinate
    private int nY; // top left y coordinate
    private static int overallLength;

    QuadTreeNodeImpl[] children = null;

    //*** Leaf Node Constructor ***//
    QuadTreeNodeImpl(int len, int x, int y, int color) {
        this.color = color;
        this.length = len;
        nX = x;
        nY = y;
    }

    //*** Internal Node Constructor ***//
    QuadTreeNodeImpl(int[][] image, int len, int nX, int nY) {
        this.length = len;
        this.nX = nX;
        this.nY = nY;

        if (length == 1) {
            color = image[nY][nX];
        } else {
            children = new QuadTreeNodeImpl[4];
            children[0] = new QuadTreeNodeImpl(image, len / 2, nX, nY);
            children[1] = new QuadTreeNodeImpl(image, len / 2, nX + len / 2, nY);
            children[2] = new QuadTreeNodeImpl(image, len / 2, nX, nY + len / 2);
            children[3] = new QuadTreeNodeImpl(image, len / 2, nX + len / 2, nY + len / 2);
            combine();
        }
    }
    void combine() {
        if ((children != null) && (children[0].isLeaf())
                && (children[1].isLeaf()) && (children[2].isLeaf()) && (children[3].isLeaf())) {
            int c0 = children[0].color;
            int c1 = children[1].color;
            int c2 = children[2].color;
            int c3 = children[3].color;
            if ((c0 == c1) && (c1 == c2) &&  (c2 == c3)) {
                children[0] = null;
                children[1] = null;
                children[2] = null;
                children[3] = null;
                children = null;
                color = c0;
            }
        }
    }
    public static QuadTreeNodeImpl buildFromIntArray(int[][] image) {

        //*** Check if image is null ***//
        if (image == null) {
            throw new IllegalArgumentException("Image is null");
        }

        //*** Check if image is empty ***//
        int length = image.length;
        if (length == 0) {
            throw new IllegalArgumentException("Image has a length of 0");
        }

        //*** Check if image is square ***//
        int width = image[0].length;
        if (length != width) {
            throw new IllegalArgumentException("Image is not square");
        }

        //*** Check if n is a power of 2 ***//
        int n = length * width;
        while (n > 1) {
            if (n % 2 != 0) {
                throw new IllegalArgumentException("n is not a power of 2");
            }
            n = n / 2;
        }

        //*** Check if image is jagged ***//
        for (int i = 0; i < length; i++) {
            if (image[i].length != length) {
                throw new IllegalArgumentException("Image is jagged");
            }
        }
        overallLength = length;
        return new QuadTreeNodeImpl(image, length, 0,0);
    }

    @Override
    public int getColor(int x, int y) {
        if ((x > getDimension() - 1)  || (x < 0)) {
            throw new IllegalArgumentException("Invalid x bound");
        }
        if ((y > getDimension() - 1) || (y < 0)) {
            throw new IllegalArgumentException("Invalid y bound");
        }
        if (isLeaf()) {
            return color;
        } else {
            if (x < length / 2) {
                if (y < length / 2) {
                    if (children[0] != null) {
                        return children[0].getColor(x, y);
                    }
                } else {
                    if (children[2] != null) {
                        return children[2].getColor(x, y - length / 2);
                    }
                }
            } else {
                if (y < length / 2) {
                    if (children[1] != null) {
                        return children[1].getColor(x  - length / 2, y);
                    }
                } else {
                    if (children[3] != null) {
                        return children[3].getColor(x - length / 2, y - length / 2);
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public void setColor(int x, int y, int c) {
        if ((x < 0) || (x > overallLength - 1)) {
            throw new IllegalArgumentException("Invalid x bounds");
        }
        if ((y < 0) || (y > overallLength - 1)) {
            throw new IllegalArgumentException("Invalid y bounds");
        }
        if (isLeaf()) {
            if (c != color) {
                if (length == 1) {
                    color = c;
                } else {

                    children = new QuadTreeNodeImpl[4];
                    children[0] = new QuadTreeNodeImpl(length / 2, nX, nY, color);
                    children[1] = new QuadTreeNodeImpl(length / 2, nX + length / 2, nY, color);
                    children[2] = new QuadTreeNodeImpl(length / 2, nX, nY + length / 2, color);
                    children[3] = new QuadTreeNodeImpl(length / 2, nX + length / 2,
                            nY + length / 2, color);

                    color = null;

                    if (x < nX + length / 2) {
                        if (y < nY + length / 2) {
                            children[0].setColor(x, y, c);
                        } else {
                            children[2].setColor(x, y, c);
                        }
                    } else {
                        if (y < nY + length / 2) {
                            children[1].setColor(x, y, c);
                        } else {
                            children[3].setColor(x, y, c);
                        }
                    }
                }
            }
        } else {
            if (x < nX + length / 2) {
                if (y < nY + length / 2) {
                    if (children[0] != null) {
                        children[0].setColor(x,y,c);
                    }
                } else {
                    if (children[2] != null) {
                        children[2].setColor(x,y, c);
                    }
                }
            } else {
                if (y < nY + length / 2) {
                    if (children[1] != null) {
                        children[1].setColor(x, y,c);
                    }
                } else {
                    if (children[3] != null) {
                        children[3].setColor(x,y, c);
                    }
                }
            }
        }
        combine();
    }

    @Override
    public QuadTreeNode getQuadrant(QuadName quadrant) {
        if (quadrant == QuadName.TOP_LEFT) {
            return children[0];
        }
        if (quadrant == QuadName.TOP_RIGHT) {
            return children[1];
        }
        if (quadrant == QuadName.BOTTOM_LEFT) {
            return children[2];
        }
        if (quadrant == QuadName.BOTTOM_RIGHT) {
            return children[3];
        }
        return null;
    }
    @Override
    public int getDimension() {
        return length;
    }
    @Override
    public int getSize() {
        int child0 = 0;
        int child1 = 0;
        int child2 = 0;
        int child3 = 0;
        if (!isLeaf()) {
            child0 = children[0].getSize();
            child1 = children[1].getSize();
            child2 = children[2].getSize();
            child3 = children[3].getSize();
        }
        return 1 + child0 + child1 + child2 + child3;
    }

    @Override
    public boolean isLeaf() {
        return (children == null);
    }
    void traverse(int[][] array) {
        //int len = length;
        if (isLeaf()) {
            for (int i = nY; i < nY + length; i++) {
                for (int j = nX; j < nX + length; j++) {
                    array[i][j] = color;
                }
            }
        } else {
            if (children[0] != null) {
                children[0].traverse(array);
            }
            if (children[1] != null) {
                children[1].traverse(array);
            }
            if (children[2] != null) {
                children[2].traverse(array);
            }
            if (children[3] != null) {
                children[3].traverse(array);
            }
        }
    }
    @Override
    public int[][] decompress() {
        int dimension = getDimension();
        int[][] decompressed = new int[dimension][dimension];
        traverse(decompressed);
        return decompressed;
    }
    @Override
    public double getCompressionRatio() {
        return ((double) getSize() / (getDimension() * getDimension()));
    }
}

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingDequeImpl<E> implements ResizingDeque<E> {
    private E[] elements = (E[]) new Object[2];
    int head = 0;
    int tail = 0;
    int oldHead = 0;
    int oldTail = 0;
    int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public E[] getArray() {
        return elements;
    }

    @Override
    public void addFirst(E e) {
        oldHead = head;
        if (size == 0) {
            head = 0;
            tail = 0;
            elements[0] = e;
        } else {
            if (size + 1 > elements.length) {
                resizeUp();
            }
            if (head == 0) {
                elements[elements.length - 1] = e;
                head = elements.length - 1;
            } else {
                elements[head - 1] = e;
                head = head - 1;
            }
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        oldTail = tail;
        if (size == 0) {
            head = 0;
            tail = 0;
            elements[0] = e;
        } else {
            if (size + 1 > elements.length) {
                resizeUp();
            }
            if (tail == elements.length - 1) {
                elements[0] = e;
                tail = 0;
            } else {
                elements[tail + 1] = e;
                tail = tail + 1;
            }
        }
        size++;
    }
    @Override
    public E pollFirst() {
        if (size <= 0) {
            throw new NoSuchElementException("the deque is empty");
        }

        E first = elements[head];
        elements[head] = null;
        size--;
        if ((size < elements.length / 4) && (elements.length != 2)) {
            srcPos = head + 1;
            resizeDown();
        } else {
            if (head != tail) {
                if (head != elements.length - 1) {
                    head++;
                } else {
                    head = 0;
                }
            }
        }
        return first;
    }
    @Override
    public E pollLast() {
        if (size <= 0) {
            throw new NoSuchElementException("the deque is empty");
        }
        E last = elements[tail];
        elements[tail] = null;
        size--;
        if ((size < elements.length / 4) && (elements.length != 2)) {
            srcPos = head;
            resizeDown();
        } else {
            if (head != tail) {
                if (tail != 0) {
                    tail--;
                } else {
                    tail = elements.length - 1;
                }
            }
        }
        return last;
    }
    @Override
    public E peekFirst() {
        if (size <= 0) {
            throw new NoSuchElementException("the deque is empty");
        }
        return elements[head];
    }
    @Override
    public E peekLast() {
        if (size <= 0) {
            throw new NoSuchElementException("the deque is empty");
        }
        return elements[tail];
    }
    @Override
    public Iterator<E> iterator() {
        return new DequeIterator<>();
    }
    private class DequeIterator<E> implements Iterator<E> {
        private Integer curr;
        public DequeIterator() {
            curr = head;
        }
        @Override
        public boolean hasNext() {
            return (curr < elements.length && elements[curr] != null);
        }
        @Override
        public E next() {
            if (hasNext()) {
                E e = (E) elements[curr];
                if (tail < head) {
                    if (curr != elements.length - 1) {
                        curr++;
                    } else {
                        curr = 0;
                    }
                } else {
                    curr++;
                }
                if (curr == head) {
                    curr = elements.length;
                }
                return e;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
    void resizeUp() {
        E[] resized = (E[]) new Object[2 * elements.length];
        if (tail < head) {
            System.arraycopy(elements, head, resized, 0, elements.length - head);
            System.arraycopy(elements, 0, resized, elements.length - head, tail + 1);
        } else {
            System.arraycopy(elements, head, resized, 0, tail + 1);
        }
        head = 0;
        tail = size - 1;
        elements = resized;
    }
    int srcPos;
    void resizeDown() {
        E[] resized = (E[]) new Object[elements.length / 2];
        if (tail == head) {
            System.arraycopy(elements, head, resized, 0, 1);
        }
        if (tail < head) {
            System.arraycopy(elements, srcPos, resized, 0, elements.length - srcPos);
            System.arraycopy(elements, 0, resized, elements.length - srcPos, tail + 1);
        } else {
            System.arraycopy(elements, srcPos, resized, 0, size);
        }
        head = 0;
        if (size != 1) {
            tail = size - 1;
        } else {
            tail = head;
        }
        elements = resized;
    }
}

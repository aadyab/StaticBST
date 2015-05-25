import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by 40218 on 5/21/15.
 */

public class StaticBST<T>  extends ArrayBinaryTree<T> implements BinarySearchTreeADT<T> {


    private T tree[];
    private int h,mI;


    public StaticBST()
    {
        super();
        h = 0;
        mI = -1;
    }
    public StaticBST(T element)
    {
        super(element);
        h = 1;
        mI = 0;
    }
    public void addElement (T element)
    {

        if (tree.length < mI*2+3)
            expandCapacity();

        Comparable<T> temp = (Comparable<T>)element;

        if (isEmpty()) {
            tree[0] = element;
            mI = 0;
        }
        else
        {
            boolean added = false;
            int currentIndex = 0;

            while (!added)
            {



                if (temp.compareTo((tree[currentIndex]) ) < 0)
                {

                    if (tree[currentIndex*2+1] == null)
                    {
                        tree[currentIndex*2+1] = element;
                        added = true;
                        if (currentIndex*2+1 > mI)
                            mI = currentIndex*2+1;
                    }
                    else
                        currentIndex = currentIndex*2+1;
                }
                else {

                    if (tree[currentIndex*2+2] == null)
                    {
                        tree[currentIndex*2+2] = element;
                        added = true;
                        if (currentIndex*2+2 > mI)
                            mI = currentIndex*2+2;
                    }
                    else
                        currentIndex = currentIndex*2+2;
                }

            }//while
        }//else

        h = (int)(Math.log(mI + 1) / Math.log(2)) + 1;
    }
    public T[] getArray()
    {
        T[] temp;
        if (size() == 0) {
            temp = (T[]) new Object[0];
            return temp;
        }

        temp = (T[]) new Object[tree.length];
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                temp[i] = tree[i];
            else
                temp[i] = null;
        }
        return temp;
    }
    public int getHeight()
    {
        return h;
    }
    public int getMaxIndex()
    {
        return mI;
    }
    public void removeAllElements()
    {
        super.removeAllElements();
        h = 0;
        mI = -1;
    }

    public void removeAllOccurrences (T targetElement)
    {
        removeElement(targetElement);

        while (contains(targetElement))
            removeElement(targetElement);

    }
    public T findMin()
    {
        T result;

        if (isEmpty())
            return null;
        else {
            int currentIndex = 0;
            while ((currentIndex*2+1 <= mI) && (tree[currentIndex*2+1] != null))
                currentIndex = currentIndex*2+1;
            result = tree[currentIndex] ;
        }
        return result;
    }
    public T findMax()
    {
        T result;

        if (isEmpty())
            return null;
        else {
            int currentIndex = 0;
            while ((currentIndex*2+2 <= mI) && (tree[currentIndex*2+2] != null))
                currentIndex = currentIndex*2+2;
            result = tree[currentIndex] ;
        }
        return result;
    }

    public String toString()
    {
        String result = "";

        for (int i = 0; i <= mI; i++)
            if (tree[i] != null)
                result += tree[i] .toString() + "\n";

        return result;
    }
    public T removeElement (T targetElement)
    {
        T result = null;
        boolean found = false;

        if (isEmpty())
            return null;

        for (int i = 0; (i <= mI) && !found; i++) {
            if ((tree[i] != null) && targetElement.equals(tree[i]))
            {
                found = true;
                result = tree[i] ;
                replace(i);

            }
        }

        if (!found)
            return null;

        int temp = mI;
        mI = -1;
        for (int i = 0; i <= temp; i++)
            if (tree[i] != null)
               mI = i;

        h = (int)(Math.log(mI + 1) / Math.log(2)) + 1;

        return result;

    }
    private void replace (int targetIndex)
    {
        int currentIndex, oldIndex, newIndex;
       LinkedList<Integer> newlist = new LinkedList<Integer>();
        LinkedList<Integer> templist = new LinkedList<Integer>();
        LinkedList<Integer> oldlist = new LinkedList<Integer>();

        Iterator<Integer> oldIt, newIt;

        // if target node has no children
        if ((targetIndex*2+1 >= tree.length) || (targetIndex*2+2 >= tree.length))
            tree[targetIndex] = null;

            // if target node has no children
        else if ((tree[targetIndex*2+1] == null) && (tree[targetIndex*2+2] == null))
            tree[targetIndex] = null;

            // if target node only has a left child
        else {
            if ((tree[targetIndex * 2 + 1] != null) && (tree[targetIndex * 2 + 2] == null)) {

                // fill newlist with indices of nodes that will replace
                // the corresponding indices in oldlist

                // fill newlist
                currentIndex = targetIndex * 2 + 1;
                templist.addLast(currentIndex);
                while (!templist.isEmpty()) {
                    currentIndex = templist.removeFirst();
                    newlist.addLast(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                        templist.addLast(currentIndex * 2 + 1);
                        templist.addLast(currentIndex * 2 + 2);
                    }
                }

                // fill oldlist
                currentIndex = targetIndex;
                templist.addLast(currentIndex);
                while (!templist.isEmpty()) {
                    currentIndex = templist.removeFirst();
                    oldlist.addLast(currentIndex);
                    if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                        templist.addLast(currentIndex * 2 + 1);
                        templist.addLast(currentIndex * 2 + 2);
                    }
                }

                // do replacement
                oldIt = oldlist.iterator();
                newIt = newlist.iterator();
                while (newIt.hasNext()) {
                    oldIndex = oldIt.next();
                    newIndex = newIt.next();
                    tree[oldIndex] = tree[newIndex];
                    tree[newIndex] = null;
                }
            }

            // if target node only has a right child
            else {
                if ((tree[targetIndex * 2 + 1] == null) && (tree[targetIndex * 2 + 2] != null)) {

                    // fill newlist with indices of nodes that will replace
                    // the corresponding indices in oldlist

                    // fill newlist
                    currentIndex = targetIndex * 2 + 2;
                    templist.addLast(currentIndex);
                    while (!templist.isEmpty()) {
                        currentIndex = templist.removeFirst();
                        newlist.addLast(currentIndex);
                        if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                            templist.addLast(currentIndex * 2 + 1);
                            templist.addLast(currentIndex * 2 + 2);
                        }
                    }

                    // fill oldlist
                    currentIndex = targetIndex;
                    templist.addLast(currentIndex);
                    while (!templist.isEmpty()) {
                        currentIndex = templist.removeFirst();
                        oldlist.addLast(currentIndex);
                        if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                            templist.addLast(currentIndex * 2 + 1);
                            templist.addLast(currentIndex * 2 + 2);
                        }
                    }

                    // do replacement
                    oldIt = oldlist.iterator();
                    newIt = newlist.iterator();
                    while (newIt.hasNext()) {
                        oldIndex = oldIt.next();
                        newIndex = newIt.next();
                        tree[oldIndex] = tree[newIndex];
                        tree[newIndex] = null;
                    }
                }

                // target node has two children
                else {
                    currentIndex = targetIndex * 2 + 2;

                    while (tree[currentIndex * 2 + 1] != null) {
                        currentIndex = currentIndex * 2 + 1;
                    }

                    tree[targetIndex] = tree[currentIndex];

                    // the index of the root of the subtree to be replaced
                    int currentRoot = currentIndex;

                    // if currentIndex has a right child
                    if (tree[currentRoot * 2 + 2] != null) {

                        // fill newlist with indices of nodes that will replace
                        // the corresponding indices in oldlist

                        // fill newlist
                        currentIndex = currentRoot * 2 + 2;
                        templist.addLast(currentIndex);
                        while (!templist.isEmpty()) {
                            currentIndex = templist.removeFirst();
                            newlist.addLast(currentIndex);
                            if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                                templist.addLast(currentIndex * 2 + 1);
                                templist.addLast(currentIndex * 2 + 2);
                            }
                        }

                        // fill oldlist
                        currentIndex = currentRoot;
                        templist.addLast(currentIndex);
                        while (!templist.isEmpty()) {
                            currentIndex =templist.removeFirst();
                            oldlist.addLast(currentIndex);
                            if ((currentIndex * 2 + 2) <= (Math.pow(2, h) - 2)) {
                                templist.addLast(currentIndex * 2 + 1);
                                templist.addLast(currentIndex * 2 + 2);
                            }
                        }

                        // do replacement
                        oldIt = oldlist.iterator();
                        newIt = newlist.iterator();
                        while (newIt.hasNext()) {
                            oldIndex = oldIt.next();
                            newIndex = newIt.next();
                            tree[oldIndex] = tree[newIndex];
                            tree[newIndex] = null;
                        }
                    } else
                        tree[currentRoot] = null;

                }
            }
        }

    }


}

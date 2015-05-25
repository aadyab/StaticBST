/**
 * Created by 40218 on 5/25/15.
 */
public class ArrayBinaryTree<T> implements BinaryTreeADT<T>
{

    private int count;
    private T[] tree;
    private final int capacity = 50;

    public ArrayBinaryTree()
    {
        count = 0;
        tree = (T[]) new Object[capacity];
    }

    public ArrayBinaryTree (T element)
    {
        count = 1;
        tree = (T[]) new Object[capacity];

        tree[0] = element;
    }
    public void expandCapacity()
    {
        T[] temp = (T[]) new Object[tree.length * 2];
        for (int ct=0; ct < tree.length; ct++)
            temp[ct] = tree[ct];
        tree = temp;
    }

    public void removeAllElements()
    {
        count = 0;
        for (int ct=0; ct<tree.length; ct++)
            tree[ct] = null;
    }
    public boolean isEmpty()
    {
        return (count == 0);
    }
    public int size()
    {
        return count;
    }
    public boolean contains (T targetElement)
    {
        boolean found = false;

        for (int ct=0; ct<count && !found; ct++)
            if (targetElement.equals(tree[ct]))
                found = true;

        return found;

    }
    public T find (T targetElement)
    {
        T temp=null;
        boolean found = false;

        for (int ct=0; ct<count && !found; ct++)
            if (targetElement.equals(tree[ct]))
            {
                found = true;
                temp = tree[ct];
            }

        if (!found)
            return null;

        return temp;


    }

}


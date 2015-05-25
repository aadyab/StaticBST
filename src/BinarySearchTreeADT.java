/**
 * Created by 40218 on 5/25/15.
 */
public interface BinarySearchTreeADT<T> extends BinaryTreeADT<T>
{

    public void addElement (T element);
    public void removeAllOccurrences (T targetElement);
    public T findMin();
    public T removeElement (T targetElement);
    public T findMax();

}


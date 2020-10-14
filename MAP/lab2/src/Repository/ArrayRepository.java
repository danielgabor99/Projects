package Repository;

import Model.Animale;


public class ArrayRepository implements Repository {
    private int myIndex;
    private Animale[] myarray;

    public ArrayRepository(int capacity) {
        myIndex = 0;
        myarray = new Animale[capacity];
    }

    public void addtoRepo(Animale A) throws Exception {
        if (myIndex < myarray.length) {
            myarray[myIndex] = A;
            myIndex++;
        } else
            throw new MyException("Max capacity");
    }

    public void updateRepo(int index, int w) throws Exception {
        if (index >= 0 && index < myIndex)
            myarray[index].setWeight(w);
        else
            throw new MyException("Invalid index to update");
    }

    public void deletefromRepo(int index) throws Exception {
        if (index >= 0 && index < myIndex) {
            if (index == myIndex - 1)
                myIndex--;
            else {
                myarray[index] = myarray[myIndex - 1];
                myIndex--;
            }
        } else {
            throw new MyException("Invalid index to delete");
        }
    }

    public Animale[] getAll() {
        Animale[] copy = new Animale[myIndex];
        System.arraycopy(myarray, 0, copy, 0, myIndex);
        return copy;
    }

    public int getSize() {
        return this.myIndex;
    }

    public Animale[] Filter(int w) {
        Animale[] copy = new Animale[myIndex];
        int size = 0;
        for (int i = 0; i <= myIndex; i++)
            if (myarray[i].getWeight() > w) {
                copy[i] = myarray[i];
                size++;
            }
        size--;
        Animale[] copy2 = new Animale[size];
        if (size > 0) System.arraycopy(copy, 0, copy2, 0, size);
        return copy2;
    }
}

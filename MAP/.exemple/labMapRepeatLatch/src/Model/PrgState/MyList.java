package Model.PrgState;

import java.io.LineNumberInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyList<T> implements MyIList<T> {
    private LinkedList<T> List;

    public MyList() {
        this.List = new LinkedList<T>();
    }

    @Override
    public void addB(T object) {
        List.addLast(object);
    }

    @Override
    public LinkedList<T> getValues() {
        return this.List;
    }


    @Override
    public T getFirst() {
        return List.getFirst();
    }

    public String toString() {
        String output = "Out:\n";
        for (int i = 0; i < List.size(); i++)
            output = output + List.get(i).toString() + " ";
        if (output.equals("Out:\n"))
            return output;
        return output + "\n";
    }
}
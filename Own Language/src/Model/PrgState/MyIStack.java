package Model.PrgState;

import Model.IStmt;

import java.util.List;

public interface MyIStack<T> {

    T pop();

    void push(T v);

    public boolean isEmpty();

    List<T> convertToList();
}

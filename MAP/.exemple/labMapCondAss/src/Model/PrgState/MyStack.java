package Model.PrgState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    // CollectionType<T> stack; //a field whose type CollectionType is an appropriate
    // generic java library collection

    private Stack<T> stack;

    public MyStack() {
        stack = new Stack<T>();
    }

    public T pop() {
        return stack.pop();
    }

    public void push(T v) {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        return stack.empty();
    }

    @Override
    public List<T> convertToList() {
        List<T> stackList = new ArrayList<T>(stack);
        Collections.reverse(stackList);
        return stackList;
    }

    public String toString() {
        String output = "";
        for (T v : stack)
            output = "[" + v.toString() + "]\n" + output;
        output = "ExeStack:\n" + output;
        if (output.equals("ExeStack:\n"))
            output = "ExeStack: empty\n";
        return output;
    }
}










































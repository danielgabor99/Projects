package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

public class ValueExp implements Exp {
    Value e;

    public ValueExp(Value value) {
        this.e = value;
    }

    public String toString() {
        return this.e.toString();
    }

    ///
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        return e;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }
}

package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

public class VarExp implements Exp {
    String id;

    public VarExp(String v) {
        this.id = v;
    }

    public String toString() {
        String result = "";
        result = result + this.id;
        return result;
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        return tbl.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }

}

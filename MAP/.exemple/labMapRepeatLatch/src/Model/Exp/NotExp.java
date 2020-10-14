package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import com.company.MyException;

public class NotExp implements Exp {
    Exp exp;

    public NotExp(Exp e) {
        exp = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        boolean b = (boolean) exp.eval(tbl, hp).getVal();
        return new BoolValue(!b);

    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

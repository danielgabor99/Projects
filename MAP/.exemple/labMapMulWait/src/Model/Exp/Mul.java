package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

public class Mul implements Exp {
    Exp exp1, exp2;

    public Mul(Exp e1, Exp e2) {
        exp1 = e1;
        exp2 = e2;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1 = exp1.eval(tbl, hp);
        int n1 = (int) v1.getVal();
        Value v2 = exp2.eval(tbl, hp);
        int n2 = (int) v2.getVal();
        int a = (n1 * n2) - (n1 + n2);
        return new IntValue(a);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

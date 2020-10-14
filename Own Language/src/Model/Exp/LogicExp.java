package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

public class LogicExp implements Exp {
    Exp e1;
    Exp e2;
    int op;

    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value nr1, nr2;
        nr1 = e1.eval(tbl, hp);
        if (nr1.getType().equals(new BoolType())) {
            nr2 = e2.eval(tbl, hp);
            if (nr2.getType().equals(new BoolType())) {
                nr1 = nr2;
            } else
                throw new MyException(" \"Operand2 is not a boolean\"");
        } else
            throw new MyException(" \"Operand2 is not a boolean\"");
        return nr1;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type nr1, nr2;
        nr1 = e1.typecheck(typeEnv);
        if (nr1.equals(new BoolType())) {
            nr2 = e2.typecheck(typeEnv);
            if (nr2.equals(new BoolType())) {
                nr1 = nr2;
            } else
                throw new MyException(" \"Operand2 is not a boolean\"");
        } else
            throw new MyException(" \"Operand2 is not a boolean\"");
        return nr1;
    }
}

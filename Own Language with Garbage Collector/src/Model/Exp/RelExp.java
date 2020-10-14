package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

public class RelExp implements Exp {
    Exp e1;
    Exp e2;
    String op;

    public RelExp(Exp v, Exp valueExp, String s) {
        e1 = v;
        e2 = valueExp;
        op = s;
    }

    public RelExp notExpp() {
        return new RelExp(this.e1, this.e2, "!=");
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            Value v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                int nr1 = ((IntValue) v1).getVal();
                int nr2 = ((IntValue) v2).getVal();
                switch (op) {
                    case "<":
                        if (nr1 < nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case "<=":
                        if (nr1 <= nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case "==":
                        if (nr1 == nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case "!=":
                        if (nr1 != nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);

                    case ">":
                        if (nr1 > nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    case ">=":
                        if (nr1 >= nr2)
                            return new BoolValue(true);
                        else
                            return new BoolValue(false);
                    default:
                        throw new MyException("invalid op");
                }
            } else throw new MyException("invalid expression");

        } else throw new MyException("invalid expression");
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1 = e1.typecheck(typeEnv);
        Type typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return (Type) new BoolValue(true);
            } else throw new MyException("invalid expression");
        } else throw new MyException("invalid expression");
    }

    public String toString() {
        String result = "(" + e1.toString() + op + e2.toString() + ")";
        return result;
    }

}

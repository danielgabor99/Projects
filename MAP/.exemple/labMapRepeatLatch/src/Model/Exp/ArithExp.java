package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

public class ArithExp implements Exp {
    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    public ArithExp(char c, Exp valueExp, Exp valueExp1) {
        this.e1 = valueExp;
        this.e2 = valueExp1;
        if (c == '+')
            this.op = 1;
        if (c == '-')
            this.op = 2;
        if (c == '*')
            this.op = 3;
        if (c == '/')
            this.op = 4;
    }

    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v1, v2;
        v1 = e1.eval(tbl, hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl, hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue) v1;
                IntValue i2 = (IntValue) v2;
                int n1, n2;
                n1 = ((IntValue) v1).getVal();
                n2 = ((IntValue) v2).getVal();
                if (op == 1) return new IntValue(n1 + n2);
                if (op == 2) return new IntValue(n1 - n2);
                if (op == 3) return new IntValue(n1 * n2);
                if (op == 4)
                    if (n2 == 0) throw new MyException("division by zero");
                    else return new IntValue(n1 / n2);
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
        return v1;
    }

    public String toString() {
        String output = "";
        if (this.op == 1)
            output = this.e1.toString() + "+" + this.e2.toString();
        if (this.op == 2)
            output = this.e1.toString() + "-" + this.e2.toString();
        if (this.op == 3)
            output = this.e1.toString() + "*" + this.e2.toString();
        if (this.op == 4)
            output = this.e1.toString() + "/" + this.e2.toString();
        return output;
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1 = e1.typecheck(typeEnv);
        typ2 = e2.typecheck(typeEnv);
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new IntType();
            } else
                throw new MyException("second operand is not an integer");
        } else
            throw new MyException("first operand is not an integer");
    }
}
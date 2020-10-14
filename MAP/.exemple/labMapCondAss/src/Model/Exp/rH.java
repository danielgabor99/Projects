package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import com.company.MyException;

public class rH implements Exp {
    Exp exp;

    public rH(Exp e) {
        exp = e;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {
        Value v = exp.eval(tbl, hp);//v:(1 int)
        if (v instanceof RefValue) {
            RefValue rv = (RefValue) v;
            int addr = rv.getAddress();
            if (hp.isKey(addr)) {
                return hp.getVal(addr);
            } else {
                throw new MyException("Invalid address");
            }
        } else throw new MyException("not a ref value");
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ = exp.typecheck(typeEnv);
        if (typ instanceof RefType) {
            RefType reft = (RefType) typ;
            return reft.getInner();
        } else
            throw new MyException("the rH argument is not a Ref Type");
    }

    @Override
    public String toString() {
        return "wH(" + exp.toString() + ")";
    }
}

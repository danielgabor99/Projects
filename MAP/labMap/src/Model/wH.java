package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.PrgState.PrgState;
import Model.Type.RefType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class wH implements IStmt {
    String var_name;
    Exp exp;

    public wH(String v, Exp e) {
        var_name = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (symTbl.isDefined(var_name)) {
            Value v = symTbl.getValue(var_name);//v:(1 int)
            if (v.getType() instanceof RefType) {
                RefValue rv = (RefValue) v;
                if (heap.isKey(rv.getAddress())) {
                    Value ev = exp.eval(symTbl, heap);//ev:30
                    if (ev.getType().equals(((RefType) rv.getType()).getInner())) {
                        heap.update(rv.getAddress(), ev);
                        return null;
                    } else throw new MyException("invalid type");
                } else throw new MyException("invalid address");
            } else throw new MyException("invalid type");
        } else throw new MyException("variable not defined");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    @Override
    public String toString() {
        return "wH(" + var_name + ", " + exp.toString() + ")";
    }
}

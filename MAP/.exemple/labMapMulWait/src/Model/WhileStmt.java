package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class WhileStmt implements IStmt {
    Exp exp;
    IStmt stmt;

    public WhileStmt(Exp e, IStmt s) {
        exp = e;
        stmt = s;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap heap = state.getHeap();
        Value v = exp.eval(symTbl, heap);
        if (v.getType().equals(new BoolType())) {
            BoolValue bv = (BoolValue) v;
            if (bv.getVal() == true)//daca v>0
            {
                state.getStk().push(new WhileStmt(exp, stmt));
                state.getStk().push(stmt);
            }
            return null;
        } else throw new MyException("invalid type");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1 = (Type) stmt.typecheck(typeEnv);
        Type typ2 = exp.typecheck(typeEnv);
        if (typ1.equals(typ2))
            return typeEnv;
        else
            throw new MyException("Diferrent types");
    }

    public String toString() {
        String result = "while" + exp.toString() + stmt.toString();
        return result;
    }
}

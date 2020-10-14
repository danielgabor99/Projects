package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class IfStmt implements IStmt {
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        exp = e;
        thenS = t;
        elseS = el;
    }

    public String toString() {
        return "IF(" + exp.toString() + ") THEN(" + thenS.toString()
                + ")ELSE(" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        Value val = exp.eval(symTbl, state.getHeap());
        if (((Boolean) val.getVal())) {
            return thenS.execute(state);
        } else
            return elseS.execute(state);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(clone(typeEnv));//clonarea
            elseS.typecheck(clone(typeEnv));
            return typeEnv;
        } else
            throw new MyException("The condition of IF has not the type bool");
    }

    private MyIDictionary<String, Type> clone(MyIDictionary<String, Type> typeEnv) {
        return null;
    }
}


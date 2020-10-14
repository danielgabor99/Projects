package Model;

import Model.Exp.Exp;
import Model.Exp.VarExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIList;
import Model.PrgState.MyList;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

public class PrintStmt implements IStmt {
    Exp exp;

    public PrintStmt(Exp v) {
        this.exp = v;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIList<Value> out = new MyList<>();
        out = state.getOut();
        MyIDictionary<String, Value> tbl = state.getSymTable();
        out.addB(this.exp.eval(tbl, state.getHeap()));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}

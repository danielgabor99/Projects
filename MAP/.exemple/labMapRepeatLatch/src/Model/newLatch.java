package Model;

import Model.Exp.Exp;
import Model.Exp.ValueExp;
import Model.PrgState.ILatch;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class newLatch implements IStmt {
    String var;
    Exp exp;

    public newLatch(String v, Exp e) {
        var = v;
        exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        ILatch<Integer, Integer> latch = state.getLatch();
        int i = (int) exp.eval(symTbl, state.getHeap()).getVal();
        int newfree = latch.getFirstFree();
        latch.update(newfree, i);
        symTbl.update(var, new IntValue(newfree));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

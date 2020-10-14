package Model;

import Model.Exp.Exp;
import Model.PrgState.IBarrier;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class newBarrier implements IStmt {
    String var;
    Exp exp;

    public newBarrier(String v, Exp e) {
        var = v;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IBarrier<Integer, Pair<Integer, List<Integer>>> bar = state.getBarrier();
        int v = (int) exp.eval(symTbl, state.getHeap()).getVal();
        List<Integer> l = new ArrayList<>();
        int i = bar.getFirstFree();
        Pair<Integer, List<Integer>> p = new Pair<>(i, l);
        bar.update(i, p);
        symTbl.update(var, new IntValue(i));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

package Model;

import Model.Exp.Exp;
import Model.PrgState.IBarrier;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.IntType;
import Model.Type.RefType;
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

        if (!(exp.eval(symTbl, state.getHeap()).getType() instanceof IntType))
            throw new MyException("Invalid type");

        int nr = (int) exp.eval(symTbl, state.getHeap()).getVal();

        List<Integer> l = new ArrayList<>();//empty list

        int i = bar.getFirstFree();
        Pair<Integer, List<Integer>> p = new Pair<>(nr, l);

        bar.update(i, p);

        symTbl.update(var, new IntValue(i));
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp) && typevar instanceof IntType)
            return typeEnv;
        else
            throw new MyException("different types");
    }

    @Override
    public String toString() {
        return "new Barrier(" + var.toString() + "," + exp.toString() + ")";
    }
}

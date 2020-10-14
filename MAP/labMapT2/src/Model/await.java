package Model;

import Model.PrgState.IBarrier;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.IntType;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.List;

public class await implements IStmt {
    String var;

    public await(String v) {
        var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IBarrier<Integer, Pair<Integer, List<Integer>>> bar = state.getBarrier();
        if (!symTbl.isDefined(var)) {
            throw new MyException("not defined");
        }
        int foundIndex = (int) symTbl.lookup(var).getVal();//cauta variabila cnt
        if (symTbl.isDefined(var)) {
            if (bar.isDefined(foundIndex)) {
                List<Integer> l = bar.lookup(foundIndex).getValue();
                int n = bar.lookup(foundIndex).getKey();
                int listsize = l.size();
                if (n > listsize)
                    if (l.contains(state.t_id))
                        stk.push(new await(var));
                    else {
                        l.add(state.t_id);
                        stk.push(new await(var));
                    }
            } else throw new MyException("is not an index");
        } else throw new MyException("is not defined");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var);
        ;
        if (typevar instanceof IntType)
            return typeEnv;
        else
            throw new MyException("var is not of type in");
    }

    @Override
    public String toString() {
        return "await(" + var + ")";
    }
}

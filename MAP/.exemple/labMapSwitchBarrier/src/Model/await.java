package Model;

import Model.PrgState.IBarrier;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.util.ArrayList;
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
        int foundIndex = (int) symTbl.lookup(var).getVal();
        if (symTbl.isDefined(var))
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

            }

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

package Model;

import Model.PrgState.ILatch;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class await implements IStmt {
    String var;

    public await(String v) {
        var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        ILatch<Integer, Integer> latch = state.getLatch();
        if (symTbl.isDefined(var)) {
            int foundIndex = (int) symTbl.lookup(var).getVal();
            if (latch.isDefined(foundIndex)) {
                int i = latch.lookup(foundIndex);
                if (i != 0)
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

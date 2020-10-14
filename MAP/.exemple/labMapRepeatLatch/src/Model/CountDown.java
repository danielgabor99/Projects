package Model;

import Model.PrgState.*;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class CountDown implements IStmt {
    String var;

    public CountDown(String v) {
        var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        ILatch<Integer, Integer> latch = state.getLatch();
        MyIList<Value> out = state.getOut();

        if (symTbl.isDefined(var)) {
            int foundIndex = (int) symTbl.lookup(var).getVal();
            if (latch.isDefined(foundIndex)) {
                int i = latch.lookup(foundIndex);
                if (i > 0) {
                    latch.update(foundIndex, i - 1);
                    out.addB(new IntValue(state.t_id));
                }
            }
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

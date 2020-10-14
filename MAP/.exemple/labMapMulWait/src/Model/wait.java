package Model;

import Model.Exp.ValueExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class wait implements IStmt {
    int number;

    public wait(int n) {
        number = n;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (number == 0)
            return null;
        IStmt newStmt = new CompStmt(new PrintStmt(new ValueExp(new IntValue(number))), new wait((number - 1)));
        stk.push(newStmt);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

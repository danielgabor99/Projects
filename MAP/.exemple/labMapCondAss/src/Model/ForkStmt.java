package Model;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import com.company.MyException;

import java.io.FileNotFoundException;

public class ForkStmt implements IStmt {
    IStmt stmt;

    public ForkStmt(IStmt st) {
        stmt = st;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        //  PrgState.setId();
        PrgState newState = new PrgState(new MyStack<IStmt>(), state.copySymTbl(), state.getOut(), state.getFileTable(), state.getHeap(), stmt);
        return newState;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        stmt.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" + stmt.toString() + ")";
    }
}

package Model;

import Model.Exp.Exp;
import Model.Exp.NotExp;
import Model.Exp.RelExp;
import Model.Exp.ValueExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class repeatStmt implements IStmt {
    IStmt stmt;
    Exp exp;

    public repeatStmt(IStmt s, Exp e) {
        stmt = s;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {

        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        RelExp e2 = exp;
        Exp e3 = e2.notExpp();

        IStmt newStmt = new CompStmt(stmt, new WhileStmt(e3, stmt));
        stk.push(newStmt);

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

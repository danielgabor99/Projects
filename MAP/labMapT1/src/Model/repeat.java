package Model;

import Model.Exp.Exp;
import Model.Exp.RelExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.BoolType;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class repeat implements IStmt {
    IStmt stmt;
    Exp exp;

    public repeat(IStmt s, Exp e) {
        stmt = s;
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();

        IStmt newStmt = new CompStmt(stmt, new WhileStmt(((RelExp) exp).notExpp(), stmt));
        stk.push(newStmt);
        return null;
    }

    public String toString() {
        String result = "repeat" + stmt.toString() + "until" + exp.toString();
        return result;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp = exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            stmt.typecheck(typeEnv);
            return typeEnv;
        } else throw new MyException("the exp has not the type bool");
    }
}

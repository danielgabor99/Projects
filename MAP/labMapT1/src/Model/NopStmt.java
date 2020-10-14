package Model;

import Model.PrgState.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;
import com.company.MyException;

public class NopStmt implements IStmt {
    @Override
    public PrgState execute(PrgState state) throws MyException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        String result = "nop";
        return result;
    }
    ///
}

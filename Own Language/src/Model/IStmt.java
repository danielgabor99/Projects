package Model;

import Model.PrgState.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.Type;
import com.company.MyException;

import java.io.FileNotFoundException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, FileNotFoundException;

    //which is the execution method for a statement.
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}

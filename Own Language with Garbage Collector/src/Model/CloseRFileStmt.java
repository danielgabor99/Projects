package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.StringValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt {
    Exp exp;

    public CloseRFileStmt(Exp e) {
        exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (exp.eval(symTbl, state.getHeap()).getType().equals(new StringType())) {
            StringValue fileName = (StringValue) exp.eval(symTbl, state.getHeap());
            if (!fileTable.isDefined(fileName.getVal())) {
                throw new MyException("no such key");
            } else {
                BufferedReader buffer = fileTable.getValue(fileName.getVal());
                fileTable.remove(fileName.getVal());
                try {
                    buffer.close();
                    ;
                } catch (IOException e) {
                    throw new MyException(e.getMessage());
                }
            }

        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        return "close file";
    }
}

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

public class OpenRFileStmt implements IStmt {
    Exp exp;

    public OpenRFileStmt(Exp e) {
        this.exp = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (exp.eval(symTbl, state.getHeap()).getType().equals(new StringType())) {
            StringValue fileName = (StringValue) exp.eval(symTbl, state.getHeap());
            if (fileTable.isDefined(fileName.getVal())) {
                throw new MyException("key already exist");
            } else {
                BufferedReader buffer = new BufferedReader(new FileReader(fileName.getVal()));
                fileTable.update(fileName.getVal(), buffer);
            }

        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }

    public String toString() {
        return "open file";
    }

}

package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.PrgState;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFileStmt implements IStmt {
    Exp exp;
    String value;

    public ReadFileStmt(Exp e, String val) {
        exp = e;
        value = val;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        if (exp.eval(symTbl, state.getHeap()).getType().equals(new StringType())) {
            StringValue fileName = (StringValue) exp.eval(symTbl, state.getHeap());
            if (fileTable.isDefined(fileName.getVal()) == false) {
                throw new MyException("no such file");
            } else {
                BufferedReader buffer = fileTable.getValue(fileName.getVal());
                try {
                    String readLine = buffer.readLine();
                    if (readLine == null) {
                        symTbl.update(value.toString(), new IntValue(0));
                    } else {
                        symTbl.update(value.toString(), new IntValue(Integer.parseInt(readLine)));
                    }
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
        return "read file";
    }
}

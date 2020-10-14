package Model;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.MyStack;
import Model.PrgState.PrgState;
import Model.Type.*;
import Model.Value.*;
import com.company.MyException;

public class VarDeclStmt implements IStmt {
    String name;
    Type type;


    public VarDeclStmt(String v, Type type) {
        this.name = v;
        this.type = type;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        if (this.type.equals(new IntType())) {
            Value val = new IntValue(10);
            val.defaultValue();
            tbl.update(this.name, val);
        }
        if (this.type.equals(new BoolType())) {
            Value val2 = new BoolValue(true);
            val2.defaultValue();
            tbl.update(this.name, val2);
        }
        if (this.type.equals(new StringType())) {
            Value val3 = new StringValue("");
            val3.defaultValue();
            tbl.update(this.name, val3);
        }
        if (this.type instanceof RefType) {
            Value val4 = new RefValue(0, new IntType());
            tbl.update(this.name, val4);
        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.update(name, type);
        return typeEnv;
    }

    public String toString() {
        String output = this.type.toString() + " " + this.name.toString();
        return output;
    }
    ///
}
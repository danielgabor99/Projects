package Model;

import Model.Exp.Exp;
import Model.Exp.ValueExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.IntValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class CondAssign implements IStmt {
    String id;
    Exp exp1, exp2, exp3;

    public CondAssign(String name, Exp e1, Exp e2, Exp e3) {
        this.id = name;
        this.exp1 = e1;
        this.exp2 = e2;
        this.exp3 = e3;
    }

    public String toString() {
        return id + "=" + exp1.toString() + "?" + exp1.toString() + " " + exp1.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        IStmt ifcon = new IfStmt(this.exp1, new AssignStmt(this.id, new ValueExp(new IntValue(100))), new AssignStmt(this.id, new ValueExp(new IntValue(200))));
        stk.push(ifcon);
        return null;
    }


    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

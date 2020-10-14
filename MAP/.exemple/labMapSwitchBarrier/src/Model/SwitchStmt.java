package Model;

import Model.Exp.Exp;
import Model.Exp.ValueExp;
import Model.Exp.VarExp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIStack;
import Model.PrgState.PrgState;
import Model.Type.Type;
import Model.Value.BoolValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class SwitchStmt implements IStmt {
    Exp exp, exp1, exp2;
    IStmt stm1, stm2, stm3;

    public SwitchStmt(Exp e, Exp e1, IStmt s1, Exp e2, IStmt s2, IStmt s3) {
        exp = e;
        exp1 = e1;
        exp2 = e2;
        stm1 = s1;
        stm2 = s2;
        stm3 = s3;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        int v = (int) exp.eval(symTbl, state.getHeap()).getVal();
        int v1 = (int) exp1.eval(symTbl, state.getHeap()).getVal();
        int v2 = (int) exp2.eval(symTbl, state.getHeap()).getVal();
        boolean b = false;
        boolean b2 = false;
        if (v == v1)
            b = true;
        else if (v == v2)
            b2 = true;
        System.out.println(b);
        System.out.println(b2);
        IStmt iff = new IfStmt(new ValueExp(new BoolValue(b)), stm1, new IfStmt(new ValueExp(new BoolValue(b2)), stm2, stm3));
        stk.push(iff);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return null;
    }
}

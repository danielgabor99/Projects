package Model;

import Model.Exp.Exp;
import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.PrgState.PrgState;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Type.Type;
import Model.Value.RefValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.FileNotFoundException;

public class NewAllocation implements IStmt {
    String varName;
    Exp expression;

    public NewAllocation(String s, Exp e) {
        varName = s;
        expression = e;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> hp = state.getHeap();
        Value v = expression.eval(symTbl, hp);//v:20
        if (symTbl.isDefined(varName)) {
            Type t = symTbl.getValue(varName).getType();//t:Ref(int)
            if (t instanceof RefType) {
                int newFree = hp.getFirstFree();
                hp.update(newFree, v);
                symTbl.update(varName, new RefValue(newFree, v.getType()));
                return null;
            } else throw new MyException("not a ref type");
        } else throw new MyException("variable is not defined");
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(varName);
        Type typexp = expression.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    public String toString() {
        return "newH(" + varName + ", " + expression.toString() + ")";
    }
}

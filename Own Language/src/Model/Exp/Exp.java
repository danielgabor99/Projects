package Model.Exp;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyIHeap;
import Model.Type.Type;
import Model.Value.Value;
import com.company.MyException;

public interface Exp {
    Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException;

    Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException;
}

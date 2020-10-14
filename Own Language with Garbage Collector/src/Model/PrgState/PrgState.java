package Model.PrgState;

import Model.IStmt;
import Model.Value.StringValue;
import Model.Value.Value;
import com.company.MyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicInteger;

public class PrgState {

    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    MyIDictionary<String, BufferedReader> fileTable;
    MyIHeap<Integer, Value> heap;
    public static AtomicInteger id = new AtomicInteger();
    public int t_id;
    //IStmt originalProgram; //optional field, but good to have

    public PrgState(MyIStack<IStmt> stk, MyIDictionary<String, Value> symtbl,
                    MyIList<Value> ot, MyIDictionary<String, BufferedReader> ft, MyIHeap<Integer, Value> h, IStmt prg) {
        exeStack = stk;
        symTable = symtbl;
        out = ot;
        //originalProgram = deepCopy(prg);//recreate the entire original prg
        fileTable = ft;
        heap = h;
        t_id = setId();
        stk.push(prg);

    }

    public int setId() {
        return id.incrementAndGet();
    }

    public PrgState oneStep() throws MyException, FileNotFoundException {
        if (exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt crtStmt = exeStack.pop();
        return crtStmt.execute(this);

    }

    public int getId() {
        return t_id;
    }

    public MyIHeap<Integer, Value> getHeap() {
        return heap;
    }

    public MyIList<Value> getOut() {
        return this.out;
    }

    public MyIStack<IStmt> getStk() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public Boolean isNotCompleted() {
        if (exeStack.isEmpty())
            return false;
        return true;
    }

    public MyIDictionary<String, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public String toString() {// folosesc deja celalat toString de la MyList
        String result = "\nout: ";
        result = result + this.out.toString();
        return result;
    }

    private IStmt deepCopy(IStmt prg) {
        return prg;
    }

    public MyIDictionary<String, Value> copySymTbl() throws MyException {
        MyIDictionary<String, Value> symTblClone = new MyDictionary<>();
        for (String key : symTable.getContent().keySet()) {
            symTblClone.update(key, symTable.lookup(key));
        }
        return symTblClone;
    }
}

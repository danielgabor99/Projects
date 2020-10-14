package Repository;

import Model.PrgState.MyIDictionary;
import Model.PrgState.MyList;
import Model.PrgState.PrgState;
import com.company.MyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Repository implements RepositoryI {
    String logFilePath;
    PrgState prg;
    List<PrgState> List;

    public Repository(PrgState prg, String File) throws FileNotFoundException {
        this.List = new ArrayList<>();
        this.logFilePath = File;
        this.prg = prg;
        this.add(prg);
    }

    @Override
    public List<PrgState> getPrgList() {
        return List;
    }

    @Override
    public void setPrgList(List<PrgState> l) {
        List = l;
    }

    public void add(PrgState prg) {
        List.add(prg);
    }

    public void logPrgStateExec(PrgState p) throws MyException, IOException {
        int id = p.t_id;
        String s = "id=" + id + "\n";
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        PrgState prg = p;//?
        logFile.append(prg.getStk().toString());
        logFile.append(s);
        logFile.append(prg.getSymTable().toString());
        logFile.append(prg.getOut().toString());
        logFile.append(prg.getFileTable().toString());
        logFile.append(prg.getHeap().toString());
        logFile.flush();
    }

    public PrgState getPrgStateWithId(int currentId) {
        for (PrgState program : List)
            if (program.getId() == currentId)
                return program;
        return null;
    }
}

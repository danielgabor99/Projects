package Repository;

import Model.PrgState.MyList;
import Model.PrgState.PrgState;
import com.company.MyException;

import java.io.IOException;
import java.util.List;

public interface RepositoryI {
    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> l);

    void logPrgStateExec(PrgState p) throws MyException, IOException;

    PrgState getPrgStateWithId(int currentId);
}

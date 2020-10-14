package Controller;

import Model.Animale;
import Repository.ArrayRepository;
import Repository.Repository;

public class Controller {
    private ArrayRepository repo;

    public Controller(ArrayRepository r) {
        repo = r;
    }

    public void add(Animale A) throws Exception {
        repo.addtoRepo(A);
    }

    public void delete(int index) throws Exception {
        repo.deletefromRepo(index);
    }

    public Animale[] getAllArray() {
        return repo.getAll();
    }

    public void updateCtrl(int index, int w) throws Exception {
        repo.updateRepo(index, w);
    }

    public Animale[] Filter(int w) {
        return repo.Filter(w);
    }
}

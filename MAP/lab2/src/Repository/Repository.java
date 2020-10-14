package Repository;

import Model.Animale;

public interface Repository {
    public void addtoRepo(Animale A) throws Exception;

    public void deletefromRepo(int index) throws Exception;

    public Animale[] getAll();

    public void updateRepo(int index, int w) throws Exception;

    public Animale[] Filter(int w);
}

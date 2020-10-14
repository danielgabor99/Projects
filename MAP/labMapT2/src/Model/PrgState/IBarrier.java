package Model.PrgState;

import java.util.Map;

public interface IBarrier<T, V> {
    public int getFirstFree();

    public void update(T id, V v);

    V getVal(T addr);

    public boolean isDefined(T id);

    public V lookup(T id);
}

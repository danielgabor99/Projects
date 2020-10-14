package Model.Value;

import Model.Type.Type;

public interface Value<T> {
    Type getType();

    public T getVal();

    void defaultValue();
}

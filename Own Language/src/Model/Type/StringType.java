package Model.Type;

import Model.Value.Value;

public class StringType implements Type {
    @Override
    public Value defaultValue() {
        return null;
    }

    public boolean equals(Object another) {
        if (another instanceof StringType)
            return true;
        else
            return false;
    }

    public String toString() {
        return "string";
    }
}

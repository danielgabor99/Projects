package Model.Value;

import Model.Type.IntType;
import Model.Type.Type;

public class IntValue implements Value<Integer> {
    int val;

    public IntValue(int v) {
        this.val = v;
    }

    public Integer getVal() {
        return val;
    }

    public void defaultValue() {
        this.val = 0;
    }

    public String toString() {
        String output = "";
        output = output + this.val;
        return output;
    }

    public Type getType() {
        return new IntType();
    }

    public boolean equals(Object another) {
        if (another instanceof IntValue)
            return true;
        else
            return false;
    }
}

package Model.Value;

import Model.Type.BoolType;
import Model.Type.Type;

public class BoolValue implements Value<Boolean> {
    boolean val;

    public BoolValue(boolean v) {
        val = v;
    }

    public void defaultValue() {
        this.val = false;
    }

    public Boolean getVal() {
        return this.val;
    }

    public String toString() {
        String output = "";
        output = output + this.val;
        return output;
    }

    public Type getType() {
        return new BoolType();
    }

    public boolean equals(Object another) {
        if (another instanceof BoolValue)
            return true;
        else
            return false;
    }
}

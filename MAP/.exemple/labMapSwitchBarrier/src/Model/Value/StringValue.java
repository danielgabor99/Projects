package Model.Value;

import Model.Type.StringType;
import Model.Type.Type;

public class StringValue implements Value<String> {
    String string;

    public StringValue(String s) {
        this.string = s;
    }

    @Override
    public Type getType() {
        return new StringType();
    }

    public String getVal() {
        return string;
    }

    @Override
    public void defaultValue() {
        this.string = "";
    }

    public boolean equals(Object another) {
        if (another instanceof StringValue)
            return true;
        else
            return false;
    }

    public String toString() {
        String output = "";
        output = output + this.string;
        return output;
    }
}

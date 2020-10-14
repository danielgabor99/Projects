package Model.Value;

import Model.Type.RefType;
import Model.Type.Type;

public class RefValue implements Value<RefValue> {

    int address;
    Type locationType;

    public RefValue(int adr, Type inner) {
        this.address = adr;
        this.locationType = inner;
    }

    @Override
    public RefValue getVal() {
        return null;
    }

    public int getAddress() {
        return address;
    }

    public Type getLocationType() {
        return locationType;
    }

    @Override
    public void defaultValue() {
    }

    int getAddr() {
        return address;
    }

    public Type getType() {
        return new RefType(locationType);
    }

    public String toString() {
        String output = "";
        output = output + "(" + this.address + " " + this.locationType.toString() + ")";
        return output;
    }

}

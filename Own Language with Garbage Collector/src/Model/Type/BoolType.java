package Model.Type;

import Model.Value.Value;
import Model.VarDeclStmt;

public class BoolType implements Type {

    public boolean equals(Object another) {
        if (another instanceof BoolType)
            return true;
        else
            return false;
    }

    public Value defaultValue() {
        return null;
    }

    public String toString() {
        return "bool";
    }
}

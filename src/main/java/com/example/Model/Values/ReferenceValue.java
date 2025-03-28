package com.example.Model.Values;

import com.example.Model.Types.ReferenceType;
import com.example.Model.Types.Type;

public class ReferenceValue implements Value {
    Integer address;
    Type locationType;

    public ReferenceValue(int address, Type locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    @Override
    public Value createCopy() {
        return new ReferenceValue(address, locationType);
    }

    public int getAddress() {
        return address;
    }

    @Override
    public Type getType() {
        return new ReferenceType(locationType);
    }

    @Override
    public String toString() {
        return "(" + address + ", " + locationType + ")";
    }
}

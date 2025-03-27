package com.example.Model.Types;

import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.Value;

public class BooleanType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof BooleanType;
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Value defaultValue() {
        return new BooleanValue(false);
    }

    @Override
    public Type createCopy() {
        return new BooleanType();
    }
}

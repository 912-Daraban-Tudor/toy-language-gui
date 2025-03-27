package com.example.Model.Types;

import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class IntegerType implements Type {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntegerType;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Value defaultValue() {
        return new IntegerValue(0);
    }

    @Override
    public Type createCopy() {
        return new IntegerType();
    }
}

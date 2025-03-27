package com.example.Model.Types;

import com.example.Model.Values.StringValue;
import com.example.Model.Values.Value;

public class StringType implements Type {

    @Override
    public Value defaultValue() {
        return new StringValue("");
    }

    @Override
    public String toString() {
        return "string";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof StringType;
    }

    @Override
    public Type createCopy() {
        return new StringType();
    }
}

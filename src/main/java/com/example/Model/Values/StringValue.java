package com.example.Model.Values;

import com.example.Model.Types.StringType;
import com.example.Model.Types.Type;

public class StringValue implements Value {
    String value;
    @Override
    public Type getType() {
        return new StringType();
    }
    public StringValue(String s) {
        value = s;
    }
    public StringValue() {
        value = "";
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public Value createCopy() {
        return new StringValue(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringValue)) return false;
        StringValue that = (StringValue) o;
        return value.equals(that.value);
    }
}

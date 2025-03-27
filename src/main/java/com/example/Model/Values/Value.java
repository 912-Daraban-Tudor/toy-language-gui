package com.example.Model.Values;

import com.example.Model.Types.Type;

public interface Value {
    Type getType();
    Value createCopy();

}

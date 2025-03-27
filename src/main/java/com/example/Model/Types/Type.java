package com.example.Model.Types;

import com.example.Model.Values.Value;

public interface Type {
    Value defaultValue();
    Type createCopy();
}

package com.example.Model.Expression;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public class ValueExpression implements IExpression {
    final Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        return value;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return value.getType();
    }
}

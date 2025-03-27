package com.example.Model.Expression;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public class VariableExpression implements IExpression {
    final String id;

    @Override
    public String toString() {
        return id;
    }

    public VariableExpression(String id) {
        this.id = id;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        return table.lookup(id);
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table.lookup(id);
    }
}

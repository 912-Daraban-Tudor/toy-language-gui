package com.example.Model.Expression;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public interface IExpression {
    Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException;
    Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException;

}

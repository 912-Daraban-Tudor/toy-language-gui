package com.example.Model.Expression;

import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.BooleanType;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.Value;

public class LogicExpression implements IExpression {
    final IExpression expression1;
    final IExpression expression2;
    final int operation;

    @Override
    public String toString() {
        switch (operation) {
            case 1:
                return expression1.toString() + "&" + expression2.toString();
            case 2:
                return expression1.toString() + "|" + expression2.toString();
            default:
                return "";
        }
    }

    public LogicExpression(IExpression expression1, IExpression expression2, int operation) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new BooleanType())) {
            value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new BooleanType())) {
                BooleanValue booleanValue1 = (BooleanValue)value1;
                BooleanValue booleanValue2 = (BooleanValue)value2;
                Boolean bool1 = booleanValue1.getValue();
                Boolean bool2 = booleanValue2.getValue();
                switch (operation) {
                    case 1:
                        return new BooleanValue(bool1 & bool2);
                    case 2:
                        return new BooleanValue(bool1 | bool2);
                    default:
                        throw new InterpreterException("Invalid operation");
                }
            } else {
                throw new InterpreterException("Second operand is not a boolean");
            }
        } else {
            throw new InterpreterException("First operand is not a boolean");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type type1, type2;
        type1 = expression1.typecheck(table);
        type2 = expression2.typecheck(table);
        if (type1.equals(new BooleanType())) {
            if (type2.equals(new BooleanType())) {
                return new BooleanType();
            } else {
                throw new TypeException("Second operand is not an integer");
            }
        } else {
            throw new TypeException("First operand is not an integer");
        }
    }

}

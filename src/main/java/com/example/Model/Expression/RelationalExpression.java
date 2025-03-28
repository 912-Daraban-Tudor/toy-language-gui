package com.example.Model.Expression;


import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.BooleanType;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class RelationalExpression implements IExpression {
    String operation;
    IExpression expression1;
    IExpression expression2;

    public RelationalExpression(String operation, IExpression expression1, IExpression expression2) {
        this.operation = operation;
        this.expression1 = expression1;
        this.expression2 = expression2;
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new IntegerType())) {
            Value value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue)value1;
                IntegerValue integerValue2 = (IntegerValue)value2;
                int intValue1 = integerValue1.getValue();
                int intValue2 = integerValue2.getValue();
                switch (operation) {
                    case "<":
                        return new BooleanValue(intValue1 < intValue2);
                    case "<=":
                        return new BooleanValue(intValue1 <= intValue2);
                    case "==":
                        return new BooleanValue(intValue1 == intValue2);
                    case "!=":
                        return new BooleanValue(intValue1 != intValue2);
                    case ">":
                        return new BooleanValue(intValue1 > intValue2);
                    case ">=":
                        return new BooleanValue(intValue1 >= intValue2);
                    default:
                        throw new InterpreterException("Invalid operation");
                }
            } else {
                throw new InterpreterException("Expression 2 not of integer type");
            }
        } else {
            throw new InterpreterException("Expression 1 not of integer type");
        }
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type type1, type2;
        type1 = expression1.typecheck(table);
        type2 = expression2.typecheck(table);
        if (type1.equals(new IntegerType())) {
            if (type2.equals(new IntegerType())) {
                return new BooleanType();
            } else {
                throw new TypeException("Second operand is not an integer");
            }
        } else {
            throw new TypeException("First operand is not an integer");
        }
    }

}

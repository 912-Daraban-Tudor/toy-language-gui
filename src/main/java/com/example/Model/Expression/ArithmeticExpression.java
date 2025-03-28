package com.example.Model.Expression;

import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class ArithmeticExpression implements IExpression {
    final IExpression expression1;
    final IExpression expression2;
    int operation;

    public ArithmeticExpression(char operation, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        switch (operation) {
            case '+':
                this.operation = 1;
                break;
            case '-':
                this.operation = 2;
                break;
            case '*':
                this.operation = 3;
                break;
            case '/':
                this.operation = 4;
                break;
        }
    }

    @Override
    public String toString() {
        switch (operation) {
            case 1:
                return expression1.toString() + "+" + expression2.toString();
            case 2:
                return expression1.toString() + "-" + expression2.toString();
            case 3:
                return expression1.toString() + "*" + expression2.toString();
            case 4:
                return expression1.toString() + '/' + expression2.toString();
            default:
                return "";
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type type1, type2;
        type1 = expression1.typecheck(table);
        type2 = expression2.typecheck(table);
        if (type1.equals(new IntegerType())) {
            if (type2.equals(new IntegerType())) {
                return new IntegerType();
            } else {
                throw new TypeException("Second operand is not an integer");
            }
        } else {
            throw new TypeException("First operand is not an integer");
        }
    }

    @Override
    public Value evaluateExpression(MyIDictionary<String, Value> table, MyIHeap<Value> heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.evaluateExpression(table, heap);
        if (value1.getType().equals(new IntegerType())) {
            value2 = expression2.evaluateExpression(table, heap);
            if (value2.getType().equals(new IntegerType())) {
                IntegerValue integerValue1 = (IntegerValue)value1;
                IntegerValue integerValue2 = (IntegerValue)value2;
                int number1, number2;
                number1 = integerValue1.getValue();
                number2 = integerValue2.getValue();
                switch (operation) {
                    case 1:
                        return new IntegerValue(number1 + number2);
                    case 2:
                        return new IntegerValue(number1 - number2);
                    case 3:
                        return new IntegerValue(number1 * number2);
                    case 4:
                        if (number2 == 0) throw new InterpreterException("Division by zero");
                        return new IntegerValue(number1 / number2);
                    default:
                        throw new InterpreterException("Invalid operation");
                }
            } else {
                throw new InterpreterException("Second operand is not an integer");
            }
        } else {
            throw new InterpreterException("First operand is not an integer");
        }
    }
}

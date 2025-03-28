package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class NewLatchStatement implements IStatement {
    String id;
    IExpression expression;

    public NewLatchStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        Value expressionResult = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (expressionResult.getType().equals(new IntegerType())) {
            IntegerValue num1 = (IntegerValue) expressionResult;
            if (state.getSymbolTable().isVariableDefined(id)) {
                Value variableValue = state.getSymbolTable().lookup(id);
                if (variableValue.getType().equals(new IntegerType())) {
                    int addr = state.getLatchTable().allocate(num1.getValue());
                    state.getSymbolTable().update(id, new IntegerValue(addr));
                }
            } else throw new InterpreterException("Variable not defined");
        } else {
            throw new InterpreterException("Expression not of type int");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NewLatchStatement(id, expression);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type variableType = table.lookup(id);
        Type expressionType = expression.typecheck(table);
        if (variableType.equals(new IntegerType()) && expressionType.equals(new IntegerType())) {
            return table;
        }
        throw new TypeException("Latch must be type int");
    }

    @Override
    public String toString() {
        return "newLatch (" + id + ", " + expression.toString() + ")";
    }
}

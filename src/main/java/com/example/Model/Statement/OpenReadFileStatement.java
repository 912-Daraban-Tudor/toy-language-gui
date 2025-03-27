package com.example.Model.Statement;

import com.example.Exceptions.FileException;
import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.StringType;
import com.example.Model.Types.Type;
import com.example.Model.Values.StringValue;
import com.example.Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement {
    IExpression expression;
    public OpenReadFileStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        Value val = expression.evaluateExpression(table, state.getHeapTable());
        if (val.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) val;
            if (fileTable.isVariableDefined(stringValue)) {
                throw new FileException("File is already opened");
            } else {
                try {
                    BufferedReader br = new BufferedReader(new FileReader(stringValue.getValue()));
                    fileTable.add(stringValue, br);
                } catch (IOException exception) {
                    throw new FileException("File cannot be opened " + exception.getMessage());
                }
            }
        } else {
            throw new FileException("Expression not of string type");
        }
        state.setExecutionStack(stack);
        state.setSymbolTable(table);
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new OpenReadFileStatement(expression);
    }

    @Override
    public String toString() {
        return "open(" + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type type = expression.typecheck(table);
        if (type.equals(new StringType())) {
            return table;
        } else {
            throw new TypeException("Expression not of type string");
        }
    }
}

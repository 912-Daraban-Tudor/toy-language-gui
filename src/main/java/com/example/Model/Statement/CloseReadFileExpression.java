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
import java.io.IOException;

public class CloseReadFileExpression implements IStatement {
    IExpression expression;

    public CloseReadFileExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        Value value = expression.evaluateExpression(table, state.getHeapTable());
        if (value.getType().equals(new StringType())) {
            StringValue stringValue = (StringValue) value;
            if (fileTable.isVariableDefined(stringValue)) {
                BufferedReader bf = fileTable.lookup(stringValue);
                try {
                    bf.close();
                }catch (IOException exception) {
                    throw new FileException("Could not close file " + exception.getMessage());
                }
                fileTable.remove(stringValue);
            } else {
                throw new FileException("File to close does not exist");
            }
        } else {
            throw new FileException("File name is not a string");
        }
        state.setExecutionStack(stack);
        state.setSymbolTable(table);
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new CloseReadFileExpression(expression);
    }

    @Override
    public String toString() {
        return "closeReadFile(" + expression.toString() + ")";
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

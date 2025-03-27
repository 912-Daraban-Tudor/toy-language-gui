package com.example.Model.Statement;

import com.example.Exceptions.AssignmentException;
import com.example.Exceptions.FileException;
import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.StringType;
import com.example.Model.Types.Type;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.StringValue;
import com.example.Model.Values.Value;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements IStatement {
    IExpression expression;
    String variableName;

    public ReadFileStatement(IExpression expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public IStatement createCopy() {
        return new ReadFileStatement(expression, variableName);
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (table.isVariableDefined(variableName)) {
            Value value = table.lookup(variableName);
            if (value.getType().equals(new IntegerType())) {
                Value value1 = expression.evaluateExpression(table, state.getHeapTable());

                if (value1.getType().equals(new StringType())) {
                    StringValue stringValue = (StringValue)value1;
                    BufferedReader reader = fileTable.lookup(stringValue);
                    try {
                        String line = reader.readLine();
                        IntegerValue valueForVariable;
                        if (line == null) {
                            valueForVariable = new IntegerValue();
                        } else {
                            valueForVariable = new IntegerValue(Integer.parseInt(line));
                        }
                        table.update(variableName, valueForVariable);
                    } catch (IOException exception) {
                        throw new FileException("Cannot read from file");
                    }
                } else {
                    throw new AssignmentException("Cannot read - Expression not of type string");
                }
            } else {
                throw new AssignmentException("Variable is not of type int");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }
        state.setFileTable(fileTable);
        state.setSymbolTable(table);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public String toString() {
        return "readFile(" + expression.toString() + ", " + variableName + ")";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type expressionType = expression.typecheck(table);
        Type variableType = table.lookup(variableName);
        if (expressionType.equals(new StringType())) {
            if (variableType.equals(new IntegerType())) {
                return table;
            } else {
                throw new TypeException("Variable not of type int");
            }
        } else {
            throw new TypeException("Expression not of type string");
        }
     }
}

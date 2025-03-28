package com.example.Model.Statement;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Exceptions.DeclarationException;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ProgramState;
import com.example.Model.Types.*;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.StringValue;
import com.example.Model.Values.Value;

public class VariableDeclarationStatement implements IStatement {
    final String name;
    final Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        if (table.isVariableDefined(name)) {
            throw new DeclarationException("Variable is already declared");
        } else {
//            if (type.equals(new IntegerType())) {
//                table.add(name, new IntegerType().defaultValue());
//            } else if (type.equals(new BooleanType())) {
//                table.add(name, new BooleanType().defaultValue());
//            } else if (type.equals(new StringType())) {
//                table.add(name, new StringType().defaultValue());
//            } else if (type instanceof ReferenceType) {
//                table.add();
//            } else {
//                throw new DeclarationException("Type does not exist");
//            }
            Value defaultValue = type.defaultValue();
            table.add(name, defaultValue);
        }
        state.setSymbolTable(table);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        table.add(name, type);
        return table;
    }
}

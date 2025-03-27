package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ProgramState;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class AwaitStatement implements IStatement {
    String id;

    public AwaitStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndex = state.getSymbolTable().lookup(id);
            if (foundIndex.getType().equals(new IntegerType())) {
                int found = ((IntegerValue) foundIndex).getValue();
                if (state.getLatchTable().exists(found)) {
                    if (state.getLatchTable().get(found) != 0) {
                        state.getExecutionStack().push(this);
                    }
                } else {
                    throw new InterpreterException("Latch does not exist");
                }
            } else {
                throw new InterpreterException("Variable not of type int");
            }
        } else {
            throw new InterpreterException("Variable not defined");
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new AwaitStatement(id);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type variableType = table.lookup(id);
        if (variableType.equals(new IntegerType())) {
            return table;
        }
        throw new TypeException("Variable not of type int");
    }

    @Override
    public String toString() {
        return "await (" + id + ")";
    }
}

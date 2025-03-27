package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ProgramState;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.Type;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.Value;

public class CountDownStatement implements IStatement {
    String id;

    public CountDownStatement(String id) {
        this.id = id;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        if (state.getSymbolTable().isVariableDefined(id)) {
            Value foundIndex = state.getSymbolTable().lookup(id);
            if (foundIndex.getType().equals(new IntegerType())) {
                int found = ((IntegerValue) foundIndex).getValue();
                if (state.getLatchTable().exists(found)) {
                    if (state.getLatchTable().get(found) > 0) {
                        state.getLatchTable().update(found, state.getLatchTable().get(found) - 1);
                    }
                    state.getOutputConsole().addElement(new IntegerValue(state.getId()));
                }
            }
        }
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new CountDownStatement(id);
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
        return "countDown (" + id + ")";
    }
}

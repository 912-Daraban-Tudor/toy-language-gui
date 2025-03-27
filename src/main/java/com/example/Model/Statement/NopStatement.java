package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ProgramState;
import com.example.Model.Types.Type;

public class NopStatement implements IStatement{
    public NopStatement() {}

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
//        MyIStack<IStatement> stack = state.getExecutionStack();
//        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() {
        return "\n";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return table;
    }
}

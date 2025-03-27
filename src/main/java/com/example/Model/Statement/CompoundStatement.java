package com.example.Model.Statement;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ProgramState;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public class CompoundStatement implements IStatement {
    final IStatement firstStatement;
    final IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new CompoundStatement(firstStatement, secondStatement);
    }

    @Override
    public String toString() {
        return firstStatement.toString() + ";\n" + secondStatement.toString();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        return secondStatement.typecheck(firstStatement.typecheck(table));
    }
}

package com.example.Model.Statement;

import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIList;
import com.example.Model.ADTs.MyIStack;
import com.example.Exceptions.InterpreterException;
import com.example.Model.ProgramState;
import com.example.Model.Expression.IExpression;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public class PrintStatement implements IStatement {
    final IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIList<Value> outConsole = state.getOutputConsole();
        outConsole.addElement(expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable()));
        state.setExecutionStack(stack);
        state.setOutputConsole(outConsole);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new PrintStatement(expression);
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        expression.typecheck(table);
        return table;
    }
}

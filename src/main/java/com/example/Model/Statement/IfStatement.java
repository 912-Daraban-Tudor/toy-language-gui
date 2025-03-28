package com.example.Model.Statement;

import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyDictionary;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Exceptions.ConditionException;
import com.example.Exceptions.InterpreterException;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.BooleanType;
import com.example.Model.Types.Type;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.Value;

import java.util.Map;

public class IfStatement implements IStatement {
    final IExpression expression;
    final IStatement thenStatement;
    final IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString() {
        return "( IF (" + expression.toString() + ") THEN (" + thenStatement.toString() + ") ELSE (" + elseStatement.toString() + "))";
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        Value condition = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (!condition.getType().equals(new BooleanType())) {
            throw new ConditionException("Condition is not of boolean type");
        }
        if (condition.equals(new BooleanValue(true))) {
            stack.push(thenStatement);
        } else {
            stack.push(elseStatement);
        }
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new IfStatement(expression, thenStatement, elseStatement);
    }

    private static MyIDictionary<String, Type> clone(MyIDictionary<String, Type> table) throws InterpreterException {
        MyIDictionary<String, Type> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, Type> entry: table.getContent().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue());
        }
        return newSymbolTable;
    }
    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type expressionType = expression.typecheck(table);
        if (expressionType.equals(new BooleanType())) {
            thenStatement.typecheck(clone(table));
            elseStatement.typecheck(clone(table));
            return table;
        } else {
            throw new TypeException("Condition not of type bool");
        }
    }
}

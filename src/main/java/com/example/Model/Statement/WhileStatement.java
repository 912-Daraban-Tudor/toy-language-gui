package com.example.Model.Statement;

import com.example.Exceptions.ConditionException;
import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyDictionary;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.BooleanType;
import com.example.Model.Types.Type;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.Value;

import java.util.Map;

public class WhileStatement implements IStatement {

    IExpression expression;
    IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        Value expressionValue = expression.evaluateExpression(state.getSymbolTable(), state.getHeapTable());
        if (expressionValue.getType().equals(new BooleanType())) {
            if (expressionValue.equals(new BooleanValue(true))) {
                stack.push(new WhileStatement(expression, statement));
                stack.push(statement);
            }
        } else {
            throw new ConditionException("Expression not of type bool");
        }

        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new WhileStatement(expression, statement);
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + ") { " + statement.toString() + "}";
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
            statement.typecheck(clone(table));
            return table;
        } else {
            throw new TypeException("Condition not of type bool");
        }
    }
}

package com.example.Model.Statement;

import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Exceptions.AssignmentException;
import com.example.Exceptions.InterpreterException;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public class AssignStatement implements IStatement{
    final String id;
    final IExpression expression;

    public AssignStatement(String id, IExpression expression) {
        this.id = id;
        this.expression = expression;
    }

    public String getId() {
        return id;
    }

    public IExpression getExpression() {
        return expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> table = state.getSymbolTable();
        if (table.isVariableDefined(id)) {
            Value value = expression.evaluateExpression(table, state.getHeapTable());
            if (value.getType().equals(table.lookup(id).getType())) {
                table.update(id, value);
            } else {
                throw new AssignmentException("Type of expression and type of variable do not match");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }
        state.setExecutionStack(stack);
        state.setSymbolTable(table);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new AssignStatement(id, expression);
    }

    @Override
    public String toString() {
        return id + " = " + expression.toString();
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type variableType = table.lookup(id);
        Type expressionType = expression.typecheck(table);
        if (variableType.equals(expressionType)) {
            return table;
        } else {
            throw new TypeException("Not the same type on assignment");
        }
    }
}

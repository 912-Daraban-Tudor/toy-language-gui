package com.example.Model.Statement;

import com.example.Exceptions.AssignmentException;
import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.ADTs.MyIStack;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.ReferenceType;
import com.example.Model.Types.Type;
import com.example.Model.Values.ReferenceValue;
import com.example.Model.Values.Value;

public class AllocateHeapStatement implements IStatement{
    String variableName;
    IExpression expression;

    public AllocateHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExecutionStack();
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heapTable = state.getHeapTable();

        if (symbolTable.isVariableDefined(variableName)) {
            if (symbolTable.lookup(variableName).getType() instanceof ReferenceType) {
                Value value = expression.evaluateExpression(symbolTable, heapTable);
                Value tableValue = symbolTable.lookup(variableName);
//                System.out.println(value.getType().toString());
//                System.out.println(((ReferenceType)(tableValue.getType())).getInner());
                if (value.getType().equals(((ReferenceType)(tableValue.getType())).getInner())) {
                    int addr = heapTable.allocate(value);
                    symbolTable.update(variableName, new ReferenceValue(addr, value.getType()));
                } else {
                    throw new AssignmentException("Value is not of correct type");
                }
            } else {
                throw new AssignmentException("Variable is not of reference type");
            }
        } else {
            throw new AssignmentException("Variable is not declared");
        }

        state.setHeapTable(heapTable);
        state.setSymbolTable(symbolTable);
        state.setExecutionStack(stack);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new AllocateHeapStatement(variableName, expression);
    }

    @Override
    public String toString() {
        return "new(" + variableName + ',' + expression +
                ')';
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type variableType = table.lookup(variableName);
        Type expressionType = expression.typecheck(table);
        if (variableType.equals(new ReferenceType(expressionType))) {
            return table;
        } else {
            throw new TypeException("Different types on heap allocation");
        }
    }
}

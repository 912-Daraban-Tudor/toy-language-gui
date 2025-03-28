package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Exceptions.TypeException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIHeap;
import com.example.Model.Expression.IExpression;
import com.example.Model.ProgramState;
import com.example.Model.Types.ReferenceType;
import com.example.Model.Types.StringType;
import com.example.Model.Types.Type;
import com.example.Model.Values.ReferenceValue;
import com.example.Model.Values.Value;


public class WriteHeapStatement implements IStatement {
    String variableName;
    IExpression expression;

    public WriteHeapStatement(String variableName, IExpression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }


    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        MyIHeap<Value> heap = state.getHeapTable();

        if (symbolTable.isVariableDefined(variableName)) {
            Value value = symbolTable.lookup(variableName);
            if (value instanceof ReferenceValue) {
                int referenceAddress = ((ReferenceValue)value).getAddress();
                if (heap.exists(referenceAddress)) {
                    Value value1 = expression.evaluateExpression(symbolTable, heap);
                    if (value1.getType().equals(heap.get(referenceAddress).getType())) {
                        heap.put(referenceAddress, value1);
                    } else {
                        throw new InterpreterException("Expression not of variable type");
                    }
                } else {
                    throw new InterpreterException("Value does not exist on heap");
                }
            } else {
                throw new InterpreterException("Value is not a reference");
            }
        } else {
            throw new InterpreterException("Variable not declared");
        }
        state.setSymbolTable(symbolTable);
        state.setHeapTable(heap);
        return null;
    }

    @Override
    public IStatement createCopy() {
        return new WriteHeapStatement(variableName, expression);
    }

    @Override
    public String toString() {
        return "writeHeap(" + variableName + ", " + expression.toString() + ")";
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException {
        Type expressionType = expression.typecheck(table);
        Type variableType = table.lookup(variableName);
        if (variableType instanceof ReferenceType) {
            ReferenceType referenceType = (ReferenceType) variableType;
            if (expressionType.equals(referenceType.getInner())) {
                return table;
            } else {
                throw new TypeException("Not the same type on heap modification");
            }
        } else {
            throw new TypeException("Variable not of reference type");
        }
    }
}

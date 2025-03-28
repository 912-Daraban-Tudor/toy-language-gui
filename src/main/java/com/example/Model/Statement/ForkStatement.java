package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyDictionary;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ADTs.MyIStack;
import com.example.Model.ADTs.MyStack;
import com.example.Model.ProgramState;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

import java.util.Map;

public class ForkStatement implements IStatement {
    IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public IStatement createCopy() {
        return new ForkStatement(statement);
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> newSymbolTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymbolTable().getContent().entrySet()) {
            newSymbolTable.add(entry.getKey(), entry.getValue().createCopy());
        }
        MyIStack <IStatement> stack = new MyStack<>();
        stack.push(new NopStatement());
        stack.push(statement);
        ProgramState newProgram = new ProgramState(stack, newSymbolTable, state.getOutputConsole(), state.getFileTable(), state.getHeapTable(), state.getLatchTable());
        newProgram.setId();
        return newProgram;
    }

    @Override
    public String toString() {
        return "fork(" + statement.toString() + ")";
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
        statement.typecheck(clone(table));
        return table;
    }
}

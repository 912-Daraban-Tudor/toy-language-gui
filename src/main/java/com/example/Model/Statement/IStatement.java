package com.example.Model.Statement;

import com.example.Exceptions.InterpreterException;
import com.example.Model.ADTs.MyIDictionary;
import com.example.Model.ProgramState;
import com.example.Model.Types.Type;
import com.example.Model.Values.Value;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
    IStatement createCopy();
    MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> table) throws InterpreterException;
}



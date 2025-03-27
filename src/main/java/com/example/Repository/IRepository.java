package com.example.Repository;

import com.example.Exceptions.InterpreterException;
import com.example.Model.ProgramState;

import java.util.List;

public interface IRepository {
    void addState(ProgramState state);
//    ProgramState getCurrentProgram();
    void logProgramStateExecution(ProgramState state) throws InterpreterException;
    List<ProgramState> getProgramList();
    void setProgramList(List<ProgramState> programStateList);
}

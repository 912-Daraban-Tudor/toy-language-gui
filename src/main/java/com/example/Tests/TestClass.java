// package com.example.Tests;

// import com.example.Exceptions.InterpreterException;
// import com.example.Model.ADTs.MyDictionary;
// import com.example.Model.ADTs.MyIStack;
// import com.example.Model.ADTs.MyList;
// import com.example.Model.ADTs.MyStack;
// import com.example.Model.Expression.ValueExpression;
// import com.example.Model.ProgramState;
// import com.example.Model.Statement.*;
// import com.example.Model.Types.IntegerType;
// import com.example.Model.Values.IntegerValue;
// import com.example.Model.Values.Value;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;
// public class TestClass {
//     @Test
//     public void testPushStatement() {
//         MyIStack<IStatement> stack = new MyStack<>();
//         IStatement statement = new NopStatement();
//         stack.push(statement);
//         ProgramState state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>());
//         Assertions.assertEquals(state.getExecutionStack().size(), 1);
//     }

//     @Test
//     public void testCompoundStatement() throws InterpreterException {
//         MyIStack<IStatement> stack = new MyStack<>();
//         IStatement statement = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()), new AssignStatement("a", new ValueExpression(new IntegerValue(2))));
//         stack.push(statement);
//         ProgramState state = new ProgramState(stack, new MyDictionary<>(), new MyList<>(), new MyDictionary<>());
//         stack.pop();
//         statement.execute(state);
//         Assertions.assertEquals(state.getExecutionStack().size(), 2);
//     }

// }

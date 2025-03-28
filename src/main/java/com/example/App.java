package com.example;

import java.io.Console;
import java.io.IOException;

import com.example.GUI.ChooseProgramController;
import com.example.Model.Expression.*;
import com.example.Model.Statement.*;
import com.example.Model.Types.BooleanType;
import com.example.Model.Types.IntegerType;
import com.example.Model.Types.ReferenceType;
import com.example.Model.Types.StringType;
import com.example.Model.Values.BooleanValue;
import com.example.Model.Values.IntegerValue;
import com.example.Model.Values.StringValue;
import com.example.Model.Values.Value;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class App extends Application {
    ChooseProgramController chooseProgramController;

    @Override
    public void start(Stage primaryStage) {
        try {
            Rectangle2D screenBounds = Screen.getPrimary().getBounds();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI/ChooseProgramStyle.fxml"));
            GridPane root = (GridPane) loader.load();
            chooseProgramController = loader.getController();
            addStatementsToController();
            chooseProgramController.setMenuStage(primaryStage);
            Scene scene = new Scene(root, screenBounds.getWidth() / 3, screenBounds.getHeight());
            scene.getStylesheets().add(getClass().getResource("GUI/application.css").toExternalForm());
            primaryStage.setTitle("Toy Language Menu");
            primaryStage.setScene(scene);
            primaryStage.setX(0);
            primaryStage.setY(0);
            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("GUI/pokemon.png")));

            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addStatementsToController() {
        chooseProgramController.addStatement(example1);
        chooseProgramController.addStatement(example2);
        chooseProgramController.addStatement(example3);
        chooseProgramController.addStatement(example4);
        chooseProgramController.addStatement(example5);
        chooseProgramController.addStatement(example6);
        chooseProgramController.addStatement(example7);
        chooseProgramController.addStatement(example8);
        chooseProgramController.addStatement(example9);
        chooseProgramController.addStatement(example10);
        chooseProgramController.addStatement(example11);
        chooseProgramController.addStatement(example12);
        chooseProgramController.addStatement(example13);

    }

    private static final IStatement example1;

    static {
        example1 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(2)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
    }

    private static final IStatement example2;

    static {
        example2 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement(
                        "b",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ArithmeticExpression(
                                                '+',
                                                new ValueExpression(
                                                        new IntegerValue(2)
                                                ),
                                                new ArithmeticExpression(
                                                        '*',
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "b",
                                                new ArithmeticExpression(
                                                        '+',
                                                        new VariableExpression("a"),
                                                        new ValueExpression(
                                                                new IntegerValue(1)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("b")
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example3;

    static {
        example3 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new BooleanType()
                ),
                new CompoundStatement(new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "a",
                                        new ValueExpression(
                                                new BooleanValue(true)
                                        )
                                ),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("a"),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(2)
                                                        )
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(3)
                                                        )
                                                )
                                        ),
                                        new PrintStatement(
                                                new VariableExpression("v")
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example4;

    static {
        example4 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "varf",
                        new StringType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "varf",
                                new ValueExpression(
                                        new StringValue("test.in")
                                )
                        ),
                        new CompoundStatement(
                                new OpenReadFileStatement(
                                        new VariableExpression(
                                                "varf"
                                        )
                                ),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(
                                                "varc",
                                                new IntegerType()
                                        ),
                                        new CompoundStatement(
                                                new ReadFileStatement(
                                                        new VariableExpression(
                                                                "varf"
                                                        ),
                                                        "varc"
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(
                                                                new VariableExpression(
                                                                        "varc"
                                                                )
                                                        ),
                                                        new CompoundStatement(
                                                                new ReadFileStatement(
                                                                        new VariableExpression(
                                                                                "varf"
                                                                        ),
                                                                        "varc"
                                                                ),
                                                                new CompoundStatement(
                                                                        new PrintStatement(
                                                                                new VariableExpression(
                                                                                        "varc"
                                                                                )
                                                                        ),
                                                                        new CloseReadFileExpression(
                                                                                new VariableExpression(
                                                                                        "varf"
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example5;

    static {
        example5 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(
                                                new VariableExpression(
                                                        "v"
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new WriteHeapStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntegerValue(30)
                                                )
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        '+',
                                                        new ReadHeapExpression(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example6;

    static {
        example6 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "a",
                                        new ReferenceType(
                                                new ReferenceType(
                                                        new IntegerType()
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new VariableExpression("v")
                                                        )

                                                ),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example7;

    static {
        example7 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(4)
                                )
                        ),
                        new CompoundStatement(
                                new WhileStatement(
                                        new RelationalExpression(
                                                ">",
                                                new VariableExpression("v"),
                                                new ValueExpression(
                                                        new IntegerValue(0)
                                                )
                                        ),
                                        new CompoundStatement(
                                                new PrintStatement(
                                                        new VariableExpression("v")
                                                ),
                                                new AssignStatement(
                                                        "v",
                                                        new ArithmeticExpression(
                                                                '-',
                                                                new VariableExpression("v"),
                                                                new ValueExpression(
                                                                        new IntegerValue(1)
                                                                )
                                                        )
                                                )
                                        )
                                ),
                                new PrintStatement(
                                        new VariableExpression("v")
                                )
                        )
                )
        );
    }

    private static final IStatement example8;

    static {
        example8 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "a",
                                        new ReferenceType(
                                                new ReferenceType(
                                                        new IntegerType()
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new VariableExpression("v")
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement(
                                                        "v",
                                                        new ValueExpression(
                                                                new IntegerValue(30)
                                                        )
                                                ),
                                                new PrintStatement(
                                                        new ReadHeapExpression(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example9;

    static {
        example9 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new ReferenceType(
                                new IntegerType()
                        )
                ),
                new CompoundStatement(
                        new AllocateHeapStatement(
                                "v",
                                new ValueExpression(
                                        new IntegerValue(20)
                                )
                        ),
                        new CompoundStatement(
                                new PrintStatement(
                                        new ReadHeapExpression(
                                                new VariableExpression(
                                                        "v"
                                                )
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "v",
                                                new ValueExpression(
                                                        new IntegerValue(30)
                                                )
                                        ),
                                        new PrintStatement(
                                                new ArithmeticExpression(
                                                        '+',
                                                        new ReadHeapExpression(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new ValueExpression(
                                                                new IntegerValue(5)
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example10;

    static {
        example10 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "a",
                                new ReferenceType(
                                        new IntegerType()
                                )
                        ),
                        new CompoundStatement(
                                new AssignStatement(
                                        "v",
                                        new ValueExpression(
                                                new IntegerValue(10)
                                        )
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new ValueExpression(
                                                        new IntegerValue(22)
                                                )
                                        ),
                                        new CompoundStatement(
                                                new ForkStatement(
                                                        new CompoundStatement(
                                                                new WriteHeapStatement(
                                                                        "a",
                                                                        new ValueExpression(
                                                                                new IntegerValue(30)
                                                                        )
                                                                ),
                                                                new CompoundStatement(
                                                                        new AssignStatement(
                                                                                "v",
                                                                                new ValueExpression(
                                                                                        new IntegerValue(32)
                                                                                )
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(
                                                                                        new VariableExpression("v")
                                                                                ),
                                                                                new PrintStatement(
                                                                                        new ReadHeapExpression(
                                                                                                new VariableExpression(
                                                                                                        "a"
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        )
                                                ),
                                                new CompoundStatement(
                                                        new PrintStatement(
                                                                new VariableExpression(
                                                                        "v"
                                                                )
                                                        ),
                                                        new PrintStatement(
                                                                new ReadHeapExpression(
                                                                        new VariableExpression("a")
                                                                )
                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example11;

    static {
        example11 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v",
                        new IntegerType()
                ),
                new CompoundStatement(
                        new AssignStatement(
                                "v",
                                new ValueExpression(
                                        new BooleanValue(true)
                                )
                        ),
                        new PrintStatement(
                                new VariableExpression(
                                        "v"
                                )
                        )
                )
        );
    }

    private static final IStatement example12;

    static {
        example12 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "a",
                        new ReferenceType(new IntegerType())
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "b",
                                new ReferenceType(new IntegerType())
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "v",
                                        new IntegerType()
                                ),
                                new CompoundStatement(
                                        new AllocateHeapStatement(
                                                "a",
                                                new ValueExpression(new IntegerValue(0))
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement(
                                                        "b",
                                                        new ValueExpression(new IntegerValue(0))
                                                ),
                                                new CompoundStatement(
                                                        new WriteHeapStatement("a",
                                                                new ValueExpression(new IntegerValue(1))
                                                        ),
                                                        new CompoundStatement(
                                                                new WriteHeapStatement(
                                                                        "b",
                                                                        new ValueExpression(new IntegerValue(2))
                                                                ),
                                                                new CompoundStatement(
                                                                        new ConditionalAssignmentStatement(
                                                                                "v",
                                                                                new RelationalExpression(
                                                                                        "<",
                                                                                        new ReadHeapExpression(new VariableExpression("a")),
                                                                                        new ReadHeapExpression(new VariableExpression("b"))
                                                                                ),
                                                                                new ValueExpression(new IntegerValue(100)),
                                                                                new ValueExpression(new IntegerValue(200))
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("v")),
                                                                                new CompoundStatement(
                                                                                        new ConditionalAssignmentStatement(
                                                                                                "v",
                                                                                                new RelationalExpression(
                                                                                                        ">",
                                                                                                        new ArithmeticExpression(
                                                                                                                '-',
                                                                                                                new ReadHeapExpression(new VariableExpression("b")),
                                                                                                                new ValueExpression(new IntegerValue(2))
                                                                                                        ),
                                                                                                        new ReadHeapExpression(new VariableExpression("a"))
                                                                                                ),
                                                                                                new ValueExpression(new IntegerValue(100)),
                                                                                                new ValueExpression(new IntegerValue(200))
                                                                                        ),
                                                                                        new PrintStatement(new VariableExpression("v"))
                                                                                )
                                                                        )
                                                                )

                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    private static final IStatement example13;

    static {
        example13 = new CompoundStatement(
                new VariableDeclarationStatement(
                        "v1",
                        new ReferenceType(new IntegerType())
                ),
                new CompoundStatement(
                        new VariableDeclarationStatement(
                                "v2",
                                new ReferenceType(new IntegerType())
                        ),
                        new CompoundStatement(
                                new VariableDeclarationStatement(
                                        "v3",
                                        new ReferenceType(new IntegerType())
                                ),
                                new CompoundStatement(
                                        new VariableDeclarationStatement(
                                                "cnt",
                                                new IntegerType()
                                        ),
                                        new CompoundStatement(
                                                new AllocateHeapStatement(
                                                        "v1",
                                                        new ValueExpression(new IntegerValue(2))
                                                ),
                                                new CompoundStatement(
                                                        new AllocateHeapStatement(
                                                                "v2",
                                                                new ValueExpression(new IntegerValue(3))
                                                        ),
                                                        new CompoundStatement(
                                                                new AllocateHeapStatement(
                                                                        "v3",
                                                                        new ValueExpression(new IntegerValue(4))
                                                                ),
                                                                new CompoundStatement(
                                                                        new NewLatchStatement(
                                                                                "cnt",
                                                                                new ReadHeapExpression(new VariableExpression("v2"))
                                                                        ),
                                                                        new CompoundStatement(
                                                                                new ForkStatement(
                                                                                        new CompoundStatement(
                                                                                                new WriteHeapStatement(
                                                                                                        "v1",
                                                                                                        new ArithmeticExpression(
                                                                                                                '*',
                                                                                                                new ReadHeapExpression(new VariableExpression("v1")),
                                                                                                                new ValueExpression(new IntegerValue(10))
                                                                                                        )
                                                                                                ),
                                                                                                new CompoundStatement(
                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v1"))),
                                                                                                        new CountDownStatement("cnt")
                                                                                                )
                                                                                        )
                                                                                ),
                                                                                new CompoundStatement(
                                                                                        new ForkStatement(
                                                                                                new CompoundStatement(
                                                                                                        new WriteHeapStatement(
                                                                                                                "v2",
                                                                                                                new ArithmeticExpression(
                                                                                                                        '*',
                                                                                                                        new ReadHeapExpression(new VariableExpression("v2")),
                                                                                                                        new ValueExpression(new IntegerValue(10))
                                                                                                                )
                                                                                                        ),
                                                                                                        new CompoundStatement(
                                                                                                                new PrintStatement(new ReadHeapExpression(new VariableExpression("v2"))),
                                                                                                                new CountDownStatement("cnt")
                                                                                                        )
                                                                                                )
                                                                                        ),
                                                                                        new CompoundStatement(
                                                                                                new ForkStatement(
                                                                                                        new CompoundStatement(
                                                                                                                new WriteHeapStatement(
                                                                                                                        "v3",
                                                                                                                        new ArithmeticExpression(
                                                                                                                                '*',
                                                                                                                                new ReadHeapExpression(new VariableExpression("v3")),
                                                                                                                                new ValueExpression(new IntegerValue(10))
                                                                                                                        )
                                                                                                                ),
                                                                                                                new CompoundStatement(
                                                                                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v3"))),
                                                                                                                        new CountDownStatement("cnt")
                                                                                                                )
                                                                                                        )
                                                                                                ),
                                                                                                new CompoundStatement(
                                                                                                        new AwaitStatement("cnt"),
                                                                                                        new CompoundStatement(
                                                                                                                new PrintStatement(new ValueExpression(new IntegerValue(100))),
                                                                                                                new CompoundStatement(
                                                                                                                        new CountDownStatement("cnt"),
                                                                                                                        new PrintStatement(new ValueExpression(new IntegerValue(100)))
                                                                                                                )
                                                                                                        )
                                                                                                )

                                                                                        )

                                                                                )
                                                                        )
                                                                )

                                                        )
                                                )
                                        )
                                )
                        )
                )
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}


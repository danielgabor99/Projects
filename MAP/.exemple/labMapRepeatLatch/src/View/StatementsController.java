package View;

import Controller.Controller;
import Model.*;
import Model.Exp.*;
import Model.PrgState.*;
import Model.Type.BoolType;
import Model.Type.IntType;
import Model.Type.RefType;
import Model.Type.StringType;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.Repository;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class StatementsController implements Initializable {
    private List<IStmt> programStatements;
    private MainWindowController mainWindowController;

    @FXML
    private ListView<String> programStatementsListView;

    @FXML
    private Button executeProgram;

    void setMainWindowController(MainWindowController mainWindowController) {
        this.mainWindowController = mainWindowController;
    }

    public void buildstatements() {
        //int v; v=2;//Print(v) is represented as:
        MyIStack<IStmt> ExeStack1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<String, Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable1 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp1 = new MyHeap<Integer, Value>();
        ILatch<Integer, Integer> latch = new Latch<>();


        executeProgram.setOnAction(actionEvent -> {
            int index = programStatementsListView.getSelectionModel().getSelectedIndex();
            if (index < 0)
                return;
            PrgState initialPrgState = new PrgState(ExeStack1, symTable1, out1, fileTable1, hp1, programStatements.get(index), latch);
            Repository repository;
            index++;
            try {
                repository = new Repository(initialPrgState, "log" + index + ".txt");
                Controller ctrl = new Controller(repository);
                mainWindowController.setController(ctrl);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buildstatements();
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        //Print(b) is represented as:
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
        IStmt Fork = new CompStmt(new wH("a", new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a"))))));
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewAllocation("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(Fork),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in.txt"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        IStmt ex5 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));

        IStmt f = new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))));
        IStmt repeat = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new repeatStmt(new CompStmt(new ForkStmt(f), new AssignStmt("v", new ArithExp('+', new VarExp("v"), new ValueExp(new IntValue(1))))),
                        new RelExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==")),
                        new CompStmt(new VarDeclStmt("x", new IntType()),
                                new CompStmt(new AssignStmt("x", new ValueExp(new IntValue(1))),
                                        new CompStmt(new VarDeclStmt("y", new IntType()),
                                                new CompStmt(new AssignStmt("y", new ValueExp(new IntValue(2))),
                                                        new CompStmt(new VarDeclStmt("z", new IntType()),
                                                                new CompStmt(new AssignStmt("z", new ValueExp(new IntValue(3))),
                                                                        new CompStmt(new VarDeclStmt("w", new IntType()),
                                                                                new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(4))),
                                                                                        new PrintStmt(new ArithExp('*', new VarExp("v"), new ValueExp(new IntValue(10))))))))))))));
        IStmt fork3 = new CompStmt(new wH("v3", new ArithExp('*', new rH(new VarExp("v3")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v3"))), new CountDown("cnt")));
        IStmt fork2 = new CompStmt(new wH("v2", new ArithExp('*', new rH(new VarExp("v2")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v2"))),
                        new CompStmt(new CountDown("cnt"), new ForkStmt(fork3))));
        IStmt fork1 = new CompStmt(new wH("v1", new ArithExp('*', new rH(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                new CompStmt(new PrintStmt(new rH(new VarExp("v1"))),
                        new CompStmt(new CountDown("cnt"), new ForkStmt(fork2))));

        IStmt Latch = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                new CompStmt(new NewAllocation("v1", new ValueExp(new IntValue(2))),
                        new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),

                                new CompStmt(new NewAllocation("v2", new ValueExp(new IntValue(3))),
                                        new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                                new CompStmt(new NewAllocation("v3", new ValueExp(new IntValue(4))),
                                                        new CompStmt(new newLatch("cnt", new rH(new VarExp("v2"))),
                                                                new CompStmt(new ForkStmt(fork1),
                                                                        new CompStmt(new await("cnt"),
                                                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                                                        new CompStmt(new CountDown("cnt"), new PrintStmt(new ValueExp(new IntValue(100))))))))))))));


        programStatements = new ArrayList<>(Arrays.asList(ex1, ex2, ex10, ex4, ex5, repeat, Latch));
        programStatementsListView.getItems().addAll(ex1.toString(), ex2.toString(), ex10.toString(), ex4.toString(), ex5.toString(), repeat.toString(), Latch.toString());
    }
}

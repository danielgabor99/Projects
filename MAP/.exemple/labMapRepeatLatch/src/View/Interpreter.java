package View;

import Controller.Controller;
import Model.*;
import Model.Exp.*;
import Model.PrgState.*;
import Model.Type.*;
import Model.Value.BoolValue;
import Model.Value.IntValue;
import Model.Value.StringValue;
import Model.Value.Value;
import Repository.Repository;
import com.company.MyException;
import Repository.RepositoryI;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Interpreter {
    public static void main(String[] args) throws FileNotFoundException, MyException {
        // write your code here
        MyIStack<IStmt> ExeStack1 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable1 = new MyDictionary<String, Value>();
        MyIList<Value> out1 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable1 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp1 = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStack2 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable2 = new MyDictionary<String, Value>();
        MyIList<Value> out2 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable2 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp2 = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStack3 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable3 = new MyDictionary<String, Value>();
        MyIList<Value> out3 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable3 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp3 = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStack4 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable4 = new MyDictionary<String, Value>();
        MyIList<Value> out4 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable4 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp4 = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStackwhile = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTablewhile = new MyDictionary<String, Value>();
        MyIList<Value> outwhile = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTablewhile = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hpwhile = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStacknewAllocation = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTablenewAllocation = new MyDictionary<String, Value>();
        MyIList<Value> outnewAllocation = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTablenewAllocation = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hpnewAllocation = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStackrH = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTablerH = new MyDictionary<String, Value>();
        MyIList<Value> outrH = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTablerH = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hprH = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStackwH = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTablewH = new MyDictionary<String, Value>();
        MyIList<Value> outwH = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTablewH = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hpwH = new MyHeap<Integer, Value>();

        MyIStack<IStmt> ExeStackg = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTableg = new MyDictionary<String, Value>();
        MyIList<Value> outg = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTableg = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hpg = new MyHeap<Integer, Value>();


        MyIStack<IStmt> ExeStack10 = new MyStack<IStmt>();
        MyIDictionary<String, Value> symTable10 = new MyDictionary<String, Value>();
        MyIList<Value> out10 = new MyList<Value>();
        MyIDictionary<String, BufferedReader> fileTable10 = new FileTable<String, BufferedReader>();
        MyIHeap<Integer, Value> hp10 = new MyHeap<Integer, Value>();


        //int v; v=2;//Print(v) is represented as:
        IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new
                        VarExp("v"))));
        int a;
        int b;
        a = 2 + 3 * 5;
        b = a + 1;//Print(b) is represented as:
        IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                new CompStmt(new VarDeclStmt("b", new IntType()),
                        new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)), new
                                ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"), new
                                        ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));


        //boolean a;int v;a=true;//(If a Then v=2 Else v=3);Print(v) is represented as
        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                        IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                        VarExp("v"))))));
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("test.in.txt"))),
                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        IStmt exwhile = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                        new CompStmt(new WhileStmt(new RelExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"),
                                new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(1)))))),
                                new PrintStmt(new VarExp("v")))));
        IStmt exnewAllocation = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAllocation("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        IStmt exrH = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAllocation("a", new VarExp("v")),
                                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                                new PrintStmt(new ArithExp('+', new rH(new rH(new VarExp("a"))), new ValueExp(new IntValue(5)))))))));
        IStmt exwH = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new PrintStmt(new rH(new VarExp("v"))),
                                new CompStmt(new wH("v", new ValueExp(new IntValue(30))),
                                        new PrintStmt(new ArithExp('+', new rH(new VarExp("v")), new ValueExp(new IntValue(5))))))));
        IStmt exg = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                new CompStmt(new NewAllocation("v", new ValueExp(new IntValue(20))),
                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                new CompStmt(new NewAllocation("a", new VarExp("v")),
                                        new CompStmt(new NewAllocation("v", new ValueExp(new IntValue(30))),
                                                new CompStmt(new NewAllocation("a", new VarExp("v")), new PrintStmt(new rH(new rH(new VarExp("a"))))))))));

        IStmt Fork = new CompStmt(new wH("a", new ValueExp(new IntValue(30))),
                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a"))))));

        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()),
                new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                new CompStmt(new NewAllocation("a", new ValueExp(new IntValue(22))),
                                        new CompStmt(new ForkStmt(Fork),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new rH(new VarExp("a")))))))));

        MyIDictionary<String, Type> typecheckrs = new MyDictionary<String, Type>();
        System.out.println(ex2.typecheck(typecheckrs).toString());
        /**
         PrgState prg1 = new PrgState(ExeStack1, symTable1, out1, fileTable1, hp1, ex1);
         RepositoryI repo1 = new Repository(prg1, "text1.txt");
         Controller ctr1 = new Controller(repo1);
         PrgState prg2 = new PrgState(ExeStack2, symTable2, out2, fileTable2, hp2, ex2);
         RepositoryI repo2 = new Repository(prg2, "text2.txt");
         Controller ctr2 = new Controller(repo2);
         PrgState prg3 = new PrgState(ExeStack3, symTable3, out3, fileTable3, hp3, ex3);
         RepositoryI repo3 = new Repository(prg3, "text3.txt");
         Controller ctr3 = new Controller(repo3);
         PrgState prg4 = new PrgState(ExeStack4, symTable4, out4, fileTable4, hp4, ex4);
         RepositoryI repo4 = new Repository(prg4, "text4.txt");
         Controller ctr4 = new Controller(repo4);
         PrgState prgwhile = new PrgState(ExeStackwhile, symTablewhile, outwhile, fileTablewhile, hpwhile, exwhile);
         RepositoryI repowhile = new Repository(prgwhile, "testwhile.txt");
         Controller ctrwhile = new Controller(repowhile);
         PrgState prgnewAllocation = new PrgState(ExeStacknewAllocation, symTablenewAllocation, outnewAllocation, fileTablenewAllocation, hpnewAllocation, exnewAllocation);
         RepositoryI reponewAllocation = new Repository(prgnewAllocation, "testnewAllocation.txt");
         Controller ctrnewAllocation = new Controller(reponewAllocation);
         PrgState prgrH = new PrgState(ExeStackrH, symTablerH, outrH, fileTablerH, hprH, exrH);
         RepositoryI reporH = new Repository(prgrH, "testrH.txt");
         Controller ctrrH = new Controller(reporH);
         PrgState prgwH = new PrgState(ExeStackwH, symTablewH, outwH, fileTablewH, hpwH, exwH);
         RepositoryI repowH = new Repository(prgwH, "testwH.txt");
         Controller ctrwH = new Controller(repowH);
         PrgState prgg = new PrgState(ExeStackg, symTableg, outg, fileTableg, hpg, exg);
         RepositoryI repog = new Repository(prgg, "testg.txt");
         Controller ctrg = new Controller(repog);
         PrgState prg10 = new PrgState(ExeStack10, symTable10, out10, fileTable10, hp10, ex10);
         RepositoryI repo10 = new Repository(prg10, "text10.txt");
         Controller ctr10 = new Controller(repo10);

         TextMenu menu = new TextMenu();
         menu.addCommand(new ExitCommand("0", "exit"));
         menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
         menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
         menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
         menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
         menu.addCommand(new RunExample("5", exwhile.toString(), ctrwhile));
         menu.addCommand(new RunExample("6", exnewAllocation.toString(), ctrnewAllocation));
         menu.addCommand(new RunExample("7", exrH.toString(), ctrrH));
         menu.addCommand(new RunExample("8", exwH.toString(), ctrwH));
         menu.addCommand(new RunExample("9", exg.toString(), ctrg));
         menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
         menu.show();
         **/
    }

}

package View;

import Controller.Controller;
import Model.IStmt;
import Model.IfStmt;
import Model.PrgState.*;
import Model.Value.Value;
import com.company.MyException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


import java.io.BufferedReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {
    private Controller controller;
    @FXML
    private Button executeOneStepButton;

    @FXML
    private ListView<Integer> prgStateListView;
    @FXML
    private TextField nrPrgStatesTextField;

    @FXML
    private TableView<Map.Entry<Integer, Value>> heapTableView;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Integer> heapAddressColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Value> heapValueColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Integer> indexSemColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, Value> valueSemColumn;
    @FXML
    private TableColumn<Map.Entry<Integer, Value>, List<Value>> listValuesSemColumn;
    @FXML
    private TableView<Map.Entry<Integer, Value>> SemView;


    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<Value> outputListView;

    @FXML
    private TableView<Map.Entry<String, Value>> symblTableView;
    @FXML
    private TableColumn<Map.Entry<String, Integer>, String> symbolTableVariableColumn;
    @FXML
    private TableColumn<Map.Entry<String, Value>, Value> symbolTableValueColumn;

    @FXML
    private ListView<String> executionStackListView;

    public void setController(Controller ctrl) {
        this.controller = ctrl;
        populatePrgStateIdentifiers();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        heapAddressColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        heapValueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));
        symbolTableVariableColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().getKey() + ""));
        symbolTableValueColumn.setCellValueFactory(p -> new SimpleObjectProperty<>(p.getValue().getValue()));
//        indexSemColumn.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().getKey()).asObject());
        //      valueSemColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue()));
        //listValuesSemColumn.setCellValueFactory(p->new SimpleObjectProperty<>(p.getValue().getValue()));

        prgStateListView.setOnMouseClicked(mouseEvent -> changePrgState(getCurrentPrgState()));
        executeOneStepButton.setOnAction(actionEvent -> executeOneStep());

    }

    private List<Integer> getPrgStatesIds(List<PrgState> prgStateList) {
        return prgStateList.stream().map(PrgState::getId).collect(Collectors.toList());
    }

    private void populatePrgStateIdentifiers() {
        List<PrgState> prgStates = controller.getRepo().getPrgList();
        prgStateListView.setItems(FXCollections.observableList(getPrgStatesIds(prgStates)));
        nrPrgStatesTextField.setText("" + prgStates.size());
    }

    private void executeOneStep() {
        if (controller == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "The program was not selected", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        boolean programStateLeft = controller.getRepo().getPrgList().isEmpty();
        if (programStateLeft) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nothing left to execute", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        try {
            controller.oneStep();
        } catch (MyException | InterruptedException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return;
        }
        changePrgState(getCurrentPrgState());
        controller.removeAfterOneStep();
        populatePrgStateIdentifiers();
    }

    private void changePrgState(PrgState currentPrgState) {
        if (currentPrgState == null) {
            return;
        } else {
            populateExecutionStack(currentPrgState);
            populateSymbolTable(currentPrgState);
            populateOutput(currentPrgState);
            populateFileTable(currentPrgState);
            populateHeapTable(currentPrgState);
            pupulateSemTable(currentPrgState);
        }
    }

    public void populateHeapTable(PrgState currentPrgState) {
        MyIHeap<Integer, Value> heapTable = currentPrgState.getHeap();

        List<Map.Entry<Integer, Value>> heapTableList = new ArrayList<>(heapTable.getContent().entrySet());
        heapTableView.setItems(FXCollections.observableList(heapTableList));
        heapTableView.refresh();
    }

    public void populateFileTable(PrgState currentPrgState) {
        MyIDictionary<String, BufferedReader> fileTable = currentPrgState.getFileTable();
        List<String> fileList = new ArrayList<>();
        for (Map.Entry<String, BufferedReader> entry : fileTable.getContent().entrySet())
            fileList.add(entry.getKey());
        ObservableList<String> files = FXCollections.observableArrayList(fileList);
        fileTableListView.setItems(files);
        fileTableListView.refresh();
    }

    public void populateOutput(PrgState currentPrgState) {
        ObservableList<Value> output = FXCollections.observableList(currentPrgState.getOut().getValues());
        outputListView.setItems(output);
        outputListView.refresh();
    }

    public void populateSymbolTable(PrgState currentPrgState) {
        MyIDictionary<String, Value> symblTable = currentPrgState.getSymTable();

        List<Map.Entry<String, Value>> symblTableList = new ArrayList<>(symblTable.getContent().entrySet());
        symblTableView.setItems(FXCollections.observableList(symblTableList));
        symblTableView.refresh();
    }

    public void pupulateSemTable(PrgState currentPrgState) {
        // SemaphoreI<Integer, Triplet<Integer, List<Integer>,Integer>> sem=currentPrgState.getSem();
        //List<Map.Entry<Integer,Value>> semList= new ArrayList<>(sem.getContent().entrySet());
        //SemView.setItems(FXCollections.observableList(semList));
//        SemView.refresh();
    }

    private void populateExecutionStack(PrgState currentPrgState) {
        MyIStack<IStmt> executionStack = currentPrgState.getStk();

        List<String> executionStackList = new ArrayList<>();
        for (IStmt s : executionStack.convertToList()) {
            executionStackList.add(0, s.toString());
        }
        executionStackListView.setItems(FXCollections.observableList(executionStackList));
        executionStackListView.refresh();
    }

    private PrgState getCurrentPrgState() {
        if (prgStateListView.getSelectionModel().getSelectedIndex() == -1)
            return null;
        int currentId = prgStateListView.getSelectionModel().getSelectedItem();
        return controller.getRepo().getPrgStateWithId(currentId);
    }

}

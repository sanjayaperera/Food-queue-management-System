package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class QueuesGUIController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<Customer> table;          //THE NAME OF THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, String> c1;   //THE NAME OF THA FIRST COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, String> c2;   //THE NAME OF THA SECOND COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, Integer> c3;  //THE NAME OF THA THIRD COLUMN IN THE TABLE IN THE FXML FILE

    //CUSTOMER DETAILS ADD TABLE------------------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    ObservableList<Customer> que=FXCollections.observableArrayList();
    for(int i = 0; i< Main.q1Size(); i++){
            que.add(new Customer(Main.q1Burgers(i), Main.q1Fname(i), Main.q1Sname(i)));}
    for(int i = 0; i< Main.q2Size(); i++){
            que.add( new Customer(Main.q2Burgers(i), Main.q2Fname(i), Main.q2Sname(i)));}
    for(int i = 0; i< Main.q3Size(); i++){
            que.add(new Customer(Main.q3Burgers(i), Main.q3Fname(i), Main.q3Sname(i)));}

        table.setItems(que);

    c1.setCellValueFactory(new PropertyValueFactory<Customer,String>("Firstname"));
    c2.setCellValueFactory(new PropertyValueFactory<Customer,String>("Secondname"));
    c3.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("BurgersAmount"));
    }
    //LOAD WAITING QUEUE PART-------------------------------------------------------------------------------------------
    public void goNext(ActionEvent event) {
            try{Parent root = FXMLLoader.load(getClass().getResource("waitingQueueGUI.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();}catch (Exception e){}
    }
}

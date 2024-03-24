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

public class WaitingQueueGUIController implements Initializable {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private TableView<Customer> table;          //THE NAME OF THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, String> c1;    //THE NAME OF THA FIRST COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, String> c2;     //THE NAME OF THA SECOND COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, Integer> c3;     //THE NAME OF THA THIRD COLUMN IN THE TABLE IN THE FXML FILE

    //WAITING QUEUE CUSTOMER DETAILS ADD TABLE--------------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> que1= FXCollections.observableArrayList();
        for(int i =0;  i<10; i++){
            if(Main.wqObject(i)!=null){
                 que1.add(new Customer(Main.wqBurgers(i), Main.wqFname(i), Main.wqSname(i)));}
        table.setItems(que1);

        c1.setCellValueFactory(new PropertyValueFactory<Customer,String>("Firstname"));
        c2.setCellValueFactory(new PropertyValueFactory<Customer,String>("Secondname"));
        c3.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("BurgersAmount"));}
    }

    //LOAD QUEUE PART---------------------------------------------------------------------------------------------------
    public void goBefore(ActionEvent event){
       try{ Parent root = FXMLLoader.load(getClass().getResource("queuesGUI.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }catch (Exception e){}  }

    //LOAD SEARCH PART--------------------------------------------------------------------------------------------------
    public void goForward(ActionEvent event){
        try{Parent root = FXMLLoader.load(getClass().getResource("serchCustomerGUI.fxml"));
        stage=(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
        }catch (Exception e){}  }
}

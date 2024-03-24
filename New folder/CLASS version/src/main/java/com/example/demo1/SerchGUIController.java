package com.example.demo1;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class SerchGUIController {
    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private TableColumn<Customer, String> c1;       //THE NAME OF THA FIRST COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, String> c2;       //THE NAME OF THA SECOND COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableColumn<Customer, Integer> c3;      //THE NAME OF THA THIRD COLUMN IN THE TABLE IN THE FXML FILE
    @FXML
    private TableView<Customer> table;                //THE NAME OF THE TABLE IN THE FXML FILE
    @FXML
    private TextField t1;                             //THE NAME OF THE TEXT FIELD
    @FXML
    private Label l1;                                  //THE NAME OF THE LABEL

    //SEARCH CUSTOMER DETAILS-------------------------------------------------------------------------------------------
    public void searchCustomer() {
        for (int i = 0; i < 10; i++) {
            if(i<Main.q1Size()){
                if (t1.getText().equals(Main.q1Fname(i))) {
                    serch(Main.q1Fname(i), Main.q1Sname(i), Main.q1Burgers(i));
                    l1.setText("this customer is in queues");
                    break;
                }
            }
            if(i<Main.q2Size()){
                if (t1.getText().equals(Main.q2Fname(i))) {
                    serch(Main.q2Fname(i), Main.q2Sname(i), Main.q2Burgers(i));
                    l1.setText("this customer is in queues");
                    break;
                }
            }
            if(i<Main.q3Size()){
                if (t1.getText().equals(Main.q3Fname(i))) {
                    serch(Main.q3Fname(i), Main.q3Sname(i), Main.q3Burgers(i));
                    l1.setText("this customer is in queues");
                    break;
                }
            }
            if (Main.wqObject(i) != null) {
                if (t1.getText().equals(Main.wqFname(i))) {
                    serch(Main.wqFname(i), Main.wqSname(i), Main.wqBurgers(i));
                    l1.setText("this customer is in queues");
                    break;
                }
            }
            else {l1.setText("this customer is not in queues");}
        }
    }
    public void serch(String fname,String sname,int burgers){

        ObservableList<Customer> que2= FXCollections.observableArrayList();
        que2.add(new Customer(burgers, fname, sname));
        table.setItems(que2);

        c1.setCellValueFactory(new PropertyValueFactory<Customer,String>("Firstname"));
        c2.setCellValueFactory(new PropertyValueFactory<Customer,String>("Secondname"));
        c3.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("BurgersAmount"));
    }

    //LOAD WAITING QUEUE PART-------------------------------------------------------------------------------------------
    public void goBefore1(ActionEvent event) {
        try{Parent root = FXMLLoader.load(getClass().getResource("waitingQueueGUI.fxml"));
            stage=(Stage)((Node)event.getSource()).getScene().getWindow();
            scene=new Scene(root);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e){}
    }
}



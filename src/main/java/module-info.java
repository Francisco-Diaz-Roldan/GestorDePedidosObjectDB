module com.example.gestordepedidos {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;


    exports com.example.gestordepedidos;
    //exports com.example.gestordepedidos.controller.login;

    opens com.example.gestordepedidos to javafx.fxml;
    //opens com.example.gestordepedidos.controller.login to javafx.fxml;
    exports com.example.gestordepedidos.controller;
    opens com.example.gestordepedidos.controller to javafx.fxml;
}
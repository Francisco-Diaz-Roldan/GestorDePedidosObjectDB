module com.example.gestordepedidos {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires lombok;
    requires java.naming;
    requires javafx.swing; // Para swing
    requires jasperreports;// Para jaspersoft
    requires javax.persistence; // Para objectDB (sustituye a jakarta y elimina tambien hibernate por incompatibilidad)


    opens com.example.gestordepedidoshibernate.domain.item;
    opens com.example.gestordepedidoshibernate.domain.pedido;
    opens com.example.gestordepedidoshibernate.domain.producto;
    opens com.example.gestordepedidoshibernate.domain.usuario;

    opens com.example.gestordepedidoshibernate to javafx.fxml;
    opens com.example.gestordepedidoshibernate.controller to javafx.fxml;

    exports com.example.gestordepedidoshibernate;
    exports com.example.gestordepedidoshibernate.controller;
}
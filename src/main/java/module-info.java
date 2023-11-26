module com.example.gestordepedidos {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires lombok;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;


    opens com.example.gestordepedidoshibernate.domain.item;
    opens com.example.gestordepedidoshibernate.domain.pedido;
    opens com.example.gestordepedidoshibernate.domain.producto;
    opens com.example.gestordepedidoshibernate.domain.usuario;

    opens com.example.gestordepedidoshibernate to javafx.fxml;
    opens com.example.gestordepedidoshibernate.controller to javafx.fxml;

    exports com.example.gestordepedidoshibernate;
    exports com.example.gestordepedidoshibernate.controller;

}
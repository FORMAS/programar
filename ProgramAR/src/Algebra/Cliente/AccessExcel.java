package Algebra.Cliente;

import java.sql.Connection;
import java.sql.DriverManager;

public class AccessExcel{

    public AccessExcel(){
    }

    public Connection getConnection() throws Exception {
        Class.forName("org.gjt.mm.mysql.Driver").newInstance();
        return DriverManager.getConnection("jdbc:mysql://localhost/JAVADB");
    }
}


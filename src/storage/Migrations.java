package src.storage;

import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Migrations {

    private static Migrations migrationsInstance = null;

    private Migrations() {
    }

    public static Migrations getInstance() {
        if (migrationsInstance == null) migrationsInstance = new Migrations();
        return migrationsInstance;
    }

    //create table warehouse (name VARCHAR2(30) not null, value NUMBER(8,0) default 0);
    //create table orders (lieferung NUMBER(1,0),
    //                plz NUMBER(8,0),
    //                firstname VARCHAR2(30),
    //                secondname VARCHAR2(30),
    //                address VARCHAR2(50),
    //                floor NUMBER(3,0),
    //                telefon VARCHAR2(30),
    //                orderid NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY);
    public void createTables() {
        String createWarehouse = "create table warehouse (name VARCHAR2(30) not null, value NUMBER(8,0) default 0);";
        String createOrder = "create table orders (lieferung NUMBER(1,0), " +
                "plz NUMBER(8,0), " +
                "firstname VARCHAR2(30), " +
                "secondname VARCHAR2(30), " +
                "address VARCHAR2(50), " +
                "floor NUMBER(3,0), " +
                "telefon VARCHAR2(30), " +
                "orderid NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY);";

        Connection connection = null;

        try {
            connection = OracleDsSingleton.getInstance().getConnection();
            connection.prepareStatement(createWarehouse).executeUpdate();
            connection.prepareStatement(createOrder).executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
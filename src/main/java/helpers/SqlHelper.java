package helpers;

import java.sql.*;

public class SqlHelper {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        executeUpdate("Delete FROM test where id=1");
        executeUpdate("INSERT INTO test (name,method_name,project_id,session_id,env) VALUES ('Auto incrementer','blabla_method','2','2','blabla_env');");

        ResultSet rs = executeFetch("SELECT * FROM test");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "----------------" + rs.getString(2));
        }

    }

    public static Connection connection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/union_reporting", "root", "root");
            return con;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet executeFetch(String sqlFetchCommand) {
        Statement statement;
        try {
            statement = connection().createStatement();
            ResultSet rs = statement.executeQuery(sqlFetchCommand);
            connection().close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeUpdate(String sqlUpdateCommand) {
        Statement statement = null;
        try {
            statement = connection().createStatement();
            statement.executeUpdate(sqlUpdateCommand);
            connection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    sql command for inserting new rows in union_reportings test table

    INSERT INTO test (id, name,method_name,project_id,session_id,env)
VALUES ('1', 'Tom B. Erichsen','blabla_method','2','2','blabla_env');

     */
    public static void insertTestResults(String name, String method_name, int project_id, int session_id, String env) {
        executeUpdate("INSERT INTO test (name,method_name,project_id,session_id,env) VALUES ('" + name + "','" + method_name + "','" + project_id + "','" + session_id + "','" + env + "');");

    }
}

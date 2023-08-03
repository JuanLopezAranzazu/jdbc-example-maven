package factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import utils.DatabaseConfig;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private DataSource dataSource;
    public ConnectionFactory(){
        Properties props = new DatabaseConfig().loadEnv();
        String host = props.getProperty("DB_HOST");
        String port = props.getProperty("DB_PORT");
        String dbName = props.getProperty("DB_NAME");
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");
        var pooledDataSource=new ComboPooledDataSource();
        pooledDataSource.setJdbcUrl("jdbc:mysql://"+host+":"+port+"/"+dbName+"?userTimeZone=true&serverTimeZone=UTC");
        pooledDataSource.setUser(user);
        pooledDataSource.setPassword(password);
        this.dataSource=pooledDataSource;
    }
    public Connection getConnection(){
        /*Properties props = new DatabaseConfig().loadEnv();
        String host = props.getProperty("DB_HOST");
        String port = props.getProperty("DB_PORT");
        String dbName = props.getProperty("DB_NAME");
        String user = props.getProperty("DB_USER");
        String password = props.getProperty("DB_PASSWORD");
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://"+host+":"+port+"/"+dbName+"?userTimeZone=true&serverTimeZone=UTC",
                    user,
                    password
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;*/
        try {
            return this.dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

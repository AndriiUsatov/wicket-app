package com.andrii.app.util;


import com.andrii.app.exception.ConnectionException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger logger  = Logger.getLogger(ConnectionPool.class);
    private DataSource dataSource;
    private static ConnectionPool connectionPoolInstance;

    private ConnectionPool(){
        try {
            dataSource = ((DataSource)((Context)(new InitialContext()
                    .lookup("java:comp/env")))
                    .lookup("jdbc/wicket_crud"));
        } catch (NamingException e) {
            logger.error(e);
            e.printStackTrace();
        }
    }

    public static ConnectionPool getInstance(){
        if(connectionPoolInstance == null){
            synchronized (ConnectionPool.class){
                if(connectionPoolInstance == null){
                    connectionPoolInstance = new ConnectionPool();
                }
            }
        }
        return connectionPoolInstance;
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            e.printStackTrace();
            throw new ConnectionException(e);
        }
    }
}

package com.hololeenko.task_4.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);

    private static ConnectionPool instance;

    private static  final ReentrantLock lock = new ReentrantLock();

    private BlockingQueue<Connection> free;
    private BlockingQueue<Connection> used;

    private String url;
    private String user;
    private String password;
    private String driver;
    private int poolSize;




    private ConnectionPool(){
        loadProperties();
        initPool();
    }

    public static ConnectionPool getInstance() {
        if(instance == null){
            lock.lock();
            try{
                if(instance == null){
                    instance = new ConnectionPool();
                    logger.info("Connection pool is created");
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private void loadProperties(){
        Properties properties = new Properties();

        try(InputStream inputStream = ConnectionPool.class.getClassLoader()
                .getResourceAsStream("db.properties")){

            properties.load(inputStream);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            driver = properties.getProperty("db.driver");
            poolSize = Integer.parseInt(properties.getProperty("db.pool.size"));

        }catch (IOException e){
            logger.fatal("Error loading db properties", e);
            throw new ExceptionInInitializerError("DB properties not found");
        }
    }

    private void initPool(){
        try {
            Class.forName(driver);

            free = new ArrayBlockingQueue<>(poolSize);
            used = new ArrayBlockingQueue<>(poolSize);

            for(int i = 0; i < poolSize; i++){
                Connection connection = DriverManager.getConnection(url, user, password);
                free.add(connection); //add отличается от offer тем, что выбрасывает ошибку если очередь переполнена, а offer просто возвращает false
            }

            logger.info("Pool has been initialized");
        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal("Error initializing connection pool", e);
            throw new ExceptionInInitializerError("Error initializing connection pool");
        }
    }

    public Connection getConnection(){
        Connection connection = null;
        try{
            connection = free.take();
            used.put(connection);
        } catch (InterruptedException e) {
            logger.error("Error getting connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            try {
                used.remove(connection);
                free.put(connection);
            } catch (InterruptedException e) {
                logger.error("Error realising connection", e);
                Thread.currentThread().interrupt();
            }
        }
    }

    public void destroyPool(){
        for(int i = 0; i < poolSize; i++){
            try{
                Connection freeConnection = free.poll();
                if(freeConnection != null){
                    freeConnection.close();
                }

                Connection usedConnection = used.poll();
                if(usedConnection != null){
                    usedConnection.close();
                }
            }catch (SQLException e){
                logger.error("Error closing connection", e);
            }
        }

        //Дерегестрировать драйвер
    }
}

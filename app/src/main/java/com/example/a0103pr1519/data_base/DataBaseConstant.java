package com.example.a0103pr1519.data_base;

public class DataBaseConstant {
    /**table users */
    public static final String DATA_BASE_NAME = "salon.db";
    public static final int DATA_BASE_VERSION = 1;
    public static final String USERS_TABLE_NAME = "Users";
    public static final String USER_ID = "id";
    public static final String USER_LOGIN = "login";
    public static final String USER_PASSWORD = "password";
    public static final String USER_FIRST_NAME = "firstName";
    public static final String USER_SECOND_NAME = "secondName";
    public static final String USER_PATRONYMIC = "patronymic";
    public static final String USER_DATE_BIRTHDAY = "dateBirthday";

    /** table orders */
    public static final String ORDERS_TABLE_NAME = "Orders";
    public static final String ORDER_ID = "id";
    public static final String ID_USER = "userId";
    public static final String ID_SERVICE = "serviceId";
    public static final String DATE_RECORD = "dateRecord";

    /** table services */

    public static final String SERVICES_TABLE_NAME = "Services";
    public static final String SERVICE_ID = "id";
    public static final String SERVICE_NAME = "name";
    public static final String SERVICE_PRICE = "price";
    public static final String SERVICE_DESCRIPTION = "description";

    /** create and delete tables */

    public static final String CREATE_TABLE_USERS = "CREATE TABLE IF NOT EXISTS " +
            "" + USERS_TABLE_NAME + " ( " + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + USER_LOGIN + " TEXT UNIQUE, " + USER_PASSWORD + " TEXT, " +
            "" + USER_FIRST_NAME + " TEXT, " + USER_SECOND_NAME + " TEXT, " +
            "" + USER_PATRONYMIC + " TEXT, " + USER_DATE_BIRTHDAY + " TEXT);";
    public static final String DELETE_TABLE_USERS = "DROP TABLE IF EXISTS " + USERS_TABLE_NAME;

    public static final String CREATE_TABLE_SERVICES = "CREATE TABLE IF NOT EXISTS " +
            "" + SERVICES_TABLE_NAME + " ( " + SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "" + SERVICE_NAME + " TEXT UNIQUE, " + SERVICE_PRICE + " INTEGER, " +
            "" + SERVICE_DESCRIPTION + " TEXT);";
    public static final String DELETE_TABLE_SERVICES = "DROP TABLE IF EXISTS " + SERVICES_TABLE_NAME;

    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE IF NOT EXISTS " +
            "" + ORDERS_TABLE_NAME + " ( " + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + DATE_RECORD + " TEXT, " +
            "" + ID_USER + " INTEGER, " + ID_SERVICE + " INTEGER, " + "FOREIGN KEY (" + ID_USER + ")  REFERENCES " + USERS_TABLE_NAME + "(" + USER_ID + "), " +
            "" + "FOREIGN KEY (" + ID_SERVICE + ") REFERENCES " + SERVICES_TABLE_NAME + "(" + SERVICE_ID + "));";
    public static final String DETELE_TABLE_ORDERS = "DROP TABLES IF EXISTS " + ORDERS_TABLE_NAME;
}

package com.example.a0103pr1519.data_base;

import static com.example.a0103pr1519.data_base.DataBaseConstant.DATE_RECORD;
import static com.example.a0103pr1519.data_base.DataBaseConstant.ID_SERVICE;
import static com.example.a0103pr1519.data_base.DataBaseConstant.ID_USER;
import static com.example.a0103pr1519.data_base.DataBaseConstant.ORDERS_TABLE_NAME;
import static com.example.a0103pr1519.data_base.DataBaseConstant.ORDER_ID;
import static com.example.a0103pr1519.data_base.DataBaseConstant.SERVICES_TABLE_NAME;
import static com.example.a0103pr1519.data_base.DataBaseConstant.SERVICE_DESCRIPTION;
import static com.example.a0103pr1519.data_base.DataBaseConstant.SERVICE_ID;
import static com.example.a0103pr1519.data_base.DataBaseConstant.SERVICE_NAME;
import static com.example.a0103pr1519.data_base.DataBaseConstant.SERVICE_PRICE;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USERS_TABLE_NAME;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_DATE_BIRTHDAY;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_FIRST_NAME;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_ID;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_LOGIN;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_PASSWORD;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_PATRONYMIC;
import static com.example.a0103pr1519.data_base.DataBaseConstant.USER_SECOND_NAME;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.a0103pr1519.data.Orders;
import com.example.a0103pr1519.data.Services;
import com.example.a0103pr1519.data.Users;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {
    private Context context;
    private DataBaseHelper dbHelper;
    private SQLiteDatabase db;

    public DataBaseManager(Context context) {
        this.context = context;
        dbHelper = new DataBaseHelper(this.context);
    }

    public void openDb() {
        db = dbHelper.getWritableDatabase();
    }

    public void closeDb() {
        db.close();
    }

    @SuppressLint("Range")
    public List<Users> getUsers() {
        List<Users> users = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + USERS_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Users user = new Users();
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            user.setSecondName(cursor.getString(cursor.getColumnIndex(USER_SECOND_NAME)));
            user.setSurName(cursor.getString(cursor.getColumnIndex(USER_PATRONYMIC)));
            user.setDateBirth(cursor.getString(cursor.getColumnIndex(USER_DATE_BIRTHDAY)));
            users.add(user);
        }
        return users;
    }

    @SuppressLint("Range")
    public Users getUser(String login, String pass) {
        Users user = new Users();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME +
//                " WHERE " + USER_LOGIN + " = " + "\"" + login + "\"" +
//                " AND " + USER_PASSWORD + " = " + "\"" + pass + "\"", null);
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS_TABLE_NAME + " WHERE " + USER_LOGIN + " =  + '" +login+"'" +  " AND " + USER_PASSWORD + " =  + '" +pass+"'", null);
        Log.d("QWECursor", cursor.moveToFirst() + "");
        if (cursor.moveToFirst()) {
            Log.d("QWEDBM", cursor.getString(cursor.getColumnIndex(USER_LOGIN)) + "");
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(USER_ID))));
            user.setLogin(cursor.getString(cursor.getColumnIndex(USER_LOGIN)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASSWORD)));
            user.setSecondName(cursor.getString(cursor.getColumnIndex(USER_SECOND_NAME)));
            user.setFirstName(cursor.getString(cursor.getColumnIndex(USER_FIRST_NAME)));
            user.setSurName(cursor.getString(cursor.getColumnIndex(USER_PATRONYMIC)));
        }
        cursor.close();
        return user;
    }

    @SuppressLint("Range")
    public Services getService(int serviceId){
        Services service = new Services();
        Cursor cursor = db.rawQuery("SELECT * FROM " + SERVICES_TABLE_NAME + " WHERE " + SERVICE_ID + " = " + "\"" + serviceId + "\"", null);
        if (cursor.moveToFirst()){
            service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
            service.setName(cursor.getString(cursor.getColumnIndex(SERVICE_NAME)));
            service.setCost(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE))));
            service.setDescription(cursor.getString(cursor.getColumnIndex(SERVICE_DESCRIPTION)));
        }
        cursor.close();
        return service;
    }

    @SuppressLint("Range")
    public List<Services> getServices() {
        List<Services> services = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                SERVICES_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Services service = new Services();
            service.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_ID))));
            service.setName(cursor.getString(cursor.getColumnIndex(SERVICE_NAME)));
            service.setCost(Integer.parseInt(cursor.getString(cursor.getColumnIndex(SERVICE_PRICE))));
            service.setDescription(cursor.getString(cursor.getColumnIndex(SERVICE_DESCRIPTION)));
            services.add(service);
        }
        Log.d("TTT", services.size() + "");
        cursor.close();
        return services;
    }

    @SuppressLint("Range")
    public List<Orders> getOrders() {
        List<Orders> orders = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM " +
                "" + ORDERS_TABLE_NAME, null);
        while (cursor.moveToNext()) {
            Orders order = new Orders();
            order.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ORDER_ID))));
            order.setIdUser(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_USER))));
            order.setIdService(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID_SERVICE))));
            order.setDate(cursor.getString(cursor.getColumnIndex(DATE_RECORD)));
            orders.add(order);
        }
        cursor.close();
        return orders;
    }

    public void addUser(Users user) {
        ContentValues cv = new ContentValues();
        cv.put(USER_LOGIN, user.getLogin());
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_FIRST_NAME, user.getFirstName());
        cv.put(USER_SECOND_NAME, user.getSecondName());
        cv.put(USER_PATRONYMIC, user.getSurName());
        cv.put(USER_DATE_BIRTHDAY, user.getDateBirth());
        db.insert(USERS_TABLE_NAME, null, cv);
    }
    public void updateUser(Users user){
        ContentValues cv = new ContentValues();
        cv.put(USER_PASSWORD, user.getPassword());
        cv.put(USER_FIRST_NAME, user.getFirstName());
        cv.put(USER_SECOND_NAME, user.getSecondName());
        cv.put(USER_PATRONYMIC, user.getSurName());
        cv.put(USER_DATE_BIRTHDAY, user.getDateBirth());
        db.update(USERS_TABLE_NAME, cv, USER_ID + " = " + user.getId(), null);
    }
    public void deleteUser(Users user) {
        db.delete(USERS_TABLE_NAME, USER_ID + " = " + user.getId(), null);
    }

    public void addService(Services service) {
        ContentValues cv = new ContentValues();
        cv.put(SERVICE_NAME, service.getName());
        cv.put(SERVICE_PRICE, service.getCost());
        cv.put(SERVICE_DESCRIPTION, service.getDescription());
        db.insert(SERVICES_TABLE_NAME, null, cv);
    }

    public void deleteService(Services service) {
        db.delete(SERVICES_TABLE_NAME, SERVICE_ID + " = " + service.getId(), null);
    }

    public void addOrder(Orders order) {
        ContentValues cv = new ContentValues();
        cv.put(ID_USER, order.getIdUser());
        cv.put(ID_SERVICE, order.getIdService());
        cv.put(DATE_RECORD, order.getDate());
        db.insert(ORDERS_TABLE_NAME, null, cv);
    }

    public void deleteOrder(Orders order) {
        db.delete(ORDERS_TABLE_NAME, ORDER_ID + " = " + order.getId(), null);
    }
}

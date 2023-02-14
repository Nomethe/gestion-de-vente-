package com.example.gestionvende;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gestionvende.classes.Customer;
import com.example.gestionvende.classes.Order;
import com.example.gestionvende.classes.Product;

// DatabaseHelper class
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sales.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CUSTOMER = "customer";
    private static final String COLUMN_CUSTOMER_ID = "id";
    private static final String COLUMN_CUSTOMER_NAME = "name";
    private static final String COLUMN_CUSTOMER_PHONE = "phone";
    private static final String COLUMN_CUSTOMER_EMAIL = "email";

    private static final String TABLE_PRODUCT = "product";
    private static final String COLUMN_PRODUCT_ID = "id";
    private static final String COLUMN_PRODUCT_NAME = "name";
    private static final String COLUMN_PRODUCT_DESC = "description";
    private static final String COLUMN_PRODUCT_PRICE = "price";

    private static final String TABLE_ORDER = "order";
    private static final String COLUMN_ORDER_ID = "id";
    private static final String COLUMN_ORDER_DATE = "date";
    private static final String COLUMN_ORDER_STATUS = "status";
    private static final String COLUMN_ORDER_CUSTOMER_ID = "customer_id";
    private static final String COLUMN_ORDER_PRODUCT_ID = "product_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CUSTOMER_TABLE = "CREATE TABLE " + TABLE_CUSTOMER +
                "(" +
                COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY," +
                COLUMN_CUSTOMER_NAME + " TEXT," +
                COLUMN_CUSTOMER_PHONE + " TEXT," +
                COLUMN_CUSTOMER_EMAIL + " TEXT" +
                ")";

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + TABLE_PRODUCT +
                "(" +
                COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY," +
                COLUMN_PRODUCT_NAME + " TEXT," +
                COLUMN_PRODUCT_DESC + " TEXT," +
                COLUMN_PRODUCT_PRICE + " REAL" +
                ")";

        String CREATE_ORDER_TABLE = "CREATE TABLE " + TABLE_ORDER +
                "(" +
                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY," +
                COLUMN_ORDER_DATE + " STRING," +
                COLUMN_ORDER_STATUS + " TEXT," +
                COLUMN_ORDER_CUSTOMER_ID + " INTEGER," +
                COLUMN_ORDER_PRODUCT_ID + " INTEGER," +
                "FOREIGN KEY(" + COLUMN_ORDER_CUSTOMER_ID + ") REFERENCES " + TABLE_CUSTOMER + "(" + COLUMN_CUSTOMER_ID + ")," +
                "FOREIGN KEY(" + COLUMN_ORDER_PRODUCT_ID + ") REFERENCES " + TABLE_PRODUCT + "(" + COLUMN_PRODUCT_ID + ")" +
                ")";

        db.execSQL(CREATE_CUSTOMER_TABLE);
        db.execSQL(CREATE_PRODUCT_TABLE);
        db.execSQL(CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    public void addCustomer(Customer customer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CUSTOMER_NAME, customer.getName());
        values.put(COLUMN_CUSTOMER_PHONE, customer.getPhoneNumber());
        values.put(COLUMN_CUSTOMER_EMAIL, customer.getEmail());

        db.insert(TABLE_CUSTOMER, null, values);
        db.close();
    }

    public void addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, product.getName());
        values.put(COLUMN_PRODUCT_DESC, product.getDescription());
        values.put(COLUMN_PRODUCT_PRICE, product.getPrice());

        db.insert(TABLE_PRODUCT, null, values);
    }
    public void addOrder(Order order) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ORDER_ID, order.getOrderId());
        values.put(COLUMN_ORDER_DATE, String.valueOf( order.getDate()));
        values.put(COLUMN_ORDER_STATUS, order.getStatus());
        values.put(COLUMN_ORDER_CUSTOMER_ID, Integer.parseInt(String.valueOf(order.getCustomer())));
        values.put(COLUMN_ORDER_PRODUCT_ID, Integer.parseInt(String.valueOf(order.getProduct())));

        db.insert(TABLE_PRODUCT, null, values);
    }
}

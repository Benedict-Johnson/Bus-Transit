package com.example.bustrasit;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "BusSchedule.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL("CREATE TABLE students (id TEXT PRIMARY KEY, name TEXT, password TEXT)");
        db.execSQL("CREATE TABLE buses (bus_id INTEGER PRIMARY KEY AUTOINCREMENT, route TEXT, timing TEXT, start_location TEXT, destination TEXT, total_seats INTEGER, occupied_seats INTEGER, female_seats INTEGER)");
        db.execSQL("CREATE TABLE feedback (id INTEGER PRIMARY KEY AUTOINCREMENT, student_id TEXT, rating INTEGER, comment TEXT, FOREIGN KEY(student_id) REFERENCES students(id))");

        // Insert sample data
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS students");
        db.execSQL("DROP TABLE IF EXISTS buses");
        db.execSQL("DROP TABLE IF EXISTS feedback");
        onCreate(db);
    }
    public boolean checkLogin(String id, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM students WHERE id=? AND password=?", new String[]{id, password});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();

        return exists;
    }


    private void insertSampleData(SQLiteDatabase db) {
        // Students
        db.execSQL("INSERT INTO students (id, name, password) VALUES ('22MIS1134', 'Benedict Johnson', 'pass123')");
        db.execSQL("INSERT INTO students (id, name, password) VALUES ('22MIS1084', 'Rohinth KR', 'secure456')");
        db.execSQL("INSERT INTO students (id, name, password) VALUES ('22MIS0000', 'Jack Clover', 'nvrfound123')");

        // Buses with random seat availability
        String[] startLocations = {"Avadi", "Manali", "Chennai Central"};
        String destination = "VIT College, Chennai Campus";
        String[] timings = {"08:00 AM", "09:00 AM", "10:00 AM"};

        Random rand = new Random();
        for (String start : startLocations) {
            for (String time : timings) {
                int totalSeats = 40;
                int occupiedSeats = rand.nextInt(30); // Random between 0-30
                int femaleSeats = rand.nextInt(occupiedSeats / 2 + 1); // Random up to half of occupied seats
                db.execSQL("INSERT INTO buses (route, timing, start_location, destination, total_seats, occupied_seats, female_seats) VALUES ('" + start + " - " + destination + "', '" + time + "', '" + start + "', '" + destination + "', " + totalSeats + ", " + occupiedSeats + ", " + femaleSeats + ")");
            }
        }

        Log.d("DatabaseHelper", "Sample data inserted with random seat availability");
    }
}

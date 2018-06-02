package com.parkify;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class DBInitializer {
    private static final String TAG = DBInitializer.class.getName();

    public  void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    public  void populateSync(@NonNull final AppDatabase db) {
        populateWithTestData(db);
    }

    private  User addUser(final AppDatabase db, User user) {
        db.userDao().insertAll(user);
        return user;
    }

    private  void populateWithTestData(AppDatabase db) {
        User user = new User("u","p");
        addUser(db, user);

        List<User> userList = db.userDao().getAll();
        Log.d(DBInitializer.TAG, "Rows Count: " + userList.size());
    }

    private  class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(mDb);
            return null;
        }

    }
}
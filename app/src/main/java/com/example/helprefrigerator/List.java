package com.example.helprefrigerator;

import android.provider.BaseColumns;

public class List {
    private List() { }

    public static class Entry implements BaseColumns {
        public static final String TABLE_NAME = "list";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_PW = "pw";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_AMOUNT = "amount";
    }
}

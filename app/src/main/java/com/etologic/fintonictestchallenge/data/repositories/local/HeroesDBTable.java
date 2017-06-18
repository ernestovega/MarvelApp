package com.etologic.fintonictestchallenge.data.repositories.local;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

class HeroesDBTable {
    static final String TABLE_NAME = "Heroes";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_REALNAME = "photo";
    static final String COLUMN_PHOTO = "realName";
    static final String COLUMN_POWER = "height";
    static final String COLUMN_ABILITIES = "power";
    static final String COLUMN_HEIGHT = "abilities";
    static final String COLUMN_GROUPS = "groups";

    static final String DROP_TABLE = "DROP TABLE " + TABLE_NAME;

    static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
            + COLUMN_NAME + " TEXT NOT NULL PRIMARY KEY, "
            + COLUMN_REALNAME + " TEXT, "
            + COLUMN_PHOTO + " TEXT, "
            + COLUMN_POWER + " TEXT, "
            + COLUMN_ABILITIES + " TEXT, "
            + COLUMN_HEIGHT + " TEXT, "
            + COLUMN_GROUPS + " TEXT)";
}

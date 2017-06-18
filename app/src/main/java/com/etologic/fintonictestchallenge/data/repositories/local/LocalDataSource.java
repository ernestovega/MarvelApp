package com.etologic.fintonictestchallenge.data.repositories.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.errors.GetHeroDBError;
import com.etologic.fintonictestchallenge.data.errors.GetHeroesDBError;
import com.etologic.fintonictestchallenge.data.errors.SaveHeroesDBError;
import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.data.responses.GetHeroResponse;
import com.etologic.fintonictestchallenge.data.responses.GetHeroesResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

public class LocalDataSource implements DataSource {

    //region Fields

    private static LocalDataSource instance;
    private static DataBaseHelper dbHelper;

    //endregion

    //region Constructor


    private LocalDataSource(Context context) {
        dbHelper = DataBaseHelper.getInstance(context);
    }

    public static LocalDataSource getInstance(Context context) {
        if(instance == null) {
            instance = new LocalDataSource(context);
        }
        return instance;
    }

    //endregion

    @Override
    public void downloadHeroes(DownloadHeroesCallback downloadHeroesCallback) {}

    @Override
    public void saveHeroes(SaveHeroesRequest saveHeroesRequest, SaveHeroesCallback saveHeroesCallback) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        List<Hero> heroes = saveHeroesRequest.getHeroes();
        if(heroes != null) {
            ContentValues heroesValues;
            db.beginTransaction();
            for(Hero hero : heroes) {
                heroesValues = new ContentValues();
                heroesValues.put(HeroesDBTable.COLUMN_NAME, hero.getName());
                heroesValues.put(HeroesDBTable.COLUMN_REALNAME, hero.getRealName());
                heroesValues.put(HeroesDBTable.COLUMN_PHOTO, hero.getPhoto());
                heroesValues.put(HeroesDBTable.COLUMN_POWER, hero.getPower());
                heroesValues.put(HeroesDBTable.COLUMN_ABILITIES, hero.getAbilities());
                heroesValues.put(HeroesDBTable.COLUMN_HEIGHT, hero.getHeight());
                heroesValues.put(HeroesDBTable.COLUMN_GROUPS, hero.getGroups());
                db.insertWithOnConflict(HeroesDBTable.TABLE_NAME, null, heroesValues, SQLiteDatabase.CONFLICT_REPLACE);
            }
            try {
                db.setTransactionSuccessful();
                db.endTransaction();
                saveHeroesCallback.onSaveHeroesSuccess(true);
            } catch(Exception e) {
                saveHeroesCallback.onSaveHeroesSuccess(false);
                saveHeroesCallback.onSaveHeroesFailure(new SaveHeroesDBError(e.getMessage()));
            }
        }
    }

    @Override
    public void getHeroes(GetHeroesCallback getHeroesCallback) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String orderBy = String.format("%s ASC", HeroesDBTable.COLUMN_NAME);
        Cursor cursor = db.query(HeroesDBTable.TABLE_NAME, null, null, null, null, null, orderBy);
        if(cursor != null && cursor.getCount() > 0) {
            try {
                List<Hero> heroes = new ArrayList<>();
                cursor.moveToFirst();
                do {
                    heroes.add(getNewHero(cursor));
                } while(cursor.moveToNext());
                cursor.close();
                getHeroesCallback.onGetHeroesSuccess(new GetHeroesResponse(heroes));
            } catch(Exception e) {
                getHeroesCallback.onGetHeroesFailure(new GetHeroesDBError(e.getMessage()));
            }
        } else {
            getHeroesCallback.onGetHeroesFailure(new GetHeroesDBError(""));
        }
    }

    @Override
    public void getHero(GetHeroRequest getHeroRequest, GetHeroCallback getHeroCallback) {
        String heroName = getHeroRequest.getHeroName();
        if(heroName == null || heroName.isEmpty()) {
            getHeroCallback.onGetHeroFailure(new GetHeroDBError(""));
            return;
        }
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selection = HeroesDBTable.COLUMN_NAME + " = ?";
        String[] selectionArgs = { heroName };
        Cursor cursor = db.query(
                HeroesDBTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if(cursor != null && cursor.getCount() > 0) {
            try {
                cursor.moveToFirst();
                Hero hero = getNewHero(cursor);
                cursor.close();
                getHeroCallback.onGetHeroSuccess(new GetHeroResponse(hero));
            } catch(Exception e) {
                getHeroCallback.onGetHeroFailure(new GetHeroDBError(e.getMessage()));
            }
        } else {
            getHeroCallback.onGetHeroFailure(new GetHeroDBError(""));
        }
    }

    //region Private

    @NonNull
    private Hero getNewHero(Cursor cursor) {
        return new Hero(
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_PHOTO)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_REALNAME)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_HEIGHT)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_POWER)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_ABILITIES)),
                cursor.getString(cursor.getColumnIndexOrThrow(HeroesDBTable.COLUMN_GROUPS))
        );
    }

    //endregion
}

package com.app.coti.coti.brain;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.app.coti.coti.models.Knowledge;

import java.util.ArrayList;

/**
 * Created by Giorgos on 27/01/2017.
 */

public class Memory extends SQLiteOpenHelper {

    //DECLARE DATABASE NAME
    public static final String DATABASE_NAME = "coti.db";

    //DECLARE TABLES
    private static final String TB_KNOWLEDGE = "Knowledge";

    private static final String COL_ID = "id";
    private static final String COL_KNOWLEDGE = "knowledge";
    private static final String COL_YES = "yes";
    private static final String COL_NO = "no";
    private static final String COL_TOTAL = "total";
    private static final String COL_BANS = "bans";


    //TABLE CREATION QUERIES
    private static final String CREATE_COTI = "CREATE TABLE " + TB_KNOWLEDGE + " ("
            + COL_ID + " INT PRIMARY KEY NOT NULL, "
            + COL_KNOWLEDGE + " TEXT NOT NULL, "
            + COL_YES + " INT NOT NULL ,"
            + COL_NO + " INT NOT NULL ,"
            + COL_TOTAL + " INT NOT NULL, "
            + COL_BANS +" INT NOT NULL "+ ")";


    public Memory(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_COTI);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TB_KNOWLEDGE);
        onCreate(db);
    }

    public void addKnowledge(Knowledge k) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql =
                "INSERT or replace INTO Knowledge (id,knowledge,yes,no,total,bans) VALUES("
                        +k.getId()+ ",'" + escapeSQL(k.getKnowledge()) + "',"+k.getYes()+
                        ","+k.getNo()+","+k.getTotal()+","+k.getBans()+")";
        db.execSQL(sql);
    }


    public ArrayList<Knowledge> getKnowledge() {

        ArrayList<Knowledge> k = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "SELECT * FROM `Knowledge`";

        Cursor c = null;
        c = db.rawQuery(sql, null);

        c.moveToFirst();

        if(c.getCount()!=0) {
            while (!c.isAfterLast()) {
                Knowledge knowledge = new Knowledge();
                knowledge.setId(c.getInt(0));
                knowledge.setKnowledge(c.getString(1));
                knowledge.setYes(c.getInt(2));
                knowledge.setNo(c.getInt(3));
                knowledge.setTotal(c.getInt(4));
                knowledge.setBans(c.getInt(5));
                k.add(knowledge);
                c.moveToNext();
            }
        }

        c.close();
        return k;
    }

    public void deleteKnowledge(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM `Knowledge` WHERE id = " + id + ";";

        db.execSQL(sql);
    }


    /***
     * Helps with escaping '
     */
    public String escapeSQL(String old) {
        String newStr = "";
        for (int i = 0; i < old.length(); i++) {
            if (old.charAt(i) == '\'') {
                newStr = newStr.concat("\'\'");
            } else {
                newStr = newStr.concat("" + old.charAt(i));
            }
        }

        //        System.out.println("-------------------------------------------------------");
        //        System.out.println(newStr);
        //        System.out.println("-------------------------------------------------------");
        return newStr;
    }
}

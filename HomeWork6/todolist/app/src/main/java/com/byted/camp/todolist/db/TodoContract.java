package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // TODO 定义表结构和 SQL 语句常量
    public static final String SQL_CREATE_NOTES =
            "CREATE TABLE " + TodoNode.TABLE_NAME
                    + "(" + TodoNode._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + TodoNode.COLUMN_DATE + " INTEGER, "
                    + TodoNode.COLUMN_STATE + " INTEGER, "
                    + TodoNode.COLUMN_CONTENT + " TEXT";


    private TodoContract() {

    }
    public  static  class   TodoNode implements BaseColumns {

        public static final String TABLE_NAME = "note";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_STATE = "state";
        public static final String COLUMN_CONTENT = "content";

    }

}

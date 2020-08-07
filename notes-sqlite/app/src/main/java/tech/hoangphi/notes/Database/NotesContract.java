package tech.hoangphi.notes.Database;

import android.provider.BaseColumns;

public class NotesContract {

    public NotesContract() {
    }

    public static final class NotesEntry implements BaseColumns {
        public static final String TABLE_NAME = "tb_notes";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_BODY = "body";
    }

}

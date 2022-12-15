package com.twofaces.androidarchcomponents_mvvm.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.twofaces.androidarchcomponents_mvvm.data.NoteRepository
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.Note
import com.twofaces.androidarchcomponents_mvvm.data.db.entities.NoteDao
import com.twofaces.androidarchcomponents_mvvm.di.TaskActivityScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao

    class RoomCallBack @Inject constructor(
        @TaskActivityScope private val coroutineScope: CoroutineScope,
        private val noteRepositoryProvider: Provider<NoteRepository>
    ): RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            coroutineScope.launch {
                noteRepositoryProvider.get().deleteAllNotes()
                noteRepositoryProvider.get().insert(Note(0, "Tugas Mobile", "Aliquam erat volutpat. Vestibulum mattis quis diam mattis tempus. Etiam tincidunt elit at est lacinia bibendum. Donec sed libero eget.\n" +
                        "\n", 1))
                noteRepositoryProvider.get().insert(Note(0, "Tugas Web", "Nullam ex risus, euismod id justo eu, auctor dignissim mi.", 2))
                noteRepositoryProvider.get().insert(Note(0, "Tugas Techno", "Suspendisse dapibus felis a neque varius rhoncus. Phasellus sollicitudin mattis urna eget scelerisque. Mauris non.\n" +
                        "\n", 3))
                noteRepositoryProvider.get().insert(Note(0, "Project Video", "Sed sed nulla mauris. Nullam in ipsum eu eros aliquet malesuada ac ut tellus. Phasellus sollicitudin viverra lacus eget rutrum. Phasellus venenatis facilisis libero. Curabitur malesuada odio eu lectus commodo, ut iaculis justo pretium. Nam vitae turpis sed tellus tempus elementum. Quisque gravida commodo nisl, non consectetur orci commodo eu. Praesent mollis accumsan sem at rutrum. Vivamus varius fermentum lacinia. Etiam tincidunt ac nibh vel bibendum. Aenean a tellus a risus accumsan condimentum a vel nulla. Nam consectetur mattis suscipit.\n" +
                        "\n" +
                        "Morbi tincidunt at sem at rutrum. Praesent massa mauris, blandit quis dignissim id, vehicula quis velit. Nulla ultricies varius dui ac sollicitudin. Aliquam eu quam libero. Fusce posuere erat massa, sit amet aliquet erat eleifend in. Duis et justo eget nunc volutpat molestie. In hac habitasse platea dictumst. Quisque scelerisque tortor eu dolor consectetur, eu elementum dui venenatis. Maecenas vitae lacinia ex, at suscipit nisl. In suscipit tempor est, id aliquam felis gravida vel. Mauris scelerisque ullamcorper felis id feugiat.", 4))
            }

        }
    }


}




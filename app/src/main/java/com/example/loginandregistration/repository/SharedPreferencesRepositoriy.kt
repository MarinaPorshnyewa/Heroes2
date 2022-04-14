package com.example.loginandregistration.repository

import android.content.Context
import androidx.core.content.edit
import javax.inject.Inject
import javax.inject.Singleton


private const val FILE_NAME = "shared_pref_file"
private const val HEROES_ID = "heroes_id"
private const val STATE = "state"

private val DEFAULT_LIST = mutableSetOf<String>()

@Singleton
class SharedPreferencesRepositoriy @Inject constructor(
    private val context: Context
) {

    private val preferenses = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)


    fun saveIdHeroes(idHeroes: Set<String>) {
        preferenses.edit {
            putStringSet(HEROES_ID, idHeroes)
        }
    }

    fun getIdHeroes(): MutableSet<String>? = preferenses.getStringSet(HEROES_ID, DEFAULT_LIST)

    fun saveState(isFirst: Boolean) {
        preferenses.edit {
            putBoolean(STATE, isFirst)
        }
    }

    fun getState(): Boolean = preferenses.getBoolean(STATE, true)


}
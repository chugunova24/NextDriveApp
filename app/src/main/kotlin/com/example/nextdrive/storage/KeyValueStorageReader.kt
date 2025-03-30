package my.zukoap.tasky.core.keyvaluestorage.api

import kotlinx.coroutines.flow.Flow

/**
 * An interface for reading data from a key-value storage
 */
interface KeyValueStorageReader {

    /**
     * Get a set of strings from the storage by its key
     * @param key value's key
     * @return resulted value or null if the value has not been found
     */
    suspend fun getStringSet(key: String): Set<String>?

    /**
     * Get a boolean value from the storage by its key
     * @param key value's key
     * @return resulted value or [defaultValue] if the value has not been found
     */
    suspend fun getBoolean(key: String, defaultValue: Boolean = false): Boolean

    /**
     * Get a string value from the storage by its key
     * @param key value's key
     * @return resulted value or [defaultValue] if the value has not been found
     */
    suspend fun getString(key: String, defaultValue: String? = null): String?

    /**
     * Get Long value from the storage by its key [key]
     */
    suspend fun getLong(key: String): Long?

    /**
     * Get Int value from the storage by its key [key]
     */
    suspend fun getInt(key: String): Int?

    /**
     * Get Float value from the storage by its key [key]
     */
    suspend fun getFloat(key: String): Float?

    /**
     * Checks for the existence of a value in the store
     * @param key value's key
     * @return checking result
     */
    suspend fun contains(key: String): Boolean

    /** Getting Flow of String changes of the storage value by key. */
    fun observeStringValue(key: String, defaultValue: String? = null): Flow<String?>

    /** Getting Flow of Long changes of the storage value by key. */
    fun observeLongValue(key: String): Flow<Long?>

    /** Getting Flow of Int changes of the storage value by key. */
    fun observeIntValue(key: String): Flow<Int?>

    /** Getting Flow of Boolean storage value changes by key. */
    fun observeBooleanValue(key: String, defaultValue: Boolean = false): Flow<Boolean>
}

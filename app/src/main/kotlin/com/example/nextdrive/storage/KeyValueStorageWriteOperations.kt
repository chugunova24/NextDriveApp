package my.zukoap.tasky.core.keyvaluestorage.api

/**
 * A set of operations for updating data in a key-value storage
 */
interface KeyValueStorageWriteOperations {
    /**
     * Puts a value with given key to a storage. If the value exists, it'll be overwritten
     * @param key value's key
     * @param value to put
     */
    suspend fun setSetString(key: String, value: Set<String>)

    /**
     * Puts a value with given key to a storage. If the value exists, it'll be overwritten
     * @param key value's key
     * @param value to put
     */
    suspend fun setBoolean(key: String, value: Boolean)

    /**
     * Puts a value with given key to a storage. If the value exists, it'll be overwritten
     * @param key value's key
     * @param value to put
     */
    suspend fun setString(key: String, value: String)

    /**
     * Puts the Long value [value] with the given key [key] in the storage.
     * If the value exists, it will be overwritten
     */
    suspend fun setLong(key: String, value: Long)

    /**
     * Puts the Int value [value] with the given key [key] in the storage.
     * If the value exists, it will be overwritten
     */
    suspend fun setInt(key: String, value: Int)

    /**
     * Puts the Float value [value] with the given key [key] in the storage.
     * If the value exists, it will be overwritten
     */
    suspend fun setFloat(key: String, value: Float)

    /**
     * Removes a value with given key from a storage.
     */
    suspend fun remove(key: String)

    /**
     * Remove all data from a storage
     */
    suspend fun clear()
}

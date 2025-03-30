package my.zukoap.tasky.core.keyvaluestorage.api

import com.example.nextdrive.storage.KeyValueStorage


/**
 * Factory interface to retrieve [KeyValueStorage] stores based on their keys
 */
interface KeyValueStorageFactory {
    /**
     * Retrieving storage
     * @param storageKey key. Must contain only Latin characters and underscores
     */
    operator fun get(storageKey: String): KeyValueStorage
}
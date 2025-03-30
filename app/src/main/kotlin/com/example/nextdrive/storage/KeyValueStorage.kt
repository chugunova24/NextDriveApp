package com.example.nextdrive.storage

import my.zukoap.tasky.core.keyvaluestorage.api.KeyValueStorageReader
import my.zukoap.tasky.core.keyvaluestorage.api.KeyValueStorageWriter

const val COMMON_KVS_NAME = "cmn"
const val USER_CONTEXT_KVS_NAME = "user_context_storage"

/**
 * An interface for access to a key-value storage
 */
interface KeyValueStorage {
    /**
     * An entity for reading data from a key-value storage
     */
    val reader: KeyValueStorageReader

    /**
     * An entity for updating data in a key-value storage
     */
    val writer: KeyValueStorageWriter
}

suspend fun KeyValueStorage.clear() {
    this.writer.update { operations ->
        operations.clear()
    }
}

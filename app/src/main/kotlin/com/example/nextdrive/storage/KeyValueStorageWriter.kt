package my.zukoap.tasky.core.keyvaluestorage.api

/**
 * Interface for updating data in k-v storage
 */
interface KeyValueStorageWriter {

    /**
     * Performs operations within [updateAction] to update data in storage with read capability
     */
    suspend fun update(
        updateAction: suspend (KeyValueStorageReader, KeyValueStorageWriteOperations) -> Unit
    )

    /**
     * Performs operations within [updateAction] to update data in storage
     */
    suspend fun update(
        updateAction: suspend (KeyValueStorageWriteOperations) -> Unit
    )
}

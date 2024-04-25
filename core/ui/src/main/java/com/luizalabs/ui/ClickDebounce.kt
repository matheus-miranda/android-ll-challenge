package com.luizalabs.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.util.UUID

internal interface EventProcessor {
    fun processEvent(event: () -> Unit)

    companion object {
        val buttonClickMap = mutableMapOf<String, EventProcessor>()
    }
}

private const val DEBOUNCE_TIME_MILLIS = 500L

private class EventProcessorImpl : EventProcessor {
    private val now: Long
        get() = System.currentTimeMillis()

    private var lastEventTimeMs: Long = 0

    override fun processEvent(event: () -> Unit) {
        if (now - lastEventTimeMs >= DEBOUNCE_TIME_MILLIS) {
            event.invoke()
        }
        lastEventTimeMs = now
    }
}

internal fun EventProcessor.Companion.get(id: String): EventProcessor {
    return buttonClickMap.getOrPut(
        id
    ) {
        EventProcessorImpl()
    }
}

@Composable
fun debouncedClick(
    id: String = UUID.randomUUID().toString(),
    onClick: () -> Unit,
): () -> Unit {
    val multipleEventsCutter = remember { EventProcessor.get(id) }
    val newOnClick: () -> Unit = {
        multipleEventsCutter.processEvent { onClick() }
    }
    return newOnClick
}

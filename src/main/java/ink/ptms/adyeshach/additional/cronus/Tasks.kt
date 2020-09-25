package ink.ptms.adyeshach.additional.cronus

import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import ink.ptms.cronus.Cronus
import ink.ptms.cronus.CronusAPI
import ink.ptms.cronus.internal.task.TaskCache
import io.izzel.taboolib.module.inject.TListener
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener

@TListener(depend = ["Cronus"])
class Tasks : Listener {

    init {
        Cronus.getCronusService().registeredTask["ady_interact"] = TaskCache(TaskAdyInteract::class.java)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun e(e: AdyeshachEntityInteractEvent) {
        if (e.isMainHand) {
            CronusAPI.stageHandle(e.player, e, TaskAdyInteract::class.java)
        }
    }
}
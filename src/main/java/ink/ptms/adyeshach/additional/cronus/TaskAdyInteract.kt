package ink.ptms.adyeshach.additional.cronus

import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import ink.ptms.cronus.database.data.DataQuest
import ink.ptms.cronus.internal.task.Task
import ink.ptms.cronus.internal.task.special.Countable
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player

@Task(name = "ady_interact")
class TaskAdyInteract(config: ConfigurationSection) : Countable<AdyeshachEntityInteractEvent>(config) {

    val entityId = ArrayList<String>()

    override fun init(args: MutableMap<String, Any>) {
        entityId.addAll(args["id"].toString().split("[,;]".toRegex()))
    }

    override fun check(player: Player, dataQuest: DataQuest, event: AdyeshachEntityInteractEvent): Boolean {
        return event.entity.id in entityId
    }
}
package ink.ptms.adyeshach.additional.betonquest

import ink.ptms.adyeshach.Adyeshach
import ink.ptms.adyeshach.additional.betonquest.AdyConversation
import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import pl.betoncraft.betonquest.config.Config
import pl.betoncraft.betonquest.conversation.CombatTagger
import pl.betoncraft.betonquest.utils.PlayerConverter

class AdyListener : Listener {

    init {
        Bukkit.getPluginManager().registerEvents(this, Adyeshach.plugin)
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    fun e(e: AdyeshachEntityInteractEvent) {
        if (e.isMainHand && e.player.hasPermission("betonquest.conversation")) {
            val playerID = PlayerConverter.getID(e.player)
            if (CombatTagger.isTagged(playerID)) {
                Config.sendNotify(playerID, "busy", "busy,error")
                return
            }
            val assignment = Config.getNpc(e.entity.id)
            if (assignment != null) {
                AdyConversation(e.entity, playerID, assignment, e.entity.getLocation())
            }
        }
    }
}
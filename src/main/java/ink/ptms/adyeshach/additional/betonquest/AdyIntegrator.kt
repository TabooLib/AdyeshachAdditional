package ink.ptms.adyeshach.additional.betonquest

import org.bukkit.Bukkit
import pl.betoncraft.betonquest.BetonQuest
import pl.betoncraft.betonquest.compatibility.Integrator

class AdyIntegrator : Integrator {

    val plugin = BetonQuest.getInstance()

    override fun hook() {
        AdyListener()
        AdyHologram()
        val prefix = if (Bukkit.getPluginManager().getPlugin("Citizens") == null) {
            "ady_npc"
        } else {
            "npc"
        }
        plugin.registerObjectives("${prefix}range", NPCRangeObjective::class.java)
        plugin.registerObjectives("${prefix}interact", NPCInteractObjective::class.java)
        plugin.registerConditions("${prefix}distance", NPCDistanceCondition::class.java)
        plugin.registerConditions("${prefix}location", NPCLocationCondition::class.java)
        plugin.registerVariable("adyeshach", AdyVariable::class.java)
    }

    override fun reload() {
    }

    override fun close() {
    }
}
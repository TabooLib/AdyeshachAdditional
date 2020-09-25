package ink.ptms.adyeshach.additional

import ink.ptms.adyeshach.additional.betonquest.AdyIntegrator
import io.izzel.taboolib.loader.Plugin
import org.bukkit.Bukkit
import pl.betoncraft.betonquest.compatibility.Compatibility

object Additional : Plugin() {

    override fun onEnable() {
        if (Bukkit.getPluginManager().getPlugin("BetonQuest") != null) {
            try {
                AdyIntegrator().hook()
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            println("[Adyeshach] Hooking into BetonQuest.")
        }
    }
}

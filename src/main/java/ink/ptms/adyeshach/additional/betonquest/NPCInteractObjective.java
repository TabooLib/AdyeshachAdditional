package ink.ptms.adyeshach.additional.betonquest;

import ink.ptms.adyeshach.api.event.AdyeshachEntityInteractEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import pl.betoncraft.betonquest.BetonQuest;
import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.api.Objective;
import pl.betoncraft.betonquest.exceptions.InstructionParseException;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Player has to right click the NPC
 */
public class NPCInteractObjective extends Objective implements Listener {

    private final String npcId;
    private final boolean cancel;

    public NPCInteractObjective(final Instruction instruction) throws InstructionParseException {
        super(instruction);
        template = ObjectiveData.class;
        npcId = instruction.next();
        cancel = instruction.hasArgument("cancel");
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onNPCClick(final AdyeshachEntityInteractEvent event) {
        if (!event.isMainHand()) {
            return;
        }
        final String playerID = PlayerConverter.getID(event.getPlayer());
        if (!event.getEntity().getId().equals(npcId) || !containsPlayer(playerID)) {
            return;
        }
        if (checkConditions(playerID)) {
            if (cancel) {
                event.setCancelled(true);
            }
            completeObjective(playerID);
        }
    }

    @Override
    public void start() {
        Bukkit.getPluginManager().registerEvents(this, BetonQuest.getInstance());
    }

    @Override
    public void stop() {
        HandlerList.unregisterAll(this);
    }

    @Override
    public String getDefaultDataInstruction() {
        return "";
    }

    @Override
    public String getProperty(final String name, final String playerID) {
        return "";
    }

}
package ink.ptms.adyeshach.additional.betonquest;

import ink.ptms.adyeshach.api.AdyeshachAPI;
import ink.ptms.adyeshach.common.entity.EntityInstance;
import org.bukkit.entity.Player;
import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.VariableNumber;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.exceptions.InstructionParseException;
import pl.betoncraft.betonquest.exceptions.QuestRuntimeException;
import pl.betoncraft.betonquest.utils.PlayerConverter;

/**
 * Checks if the player is close to a npc
 * <p>
 * Created on 30.09.2018.
 */
public class NPCDistanceCondition extends Condition {

    private final String npcId;
    private final VariableNumber distance;

    public NPCDistanceCondition(final Instruction instruction) throws InstructionParseException {
        super(instruction, true);
        npcId = instruction.next();
        distance = instruction.getVarNum();
    }

    @Override
    protected Boolean execute(final String playerID) throws QuestRuntimeException {
        EntityInstance npc = AdyeshachAPI.INSTANCE.getEntityFromId(npcId, null);
        if (npc == null) {
            throw new QuestRuntimeException("NPC with ID " + npcId + " does not exist");
        }
        final Player player = PlayerConverter.getPlayer(playerID);
        if (!npc.getPosition().getWorld().equals(player.getWorld())) {
            return false;
        }
        final double distance = this.distance.getDouble(playerID);
        return npc.getLocation().distanceSquared(player.getLocation()) <= distance * distance;
    }
}
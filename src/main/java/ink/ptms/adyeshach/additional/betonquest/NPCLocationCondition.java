package ink.ptms.adyeshach.additional.betonquest;

import ink.ptms.adyeshach.api.AdyeshachAPI;
import ink.ptms.adyeshach.common.entity.EntityInstance;
import org.bukkit.Location;
import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.VariableNumber;
import pl.betoncraft.betonquest.api.Condition;
import pl.betoncraft.betonquest.exceptions.InstructionParseException;
import pl.betoncraft.betonquest.exceptions.QuestRuntimeException;
import pl.betoncraft.betonquest.utils.LocationData;

import java.util.Objects;

/**
 * Checks if a npc is at a specific location
 * <p>
 * Created on 01.10.2018.
 */
public class NPCLocationCondition extends Condition {

    private final String npcId;
    private final LocationData location;
    private final VariableNumber radius;

    public NPCLocationCondition(final Instruction instruction) throws InstructionParseException {
        super(instruction, true);
        super.persistent = true;
        super.staticness = true;
        npcId = instruction.next();
        location = instruction.getLocation();
        radius = instruction.getVarNum();
    }

    @Override
    protected Boolean execute(final String playerID) throws QuestRuntimeException {
        EntityInstance npc = AdyeshachAPI.INSTANCE.getEntityFromId(npcId, null);
        if (npc == null) {
            throw new QuestRuntimeException("NPC with ID " + npcId + " does not exist");
        }
        final Location location = this.location.getLocation(playerID);
        if (!Objects.equals(location.getWorld(), npc.getPosition().getWorld())) {
            return false;
        }
        final double radius = this.radius.getDouble(playerID);
        return npc.getLocation().distanceSquared(location) <= radius * radius;
    }
}
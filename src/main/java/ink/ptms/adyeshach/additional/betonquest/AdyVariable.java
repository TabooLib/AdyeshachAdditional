package ink.ptms.adyeshach.additional.betonquest;

import ink.ptms.adyeshach.api.AdyeshachAPI;
import ink.ptms.adyeshach.common.entity.EntityInstance;
import org.bukkit.Location;
import pl.betoncraft.betonquest.Instruction;
import pl.betoncraft.betonquest.api.Variable;
import pl.betoncraft.betonquest.exceptions.InstructionParseException;

import java.util.Locale;

/**
 * Provides information about a citizen npc.
 * <p>
 * Format:
 * {@code %citizen.<id>.<type>%}
 * <p>
 * Types:
 * * name - Return citizen name
 * * full_name - Full Citizen name
 * * location  - Return citizen location. x;y;z;world;yaw;pitch
 */
public class AdyVariable extends Variable {

    private final String npcId;
    private final TYPE key;

    public AdyVariable(final Instruction instruction) throws InstructionParseException {
        super(instruction);

        npcId = instruction.next();
        try {
            key = TYPE.valueOf(instruction.next().toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            throw new InstructionParseException("Invalid Type: " + instruction.current(), e);
        }
    }

    @Override
    public String getValue(final String playerID) {
        EntityInstance npc = AdyeshachAPI.INSTANCE.getEntityFromId(npcId, null);
        if (npc == null) {
            return "";
        }
        switch (key) {
            case TYPE:
                return npc.getEntityType().name();
            case NAME:
                return npc.getCustomName();
            case LOCATION:
                final Location loc = npc.getLocation();
                return String.format("%.2f;%.2f;%.2f;%s;%.2f;%.2f",
                        loc.getX(),
                        loc.getY(),
                        loc.getZ(),
                        loc.getWorld().getName(),
                        loc.getYaw(),
                        loc.getPitch());
            default:
                return "";
        }
    }

    private enum TYPE {

        TYPE, NAME, LOCATION
    }

}
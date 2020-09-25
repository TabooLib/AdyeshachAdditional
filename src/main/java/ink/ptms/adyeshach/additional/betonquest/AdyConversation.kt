package ink.ptms.adyeshach.additional.betonquest

import ink.ptms.adyeshach.common.entity.EntityInstance
import org.bukkit.Location
import pl.betoncraft.betonquest.conversation.Conversation

class AdyConversation(
        val npc: EntityInstance,
        playerID: String,
        conversationID: String,
        location: Location
) : Conversation(playerID, conversationID, location) {

}
package io.github.moosyu.helpers;

import io.github.moosyu.attachments.PlayerStatsAttachment;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import org.jetbrains.annotations.Nullable;

// warning (for me): DO NOT FUCK UP THE ORDERING OF THE DATA IT WILL NOT FIGURE IT OUT ITSELF!!!
public class StatsSyncHandler implements AttachmentSyncHandler<PlayerStatsAttachment> {
    @Override
    public void write(RegistryFriendlyByteBuf buf, PlayerStatsAttachment attachment, boolean initialSync) {
        // first sync needs to send over all the data, afterwards just update one
        if (initialSync) {
            // 2, 100.0, 100.0
            double[] stats = attachment.getStats();
            buf.writeInt(stats.length);
            for (double stat : stats) {
                buf.writeDouble(stat);
            }
        } else {
            int statIndex = attachment.getLastUpdatedStat();
            double value = attachment.getCurrentStatByIndex(statIndex);

            buf.writeInt(statIndex);
            buf.writeDouble(value);
        }
    }

    @Override
    public @Nullable PlayerStatsAttachment read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable PlayerStatsAttachment previousValue) {
        if (previousValue == null) {
            PlayerStatsAttachment attachment = new PlayerStatsAttachment();
            int length = buf.readInt();
            double[] stats = new double[length];

            for (int i = 0; i < length; i++) {
                stats[i] = buf.readDouble();
            }
            attachment.setStats(stats);
            return attachment;
        } else {
            int statIndex = buf.readInt();
            double value = buf.readDouble();

            previousValue.setCurrentStatByIndex(statIndex, value);
            return previousValue;
        }
    }

    @Override
    public boolean sendToPlayer(IAttachmentHolder holder, ServerPlayer player) {
        return holder == player;
    }
}

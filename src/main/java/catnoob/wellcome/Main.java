package catnoob.wellcome;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.event.EventHandler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public final class Main extends Plugin implements Listener {

    @Override
    public void onEnable() {
        getProxy().registerChannel("BungeeCord");
        PluginManager pm = getProxy().getPluginManager();
        pm.unregisterListeners(this);
        pm.registerListener(this, this);
    }

    @EventHandler
    public void onPluginMessageReceived(PluginMessageEvent event) {
        if (!event.getTag().equals("BungeeCord")) {
            return;
        }
        DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
        String subChannel = null;
        try {
            subChannel = in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (subChannel.equals("Catwellcomev2")) {
            String target = null;
            String message = null;
            try {
                target = in.readUTF();
                message = in.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (target.equals("msg")) {
                getProxy().broadcast(ChatColor.translateAlternateColorCodes('&',message));
            }
        }
    }
    @Override
    public void onDisable() {
        PluginManager pm = getProxy().getPluginManager();
        pm.unregisterListeners(this);
    }
}


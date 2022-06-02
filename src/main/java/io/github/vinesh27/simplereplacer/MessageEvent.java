package io.github.vinesh27.simplereplacer;

import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;

public class MessageEvent implements Listener {
    @EventHandler()
    public void onMessage(AsyncPlayerChatEvent e) {
        if (e.getMessage().startsWith("/") || e.getMessage().startsWith(".")) {
            return;
        }

        String[] wordsToReplace = SimpleReplacer.replacements.keySet().toArray(new String[0]);
        String x = e.getMessage();
        ArrayList<TextComponent> arr = new ArrayList<>();
        for (String word : wordsToReplace) {
            if (e.getMessage().contains(word)) {
                if (!e.isCancelled()) e.setCancelled(true);
                String replacement = SimpleReplacer.replacements.get(word).replacement();
                boolean hoverEnabled = SimpleReplacer.replacements.get(word).hoverEnabled();
                String hoverText = SimpleReplacer.replacements.get(word).hoverText();
                x = x.replace(word, replacement);
                arr.add(new TextComponent(replacement));
                if (hoverEnabled) {
                    arr.get(arr.size() - 1).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(hoverText)));
                }
            }
        }
        if(e.isCancelled()) e.getPlayer().spigot().sendMessage(arr.toArray(new TextComponent[0]));
    }
}

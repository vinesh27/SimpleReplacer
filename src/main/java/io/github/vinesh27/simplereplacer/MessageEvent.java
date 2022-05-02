package io.github.vinesh27.simplereplacer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MessageEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void onMessage(AsyncPlayerChatEvent e) {
        String[] wordsToReplace = SimpleReplacer.replacements.keySet().toArray(new String[0]);
        for (String word : wordsToReplace) {
            if (e.getMessage().contains(word))
                e.setMessage(e.getMessage().replace(word, SimpleReplacer.replacements.get(word)));
        }
    }
}

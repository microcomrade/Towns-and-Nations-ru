package org.tan.towns_and_nations.commands.debugsubcommands;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.tan.towns_and_nations.Lang.Lang;
import org.tan.towns_and_nations.commands.SubCommand;
import org.tan.towns_and_nations.enums.CustomVillagerProfession;
import org.tan.towns_and_nations.utils.ChatUtils;
import org.tan.towns_and_nations.utils.VillagerUtil;

import java.util.ArrayList;
import java.util.List;

public class SpawnVillager extends SubCommand {

    @Override
    public String getName() {
        return "spawnvillager";
    }

    @Override
    public String getDescription() {
        return "Spawns a custom villager named Goldsmith.";
    }

    @Override
    public int getArguments() {
        return 1;
    }

    @Override
    public String getSyntax() {
        return "/tandebug spawnvillager <Villager Name>";
    }
    public List<String> getTabCompleteSuggestions(Player player, String[] args){
        return null;
    }
    @Override
    public void perform(Player player, String[] args) {
        switch (args[1]) {
            case "g" -> {
                VillagerUtil.createCustomVillager(player, CustomVillagerProfession.GOLDSMITH);
                return;
            }
            case "c" -> {
                VillagerUtil.createCustomVillager(player, CustomVillagerProfession.COOK);
                return;
            }
            case "b" -> {
                VillagerUtil.createCustomVillager(player, CustomVillagerProfession.BOTANIST);
                return;
            }
        }
        player.sendMessage(ChatUtils.getTANString() + Lang.SYNTAX_ERROR.getTranslation());
        player.sendMessage(ChatUtils.getTANString() + getSyntax());

    }
}
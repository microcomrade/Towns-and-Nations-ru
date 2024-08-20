package org.tan.TownsAndNations.DataClass.wars.wargoals;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.tan.TownsAndNations.DataClass.territoryData.ITerritoryData;
import org.tan.TownsAndNations.DataClass.wars.CreateAttackData;
import org.tan.TownsAndNations.GUI.GuiManager;
import org.tan.TownsAndNations.Lang.Lang;
import org.tan.TownsAndNations.utils.GuiUtil;
import org.tan.TownsAndNations.utils.HeadUtils;

import java.util.function.Consumer;

public class LiberateWarGoal extends WarGoal {

    ITerritoryData territoryToLiberate;
    @Override
    public ItemStack getIcon() {
        return buildIcon(Material.LANTERN, Lang.LIBERATE_SUBJECT_WAR_GOAL_DESC.get());
    }

    @Override
    public String getDisplayName() {
        return Lang.LIBERATE_SUBJECT_WAR_GOAL.get();
    }

    @Override
    public void addExtraOptions(Gui gui, Player player, CreateAttackData createAttackData, Consumer<Player> exit) {

        GuiItem _selectedTerritory;
        if(territoryToLiberate == null){
            ItemStack selectTerritory = HeadUtils.makeSkull(Lang.GUI_SELECT_TERRITORY_TO_LIBERATE.get(),"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjMyZmZmMTYzZTIzNTYzMmY0MDQ3ZjQ4NDE1OTJkNDZmODVjYmJmZGU4OWZjM2RmNjg3NzFiZmY2OWE2NjIifX19",
                    Lang.LEFT_CLICK_TO_SELECT.get());

            _selectedTerritory = ItemBuilder.from(selectTerritory).asGuiItem(event -> {
                GuiManager.openSelecteTerritoryToLiberate(player, createAttackData,this, exit);
                event.setCancelled(true);
            });
        }
        else{
            ItemStack selectedTerritory = HeadUtils.createCustomItemStack(territoryToLiberate.getIcon() , Lang.GUI_SELECT_TERRITORY_TO_LIBERATE.get(territoryToLiberate.getName()),
                    Lang.GUI_SELECTED_TERRITORY_TO_LIBERATE.get(territoryToLiberate.getName()),
                    Lang.LEFT_CLICK_TO_SELECT.get());
            _selectedTerritory = ItemBuilder.from(selectedTerritory).asGuiItem(event -> {
                GuiManager.openSelecteTerritoryToLiberate(player, createAttackData,this, exit);
                event.setCancelled(true);
            });
        }

        gui.setItem(3, 6, _selectedTerritory);


    }

    @Override
    public void applyWarGoal() {

    }

    public ITerritoryData getTerritoryToLiberate() {
        return territoryToLiberate;
    }

    public void setTerritoryToLiberate(ITerritoryData territoryToLiberate) {
        this.territoryToLiberate = territoryToLiberate;
    }
}

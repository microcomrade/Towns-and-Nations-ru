package org.tan.TownsAndNations.Lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.tan.TownsAndNations.TownsAndNations;
import org.tan.TownsAndNations.utils.ConfigUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public enum Lang {
    WELCOME,
    LANGUAGE_SUCCESSFULLY_LOADED,
    PLUGIN_STRING,
    PLUGIN_DEBUG_STRING,
    NEWSLETTER_STRING,
    PLAYER_NO_TOWN,
    TOWN_NO_REGION,
    PLAYER_NOT_LEADER_OF_REGION,
    PLAYER_NO_PERMISSION,
    PLAYER_NO_PERMISSION_RANK_DIFFERENCE,
    PLAYER_ACTION_NO_PERMISSION,
    BASIC_LEVEL_UP,
    TELEPORTATION_CANCELLED,
    TELEPORTATION_IN_X_SECONDS,
    TELEPORTATION_IN_X_SECONDS_NOT_MOVE,
    CHUNK_BELONGS_TO,
    NOT_ENOUGH_ARGS_ERROR,
    TOO_MANY_ARGS_ERROR,
    SYNTAX_ERROR,
    SYNTAX_ERROR_AMOUNT,
    CORRECT_SYNTAX_INFO,
    MESSAGE_TOO_LONG,
    ECONOMY_EXISTS,
    CANCELLED_ACTION,
    PLAYER_ASK_TO_JOIN_TOWN,
    PLAYER_ASK_TO_JOIN_TOWN_PLAYER_SIDE,
    PLAYER_REMOVE_ASK_TO_JOIN_TOWN_PLAYER_SIDE,
    PLAYER_TOWN_NOT_RECRUITING,
    TOWN_CREATE_SUCCESS_BROADCAST,
    GUI_REGION_KICK_TOWN_BROADCAST,
    CLAIM_CHUNK_COMMAND_DESC,
    CHUNK_ALREADY_CLAIMED_WARNING,
    MAX_CHUNK_LIMIT_REACHED,
    CHUNK_NOT_ADJACENT,
    CHUNK_CLAIMED_SUCCESS_REGION,
    CHUNK_CLAIMED_SUCCESS,
    CUSTOM_VILLAGER_CREATED_SUCCESS,
    TOWN_INVITE_COMMAND_DESC,
    PLAYER_NOT_FOUND,
    TOWN_NOT_ENOUGH_LEVEL,
    CHAT_SCOPE_TOWN_MESSAGE,
    CHAT_SCOPE_ALLIANCE_MESSAGE,
    NEW_VERSION_AVAILABLE,
    NEW_VERSION_AVAILABLE_2,
    INVITATION_SENT_SUCCESS,
    INVITATION_RECEIVED_1,
    INVITATION_RECEIVED_2,
    TOWN_ACCEPT_INVITE_DESC,
    TOWN_INVITATION_NO_INVITATION,
    GUI_TOWN_MEMBERS_ROLE_MEMBER_LIST_INFO,
    GUI_TOWN_MEMBERS_ROLE_MEMBER_LIST_INFO_DESC,
    GUI_TOWN_MEMBERS_ROLE_MEMBER_LIST_INFO_DESC1,
    GUI_TOWN_MEMBERS_ROLE_MANAGE_PERMISSION,
    GUI_TOWN_MEMBERS_ROLE_CHANGE_NAME,
    GUI_TOWN_MEMBERS_ROLE_PAY_TAXES,
    GUI_TOWN_MEMBERS_ROLE_NOT_PAY_TAXES,
    GUI_TOWN_MEMBERS_ROLE_TAXES_DESC1,
    TOWN_INVITATION_ACCEPTED_MEMBER_SIDE,
    TOWN_INVITATION_ACCEPTED_TOWN_SIDE,
    TOWN_DIPLOMATIC_INVITATION_ALREADY_HAVE_RELATION,
    TOWN_DIPLOMATIC_INVITATION_SENT_SUCCESS,
    TOWN_ACCEPTED_REGION_DIPLOMATIC_INVITATION,
    TOWN_DIPLOMATIC_INVITATION_RECEIVED_1,
    TOWN_DIPLOMATIC_INVITATION_RECEIVED_2,
    REGION_DIPLOMATIC_INVITATION_RECEIVED_1,
    REGION_DIPLOMATIC_INVITATION_RECEIVED_2,
    TOWN_ACCEPT_RELATION_DESC,
    TOWN_RELATION_NO_INVITATION,
    TOWN_RELATION_ACCEPTED_MEMBER_SIDE,
    TOWN_RELATION_ACCEPTED_TOWN_SIDE,
    TOWN_CHAT_COMMAND_DESC,
    CHAT_CHANGED,
    TOWN_CHAT_ALREADY_IN_CHAT,
    CHAT_SCOPE_NOT_FOUND,
    ADD_MONEY_COMMAND_SUCCESS,
    TOWN_GUI_COMMAND_DESC,
    PAY_COMMAND_DESC,
    PAY_MINIMUM_REQUIRED,
    PAY_CONFIRMED_SENDER,
    PAY_CONFIRMED_RECEIVER,
    PAY_SELF_ERROR,
    BAL_COMMAND_DESC,
    BAL_AMOUNT,
    UNCLAIM_CHUNK_COMMAND_DESC,
    UNCLAIMED_CHUNK_SUCCESS,
    DEBUG_UNCLAIMED_CHUNK_SUCCESS,
    UNCLAIMED_CHUNK_NOT_RIGHT_TOWN,
    COMMAND_GENERIC_SUCCESS,
    ADMIN_UNCLAIM_CHUNK_NOT_CLAIMED,
    PLAYER_NOT_ENOUGH_MONEY,
    PLAYER_NOT_ENOUGH_MONEY_EXTENDED,
    PLAYER_NEED_1_OR_ABOVE,
    TOWN_NOT_ENOUGH_MONEY,
    TOWN_NOT_ENOUGH_MONEY_EXTENDED,
    REGION_NOT_ENOUGH_MONEY,
    REGION_NOT_ENOUGH_MONEY_EXTENDED,
    PLAYER_WRITE_TOWN_NAME_IN_CHAT,
    PLAYER_SEND_MONEY_TO_TOWN,
    PLAYER_SEND_MONEY_TO_REGION,
    SET_SPAWN_COMMAND_DESC,
    SPAWN_SET_SUCCESS,
    SPAWN_COMMAND_DESC,
    SPAWN_TELEPORTED,
    SPAWN_NOT_SET,
    SPAWN_NOT_UNLOCKED,
    RELATION_NEUTRAL_NAME,
    RELATION_CITY_NAME,
    RELATION_ALLIANCE_NAME,
    RELATION_NON_AGGRESSION_NAME,
    RELATION_EMBARGO_NAME,
    RELATION_WAR_NAME,
    GUI_WARNING_STILL_IN_DEV,
    GUI_BACK_ARROW,
    GUI_QUIT_ARROW,
    GUI_NEXT_PAGE,
    GUI_PREVIOUS_PAGE,
    GUI_LEFT_CLICK_TO_INTERACT,
    GUI_INCREASE_1_DESC,
    GUI_INCREASE_10_DESC,
    GUI_DECREASE_1_DESC,
    GUI_DECREASE_10_DESC,
    GUI_KINGDOM_ICON,
    GUI_KINGDOM_ICON_DESC1,
    GUI_REGION_ICON,
    GUI_REGION_ICON_DESC1_REGION,
    GUI_REGION_ICON_DESC1_NO_REGION,
    GUI_TOWN_ICON,
    GUI_TOWN_ICON_DESC1_HAVE_TOWN,
    GUI_TOWN_ICON_DESC1_NO_TOWN,
    GUI_PROFILE_ICON,
    GUI_PROFILE_ICON_DESC1,
    GUI_PLAYER_PROFILE,
    GUI_PLAYER_PROFILE_DESC1,
    GUI_PLAYER_PROFILE_DESC2,
    GUI_PLAYER_PROFILE_DESC3,
    GUI_PLAYER_PROFILE_NO_TOWN,
    GUI_YOUR_PROFILE,
    GUI_YOUR_BALANCE,
    GUI_YOUR_BALANCE_DESC1,
    GUI_YOUR_PVE_KILLS,
    GUI_YOUR_PVE_KILLS_DESC1,
    GUI_YOUR_CURRENT_TIME_ALIVE,
    GUI_YOUR_CURRENT_TIME_ALIVE_DESC1,
    GUI_YOUR_CURRENT_MURDER,
    GUI_YOUR_CURRENT_MURDER_DESC1,
    GUI_NO_TOWN_CREATE_NEW_TOWN,
    GUI_NO_TOWN_CREATE_NEW_TOWN_DESC1,
    GUI_NO_TOWN_JOIN_A_TOWN,
    GUI_NO_TOWN_JOIN_A_TOWN_DESC1,
    GUI_TOWN_INFO_DESC0,
    GUI_TOWN_INFO_DESC1,
    GUI_TOWN_INFO_DESC2,
    GUI_TOWN_INFO_DESC3,
    GUI_TOWN_INFO_DESC4,
    GUI_TOWN_INFO_DESC5_REGION,
    GUI_TOWN_INFO_DESC5_NO_REGION,
    GUI_TOWN_INFO_CHANGE_ICON,
    GUI_TOWN_TREASURY_ICON,
    GUI_TOWN_TREASURY_ICON_DESC1,
    GUI_TOWN_MEMBERS_ICON,
    GUI_TOWN_MEMBERS_ICON_DESC1,
    GUI_CLAIM_ICON,
    GUI_CLAIM_ICON_DESC1,
    GUI_OTHER_TOWN_ICON,
    GUI_OTHER_TOWN_ICON_DESC1,
    GUI_OTHER_REGION_ICON,
    GUI_OTHER_REGION_ICON_DESC1,
    GUI_RELATION_ICON,
    GUI_RELATION_ICON_DESC1,
    GUI_TOWN_LEVEL_ICON,
    GUI_TOWN_LEVEL_ICON_DESC1,
    GUI_TOWN_SETTINGS_ICON,
    GUI_TOWN_SETTINGS_ICON_DESC1,
    GUI_TREASURY_STORAGE,
    GUI_TREASURY_STORAGE_DESC1,
    GUI_TREASURY_STORAGE_DESC2,
    GUI_TREASURY_SPENDING,
    GUI_TREASURY_SPENDING_DESC1,
    GUI_TREASURY_SPENDING_DESC2,
    GUI_TREASURY_SPENDING_DESC3,
    GUI_TREASURY_LOWER_TAX,
    GUI_TREASURY_CANT_TAX_LESS,
    GUI_TREASURY_INCREASE_TAX,
    GUI_TREASURY_FLAT_TAX,
    GUI_TREASURY_FLAT_TAX_DESC1,
    GUI_TREASURY_TAX_HISTORY,
    GUI_TREASURY_TAX_HISTORY_DESC1,
    GUI_TREASURY_SALARY_HISTORY,
    GUI_TREASURY_SALARY_HISTORY_DESC1,
    GUI_TREASURY_CHUNK_SPENDING_HISTORY,
    GUI_TREASURY_CHUNK_SPENDING_HISTORY_DESC1,
    GUI_TREASURY_CHUNK_SPENDING_HISTORY_DESC2,
    GUI_TREASURY_CHUNK_SPENDING_HISTORY_DESC3,
    GUI_TREASURY_MISCELLANEOUS_SPENDING,
    GUI_TREASURY_MISCELLANEOUS_SPENDING_DESC1,
    GUI_TREASURY_DONATION,
    WRITE_IN_CHAT_AMOUNT_OF_MONEY_FOR_DONATION,
    GUI_TOWN_TREASURY_DONATION_DESC1,
    GUI_REGION_TREASURY_DONATION_DESC1,
    GUI_TREASURY_DONATION_HISTORY,
    GUI_TOWN_LEVEL_INFO,
    GUI_TOWN_LEVEL_UP,
    GUI_TOWN_LEVEL_UP_DESC1,
    GUI_TOWN_LEVEL_UP_DESC2,
    GUI_TOWN_LEVEL_UP_UNI,
    GUI_TOWN_LEVEL_UP_UNI_DESC1,
    GUI_TOWN_LEVEL_UP_UNI_DESC2,
    GUI_TOWN_LEVEL_UP_UNI_DESC3,
    GUI_TOWN_LEVEL_UP_UNI_DESC3_1,
    GUI_TOWN_LEVEL_UP_UNI_DESC3_2,
    GUI_TOWN_LEVEL_UP_UNI_DESC4,
    GUI_TOWN_LEVEL_UP_UNI_DESC4_1,
    GUI_TOWN_LEVEL_UP_UNI_DESC4_2,
    GUI_TOWN_LEVEL_UP_UNI_DESC5,
    TOWN_LEVEL_BONUS_RECAP,
    TOWN_UPGRADE_MAX_LEVEL,
    GUI_TOWN_LEVEL_UP_UNI_REQ_NOT_MET,
    GUI_TOWN_SETTINGS_LEAVE_TOWN,
    GUI_TOWN_SETTINGS_LEAVE_TOWN_DESC1,
    GUI_TOWN_SETTINGS_LEAVE_TOWN_DESC2,
    GUI_TOWN_SETTINGS_DELETE_TOWN,
    GUI_TOWN_SETTINGS_DELETE_TOWN_DESC1,
    GUI_TOWN_SETTINGS_DELETE_TOWN_DESC2,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP_DESC1,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP_DESC2,
    GUI_TOWN_RELATION_WAR,
    GUI_TOWN_RELATION_WAR_DESC1,
    GUI_TOWN_RELATION_EMBARGO,
    GUI_TOWN_RELATION_EMBARGO_DESC1,
    GUI_TOWN_RELATION_NAP,
    GUI_TOWN_RELATION_NAP_DESC1,
    GUI_TOWN_RELATION_ALLIANCE,
    GUI_TOWN_RELATION_ALLIANCE_DESC1,
    GUI_TOWN_RELATION_ADD_TOWN,
    GUI_TOWN_RELATION_REMOVE_TOWN,
    GUI_TOWN_CHANGED_RELATION_RESUME,
    GUI_TOWN_INFO_TOWN_RELATION,
    GUI_TOWN_INFO_IS_RECRUITING,
    GUI_TOWN_INFO_IS_NOT_RECRUITING,
    GUI_TOWN_INFO_LEFT_CLICK_TO_JOIN,
    GUI_TOWN_INFO_RIGHT_CLICK_TO_CANCEL,
    GUI_PLAYER_ASK_JOIN_PROFILE_DESC1,
    GUI_PLAYER_ASK_JOIN_PROFILE_DESC2,
    GUI_PLAYER_ASK_JOIN_PROFILE_DESC3,
    GUI_TOWN_RELATION_NEUTRAL,
    GUI_TOWN_ATTACK_TOWN_DESC1,
    GUI_TOWN_ATTACK_TOWN_DESC2,
    GUI_TOWN_ATTACK_TOWN_EXECUTED,
    GUI_TOWN_ATTACK_FINISHED,
    GUI_TOWN_ATTACK_TOWN_INFO,
    GUI_TOWN_CLAIM_SETTINGS_DOOR,
    GUI_TOWN_CLAIM_SETTINGS_CHEST,
    GUI_TOWN_CLAIM_SETTINGS_BUILD,
    GUI_TOWN_CLAIM_SETTINGS_BREAK,
    GUI_TOWN_CLAIM_SETTINGS_ATTACK_PASSIVE_MOBS,
    GUI_TOWN_CLAIM_SETTINGS_BUTTON,
    GUI_TOWN_CLAIM_SETTINGS_REDSTONE,
    GUI_TOWN_CLAIM_SETTINGS_FURNACE,
    GUI_TOWN_CLAIM_SETTINGS_INTERACT_ITEM_FRAME,
    GUI_TOWN_CLAIM_SETTINGS_INTERACT_ARMOR_STAND,
    GUI_TOWN_CLAIM_SETTINGS_INTERACT_ARMOR_STAND_DESC1,
    GUI_TOWN_CLAIM_SETTINGS_DECORATIVE_BLOCK,
    GUI_TOWN_CLAIM_SETTINGS_MUSIC_BLOCK,
    GUI_TOWN_CLAIM_SETTINGS_LEAD,
    GUI_TOWN_CLAIM_SETTINGS_SHEARS,
    GUI_TOWN_CLAIM_SETTINGS_DESC1,
    GUI_TOWN_CHUNK_PLAYER,
    GUI_TOWN_CHUNK_PLAYER_DESC1,
    GUI_TOWN_CHUNK_MOB,
    GUI_TOWN_CHUNK_MOB_DESC1,
    GUI_TOWN_CHUNK_MOB_SETTINGS_STATUS_ACTIVATED,
    GUI_TOWN_CHUNK_MOB_SETTINGS_STATUS_DEACTIVATED,
    GUI_TOWN_CHUNK_MOB_SETTINGS_STATUS_LOCKED,
    GUI_TOWN_CHUNK_MOB_SETTINGS_STATUS_LOCKED2,
    CHAT_CANT_LEAVE_TOWN_IF_LEADER,
    CHAT_CANT_DISBAND_TOWN_IF_NOT_LEADER,
    CHAT_PLAYER_LEFT_THE_TOWN,
    CHAT_PLAYER_TOWN_SUCCESSFULLY_DELETED,
    CHAT_PLAYER_REGION_SUCCESSFULLY_DELETED,
    GUI_TOWN_MEMBER_DESC1,
    GUI_TOWN_MEMBER_DESC2,
    GUI_TOWN_MEMBER_DESC3,
    GUI_TOWN_MEMBERS_MANAGE_ROLES,
    GUI_TOWN_MEMBERS_MANAGE_APPLICATION,
    GUI_TOWN_MEMBERS_MANAGE_APPLICATION_DESC1,
    GUI_TOWN_MEMBERS_ADD_NEW_ROLES,
    GUI_TOWN_MEMBERS_ADD_NEW_ROLES_DESC1,
    GUI_TOWN_MEMBERS_ROLE_NAME,
    GUI_TOWN_MEMBERS_ROLE_NAME_DESC1,
    GUI_TOWN_MEMBER_CANT_KICK_LEADER,
    GUI_TOWN_MEMBER_CANT_KICK_YOURSELF,
    GUI_TOWN_MEMBER_KICKED_SUCCESS,
    GUI_TOWN_MEMBER_KICKED_SUCCESS_PLAYER,
    INVITATION_ERROR_PLAYER_ALREADY_IN_TOWN,
    INVITATION_ERROR_PLAYER_ALREADY_HAVE_TOWN,
    INVITATION_TOWN_FULL,
    GUI_TOWN_MEMBERS_ROLE_NO_ITEM_SHOWED,
    GUI_TOWN_MEMBERS_ROLE_CHANGED_ICON_SUCCESS,
    GUI_TOWN_MEMBERS_CHANGE_ROLE_PRIORITY,
    GUI_TOWN_MEMBERS_CHANGE_ROLE_PRIORITY_DESC1,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_X,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_DESC1,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_DESC2,
    GUI_TOWN_MEMBERS_ROLE_SET_DEFAULT_IS_DEFAULT,
    GUI_TOWN_MEMBERS_ROLE_SET_DEFAULT_IS_NOT_DEFAULT,
    GUI_TOWN_MEMBERS_ROLE_SET_DEFAULT1,
    GUI_TOWN_MEMBERS_ROLE_SET_DEFAULT2,
    GUI_TOWN_MEMBERS_ROLE_SALARY,
    GUI_TOWN_MEMBERS_ROLE_SALARY_DESC1,
    GUI_TOWN_MEMBERS_ROLE_SET_DEFAULT_ALREADY_DEFAULT,
    GUI_TOWN_MEMBERS_ROLE_DELETE,
    GUI_TOWN_MEMBERS_ROLE_DELETE_ERROR_NOT_EMPTY,
    WRITE_IN_CHAT_NEW_ROLE_NAME,
    NOT_TOWN_LEADER_ERROR,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP_TO_SPECIFIC_PLAYER_DESC1,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP_TO_SPECIFIC_PLAYER_DESC2,
    GUI_TOWN_SETTINGS_TRANSFER_OWNERSHIP_TO_SPECIFIC_PLAYER_SUCCESS,
    GUI_REGION_SETTINGS_REGION_CHANGE_OWNERSHIP_SUCCESS,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_MESSAGE,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_MESSAGE_DESC1,
    GUI_TOWN_SETTINGS_CHANGE_MESSAGE_IN_CHAT,
    CHANGE_MESSAGE_SUCCESS,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_APPLICATION,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_APPLICATION_ACCEPT,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_APPLICATION_NOT_ACCEPT,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_APPLICATION_CLICK_TO_SWITCH,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_NAME,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_NAME_DESC1,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_NAME_DESC2,
    GUI_TOWN_SETTINGS_CHANGE_TOWN_NAME_DESC3,
    GUI_TOWN_SETTINGS_WRITE_NEW_NAME_IN_CHAT,
    GUI_TOWN_SETTINGS_WRITE_NEW_NAME_IN_CHAT_SUCCESS,
    GUI_TOWN_SETTINGS_QUIT_REGION,
    GUI_TOWN_SETTINGS_QUIT_REGION_DESC1_REGION,
    GUI_TOWN_SETTINGS_QUIT_REGION_DESC1_NO_REGION,
    GUI_TOWN_SETTINGS_NEW_TOWN_NAME_HISTORY,
    GUI_TOWN_SETTINGS_CHANGE_CHUNK_COLOR,
    GUI_TOWN_SETTINGS_CHANGE_CHUNK_COLOR_DESC1,
    GUI_TOWN_SETTINGS_CHANGE_CHUNK_COLOR_DESC2,
    GUI_TOWN_SETTINGS_CHANGE_CHUNK_COLOR_DESC3,
    GUI_TOWN_SETTINGS_WRITE_NEW_COLOR_IN_CHAT,
    GUI_TOWN_SETTINGS_WRITE_NEW_COLOR_IN_CHAT_SUCCESS,
    GUI_TOWN_SETTINGS_WRITE_NEW_COLOR_IN_CHAT_ERROR,
    GUI_TOWN_SETTINGS_NEW_TOWN_COLOR_HISTORY,
    ADMIN_GUI_REGION_DESC,
    ADMIN_GUI_TOWN_DESC,
    ADMIN_GUI_PLAYER_DESC,
    ADMIN_GUI_LEFT_CLICK_TO_MANAGE_TOWN,
    ADMIN_GUI_CHANGE_TOWN_NAME,
    ADMIN_GUI_CHANGE_TOWN_NAME_DESC1,
    ADMIN_GUI_CHANGE_TOWN_NAME_DESC2,
    ADMIN_GUI_CHANGE_TOWN_DESCRIPTION,
    ADMIN_GUI_CHANGE_TOWN_DESCRIPTION_DESC1,
    ADMIN_GUI_CHANGE_TOWN_LEADER,
    ADMIN_GUI_CHANGE_TOWN_LEADER_DESC1,
    ADMIN_GUI_DELETE_TOWN,
    ADMIN_GUI_DELETE_TOWN_DESC1,
    ADMIN_GUI_TOWN_PLAYER_TOWN,
    ADMIN_GUI_TOWN_PLAYER_TOWN_DESC1,
    ADMIN_GUI_TOWN_PLAYER_TOWN_DESC2,
    ADMIN_GUI_TOWN_PLAYER_LEAVE_TOWN_SUCCESS,
    TRANSACTION_HISTORY,
    DONATION_SINGLE_LINE_1,
    DONATION_SINGLE_LINE_2,
    TAX_SINGLE_LINE,
    HISTORY_NEGATIVE_SINGLE_LINE,
    TAX_SINGLE_LINE_NOT_ENOUGH,
    CHUNK_HISTORY_DESC1,
    CHUNK_HISTORY_DESC2,
    MISCELLANEOUS_HISTORY_DESC1,
    MISCELLANEOUS_HISTORY_DESC2,
    TOTAL_TAX_LINE,
    TOWN_RANK_CAP_REACHED,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_MANAGE_TAXES,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_PROMOTE_RANK_PLAYER,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_DERANK_RANK_PLAYER,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_CLAIM_CHUNK,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_UNCLAIM_CHUNK,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_UPGRADE_TOWN,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_INVITE_PLAYER,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_KICK_PLAYER,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_CREATE_RANK,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_DELETE_RANK,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_MODIFY_RANK,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_MANAGE_CLAIM_SETTINGS,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_MANAGE_TOWN_RELATION,
    GUI_TOWN_MEMBERS_ROLE_PRIORITY_MANAGE_MOB_SPAWN,
    GUI_TOWN_MEMBERS_ROLE_HAS_PERMISSION,
    GUI_TOWN_MEMBERS_ROLE_NO_PERMISSION,
    GUI_TOWN_MEMBERS_ROLE_SALARY_ERROR_LOWER,
    TOWN_BROADCAST_PLAYER_LEAVE_THE_TOWN,
    WARNING_OTHER_TOWN_HAS_BEEN_DELETED,
    ITEM_RARE_STONE,
    ITEM_RARE_WOOD,
    ITEM_RARE_CROP,
    ITEM_RARE_SOUL,
    RARE_ITEM_DESC_1,
    VILLAGER_GOLDSMITH,
    VILLAGER_BOTANIST,
    VILLAGER_COOK,
    VILLAGER_WIZARD,
    RARE_ITEM_NO_ITEM_IN_HANDS,
    RARE_ITEM_WRONG_ITEM,
    RARE_ITEM_SELLING_SUCCESS,
    DAILY_TAXES_SUCCESS_LOG,
    CHUNK_ENTER_WILDERNESS,
    CHUNK_ENTER_TOWN,
    CHUNK_ENTER_REGION,
    CHUNK_ENTER_TOWN_AT_WAR,
    CHUNK_INTRUSION_ALERT,
    NO_TOWN,
    NO_REGION,
    NO_KINGDOM,
    PLAYER_NOT_ONLINE,
    PLAYER_ONLY_LEADER_CAN_PERFORM_ACTION,
    CHUNK_NOT_CLAIMED,
    LEADER_NOT_ONLINE,
    GUI_REGION_CREATE,
    GUI_REGION_CREATE_DESC1,
    GUI_REGION_CREATE_DESC2,
    GUI_REGION_BROWSE,
    GUI_REGION_BROWSE_DESC1,
    GUI_REGION_BROWSE_DESC2,
    WRITE_IN_CHAT_NEW_REGION_NAME,
    REGION_CREATE_SUCCESS_BROADCAST,
    GUI_REGION_INFO_DESC0,
    GUI_REGION_INFO_DESC1,
    GUI_REGION_INFO_DESC2,
    GUI_REGION_INFO_DESC3,
    GUI_REGION_INFO_DESC4,
    GUI_REGION_INFO_DESC5,
    GUI_REGION_TOWN_LIST,
    GUI_REGION_TOWN_LIST_DESC1,
    GUI_REGION_CHANGE_CAPITAL,
    GUI_REGION_CHANGE_CAPITAL_DESC1,
    GUI_REGION_CHANGE_CAPITAL_DESC2,
    GUI_REGION_CHANGE_CAPITAL_NOT_CAPITAL,
    GUI_REGION_DELETE,
    GUI_REGION_DELETE_DESC1,
    GUI_REGION_DELETE_DESC2,
    GUI_REGION_DELETE_DESC3,
    GUI_NEED_TO_BE_LEADER_OF_REGION,
    GUI_REGION_CHANGE_DESCRIPTION,
    GUI_REGION_CHANGE_DESCRIPTION_DESC1,
    GUI_REGION_CHANGE_DESCRIPTION_DESC2,
    GUI_INVITE_TOWN_TO_REGION,
    GUI_KICK_TOWN_TO_REGION,
    GUI_REGION_INVITE_TOWN_DESC1,
    CANT_KICK_REGIONAL_CAPITAL,
    ADMIN_GUI_CANT_DELETE_REGIONAL_CAPITAL,
    ;


    private static final Map<Lang, String> translations = new HashMap<>();

    public static void loadTranslations(String filename) {

        File langFolder = new File(TownsAndNations.getPlugin().getDataFolder(), "lang");

        if (!langFolder.exists()) {
            langFolder.mkdir();
        }

        File file = new File(langFolder, filename);
        boolean replace = ConfigUtil.getCustomConfig("lang.yml").getBoolean("autoUpdateLangFiles",true);
        if(replace)
            TownsAndNations.getPlugin().saveResource("lang/" + filename, true);



        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);


        for (Lang key : Lang.values()) {

            String message = config.getString("language." + key.name());
            if (message != null) {
                translations.put(key, message);
            }
        }
        TownsAndNations.getPluginLogger().info(LANGUAGE_SUCCESSFULLY_LOADED.get());


    }

    public String get() {
        String translation = translations.get(this);
        if (translation != null) {
            return ChatColor.translateAlternateColorCodes('§', translation);
        }
        return null;
    }

    public String get(Object... placeholders) {
        String translation = translations.get(this);
        if (translation != null) {
            translation = ChatColor.translateAlternateColorCodes('§', translation);
            for (int i = 0; i < placeholders.length; i++) {
                String val = placeholders[i] == null ? "null" : placeholders[i].toString();

                translation = translation.replace("{" + i + "}",val);
            }
        }
        return translation;
    }
}
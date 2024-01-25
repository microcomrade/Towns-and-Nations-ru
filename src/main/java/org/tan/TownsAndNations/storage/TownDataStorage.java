package org.tan.TownsAndNations.storage;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.DateTypeAdapter;
import org.bukkit.entity.Player;
import org.tan.TownsAndNations.DataClass.*;
import org.tan.TownsAndNations.TownsAndNations;
import org.tan.TownsAndNations.enums.TownChunkPermission;
import org.tan.TownsAndNations.enums.TownChunkPermissionType;
import org.tan.TownsAndNations.enums.TownRelation;
import org.tan.TownsAndNations.enums.TownRolePermission;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static org.tan.TownsAndNations.TownsAndNations.isSqlEnable;

public class TownDataStorage {

    private static LinkedHashMap<String, TownData> townDataMap = new LinkedHashMap<>();
    private static int newTownId = 1;
    private static Connection connection;

    public static TownData newTown(String townName, Player player){
        String townId = "T"+newTownId;
        String playerID = player.getUniqueId().toString();
        newTownId++;

        TownData newTown = new TownData( townId, townName, playerID);

        if(isSqlEnable()){
            saveTownDataToDatabase(newTown);
            addTownLevelToDatabase(townId, new TownLevel());
            createChunkPermissions(townId);
            addPlayerToTownDatabase(townId, playerID);
        }
        else{
            townDataMap.put(townId,newTown);
            saveStats();
        }
        return newTown;
    }

    private static void saveTownDataToDatabase(TownData newTown) {

        String sql = "INSERT INTO tan_town_data (town_key, name, uuid_leader," +
                " townDefaultRank, Description, DateCreated, townIconMaterialCode, isRecruiting," +
                "balance,taxRate)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newTown.getID());
            ps.setString(2, newTown.getName());
            ps.setString(3, newTown.getUuidLeader());
            ps.setString(4, newTown.getTownDefaultRank());
            ps.setString(5, newTown.getDescription());
            ps.setString(6, newTown.getDateCreated());
            ps.setString(7, newTown.getTownIconMaterialCode());
            ps.setBoolean(8, newTown.isRecruiting());
            ps.setInt(9, newTown.getBalance());
            ps.setInt(10, newTown.getFlatTax());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerToTownDatabase(String townId, String playerUUID) {
        String sql = "INSERT INTO tan_player_current_town (town_key, player_id) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, playerUUID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static HashSet<String> getPlayersInTown(String townId) {
        HashSet<String> playerIds = new HashSet<>();
        String sql = "SELECT player_id FROM tan_player_current_town WHERE town_key = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String playerId = rs.getString("player_id");
                    playerIds.add(playerId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return playerIds;
    }

    public static void removePlayerFromTownDatabase(String playerUUID) {
        String sql = "DELETE FROM tan_player_current_town WHERE  player_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, playerUUID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void removeTown(String TownId) {
        if (isSqlEnable()) {
            removeTownFromDatabase(TownId);
        } else {
            removeTownFromDB(TownId);
        }
    }

    private static void removeTownFromDB(String TownId) {
        TownData townData = townDataMap.get(TownId);
        if (townData != null) {
            HashSet<String> array = townData.getPlayerList();
            for (String playerUUID : array) {
                PlayerDataStorage.get(playerUUID).setTownId(null);
            }
        }
        townDataMap.remove(TownId);
        saveStats();
    }

    private static void removeTownFromDatabase(String TownId) {
        // Supprimer les données de la ville de la table tan_town_data
        String sqlDeleteTown = "DELETE FROM tan_town_data WHERE town_key = ?";
        try (PreparedStatement psDeleteTown = connection.prepareStatement(sqlDeleteTown)) {
            psDeleteTown.setString(1, TownId);
            psDeleteTown.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Mettre à jour les données des joueurs liés à cette ville dans la base de données
        String sqlUpdatePlayers = "UPDATE tan_player_current_town SET town_key = NULL WHERE town_key = ?";
        try (PreparedStatement psUpdatePlayers = connection.prepareStatement(sqlUpdatePlayers)) {
            psUpdatePlayers.setString(1, TownId);
            psUpdatePlayers.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static LinkedHashMap<String, TownData> getTownList() {
        if (isSqlEnable()) {
            return getTownListFromDatabase();
        } else {
            return townDataMap;
        }
    }

    private static LinkedHashMap<String, TownData> getTownListFromDatabase() {
        LinkedHashMap<String, TownData> townList = new LinkedHashMap<>();
        String sql = "SELECT * FROM tan_town_data";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                TownData townData = new TownData(
                        rs.getString("town_key"),
                        rs.getString("name"),
                        rs.getString("uuid_leader"),
                        rs.getString("Description"),
                        rs.getString("DateCreated"),
                        rs.getString("townIconMaterialCode"),
                        rs.getString("townDefaultRank"),
                        rs.getBoolean("isRecruiting"),
                        rs.getInt("balance"),
                        rs.getInt("taxRate")
                );
                townList.put(townData.getID(), townData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return townList;
    }
    public static TownData get(PlayerData playerData){
        return get(playerData.getTownId());
    }
    public static TownData get(Player player){
        return get(PlayerDataStorage.get(player.getUniqueId().toString()).getTownId());
    }
    public static TownData get(String townId) {
        if (isSqlEnable()) {
            return getTownDataFromDatabase(townId);
        } else {
            return townDataMap.get(townId);
        }
    }

    private static TownData getTownDataFromDatabase(String townId) {
        String sql = "SELECT * FROM tan_town_data WHERE town_key = ?"; // Assurez-vous que le nom de la colonne et de la table est correct

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new TownData(
                            rs.getString("town_key"),
                            rs.getString("name"),
                            rs.getString("uuid_leader"),
                            rs.getString("Description"),
                            rs.getString("DateCreated"),
                            rs.getString("townIconMaterialCode"),
                            rs.getString("townDefaultRank"),
                            rs.getBoolean("isRecruiting"),
                            rs.getInt("balance"),
                            rs.getInt("taxRate")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Retourner null si aucune ville correspondante n'est trouvée
    }

    public static int getNumberOfTown() {
        if (isSqlEnable()) {
            return getNumberOfTownFromDatabase();
        } else {
            return townDataMap.size();
        }
    }

    private static int getNumberOfTownFromDatabase() {
        String sql = "SELECT COUNT(*) FROM tan_town_data";

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    public static void loadStats() {

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateTypeAdapter())
                .create();

        File file = new File(TownsAndNations.getPlugin().getDataFolder().getAbsolutePath() + "/TAN - Towns.json");
        if (file.exists()){
            Reader reader = null;
            try {
                reader = new FileReader(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            Type type = new TypeToken<LinkedHashMap<String, TownData>>() {}.getType();
            townDataMap = gson.fromJson(reader, type);

            int ID = 0;
            for (Map.Entry<String, TownData> entry : townDataMap.entrySet()) {
                String cle = entry.getKey();
                int newID =  Integer.parseInt(cle.substring(1));
                if(newID > ID)
                    ID = newID;
            }
            newTownId = ID+1;
        }

    }

    public static void saveStats() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(TownsAndNations.getPlugin().getDataFolder().getAbsolutePath() + "/TAN - Towns.json");
        file.getParentFile().mkdirs();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (Writer writer = new FileWriter(file, false)) {
            gson.toJson(townDataMap, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public static int getNewTownId() {
        return newTownId;
    }

    public static void initialize() {
        try {
            String url = "jdbc:mysql://localhost:3306/minecraft";
            String username = "root";
            String password = "password";
            connection = DriverManager.getConnection(url, username, password);
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_town_data (" +
                        "town_key VARCHAR(255) PRIMARY KEY," +
                        "name VARCHAR(255)," +
                        "uuid_leader VARCHAR(255)," +
                        "townDefaultRank VARCHAR(255)," +
                        "Description VARCHAR(255)," +
                        "DateCreated VARCHAR(255)," +
                        "townIconMaterialCode VARCHAR(255)," +
                        "isRecruiting BOOLEAN," +
                        "balance INT," +
                        "taxRate INT)";
                statement.executeUpdate(sql);
            }

            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_player_current_town (" +
                        "town_key VARCHAR(255)," +
                        "player_id VARCHAR(255) PRIMARY KEY)";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_player_town_application (" +
                        "town_key VARCHAR(255) PRIMARY KEY," +
                        "player_id VARCHAR(255))";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_player_town_role (" +
                        "town_id VARCHAR(255)," +
                        "name VARCHAR(255)," +
                        "level VARCHAR(255)," +
                        "rankIconName VARCHAR(255)," +
                        "salary INT," +
                        "isPayingTaxes BOOLEAN," +
                        "PRIMARY KEY (town_id, name))";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_town_upgrades (" +
                        "rank_key VARCHAR(255) PRIMARY KEY," +
                        "level VARCHAR(255)," +
                        "chunk_level VARCHAR(255)," +
                        "player_cap_level VARCHAR(255)," +
                        "town_spawn_bought BOOL)";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_chunk_permissions (" +
                        "PermissionId INT AUTO_INCREMENT PRIMARY KEY," +
                        "TownId VARCHAR(255)," +
                        "PermissionType VARCHAR(255)," +
                        "PermissionValue VARCHAR(255))";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_town_role_permissions(" +
                        "TownID  VARCHAR(255)," +
                        "RankId  VARCHAR(255)," +
                        "PermissionType  VARCHAR(255)," +
                        "IsGranted  VARCHAR(255)," +
                        "PRIMARY KEY (TownID, RankId,PermissionType))";
                statement.executeUpdate(sql);
            }
            try (Statement statement = connection.createStatement()){
                String sql = "CREATE TABLE IF NOT EXISTS tan_town_RELATION(" +
                        "town_1_id  VARCHAR(255)," +
                        "town_2_id  VARCHAR(255)," +
                        "relation_type  VARCHAR(255)," +
                        "PRIMARY KEY (town_1_id, town_2_id,relation_type))";
                statement.executeUpdate(sql);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateTownData(TownData townData) {
        if (isSqlEnable() && townData != null) {
            String sql = "UPDATE tan_town_data SET name = ?, uuid_leader = ?, townDefaultRank = ?, " +
                    "Description = ?, DateCreated = ?, townIconMaterialCode = ?, isRecruiting = ?, " +
                    "Balance = ?, taxRate = ? " +
                    "WHERE town_key = ?";

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, townData.getName());
                ps.setString(2, townData.getUuidLeader());
                ps.setString(3, townData.getTownDefaultRank());
                ps.setString(4, townData.getDescription());
                ps.setString(5, townData.getDateCreated());
                ps.setString(6, townData.getTownIconMaterialCode());
                ps.setBoolean(7, townData.isRecruiting());
                ps.setInt(8, townData.getBalance());
                ps.setInt(9, townData.getFlatTax());
                ps.setString(10, townData.getID());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addTownLevelToDatabase(String townId, TownLevel townLevel) {
        if (townLevel == null) return;
        String sql = "INSERT INTO tan_town_upgrades (rank_key, level, chunk_level, player_cap_level, town_spawn_bought) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setInt(2, townLevel.getTownLevel());
            ps.setInt(3, townLevel.getChunkCapLevel());
            ps.setInt(4, townLevel.getPlayerCapLevel());
            ps.setBoolean(5, townLevel.isTownSpawnUnlocked());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TownLevel getTownUpgradeFromDatabase(String town_id) {
        TownLevel townUpgrade = null;
        String sql = "SELECT * FROM tan_town_upgrades WHERE rank_key = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, town_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    townUpgrade = new TownLevel(
                            rs.getInt("level"),
                            rs.getInt("chunk_level"),
                            rs.getInt("player_cap_level"),
                            rs.getBoolean("town_spawn_bought")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return townUpgrade;
    }

    public static void updateTownUpgradeFromDatabase(String town_id, TownLevel townUpgrade) {
        if (townUpgrade == null) return;

        String sql = "UPDATE tan_town_upgrades SET level = ?, chunk_level = ?, " +
                "player_cap_level = ?, town_spawn_bought = ? WHERE rank_key = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, townUpgrade.getTownLevel());
            ps.setInt(2, townUpgrade.getChunkCapLevel());
            ps.setInt(3, townUpgrade.getPlayerCapLevel());
            ps.setBoolean(4, townUpgrade.isTownSpawnUnlocked());
            ps.setString(5, town_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeTownUpgradeFromDB(String townID) {
        String sql = "DELETE FROM tan_town_upgrades WHERE rank_key = ? ";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addPlayerJoinRequestToDB(String playerUUID, String townID) {
        String sql = "INSERT INTO tan_player_town_application (town_key, player_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            ps.setString(2, playerUUID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removePlayerJoinRequestFromDB(String playerUUID, String townID) {
        String sql = "DELETE FROM tan_player_town_application WHERE town_key = ? AND player_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            ps.setString(2, playerUUID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPlayerAlreadyAppliedFromDB(String playerUUID, String townID) {

        String sql = "SELECT COUNT(*) FROM tan_player_town_application WHERE town_key = ? AND player_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            ps.setString(2, playerUUID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static HashSet<String> getAllPlayerApplicationFrom(String townID) {
        HashSet<String> requests = new HashSet<>();
        String sql = "SELECT player_id FROM tan_player_town_application WHERE town_key = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    requests.add(rs.getString("player_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requests;
    }

    public static void createChunkPermissions(String townId) {

        String sql = "INSERT INTO tan_chunk_permissions (TownId, PermissionType, PermissionValue) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (TownChunkPermissionType type : TownChunkPermissionType.values()) {
                ps.setString(1, townId);
                ps.setString(2, type.toString());
                ps.setString(3, "TOWN");
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateChunkPermission(String townId, TownChunkPermissionType permissionType, TownChunkPermission newPermission) {
        String sql = "UPDATE tan_chunk_permissions SET PermissionValue = ? WHERE TownId = ? AND PermissionType = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newPermission.toString());
            ps.setString(2, townId);
            ps.setString(3, permissionType.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteChunkPermissionsFor(String townId) {
        String sql = "DELETE FROM tan_chunk_permissions WHERE TownId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TownChunkPermission getPermission(String townId, TownChunkPermissionType permissionType) {
        String sql = "SELECT PermissionValue FROM tan_chunk_permissions WHERE TownId = ? AND PermissionType = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, permissionType.toString());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String permissionValue = rs.getString("PermissionValue");
                    return TownChunkPermission.valueOf(permissionValue);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void createRole(String townID, TownRank townRank) {
        String sql = "INSERT INTO tan_player_town_role (town_id, name,level, rankIconName, salary, isPayingTaxes) VALUES (?,?,?,?,?,?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townID);
            ps.setString(2, townRank.getName());
            ps.setString(3, townRank.getRankEnum().toString());
            ps.setString(4, townRank.getRankIconName());
            ps.setInt(5, townRank.getSalary());
            ps.setBoolean(6, townRank.isPayingTaxes());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        createRankPermissions(townID, townRank.getName());
    }
    public static TownRank getRole(String townId, String roleName) {


        String sql = "SELECT * FROM tan_player_town_role WHERE town_id = ? AND name = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, roleName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new TownRank(
                        rs.getString("name"),
                        rs.getString("level"),
                        rs.getString("rankIconName"),
                        rs.getBoolean("isPayingTaxes"),
                        rs.getInt("salary")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateRank(String townId, TownRank townRank) {
        String sql = "UPDATE tan_player_town_role SET level = ?, rankIconName = ?, salary = ?, isPayingTaxes = ?  WHERE town_id = ? AND name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, townRank.getRankEnum().toString());
            ps.setString(2, townRank.getRankIconName());
            ps.setInt(3, townRank.getSalary());
            ps.setBoolean(4, townRank.isPayingTaxes());
            ps.setString(5, townId);
            ps.setString(6, townRank.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRank(String townId, String oldName, TownRank townRank) {
        String sql = "UPDATE tan_player_town_role SET name = ?, level = ?, rankIconName = ?, salary = ?, isPayingTaxes = ?  WHERE town_id = ? AND name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, townRank.getName());
            ps.setString(2, townRank.getRankEnum().toString());
            ps.setString(3, townRank.getRankIconName());
            ps.setInt(4, townRank.getSalary());
            ps.setBoolean(5, townRank.isPayingTaxes());
            ps.setString(6, townId);
            ps.setString(7, oldName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRole(String townId, String roleName) {
        String sql = "DELETE FROM tan_player_town_role WHERE town_id = ? AND name = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, roleName);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllRole(String townId) {
        String sql = "DELETE FROM tan_player_town_role WHERE town_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRolePermission(String townId, String rankName) {
        String sql = "DELETE FROM tan_town_role_permissions WHERE TownID = ? AND RankId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, rankName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRolePermissionFromTown(String townId) {
        String sql = "DELETE FROM tan_town_role_permissions WHERE TownID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getPlayerIdsByTownAndRank(String townId, String rankName) {
        List<String> playerIds = new ArrayList<>();
        String sql = "SELECT player_id FROM tan_player_data WHERE town_id = ? AND town_rank = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, rankName);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    playerIds.add(rs.getString("player_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerIds;
    }

    public static List<TownRank> getRanksByTownId(String townId) {
        List<TownRank> ranks = new ArrayList<>();
        String sql = "SELECT * FROM tan_player_town_role WHERE town_id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    ranks.add(new TownRank(
                            rs.getString("name"),
                            rs.getString("level"),
                            rs.getString("rankIconName"),
                            rs.getBoolean("isPayingTaxes"),
                            rs.getInt("salary"))
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ranks;
    }

    public static void createRankPermissions(String townId, String rankName) {

        String sql = "INSERT INTO tan_town_role_permissions (TownId, RankId, PermissionType, isGranted) VALUES (?, ?, ?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (TownRolePermission permission : TownRolePermission.values()) {
                ps.setString(1, townId);
                ps.setString(2, rankName);
                ps.setString(3, permission.toString());
                ps.setBoolean(4, false);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean getRankPermission(String townId, String rankName, TownRolePermission permission) {
        String sql = "SELECT isGranted FROM tan_town_role_permissions WHERE TownId = ? AND RankId = ? AND PermissionType = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, rankName);
            ps.setString(3, permission.toString());

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getBoolean("isGranted");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void swapRankPermission(String townId, String rankName, TownRolePermission permission) {
        String sql = "UPDATE tan_town_role_permissions SET isGranted = NOT isGranted WHERE TownId = ? AND RankId = ? AND PermissionType = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, rankName);
            ps.setString(3, permission.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void renameRankPermission(String townId, String oldRankName, String newRankName) {
        String sql = "UPDATE tan_town_role_permissions SET RankId = ? WHERE TownId = ? AND RankId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRankName);
            ps.setString(2, townId);
            ps.setString(3, oldRankName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addTownRelation(String town1Id, String town2Id, TownRelation relationType) {
        String sql = "INSERT INTO tan_town_relation (town_1_id, town_2_id, relation_type) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, town1Id);
            ps.setString(2, town2Id);
            ps.setString(3, relationType.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getTownsRelatedTo(String townId, TownRelation relationType) {
        ArrayList<String> relatedTowns = new ArrayList<>();
        String sql = "SELECT town_1_id, town_2_id FROM tan_town_relation WHERE (town_1_id = ? OR town_2_id = ?) AND relation_type = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, townId);
            ps.setString(3, relationType.toString());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String relatedTownId = rs.getString("town_1_id").equals(townId) ? rs.getString("town_2_id") : rs.getString("town_1_id");
                    relatedTowns.add(relatedTownId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatedTowns;
    }

    public static void removeTownRelation(String townId1, String townId2, TownRelation relationType) {
        String sql = "DELETE FROM tan_town_relation WHERE " +
                "((town_1_id = ? AND town_2_id = ?) OR (town_1_id = ? AND town_2_id = ?)) AND relation_type = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId1);
            ps.setString(2, townId2);
            ps.setString(3, townId2);
            ps.setString(4, townId1);
            ps.setString(5, relationType.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeAllTownRelationWith(String townToDeleteID) {
        String sql = "DELETE FROM tan_town_relation WHERE town_1_id = ? ";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townToDeleteID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TownRelation getRelationBetweenTowns(String townId1, String townId2) {
        TownRelation relationType = null;
        String sql = "SELECT relation_type FROM tan_town_relation WHERE " +
                "(town_1_id = ? AND town_2_id = ?) OR (town_1_id = ? AND town_2_id = ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId1);
            ps.setString(2, townId2);
            ps.setString(3, townId2);
            ps.setString(4, townId1);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    relationType = TownRelation.valueOf(rs.getString("relation_type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relationType; // Retourne null si aucune relation n'est trouvée
    }

    public static void removeAllChunkPermissionsForTown(String townId) {
        String sql = "DELETE FROM tan_chunk_permissions WHERE TownId = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getNumberOfPlayerByRank(String townId, String rankName) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM tan_player_data WHERE town_id = ? AND town_rank = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, townId);
            ps.setString(2, rankName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1); // Le premier et unique résultat sera le nombre de joueurs
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}

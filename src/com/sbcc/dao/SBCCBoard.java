package com.sbcc.dao;

import com.sbcc.model.Batsman;
import com.sbcc.model.Bowler;
import com.sbcc.model.Player;
import com.sbcc.utility.SBCCUtility;
import jdbc.DBHandler;

import java.sql.*;
import java.util.*;

public class SBCCBoard {
    private List<Player> playerList = new ArrayList<>();

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public int addPlayerObject(String[] playerDetails) {
        SBCCUtility sbccUtility = new SBCCUtility();

        for (String detail : playerDetails) {
            Player player = sbccUtility.parsePlayerDetails(detail);

            if (player != null) {
                playerList.add(player);
            }
        }

//        playerList.forEach(System.out::println);
        return playerList.size();
    }

    public Map<String, Double> findTopPlayerDetails(String playerType) {
        Map<String, Double> batsman = new HashMap<>();
        Map<String, Double> bowler = new HashMap<>();

        for (Player player : playerList) {
            if (player instanceof Batsman) {
                batsman.put(player.getPlayerId(), ((Batsman) player).getStarRating());
            } else {
                bowler.put(player.getPlayerId(), ((Bowler) player).getStarRating());
            }
        }

        Map<String, Double> player = "Batsman".equals(playerType) ? batsman : bowler;

        List<Map.Entry<String, Double>> sortedList = new ArrayList<>(player.entrySet());
        Collections.sort(sortedList, Map.Entry.comparingByValue());
        Collections.reverse(sortedList);

        int count = 0;
        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> obj : sortedList) {
            if (count < 3) {
                sortedMap.put(obj.getKey(), obj.getValue());
                count++;
            }
        }

        return sortedMap;
    }

    public int addPlayerDetails() {
        String query = "insert into player values (?,?,?,?,?,?,?)";
        int rowsInserted = 0;

        try (Connection con = DBHandler.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);

            for(Player ele : playerList) {
                ps.setString(1, ele.getPlayerId());
                ps.setString(2, ele.getPlayerName());
                ps.setInt(3, ele.getMatchesPlayed());
                ps.setInt(4, ele.getRunScored());
                ps.setString(5, ele.getPlayingZone());

                if (ele instanceof Bowler) {
                    ps.setString(6, "Bowler");
                    ps.setDouble(7, ((Bowler) ele).getStarRating());
                } else {
                    ps.setString(6, "Batsman");
                    ps.setDouble(7, ((Batsman) ele).getStarRating());
                }

                rowsInserted += ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return rowsInserted;
    }

    public int findPlayerCount(double startRange, double endRange) {
        String query = "select * from player where starRating between ? and ?";
        int totalRows = 0;

        try(Connection con = DBHandler.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setDouble(1, startRange);
            stmt.setDouble(2, endRange);
            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                totalRows++;
            }
        } catch(SQLException e) {
            e.getStackTrace();
        }

        return totalRows;
    }
}

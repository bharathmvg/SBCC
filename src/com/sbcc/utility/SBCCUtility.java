package com.sbcc.utility;

import com.sbcc.exception.InvalidPlayerIdException;
import com.sbcc.model.Batsman;
import com.sbcc.model.Bowler;
import com.sbcc.model.Player;

public class SBCCUtility {
    public Player parsePlayerDetails(String playerDetails) {
        String[] details = playerDetails.split(":");
        int index = 0;

        String playerId = details[index++];
        try {
            if (validatePlayerId(playerId)) {
                String playerName = details[index++];
                int matchesPlayed = Integer.parseInt(details[index++]);

                String[] runs = new String[matchesPlayed];
                for (int i = 0; i < matchesPlayed; i++) {
                    runs[i] = details[index++];
                }
                int totalRunScored = Player.calculateTotalRuns(runs);

                String playingZone = details[index++];
                String playerType = details[index++];

                if("Batsman".equals(playerType)) {
                    int noOfHundreds = Integer.parseInt(details[index++]);
                    int noOfFifties = Integer.parseInt(details[index++]);
                    Batsman batsman = new Batsman(playerId, playerName, matchesPlayed, totalRunScored, playingZone, noOfHundreds, noOfFifties);
                    batsman.findStarRating();
                    return batsman;
                } else {
                    int noOfMaiden = Integer.parseInt(details[index++]);
                    int noOfHattrick = Integer.parseInt(details[index++]);
                    Bowler bowler = new Bowler(playerId, playerName, matchesPlayed, totalRunScored, playingZone, noOfMaiden, noOfHattrick);
                    bowler.findStarRating();
                    return bowler;
                }
            }
        } catch (InvalidPlayerIdException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean validatePlayerId(String playerId) throws InvalidPlayerIdException {
        if (playerId.length() == 9 && playerId.matches("[A-Z]{4}[\\d]{4}[A-Z]{1}")) {
            return true;
        } else {
            throw new InvalidPlayerIdException("Player with Id " + playerId + " is not valid");
        }
    }
}

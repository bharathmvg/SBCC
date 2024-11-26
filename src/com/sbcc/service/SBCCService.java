package com.sbcc.service;

import com.sbcc.dao.SBCCBoard;

import java.util.Map;

public class SBCCService {
    SBCCBoard sbccBoard = new SBCCBoard();

    public int addPlayerObject(String[] playerDetails) {
        return sbccBoard.addPlayerObject(playerDetails);
    }

    public Map<String, Double> findTopPlayerDetails(String playerType) {
        return sbccBoard.findTopPlayerDetails(playerType);
    }
    
    public int addPlayerDetails() {
        return sbccBoard.addPlayerDetails();
    }

    public int findPlayerCount (double startRange, double endRange) {
        return sbccBoard.findPlayerCount(startRange, endRange);
    }

}

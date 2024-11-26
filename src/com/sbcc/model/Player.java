package com.sbcc.model;

abstract public class Player {
    protected String playerId;
    protected String playerName;
    protected int matchesPlayed;
    protected int runScored;
    protected String playingZone;

    public Player(String playerId, String playerName, int matchesPlayed, int runScored, String playingZone) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.matchesPlayed = matchesPlayed;
        this.runScored = runScored;
        this.playingZone = playingZone;
    }

    public Player() {}

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getRunScored() {
        return runScored;
    }

    public void setRunScored(int runScored) {
        this.runScored = runScored;
    }

    public String getPlayingZone() {
        return playingZone;
    }

    public void setPlayingZone(String playingZone) {
        this.playingZone = playingZone;
    }

    public static int calculateTotalRuns(String[] runScored) {
        int runs = 0;

        for (String s : runScored) {
            runs += Integer.parseInt(s);
        }

        return runs;
    }

    public abstract void findStarRating();

    public void display() {
        System.out.println("Player id: " + playerId);
        System.out.println("Player name: " + playerName);
        System.out.println("Matches played: " + matchesPlayed);
        System.out.println("Total runs scored: " + runScored);
        System.out.println("Playing zone: " + playingZone);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", matchesPlayed=" + matchesPlayed +
                ", runScored=" + runScored +
                ", playingZone='" + playingZone + '\'' +
                '}';
    }
}

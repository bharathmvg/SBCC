package com.sbcc.main;

import com.sbcc.model.*;
import com.sbcc.service.SBCCService;
import com.sbcc.utility.SBCCUtility;

import java.util.Scanner;

public class UserInterface {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SBCCUtility sbccUtility = new SBCCUtility();
        SBCCService sbccService = new SBCCService();

        outerLoop:
        while (true) {
            System.out.println("1. Validate player details");
            System.out.println("2. Create Batsman or Bowler");
            System.out.println("3. Validation with InvalidPlayerIdException");
            System.out.println("4. Add Player Details");
            System.out.println("5. Top three players");
            System.out.println("6. Persist Player Details");
            System.out.println("7. Find Player count within a specific range");
            System.out.println("8. Exit");

            System.out.println("Enter your choice");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1: {
                    System.out.println("Enter the player details");
                    String details = sc.nextLine();
                    Player player = sbccUtility.parsePlayerDetails(details);

                    if (player != null) player.display();
                    else System.out.println("Please provide a valid record");
                    break;
                }

                case 2:
                case 3: {
                    System.out.println("Enter the player details");
                    String details = sc.nextLine();
                    Player player = sbccUtility.parsePlayerDetails(details);

                    if (player != null) {
                        player.display();
                        if (player instanceof Bowler) {
                            Bowler obj = (Bowler) player;
                            System.out.println("Number of Maidens: " + obj.getNoOfMaiden());
                            System.out.println("Number of Hattricks: " + obj.getNoOfHattrick());
                            System.out.println("Star Rating: " + obj.getStarRating());
                        } else {
                            Batsman obj = (Batsman) player;
                            System.out.println("Number of Hundreds: " + obj.getNoOfHundreds());
                            System.out.println("Number of Fifties: " + obj.getNoOfFifties());
                            System.out.println("Star Rating: " + obj.getStarRating());
                        }
                    } else System.out.println("Please provide a valid record");
                    break;
                }

                case 4: {
                    System.out.println("Enter the number of player details");
                    int noOfPlayers = Integer.parseInt(sc.nextLine());

                    System.out.println("Enter the player details");
                    String[] playerDetails = new String[noOfPlayers];
                    for (int i = 0; i < noOfPlayers; i++) {
                        playerDetails[i] = sc.nextLine();
                    }

                    int count = sbccService.addPlayerObject(playerDetails);
                    System.out.println(count + " valid records found");
                    break;
                }

                case 5: {
                    System.out.println("Enter the player type");
                    String playerType = sc.nextLine();

                    sbccService.findTopPlayerDetails(playerType).forEach((key, value) -> {
                        System.out.println(key + " " + value);
                    });
                    break;
                }

                case 6: {
                    int count = sbccService.addPlayerDetails();
                    System.out.println(count + " records inserted successfully");
                    break;
                }

                case 7: {
                    System.out.println("Enter the starting range");
                    double start = Double.parseDouble(sc.nextLine());

                    System.out.println("Enter the ending range");
                    double end = Double.parseDouble(sc.nextLine());

                    int count = sbccService.findPlayerCount(start, end);
                    System.out.println(count + " players found within this range");
                    break;
                }

                default: {
                    System.out.println("Thank you for using SBCC application");
                    break outerLoop;
                }
            }
        }
    }
}

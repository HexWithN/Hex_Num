package Main;

public class AchievementTracker {
	public static int consecutiveSolves = 0;
	public static long average;
	
	public static int numGamesPlayed;
	public static int bestAverage;
	public static int averageTime;
	public static int bestStreak = 0;
	public static int averageStreak = 0;
	public static int averageBreakPoint = 0;
	
	public int[][] achievementGoals = {
			{bestAverage, 3000},
			{bestAverage, 2000},
			{bestAverage, 1000},
			{averageTime, 3000},
			{averageTime, 2000},
			{averageTime, 1000},
			{bestStreak, 3},
			{bestStreak, 15},
			{bestStreak, 27},
			{averageStreak, 3},
			{averageStreak, 15},
			{averageStreak, 27},
	};
	public String[][] achievements = {
			{"Good Wits", "Average less than 3000ms for one game"},
			{"Time Watcher", "Average less than 2000ms for one game"},
			{"Chronos Taker", "Average less than 1000ms for one game"},
			{"Paced Player", "Average less than 3000ms over the last 3 games"},
			{"Time Stalker", "Average less than 2000ms over the last 3 games"},
			{"Chronos Keeper", "Average less than 1000ms over the last 3 games"},
			{"Natural Pro", "Get THREE (3) (III) hexagons in a row"},
			{"Memory Impressive", "Get a 15 hexagon streak. Doing pretty good"},
			{"True Memory God", "Get a 27 hexagon streak. That's it, you're a master. gg no re"},
			{"Baby Step", "Get THREE (3) (III) hexagons in a row"},
			{"Good Memory", "Get a 15 hexagon streak. Doing pretty good"},
			{"True Hex God", "Get a 27 hexagon streak. That's it, you're a master"},
			{"Baby Steps", "Get THREE (3) (III) hexagons in a row for not one, nor two, but three games in a row."},
			{"Good Times. 3.", "Get a 15 hexagon streak for three games in a row. Nice"},
			{"True Hex God", "No really, you beat the game. 3 TIMES OVER. Gg no re. Pls no re"},
	};
	
	public static void init(){
		
	}
	public static void update(){
		if(Main.window.getScene().equals(Main.gameScene)){
			
		}
	}
	public static int length(){
		return 0;
	}
}

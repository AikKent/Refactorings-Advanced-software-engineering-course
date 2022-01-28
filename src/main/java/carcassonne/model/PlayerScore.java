package carcassonne.model;


import java.util.Map;
import carcassonne.model.terrain.TerrainType;
import java.util.HashMap;

public class PlayerScore {
	
	private int overallScore;
	private Map<TerrainType, Integer> terrainSpecificScores;

	public int getOverallScore() {
		return overallScore;
	}

	/**
	* Getter for a specific terrain score.
	* @param scoreType  is the type of the specific terrain score.
	* @return  the specific score.
	*/
	public int getTerrainScore(TerrainType scoreType) {
		if (terrainSpecificScores.containsKey(scoreType)) {
			return terrainSpecificScores.get(scoreType);
		}
		return -1;
	}

	/**
	* Adds points to the players score value and keeps track of the type of score.
	* @param amount  is the amount of points the player gets.
	* @param scoreType  is the pattern type responsible for the points.
	*/
	public void addPoints(int amount, TerrainType scoreType) {
		terrainSpecificScores.put(scoreType, terrainSpecificScores.get(scoreType) + amount);
		overallScore += amount;
	}

	public void initializeScores() {
		overallScore = 0;
		terrainSpecificScores = new HashMap<>();
		for (int i = 0; i < TerrainType.values().length - 1; i++) {
			terrainSpecificScores.put(TerrainType.values()[i], 0);
		}
	}
}
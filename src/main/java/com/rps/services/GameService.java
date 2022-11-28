package com.rps.services;

import java.util.Random;

import com.google.gson.Gson;
import com.rps.modele.Score;

public class GameService {
	
	Score score = new Score(0,0,0);
	Random r = new Random();
	
	public Score getScore() {
		return this.score;
	}
	
	public void setScore(int win, int lose, int tie) {
		this.score.setLose(lose);
		this.score.setWin(win);
		this.score.setTie(tie);
	}
	
	public String play(String action) {
		String servAction = new String[]{"pierre", "feuille", "ciseaux"}[r.nextInt(3)];
		String result = "";
		
		switch(action) {
		case "pierre":
			if(servAction == "pierre") {
				result = "tie";
			} else if (servAction == "feuille") {
				result = "lose";
			} else {
				result = "win";
			}
			break;
		case "feuille":
			if(servAction == "pierre") {
				result = "win";
			} else if (servAction == "feuille") {
				result = "tie";
			} else {
				result = "lose";
			}
			break;
		case "ciseaux":
			if(servAction == "pierre") {
				result = "lose";
			} else if (servAction == "feuille") {
				result = "win";
			} else {
				result = "tie";
			}
			break;
		default: 
			break;
		}
		
		switch(result) {
		case "win":
			score.addWin();
			break;
		case "lose":
			score.addLose();
			break;
		case "tie":
			score.addTie();
			break;
		default :
			break;
		}
		String scoreToJson = new Gson().toJson(score);
		
		return "Vous : " + action + "\tServer : " + servAction + "\tResultat : " + result + "\tScore : " + scoreToJson;
	}
}

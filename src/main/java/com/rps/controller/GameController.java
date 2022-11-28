package com.rps.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.rps.services.GameService;


@WebServlet("/game")
public class GameController extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	GameService game = new GameService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		
		
		response.getWriter().write( new Gson().toJson(game.getScore()));
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			
			if(action == null || (!action.equals("pierre") && !action.equals("feuille") && !action.equals("ciseaux"))) {
				throw new Exception();
			} else {
				response.setContentType("text/plain");
				response.getWriter().write(game.play(action));
			}
			
			
			response.setStatus(200);
			
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int win =  Integer.parseInt( request.getParameter("win"));
			int lose =  Integer.parseInt( request.getParameter("lose"));
			int tie =  Integer.parseInt( request.getParameter("tie"));
			
			game.setScore(win, lose, tie);
			response.setStatus(200);
			response.setContentType("application/json");
			response.getWriter().write( new Gson().toJson(game.getScore()));
			
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			game.setScore(0, 0, 0);
			response.setStatus(200);
			response.setContentType("application/json");
			response.getWriter().write( new Gson().toJson(game.getScore()));
		} catch (Exception e) {
			response.setStatus(400);
		}
	}

}

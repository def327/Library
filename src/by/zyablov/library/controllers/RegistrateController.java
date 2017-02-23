package by.zyablov.library.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.zyablov.library.commands.CommandBehavior;
import by.zyablov.library.commands.CommandRegistration;

public class RegistrateController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		registrationProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		registrationProcess(request, response);
	}

	private void registrationProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommandBehavior command = new CommandRegistration();

		String nextPage = command.execute(request);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

}

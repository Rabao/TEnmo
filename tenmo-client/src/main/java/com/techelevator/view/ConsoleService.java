package com.techelevator.view;


import com.techelevator.tenmo.model.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This is the application's view class.
 *
 * @author Jayden Southworth, Kadeam Howell, Techelevator
 *
 */
public class
ConsoleService {

	/**
	 * Declares the input read and write properties.
	 */
	private PrintWriter out;
	private Scanner in;

	/**
	 * This constructor initializes the properties handling
	 * read and write functions.
	 *
	 * @param input Initializes the read property 'in' with a Scanner object.
	 * @param output Initializes the write property 'out' with a PrintWriter object.
	 */
	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	/**
	 * This method retrieves the User's choice from a list of
	 * options via the getChoiceFromUserInput() method and returns it.
	 *
	 * @param options Retrieves a list of menu options.
	 * @return User's choice.
	 */
	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	/**
	 * This method retrieves the User's input from the InputStream.
	 *
	 * @param options Retrieves a list of menu options.
	 * @return User's choice.
	 */
	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	/**
	 * This method retrieves a list of options and prints them to the terminal.
	 *
	 * @param options Retrieves passed-in list of options.
	 */
	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	/**
	 * This method retrieves a given prompt, appends colons to it, flushes
	 * the prompt from the OutputStream and then retrieves the User's input.
	 *
	 * @param prompt Retrieves passed-in prompt.
	 * @return User's input as a String.
	 */
	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}


	/**
	 * This method retrieves a given prompt, appends colons to it, flushes
	 * the prompt from the OutputStream and then retrieves the User's input,
	 * and then parses the input to get its value as an Integer.
	 *
	 * @param prompt Retrieves passed-in prompt.
	 * @return User's input as an Integer object.
	 */
	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}


	/**
	 * This method prints a formatted list of Users and their IDs.
	 *
	 * @param users Retrieves the list of all users.
	 */
	public void printUsersToDisplay(User[] users) {
		for(User user: users) {
			out.println(String.format("%-8d %-14s",user.getId(),user.getUsername()));
		}
		out.println("-------------------------------");
		out.flush();
	}


}

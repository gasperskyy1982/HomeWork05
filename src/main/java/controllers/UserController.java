package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import models.User;

public class UserController {
	
	public User createUser(String login, String password, String name, String region, boolean gender, String comment) {
		return new User(login, password, name, region, gender, comment);
	}
	

}


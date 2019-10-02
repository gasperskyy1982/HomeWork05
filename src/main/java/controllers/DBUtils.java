package controllers;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

public class DBUtils {
	
	public static String userLogin;
	public static String userPassword;
	public static String userName;
	public static String userRegion;
	public static Boolean userGender;
	public static String userComment;
	
	public static StringBuilder table = new StringBuilder();
	
	private static final String SALT = "alex";
	
	private final static String SELECT_QUERY2 = "SELECT * FROM users WHERE login = ? && password = ?";
	
	private final static String SELECT_QUERY = "SELECT * FROM users WHERE login = ?";

	private final static String INSERT_QUERY = "INSERT INTO users (login, password, name, region, gender, comment)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";
	
	private final static String CORRECT_QUERY = "UPDATE users SET login = ?, password = ?, name = ?, region = ?, gender = ?, comment = ?"
			+ " WHERE login = ?";
	
	public static String getHash (String password) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(password + SALT));
			return String.format("%032x",new BigInteger(md5.digest()));
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} 
		return null;
		
	}
	
	public static boolean isLoginCorrect (String login) {
		if (Pattern.matches(".+@.+", login)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isPasswordCorrect(String password_1) {
		if (Pattern.matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{8,}", password_1)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isRe_PasswordCorrect(String password_1, String password_2) {
		if (password_1.equals(password_2)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean getUniqueLogin(String login) {

		boolean isUnique = true;

		try {
			System.out.println("Connecting Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		}
		System.out.println("SUCCESS");

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
			System.out.println("OK");

		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement selectStmt = null;
		ResultSet rs = null;

		try {

			PreparedStatement prepSt = conn.prepareStatement(SELECT_QUERY);
			prepSt.setString(1, login);
			rs = prepSt.executeQuery();

			while (rs.next()) {
				if (login.equals(rs.getString("login"))) {
					isUnique = false;
				}
			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					conn.close();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}
		}
		return isUnique;
	}

	public static void setUser(String login, String password, String name, String region, Boolean gender, String comment) {

		try {
			System.out.println("Connecting Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		}
		System.out.println("SUCCESS");

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
			System.out.println("OK");

		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement selectStmt = null;
		ResultSet rs = null;

		try {
		
			PreparedStatement prepSt = conn.prepareStatement(INSERT_QUERY);
			prepSt.setString(1, login);
			prepSt.setString(2, DBUtils.getHash(password));
			prepSt.setString(3, name);
			prepSt.setString(4, region);
			prepSt.setBoolean(5, gender);
			prepSt.setString(6, comment);
			prepSt.executeUpdate();
			

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					conn.close();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}

		}

	}

	public static void CorrectUser(String login, String password, String name, String region, Boolean gender, String comment) {

		try {
			System.out.println("Connecting Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		}
		System.out.println("SUCCESS");

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
			System.out.println("OK");

		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement selectStmt = null;
		ResultSet rs = null;

		try {
		
			PreparedStatement prepSt = conn.prepareStatement(CORRECT_QUERY);
			prepSt.setString(1, login);
			prepSt.setString(2, DBUtils.getHash(password));
			prepSt.setString(3, name);
			prepSt.setString(4, region);
			prepSt.setBoolean(5, gender);
			prepSt.setString(6, comment);
			prepSt.setString(7, login);
			prepSt.executeUpdate();
			

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					conn.close();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;
			}

		}

	}
	
	
public static Boolean getAuth(String login, String password) {
		
		boolean isAuth = false;
		
		try {
			System.out.println("Connecting Database");
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
		}
		System.out.println("SUCCESS");

		Connection conn = null;

		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
			System.out.println("OK");

		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

		Statement selectStmt = null;
		ResultSet rs = null;

		try {

			PreparedStatement prepSt = conn.prepareStatement(SELECT_QUERY2);
			prepSt.setString(1, login);
			prepSt.setString(2, getHash(password));
			rs = prepSt.executeQuery();
			
			while (rs.next()) {
				if (login.equals(rs.getString("login")) && getHash(password).equals(rs.getString("password"))) {
					userLogin = rs.getString("login");
					userPassword = rs.getString("password");
					userName = rs.getString("name");
					userRegion = rs.getString("region");
					userGender = rs.getBoolean("gender");
					userComment = rs.getString("comment");
					isAuth = true;
				}
			
			}
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());

		} finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				} // ignore

				rs = null;
			}

			if (selectStmt != null) {
				try {
					selectStmt.close();
					conn.close();
				} catch (SQLException sqlEx) {
				} // ignore

				selectStmt = null;

			}
		}

		return isAuth;

	}
	
	
}
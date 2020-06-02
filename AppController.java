package controller;

import java.util.*;
import javax.faces.bean.ManagedBean;
import org.postgresql.util.Base64;
import java.nio.charset.StandardCharsets;
import javax.faces.application.FacesMessage;
import model.Appuser; // Can be found on https://usecap.fra1.qualtrics.com/jfe/form/SV_1SxfxtUX38cmZzn
import dao.UserDAO; // Can be found on https://usecap.fra1.qualtrics.com/jfe/form/SV_6xHl3x69oxo9isJ

/**
 * This class implements the controller of a web application in the standard
 * Model-View-Controller (MVC) pattern. The controller takes the input,
 * processes it and hands it over to the database.
 **/

@ManagedBean(name = "appController", eager = true)
public class AppController {
	private Date birthdate;
	private String email;	
	private String firstname;
	private String gender;
	private String password;
	private String surname;
	private String username;

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		try {
			this.birthdate = birthdate;
		} catch (Exception e) {

		}
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email, boolean verified) {
		if (verified = true) {
			this.email = email;
		}
	}
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Save user with its properties into the database
	 */
	public void saveUser() {
		Appuser user = new Appuser();
		user.setSurname(this.surname);
		user.setFirstname(this.firstname);
		user.setBirthdate(this.birthdate);
		user.setGender(this.gender);
		user.setUsername(this.username);
		user.setPassword(FetchEncodedString(this.password));
		user.setEmail(this.email,true);

		UserDAO dao = new UserDAO();
		try {
			dao.save(user);	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param inptString
	 *            get encodedstring using inpString
	 */
	private String FetchEncodedString(String inpString) {
		String encStr = Base64.encodeBytes(inpString.getBytes(StandardCharsets.UTF_8));
		return encStr;
	}

}

package com.braidenmiller.notesapp.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	// F E I L D S
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	private String username;
	private String password;
	private String email;
	private boolean enabled;
	private String role;
	
	@OneToMany(mappedBy="user")
	private List<Note> notes;
	
	// C O N S T R U C T O R S
	
	public User() {
		super();
	}

	public User(int id, String email, String firstName, String lastName, String username, String password,
			boolean enabled, String role, List<Note> notes) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.notes = notes;
	}
	
	// G E T T E R S / S E T T E R S

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
	}

	// H A S H C O D E / E Q U A L S
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// T O - S T R I N G
	
	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", enabled=" + enabled + ", role=" + role + "]";
	}
	
	// M E T H O D S
	
	public Collection<Note> addNote(Note note) {
		if ( notes == null) {
			notes = new ArrayList<>();
		}
		if(!notes.contains(note)) {
			notes.add(note);
		}
		
		return notes;
	}
	
	public boolean removeNote(Note note) {
		if(notes != null) {
			if(notes.contains(note)) {
				notes.remove(note);
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
			

}

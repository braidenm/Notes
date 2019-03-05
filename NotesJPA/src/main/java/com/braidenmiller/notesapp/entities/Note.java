package com.braidenmiller.notesapp.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Note {
	
	// F I E L D S
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String details;
	private boolean completed;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@CreationTimestamp
	private Date created;
	
	@UpdateTimestamp
	private Date updated;
	
	// C O N S T U C T O R S

	public Note(int id, String title, String details, boolean completed, User user, Date created, Date updated) {
		super();
		this.id = id;
		this.title = title;
		this.details = details;
		this.completed = completed;
		this.user = user;
		this.created = created;
		this.updated = updated;
	}

	public Note() {
		super();
	}
	
	// G E T T E R S / S E T T E R S

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
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
		Note other = (Note) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	// T O - S T R I N G

	@Override
	public String toString() {
		return "Note [id=" + id + ", title=" + title + ", details=" + details + ", completed=" + completed
				+ ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	
	
	
	
	
	
}

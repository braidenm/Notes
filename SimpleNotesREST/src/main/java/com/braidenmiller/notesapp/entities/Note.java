package com.braidenmiller.notesapp.entities;

public class Note {
	
	// F I E L D S
	
	private int id;
	private String body;
	
	// C O N S T U C T O R S

	public Note(int id, String details) {
		super();
		this.id = id;
		this.body = details;
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


	public String getBody() {
		return body;
	}

	public void setDetails(String details) {
		this.body = details;
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
		return "Note [id=" + id + ", details=" + body + "]";
	}
	
	
	
	
	
	
	
	
}

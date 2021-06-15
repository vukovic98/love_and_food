package com.ftn.uns.ac.rs.love_and_food.dto;

public class CoupleDTO {

	private UserDTO user1;
	private UserDTO user2;
	
	public CoupleDTO() {
	}
	
	public CoupleDTO(UserDTO user1, UserDTO user2) {
		this.user1 = user1;
		this.user2 = user2;
	}

	public UserDTO getUser1() {
		return user1;
	}
	public void setUser1(UserDTO user1) {
		this.user1 = user1;
	}
	public UserDTO getUser2() {
		return user2;
	}
	public void setUser2(UserDTO user2) {
		this.user2 = user2;
	}

	@Override
	public int hashCode() {
		Long sum = user1.getId() + user2.getId();
		return sum.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CoupleDTO other = (CoupleDTO) obj;
		if (this.user1.getId() == other.user1.getId() && this.user2.getId() == other.user2.getId()) {
			return true;
		}
		if (this.user1.getId() == other.user2.getId() && this.user2.getId() == other.user1.getId()) {
			return true;
		}
		return false;
	}
	
}

package spring.chatapp.user.data;

public enum Gender {
	male("male"),
	female("female"),
	other("other");
	private String gender;
	private Gender(String s) {
		this.gender=s;
	}
	public String getGender() {
		return gender;
	}
}

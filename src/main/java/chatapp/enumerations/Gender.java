package chatapp.enumerations;

public enum Gender {
	Male("Male"),
	Female("Female"),
	Other("Other");
	private String gender;
	private Gender(String gender) {
		this.gender=gender;
	}
	public String getGender() {
		return gender;
	}
}

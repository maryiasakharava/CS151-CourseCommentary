//password exceptions used to tell the user when they have incorrectly entered a password

public class PasswordException extends Exception {
	String message;
	public PasswordException() {
			System.out.println("The password you entered is not strong enough. PLease enter a different password.");
	}
	
	public String printOut() {
		return this.message;
	}
}

class UpperCaseCharacterException extends PasswordException{
	String message;
	public UpperCaseCharacterException(String message) {
		this.message =message;
	}
	public String printOut() {
		return this.message;
	}
}
class LowerCaseCharacterMissing extends PasswordException{
	String message;
	public LowerCaseCharacterMissing(String message) {	
		this.message =message;

	}
	public String printOut() {
		return this.message;
	}
	
}
class SpecialCharacterMissing extends PasswordException{
	String message;
	public SpecialCharacterMissing(String message) {
		this.message =message;

	}
	public String printOut() {
		return this.message;
	}
}
class NumberCharacterMissing extends PasswordException{
	String message;
	public NumberCharacterMissing(String message) {
		this.message =message;

	}
	public String printOut() {
		return this.message;
	}
}
class Minimum8CharactersRequired extends PasswordException{
	String message;
	public Minimum8CharactersRequired(String message) {
		this.message =message;

	}
	public String printOut() {
		return this.message;
	}
}

public class Main {

    public static void main(String[] args) {
      JsonUsersFile jsonUsersFile = new JsonUsersFile();
      jsonUsersFile.formatToJson();
      PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();
      phoneNumberValidator.phoneNumberValidator();
    }
}

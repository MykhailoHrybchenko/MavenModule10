import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator {

    private static final String RELATIVE_PATH = "src/main/resources/PhoneNumbers";

    String patternString1 = "([0-9]{3}[-]){2}[0-9]{4}";
    String patternString2 = "^((\\(\\d{3}\\))|\\d{3})[ ]\\d{3}[-]\\d{4}$";


    public void phoneNumberValidator() {
        try(FileInputStream fileInputStream = new FileInputStream(RELATIVE_PATH)) {
            byte[] buffer = new byte[fileInputStream.available()];
            fileInputStream.read(buffer);
            ArrayList<String> arrayList = new ArrayList<>();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < buffer.length; i++) {
                if ((char) buffer[i] == '\n') continue;
                if ((char) buffer[i] == '\r') {
                    arrayList.add(stringBuilder.toString());
                    stringBuilder = new StringBuilder();
                } else {
                    stringBuilder.append((char) buffer[i]);
                }
            }
            arrayList.add(stringBuilder.toString());
            Pattern pattern1 = Pattern.compile(patternString1);
            Pattern pattern2 = Pattern.compile(patternString2);
            for (String number : arrayList) {
                Matcher match1 = pattern1.matcher(number);
                Matcher match2 = pattern2.matcher(number);
                if (match1.matches()) {
                    System.out.println(number);
                }
                if (match2.matches()) {
                    System.out.println(number);
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

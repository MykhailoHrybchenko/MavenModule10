import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class JsonUsersFile {

    public static final String RELATIVE_PATH = "src/main/resources/UserList";
    public static final String JASON_PATH = "src/main/resources/users.json";

    public List<JsonUser> getJsonUSer() {
        List<JsonUser> jsonUsers = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(RELATIVE_PATH))) {
            List<String> listString = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                listString.add(line);
                line = bufferedReader.readLine();
            }
            String[] array = listString.toArray(new String[0]);
            for (String userInfo : array) {
                JsonUser jsonUser = new JsonUser();
                String[] infoArr = userInfo.split("\\s");
                for (int i = 0; i < infoArr.length; i++) {
                    setUserName(infoArr, jsonUser, i);
                    setUserAge(infoArr, jsonUser, i);
                }
                jsonUsers.add(jsonUser);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jsonUsers;
    }

    public void formatToJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(getJsonUSer());
        try(PrintWriter out = new PrintWriter(new FileWriter(JASON_PATH))) {
            out.write(json);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void setUserName(String[] userDetails, JsonUser jsonUser, int i) {
        if (i < userDetails.length && i == 0) {
            jsonUser.setName(String.valueOf(userDetails[i]));
        }
    }

    private void setUserAge(String[] userDetails, JsonUser jsonUser, int i) {
        if (i < userDetails.length && i == 1) {
            jsonUser.setAge(Integer.parseInt(userDetails[i]));
        }
    }
}

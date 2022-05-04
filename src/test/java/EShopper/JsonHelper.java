package EShopper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonHelper {

    private static JsonReader getJsonReader(String jsonFilePath) {
        try {
            JsonReader reader;
            reader = new JsonReader(new FileReader(jsonFilePath));
            return reader;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    public static JsonObject getJsonObject(String jsonFilePath) {
        try {
            JsonObject object = new JsonObject();
            Gson gson = new Gson();
            JsonReader reader = getJsonReader(jsonFilePath);
            object = gson.fromJson(reader, JsonObject.class);
            return object;
        } catch (Exception e) {
            throw e;
        }
    }

}

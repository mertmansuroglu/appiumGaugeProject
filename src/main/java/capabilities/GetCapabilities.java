package capabilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.checkerframework.common.reflection.qual.GetClass;
import org.json.JSONArray;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class GetCapabilities {
    private static final Logger log = LogManager.getLogger(GetCapabilities.class);

    public DesiredCapabilities getDesiredCapabilities(String capabilityName) throws Exception {
        HashMap<String, Object> capabilities = convertCapabilitiesToHashMap(capabilityName);
        return new DesiredCapabilities(capabilities);
    }


    private static HashMap<String, Object> convertCapabilitiesToHashMap(String capabilityName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCapability(capabilityName).toString(), HashMap.class);
    }


    // TODO: 9.11.2021 burda surekli null pointer exception atiyor
    private static JsonObject getCapability(String capabilityName) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonElement jsonObject;
        JsonObject jsonElement;
        //readera json file inin pathini attik
        FileReader reader = new FileReader(
                Objects.requireNonNull(GetCapabilities.class
                        .getClassLoader()
                        .getResource("jsonFiles/getCaps.json"))
                        .getPath());
        jsonObject = gson.fromJson(reader, JsonElement.class);
        jsonElement = jsonObject.getAsJsonObject();
        // TODO: 9.11.2021 burada jsonarray ve JSONARRAY durumundan dolayimi sikinti var ? 
        JsonArray ARRAY = jsonElement.get(capabilityName).getAsJsonObject().getAsJsonArray();
        for (Object jsonObj : ARRAY) {
            JsonObject capability = (JsonObject) jsonObj;
            if (capability.get("name").toString().equalsIgnoreCase(capabilityName)) {
                return (JsonObject) capability.get("capabilities");
            }
        }
        return null;
    }
}
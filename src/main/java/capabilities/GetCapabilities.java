package capabilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import exceptions.LocatorFileNotFound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Objects;

public class GetCapabilities {
    private static final Logger log = LogManager.getLogger(GetCapabilities.class);

    public static DesiredCapabilities getDesiredCapabilities(String capabilityName) throws Exception {
        HashMap<String, Object> capabilities = convertCapabilitiesToHashMap(capabilityName);
        return new DesiredCapabilities(capabilities);
    }


    private static HashMap<String, Object> convertCapabilitiesToHashMap(String capabilityName) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(getCapability(capabilityName).toString(), HashMap.class);
    }


    private static JsonObject getCapability(String capabilityName) throws FileNotFoundException, LocatorFileNotFound {
        Gson gson = new Gson();
        //readera json file inin pathini attik
        //we can use try catch in the file reader, if they didnt find file, you should put find failure@@
        FileReader reader;
        try {
            reader = new FileReader(
                    Objects.requireNonNull(GetCapabilities.class
                            .getClassLoader()
                            .getResource("jsonFiles/getCaps.json"))
                            .getPath());
        } catch (NullPointerException e) {
            throw new LocatorFileNotFound("getCaps");
        }
        JsonArray ARRAY = gson.fromJson(reader, JsonElement.class).getAsJsonArray();
        for (Object jsonObj : ARRAY) {
            JsonObject capability = (JsonObject) jsonObj;
            if (capability.get("name").toString().contains(capabilityName)) {
                return capability.get("caps").getAsJsonObject();

            }

        }
        return null;
    }
}
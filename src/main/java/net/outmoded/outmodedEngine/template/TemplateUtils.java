package net.outmoded.outmodedEngine.template;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class TemplateUtils {
    private static final ObjectMapper defaultMapper = new ObjectMapper();

    public static JsonNode readTree(String json) throws IOException {
        return defaultMapper.readTree(json);
    }

    public static ObjectMapper mapper(){
        return defaultMapper;
    }
}

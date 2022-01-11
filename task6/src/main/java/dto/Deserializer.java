package dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deserializer {
    private static final Logger log = LoggerFactory.getLogger(Deserializer.class);

    public Message deserializeMessage(String str) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Message> typeRef = new TypeReference<>() {};
        try {
            return mapper.readValue(str, typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            log.error("Ошибка десериализации JSON", e.fillInStackTrace());
        }
        return null;
    }
}

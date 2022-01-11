package dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Serializer {
    private static final Logger log = LoggerFactory.getLogger(Serializer.class);

    public String serializeMessage(String name, String message){
        String localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm:ss"));
        Message mes = new Message();
        mes.setNameSender(" " + name + " ");
        mes.setMessage(": " + message);
        mes.setLocalDateTime("[" + localDateTime + "]");
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mes);
        } catch (JsonProcessingException e) {
            log.error("Ошибка сериализации JSON", e.fillInStackTrace());
        }
        return null;
    }

    public String serializeMessage(Message message){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            log.error("Ошибка сериализации JSON", e.fillInStackTrace());
        }
        return null;
    }


}

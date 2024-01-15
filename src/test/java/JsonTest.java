import com.fasterxml.jackson.databind.ObjectMapper;
import json.Json;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {

    ClassLoader cl = JsonTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Test for Json file")
    @Test
    void jsonTest() throws Exception {

        try (
                InputStream resource = cl.getResourceAsStream("SampleJson.json")
        ) {
            assert resource != null;
            try (InputStreamReader reader = new InputStreamReader(resource)
            ) {
                Json Json = objectMapper.readValue(reader, Json.class);
                assertThat(Json.getOrder()).isEqualTo("iPhone");
                assertThat(Json.getModel()).isEqualTo(13);
                assertThat(Json.getOrigin()).isEqualTo("China");
                assertThat(Json.getDetails()).contains("Siri", "Camera");

            }
        }
    }
}
package json;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Json {
    public String order;
    public int model;
    public String origin;
    public String[] details;

    @JsonCreator
    public Json(
            @JsonProperty("Order") String order,
            @JsonProperty("Model") int model,
            @JsonProperty("Origin") String origin,
            @JsonProperty("Details") String[] details) {
        this.order = order;
        this.model = model;
        this.origin = origin;
        this.details = details;
    }
    public String getOrder() {
        return order;
    }
    public int getModel() {
        return model;
    }
    public String getOrigin() {
        return origin;
    }
    public String[] getDetails() {
        return details;
    }

}
package catalog.representations;

//import com.fasterxml.jackson.annotation.*;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Saying {
    public long id;
    public String name;
    public String address;

    @JsonCreator
    public Saying(@JsonProperty("id") long id, @JsonProperty("content") String content, @JsonProperty("address") String address) {
        this.id = id;
        this.name = content;
        this.address = address;
    }
}


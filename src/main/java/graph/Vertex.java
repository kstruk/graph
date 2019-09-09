package graph;

import java.util.*;

import static java.util.Objects.*;

public class Vertex {

    private String key;

    private UserData userData;

    public Vertex(String key, UserData userData) {
        this.key = requireNonNull(key);
        this.userData = requireNonNull(userData);
    }

    public String getKey() {
        return key;
    }

    public UserData getUserData() {
        return userData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vertex vertex = (Vertex) o;
        return key.equals(vertex.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    @Override
    public String toString() {
        return new StringBuilder("Vertex{")
            .append("key='").append(key).append('\'')
            .append('}').toString();
    }

}

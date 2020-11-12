package product.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Role {

    ADMIN,
    MODERATOR,
    USER;

    public static List<String> getAll() {
        return Arrays.stream(Role.values()).map(Enum::toString).collect(Collectors.toList());
    }

}

package org.comparus.aggregation.data;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {

    String id;
    String username;
    String name;
    String surname;
}

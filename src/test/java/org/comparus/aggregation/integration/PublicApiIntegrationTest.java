package org.comparus.aggregation.integration;

import org.comparus.aggregation.controller.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PublicApiIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldGetAllUsersFromAllDatabases() {
        ResponseEntity<List<UserDto>> response = testRestTemplate.exchange("/public/users", HttpMethod.GET, null,
                                                                           new ParameterizedTypeReference<>() {
                                                                           });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedUsers());
    }

    private List<UserDto> expectedUsers() {
        return List.of(
                new UserDto("pg1_user_id_1", "cr7", "Cristiano", "Ronaldo"),
                new UserDto("pg1_user_id_2", "leo10", "Lionel", "Messi"),
                new UserDto("pg2_user_id_1", "pg2_user_id_1", "Evgeniy", "Konoplianka"),
                new UserDto("pg2_user_id_2", "pg2_user_id_2", "Andrey", "Yarmolenko"),
                new UserDto("mysql1_user_id_1", "sheva7", "Andrey", "Shevchenko"),
                new UserDto("mysql1_user_id_2", "paolo_maldini", "Paolo", "Maldini"),
                new UserDto("mysql1_user_id_3", "pirlo21", "Andrea", "Pirlo"),
                new UserDto("mongo1UserId1", "zizu", "Zinedin", "Zidan"),
                new UserDto("mongo1UserId2", "figo", "Luis", "Figo"),
                new UserDto("mongo1UserId3", "iniesta", "Andres", "Iniesta")
        );
    }
}

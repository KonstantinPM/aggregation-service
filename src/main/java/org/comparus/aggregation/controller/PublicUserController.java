package org.comparus.aggregation.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.comparus.aggregation.controller.dto.UserDto;
import org.comparus.aggregation.data.User;
import org.comparus.aggregation.service.AggregationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Tag(name = "Public User API")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/public", produces = MediaType.APPLICATION_JSON_VALUE)
public class PublicUserController {

    private final AggregationService<User> userAggregationService;

    @Operation(summary = "Find all users from all databases")
    @GetMapping("/users")
    public List<UserDto> getUsers() {
        log.info("Get all users");
        return userAggregationService.findAll().stream()
                                     .map(this::toDto)
                                     .toList();
    }

    private UserDto toDto(User user) {
        return new UserDto(user.getId(),
                           user.getUsername(),
                           user.getName(),
                           user.getSurname());
    }
}

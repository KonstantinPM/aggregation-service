package org.comparus.aggregation.service;

import org.comparus.aggregation.data.User;
import org.comparus.aggregation.repository.SimpleJdbcRepository;
import org.comparus.aggregation.repository.SimpleMongoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserAggregationServiceTest {

    @Mock
    private SimpleJdbcRepository<User> jdbcRepository;
    @Mock
    private SimpleMongoRepository<User> mongoRepository;

    private UserAggregationService userAggregationService;

    @BeforeEach
    void setUp() {
        userAggregationService = new UserAggregationService(List.of(jdbcRepository, mongoRepository));
    }

    @Test
    void shouldAggregateDataFromAllRepositories() {
        var jdbcUsers = jdbcUsers();
        var mongoUsers = mongoUsers();
        when(jdbcRepository.findAll()).thenReturn(jdbcUsers);
        when(mongoRepository.findAll()).thenReturn(mongoUsers);

        List<User> result = userAggregationService.findAll();

        assertThat(result).isEqualTo(Stream.concat(jdbcUsers.stream(), mongoUsers.stream())
                                           .toList());
        verify(jdbcRepository).findAll();
        verify(mongoRepository).findAll();
        verifyNoMoreInteractions(jdbcRepository, mongoRepository);
    }

    private List<User> jdbcUsers() {
        return List.of(
                User.builder().id("id1").username("username1").name("name1").surname("surname1").build(),
                User.builder().id("id2").username("username2").name("name2").surname("surname2").build()
        );
    }

    private List<User> mongoUsers() {
        return List.of(
                User.builder().id("id3").username("username3").name("name3").surname("surname3").build(),
                User.builder().id("id4").username("username4").name("name4").surname("surname4").build(),
                User.builder().id("id5").username("username5").name("name5").surname("surname5").build()
        );
    }
}
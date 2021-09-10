package _99_extra._00_league_of_amazing_astronauts;

import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();

    @Mock
    Astronaut astro;
    
    @Mock
    Rocketship rocket;
    
    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	underTest.setRocketship(rocket);
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
    	when(astro.isTrained()).thenReturn(true);
    	underTest.prepareAstronaut(astro);

        //when
    	
        //then
    	verify(astro, times(1)).train();
    	verify(rocket, times(1)).loadOccupant(astro);
    	
    }

    @Test
    void itShouldLaunchRocket() {
        //given
    	when(rocket.isLoaded()).thenReturn(true);
        //when
    	underTest.launchRocket("Mars");
        //then
    	verify(rocket, times(1)).launch();
    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() {
        //given
    	when(rocket.isLoaded()).thenReturn(true);
        //when
        //then	
    	assertThatThrownBy(() -> underTest.launchRocket("12"));
    }

    @Test
    void itShouldThrowNotLoaded() {
        //given
    	when(rocket.isLoaded()).thenReturn(false);
        //when
        //then
    	assertThatThrownBy(()->underTest.launchRocket("Mars"));

    }
}
import org.junit.jupiter.api.Test;

public class RunTest extends ApiSteps {

    @Test
    public void rickAndMortyApiTest() {
        findLastEpisodeMortySmith();
        findLastCharacterLastEpisodeMortySmith();
        findInfoAboutLastCharacterLastEpisodeMortySmith();
        assertSpeciesAndLocation();
    }
}

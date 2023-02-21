import ciril.forest.Forest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForestTest {


    @Test
    public void checkIsValidateGrille(){
        Forest forest = new Forest();
        forest.getRandomForest(5,5);
        Assert.assertNotNull(forest.getGrille());
        assertEquals(forest.getGrille().length,5);
        assertEquals(forest.getGrille()[0].length,5);
    }

    @Test
    public void checkIsValidateForest(){
        Forest forest = new Forest();
        forest.getRandomForest(5,5);
        Assert.assertTrue(forest.isValidForest());
    }

    @Test
    public void shouldGenerateRandomForest(){
        Forest forest = new Forest();
        forest.getRandomForest(10,10);
        Assert.assertNotNull(forest);
    }
}

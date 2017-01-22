package org.wahlzeit.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlcoholTypeTest {

    private AlcoholType alcohol;
    private AlcoholType beer;
    private AlcoholType beer_lager;
    private AlcoholType beer_keller;

    private Alcohol hersbrucker_lager;
    private Alcohol hersbrucker_keller;

    @Before
    public void setup() {
        alcohol = AlcoholManager.getInstance().getAlcoholTypeInstance("alcohol", new String[] { } );

        beer = AlcoholManager.getInstance().getAlcoholTypeInstance("beer", new String[] { "wasser", "hefe", "hopfen" });

        beer_lager = AlcoholManager.getInstance().getAlcoholTypeInstance("lager", new String[] { "some", "ingredients", "here" });
        beer_keller = AlcoholManager.getInstance().getAlcoholTypeInstance("keller", new String[] { "ingredients", "for", "keller", "beer" });

        hersbrucker_lager = AlcoholManager.getInstance().getAlcoholInstance("Hersbrucker Lager", beer_lager);
        hersbrucker_keller = AlcoholManager.getInstance().getAlcoholInstance("Hersbrucker Keller", beer_keller);
    }

    @Test
    public void testSubType() {
        alcohol.addSubType(beer);

        beer.addSubType(beer_lager);
        beer.addSubType(beer_keller);

        assertTrue(beer_lager.isSubtypeOf(beer));
        assertTrue(beer_keller.isSubtypeOf(beer));
        assertTrue(beer.isSubtypeOf(alcohol));
        assertFalse(alcohol.isSubtypeOf(beer));
        assertFalse(beer.isSubtypeOf(beer_keller));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCircularDependency() {
        alcohol.addSubType(beer);
        beer.addSubType(alcohol);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCircularDependency2() {
        alcohol.addSubType(alcohol);
    }
}

package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;
import org.wahlzeit.utils.PatternInstance;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsNotNull;

/**
 * The AlcoholPhoto class provides information for a user-uploaded photo of an alcoholic drink.
 */
@Subclass
@PatternInstance(
        name = "Abstract Factory",
        participants = {"ConcreteProduct"}
)
public class AlcoholPhoto extends Photo {

    private Alcohol alcohol;

    /**
     * This is the standard constructor of the AlcoholPhoto class.
     *
     * @methodtype constructor
     */
    public AlcoholPhoto() {
        super();
    }

    /**
     * Constructor of the AlcoholPhoto class.
     *
     * @param alcohol
     */
    public AlcoholPhoto(Alcohol alcohol) {
        this.alcohol = alcohol;
    }

    /**
     * Constructor of the AlcoholPhoto class.
     *
     * @param myId
     * @methodtype constructor
     */
    public AlcoholPhoto(PhotoId myId) {
        this(myId, null);
    }

    /**
     * Constructor of the AlcoholPhoto class.
     *
     * @param myId
     * @param alcohol
     * @methodtype constructor
     */
    public AlcoholPhoto(PhotoId myId, Alcohol alcohol) {
        super(myId);
        assertArgumentIsNotNull(myId, "id");

        this.alcohol = alcohol;
    }

    /**
     * Returns the alcohol of the AlcoholPhoto
     * @return
     */
    public Alcohol getAlcohol() {
        return alcohol;
    }

    /**
     * Assert that the alcohol percentage value is valid
     *
     * @param percentage
     */
    protected void assertAlcoholPercentageIsValid(float percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Illegal percentage value");
        }
    }
}

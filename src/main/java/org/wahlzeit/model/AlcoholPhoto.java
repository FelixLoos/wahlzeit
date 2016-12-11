package org.wahlzeit.model;

import com.googlecode.objectify.annotation.Subclass;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsNotNull;

/**
 * The AlcoholPhoto class provides information for a user-uploaded photo of an alcoholic drink.
 */
@Subclass
public class AlcoholPhoto extends Photo {

    protected AlcoholType alcoholType;
    protected float percentage;
    protected String brand;

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
     * @param alcoholType
     * @methodtype constructor
     */
    public AlcoholPhoto(PhotoId myId, AlcoholType alcoholType) {
        super(myId);
        assertArgumentIsNotNull(myId, "id");

        this.alcoholType = alcoholType;
    }

    /**
     * Returns the alcohol type of the AlcoholPhoto
     * @return
     */
    public AlcoholType getAlcoholType() {
        return alcoholType;
    }

    /**
     * Sets the alcohol type of the AlcoholPhoto
     * @param alcoholType new AlocholType
     */
    public void setAlcoholType(AlcoholType alcoholType) {
        this.alcoholType = alcoholType;
    }

    /**
     * Returns the alcoholic percentage of the AlcoholPhoto
     * @return
     */
    public float getPercentage() {
        return percentage;
    }

    /**
     * Sets the alcoholic percentage of the AlcoholPhoto
     * @param percentage
     */
    public void setPercentage(float percentage) {
        assertAlcoholPercentageIsValid(percentage);
        this.percentage = percentage;
    }

    /**
     * Returns the brand of the alcoholic drink in the photo
     * @return
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the alcoholic drink in the photo
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
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

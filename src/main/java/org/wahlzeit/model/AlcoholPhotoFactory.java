package org.wahlzeit.model;

import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.PatternInstance;

import java.util.logging.Logger;

import static org.wahlzeit.utils.AssertUtil.assertArgumentIsNotNull;

@PatternInstance(
        name = "Factory",
        participants = {"ConcreteFactory"}
)
public class AlcoholPhotoFactory extends PhotoFactory {

    private static final Logger log = Logger.getLogger(PhotoFactory.class.getName());

    /**
     * Hidden singleton instance; needs to be initialized from the outside
     */
    private static AlcoholPhotoFactory instance;


    /**
     * Public singleton access method
     */
    public static synchronized AlcoholPhotoFactory getInstance() {
        if (instance == null) {
            log.config(LogBuilder.createSystemMessage().addAction("setting generic AlcoholPhotoFactory").toString());
            setInstance(new AlcoholPhotoFactory());
        }
        return instance;
    }

    /**
     * Method to set the singleton instance of AlcoholPhotoFactory
     */
    protected static synchronized void setInstance(AlcoholPhotoFactory photoFactory) {
        if (instance != null) {
            throw new IllegalStateException("attempt to initalize AlcoholPhotoFactory twice");
        }
        instance = photoFactory;
    }

    /**
     * Creates a new photo
     *
     * @methodtype factory
     */
    public AlcoholPhoto createPhoto() {
        return new AlcoholPhoto();
    }

    /**
     * Creates a new photo with the specified id
     *
     * @methodtype factory
     */
    public AlcoholPhoto createPhoto(PhotoId myId) {
        assertArgumentIsNotNull(myId, "id");
        return new AlcoholPhoto(myId);
    }

    /**
     * Creates a new photo with the specified id and alcoholType
     *
     * @methodtype factory
     */
    public AlcoholPhoto createPhoto(PhotoId myId, Alcohol alcohol) {
        assertArgumentIsNotNull(myId, "id");
        return new AlcoholPhoto(myId, alcohol);
    }

    /**
     * Creates a new photo with an Alcohol object
     *
     * @param alcoholName
     * @param alcoholType
     * @return
     */
    public AlcoholPhoto createPhoto(String alcoholName, AlcoholType alcoholType) {
        Alcohol alcohol = AlcoholManager.createAlcohol(alcoholName, alcoholType);
        return new AlcoholPhoto(alcohol);
    }

    /**
     * Creates a new photo with an Alcohol object
     *
     * @param alcoholName
     * @param alcoholTypeName
     * @return
     */
    public AlcoholPhoto createPhoto(String alcoholName, String alcoholTypeName, String[] ingredients) {
        Alcohol alcohol = AlcoholManager.createAlcohol(alcoholName, alcoholTypeName, ingredients);
        return new AlcoholPhoto(alcohol);
    }
}

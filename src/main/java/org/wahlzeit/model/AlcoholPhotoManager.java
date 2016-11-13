package org.wahlzeit.model;

public class AlcoholPhotoManager extends PhotoManager {

    static {
        instance = new AlcoholPhotoManager();
    }

    /**
     * Public singleton access method
     */
    public static synchronized AlcoholPhotoManager getInstance() {
        return (AlcoholPhotoManager)instance;
    }
}

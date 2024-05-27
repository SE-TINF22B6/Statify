package fm.statify.backend_service.entities;

public class AudioFeatures {
    private float accousticness; // 0.0 to 1.0
    private float danceabilty; // 0.0 to 1.0
    private float energy; // 0.0 to 1.0
    private float instrumentalness; // probably 0.0 to 1.0
    private float liveness; // probably 0.0 to 1.0
    private float loudness; // typically between -60 and 0
    private float speechiness; // probably 0.0 to 1.0
    private float valence; // 0.0 to 1.0

    private float tempo; // beats per minute
    private int key; // -1 to 11 (-1 = no key) -> 0 = C, 1 = C♯/D♭ ...
    private int mode; // 0 or 1 (1 = major, 1 = minor)
}

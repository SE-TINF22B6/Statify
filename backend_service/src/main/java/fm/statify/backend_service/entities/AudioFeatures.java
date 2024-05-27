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

    public AudioFeatures(float accousticness, float danceabilty, float energy, float instrumentalness, float liveness, float loudness, float speechiness, float valence, float tempo, int key, int mode) {
        this.accousticness = accousticness;
        this.danceabilty = danceabilty;
        this.energy = energy;
        this.instrumentalness = instrumentalness;
        this.liveness = liveness;
        this.loudness = loudness;
        this.speechiness = speechiness;
        this.valence = valence;
        this.tempo = tempo;
        this.key = key;
        this.mode = mode;
    }

    public float getAccousticness() {
        return accousticness;
    }

    public float getDanceabilty() {
        return danceabilty;
    }

    public float getEnergy() {
        return energy;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public float getLiveness() {
        return liveness;
    }

    public float getLoudness() {
        return loudness;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public float getValence() {
        return valence;
    }

    public float getTempo() {
        return tempo;
    }

    public int getKey() {
        return key;
    }

    public int getMode() {
        return mode;
    }
}

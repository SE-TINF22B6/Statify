package fm.statify.backend_service.entities;

public class UserProfile {

    private String id;
    private String userName;
    private String email;
    private String userURL;
    private String profilePictureURL;
    private String product;

    public UserProfile(String id, String userName, String email, String userURL, String profilePictureURL, String product) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.userURL = userURL;
        this.profilePictureURL = profilePictureURL;
        this.product = product;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getUserURL() {
        return userURL;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public String getProduct() {
        return product;
    }
}

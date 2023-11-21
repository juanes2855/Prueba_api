package certificacion.models;

public class Perfil {
    private String username;
    private Boolean isContactInfoPublic;
    private String correspondenceAddress;
    private String bio;
    private String organization;
    private String country;
    private String faceBookLink;

    public Perfil(String username, Boolean isContactInfoPublic, String correspondenceAddress, String bio, String organization, String country, String faceBookLink) {
        this.username = username;
        this.isContactInfoPublic = isContactInfoPublic;
        this.correspondenceAddress = correspondenceAddress;
        this.bio = bio;
        this.organization = organization;
        this.country = country;
        this.faceBookLink = faceBookLink;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getContactInfoPublic() {
        return isContactInfoPublic;
    }

    public void setContactInfoPublic(Boolean contactInfoPublic) {
        isContactInfoPublic = contactInfoPublic;
    }

    public String getCorrespondenceAddress() {
        return correspondenceAddress;
    }

    public void setCorrespondenceAddress(String correspondenceAddress) {
        this.correspondenceAddress = correspondenceAddress;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFaceBookLink() {
        return faceBookLink;
    }

    public void setFaceBookLink(String faceBookLink) {
        this.faceBookLink = faceBookLink;
    }
}

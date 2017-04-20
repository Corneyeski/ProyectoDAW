
package proyecto.service.dto;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("display_sizes")
    @Expose
    private List<DisplaySize> displaySizes = null;
    @SerializedName("referral_destinations")
    @Expose
    private List<ReferralDestination> referralDestinations = null;
    @SerializedName("title")
    @Expose
    private String title;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<DisplaySize> getDisplaySizes() {
        return displaySizes;
    }

    public void setDisplaySizes(List<DisplaySize> displaySizes) {
        this.displaySizes = displaySizes;
    }

    public List<ReferralDestination> getReferralDestinations() {
        return referralDestinations;
    }

    public void setReferralDestinations(List<ReferralDestination> referralDestinations) {
        this.referralDestinations = referralDestinations;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}

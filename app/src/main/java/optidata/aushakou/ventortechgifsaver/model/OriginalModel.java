package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

public class OriginalModel {

    @SerializedName("url")
    public String url;

    @SerializedName("height")
    public String height;

    @SerializedName("width")
    public String width;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
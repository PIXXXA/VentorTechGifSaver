package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

public class GifsDataModel {

    @SerializedName("images")
    ImagesModel images;

    public ImagesModel getImages() {
        return images;
    }

    public void setImages(ImagesModel images) {
        this.images = images;
    }

    public GifsDataModel(ImagesModel images) {
        this.images = images;
    }
}

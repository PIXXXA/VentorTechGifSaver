package optidata.aushakou.ventortechgifsaver.model;

import com.google.gson.annotations.SerializedName;

public class GifsDataModel {

    @SerializedName("images")
    ImagesModel images;

    public ImagesModel getImages() {
        return images;
    }

    public GifsDataModel(ImagesModel images) {
        this.images = images;
    }
}

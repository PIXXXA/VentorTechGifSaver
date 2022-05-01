package optidata.aushakou.ventortechgifsaver.model.gifsmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GifObjectModel {

    @SerializedName("data")
    List<GifsDataModel> type;

    public List<GifsDataModel> getType() {
        return type;
    }

    public void setType(List<GifsDataModel> type) {
        this.type = type;
    }
}

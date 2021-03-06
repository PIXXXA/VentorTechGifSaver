package optidata.aushakou.ventortechgifsaver.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favourite_table")
public class FavoriteGifsEntity {
    @PrimaryKey(autoGenerate = true)
    long id;
    String url;

    public FavoriteGifsEntity(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public FavoriteGifsEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

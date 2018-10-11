import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PushReceiptRequest {

    @SerializedName("ids")
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds (List<String> ids) {
        this.ids = ids;
    }

}

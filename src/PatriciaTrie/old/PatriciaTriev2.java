package PatriciaTrie;
import java.util.List;

class PatriciaTrie {
    private String data;
    private PatriciaTrie[] childs;
    private List<PatriciaTrie> suffix;
    private final int size = 128;
    private final String endword = " ";

    public PatriciaTrie() {
        data = null;
        suffix = new List<PatriciaTrie>;
        childs = new PatriciaTrie[size];
    }

    public PatriciaTrie(String data) {
        this.data = data;
        childs = new PatriciaTrie[size];
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public PatriciaTrie[] getChilds() {
        return childs;
    }

    public String getPrefixe(String str1, String str2){
        for (int i = 0; i < str2.length(); i++) {
            String tmp = str2.substring(0, i);
            if (str1.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }

    public void addAllSuf(String suffix) {
        for(PatriciaTrie child : this.getChilds()) {
            String gdata = child.getData();
            child.setData(suffix.concat(gdata));
        }
    }
}

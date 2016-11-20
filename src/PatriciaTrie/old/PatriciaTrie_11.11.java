package PatriciaTrie;
import java.util.HashMap;

class PatriciaTrie {
    private String data;
    private HashMap<String, PatriciaTrie> childs;
    private final String endword = " ";

    public PatriciaTrie() {
        data = null;
        childs = new HashMap<String, PatriciaTrie> ();
    }

    public PatriciaTrie(String data) {
        this.data = data;
        childs = new HashMap<String, PatriciaTrie> ();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public HashMap<String, PatriciaTrie> getChilds() {
        return childs;
    }

    public String getPrefix(String str1, String str2){
        for (int i = 0; i < str2.length(); i++) {
            String tmp = str2.substring(0, i);
            if (str1.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }

    public boolean search(PatriciaTrie ptree, String word) {
        boolean t = childs.containsKey(" ");
        if ((word == data) && (t == true)) {
            return true;
        } else if (word == null) {
            return false;
        } else {
            String prefix = word.substring(0, 1);
            String suffix = word.substring(1, word.length());
            if(childs.containsKey(prefix)) {
                return search(childs.get(prefix), suffix);
            } else {
                return false;
            }
        }
    }

    public boolean insert(PatriciaTrie ptree, String word){
        boolean t = childs.containsKey(" ");
        if ((word == data) && (t == true)) {
            return true;
        } else if (word == data) {
            childs.put(" ", null);
            return true;
        } else {
            String prefix = getPrefix(data, word);
            String suffixd = data.substring(prefix.length(), data.length());
            if (prefix == null) {
                PatriciaTrie tmp = new PatriciaTrie(word.substring(0, 1));
                childs.put(word.substring(1, word.length()), tmp);
                return true;
            } else if (prefix == data){
                PatriciaTrie tmp = new PatriciaTrie(word.substring(0, 1));
                childs.put(word.substring(1, word.length()), tmp);
                return true;
            } else {
                String suffixw = word.substring(prefix.length(), word.length());
                return true;
            }
        }
    }
}

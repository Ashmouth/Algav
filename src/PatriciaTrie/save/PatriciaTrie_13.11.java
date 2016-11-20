package PatriciaTrie;
import java.util.HashMap;

class PatriciaTrie {
    private HashMap<String, PatriciaTrie> childs;
    private final String endword = "@";

    public PatriciaTrie() {
        childs = new HashMap<String, PatriciaTrie>();
    }

    public PatriciaTrie(String data) {
        childs = new HashMap<String, PatriciaTrie>();
        childs.put(data, null);
    }
    
    public PatriciaTrie(HashMap<String, PatriciaTrie> data) {
        childs = data;
    }

    public HashMap<String, PatriciaTrie> getChilds() {
        return childs;
    }

    public void setChilds(HashMap<String, PatriciaTrie> newchilds) {
        childs = newchilds;
    }

    public String getPrefix(String str1, String str2) {
        for (int i = str2.length(); i > 0; i--) {
            String tmp = str2.substring(0, i);
            System.out.println("test with " + tmp);
            if (str1.startsWith(tmp)) {
                return tmp;
            }
        }
        return null;
    }
    
    public void displayPtree(PatriciaTrie pt){
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	for (String k : tmp.keySet()) {
    		System.out.println("key = " + k);
    	}
    }
    
    public void printPtree(PatriciaTrie pt) {
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	for (String k : tmp.keySet()) {
    		//System.out.println(pt);
    		System.out.println("key = " + k);
    		PatriciaTrie child = childs.get(k);
    		if (child != null) {
    			System.out.println("fils = ");
    			printPtree(child);
    		}
    	}
    }

    public boolean search(PatriciaTrie ptree, String word) {
    	System.out.println(ptree);
    	System.out.println("debut de recherche");
    	ptree.displayPtree(ptree);
        if ((word == "") && (ptree.getChilds().containsKey(endword) == true)) {
            return true;
        } else if (word == "") {
            return false;
        } else {
            for (int i = 1; i < word.length()+1; i++) {
                String tmp = word.substring(0, i);
                System.out.println(tmp);
                if(ptree.getChilds().containsKey(tmp)) {
                    return search(childs.get(tmp),
                    		word.substring(tmp.length(), word.length()));
                }
            }
            System.out.println("FAIL");
            return false;
        }
    }

    public boolean delete(PatriciaTrie ptree, String word) {
        if ((word == null) && (childs.containsKey(endword) == true)) {
            childs.remove(endword);
            return true;
        } else if (word == null) {
            return true;
        } else {
            for (int i = 0; i < word.length(); i++) {
                String tmp = word.substring(0, i);
                if(childs.containsKey(tmp)) {
                    return search(childs.get(tmp), word.substring(tmp.length()));
                }
            }
            return true;
        }
    }
    
    public PatriciaTrie insert(PatriciaTrie ptree, String word) {
    	System.out.println("insertion");
    	displayPtree(ptree);
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
        if ((word == "") && (ptchilds.containsKey(endword) == true)) {
            return ptree;
        } else if (word == "") {
            ptchilds.put(endword, null);
            ptree.setChilds(ptchilds);
            return ptree;
        } else {
            for (String key : ptchilds.keySet()) {
            	if (word.startsWith(key)) {
            		String suffix = word.substring(key.length(), word.length());
            		PatriciaTrie newkey = insert(ptchilds.get(key), suffix);
            		ptchilds.remove(key);
            		ptchilds.put(key, newkey);
            		ptree.setChilds(ptchilds);
            		return ptree;
            	}
                if (key.startsWith(word.substring(0,1))) {
                    String prefix = getPrefix(key, word);
                    System.out.println("prefixe = " + prefix);
                    String suffix = word.substring(prefix.length(), word.length());
                    String rest = key.substring(prefix.length(), key.length());
                    
                    System.out.println("psr = " + prefix + " " + suffix + " " + rest);

                    HashMap<String, PatriciaTrie> newchild = 
                    		new HashMap<String, PatriciaTrie>();
                    HashMap<String, PatriciaTrie> oldchild = 
                    		new HashMap<String, PatriciaTrie>();

                    PatriciaTrie oldkey = new PatriciaTrie(oldchild);
                    PatriciaTrie resval = new PatriciaTrie(endword);

                    oldkey.setChilds(ptchilds.get(key).getChilds());
                    System.out.println("oldkey = ");
                    displayPtree(oldkey);
                    System.out.println("oldkey fin");
                    ptchilds.remove(key);

                    newchild.put(rest, oldkey);
                    newchild.put(suffix, resval);
                    PatriciaTrie newkey = new PatriciaTrie(newchild);
                    ptchilds.put(prefix, newkey);
                    ptree.setChilds(ptchilds);
                    return ptree;
                }
            }
            PatriciaTrie resval = new PatriciaTrie(endword);
            ptchilds.put(word, resval);
            ptree.setChilds(ptchilds);
            return ptree;
        }
    }
}

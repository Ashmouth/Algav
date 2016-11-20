package PatriciaTrie;
import java.util.HashMap;

class PatriciaTrie {
    private HashMap<String, PatriciaTrie> childs;
    private final String endword = "@";

    public PatriciaTrie() {
        childs = new HashMap<String, PatriciaTrie> ();
    }

    public PatriciaTrie(String data) {
        childs = new HashMap<String, PatriciaTrie> ();
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
    	//System.out.println(str1 + " " + str2);
        for (int i = str2.length(); i > 1; i--) {
            String tmp = str2.substring(0, i);
            //System.out.println("TEST" + tmp);
            if (str1.startsWith(tmp)) {
                return tmp;
            }
        }
        return null;
    }
    
    public void affiche(PatriciaTrie pt){
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	for (String k : tmp.keySet()) {
    		System.out.println("key = " + k);
    	}
    }
    
    /*
    public void printPtree(PatriciaTrie pt) {
    	HashMap<String, PatriciaTrie> tmp = this.getChilds();
    	for (String k : tmp.keySet()) {
    		System.out.println("key = " + k);
    		PatriciaTrie child = childs.get(k);
    		if (child != null) {
    			System.out.println("fils = ");
    			printPtree(child);
    		}
    	}
    }*/
    
    public void printPtree(PatriciaTrie pt) {
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	for (String k : tmp.keySet()) {
    		System.out.println(pt);
    		System.out.println("key = " + k);
    		PatriciaTrie child = childs.get(k);
    		if (child != null) {
    			System.out.println("fils = ");
    			printPtree(child);
    		}
    	}
    }

    public boolean search(PatriciaTrie ptree, String word) {
        if ((word == "") && (childs.containsKey(" ") == true)) {
        	//System.out.println(word);
            return true;
        } else if (word == "") {
            return false;
        } else {
        	/*for (String key : childs.keySet()) {
        		System.out.println("key = " + key);
        	}*/
            for (int i = 1; i < word.length()+1; i++) {
                String tmp = word.substring(0, i);
                //System.out.println("search = " + tmp);
                if(childs.containsKey(tmp)) {
                    return search(childs.get(tmp),
                    		word.substring(tmp.length(), word.length()));
                }
            }
            //System.out.println("Fail with " + word);
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
    
    /*
    public boolean insert(PatriciaTrie ptree, String word) {
        if ((word == "") && (childs.containsKey(endword) == true)) {
            return true;
        } else if (word == "") {
            childs.put(endword, null);
            return true;
        } else {
            for (String key : childs.keySet()) {			//TODO
                if (key.startsWith(word.substring(0,1))) {
                    String prefix = getPrefix(key, word);
                    String suffix = word.substring(prefix.length(), word.length());
                    String rest = key.substring(prefix.length(), key.length());
                    System.out.println("psr " + prefix + " " + suffix + " " + rest);

                    PatriciaTrie oldkey = new PatriciaTrie();
                    PatriciaTrie newkey = new PatriciaTrie();
                    PatriciaTrie resval = new PatriciaTrie(endword);

                    HashMap<String, PatriciaTrie> tmp;
                    
                    tmp = newkey.getChilds();
                    tmp.put(rest, oldkey);
                    tmp.put(suffix, resval);
                    System.out.println("new");
                    affiche(newkey);

                    tmp = oldkey.getChilds();
                    HashMap<String, PatriciaTrie> old = 
                    		childs.get(key).getChilds();
                    old.putAll(tmp);
                    old.clear();
                    System.out.println("old");
                    affiche(oldkey);
                    
                    childs.put(prefix, resval);
                    System.out.println("final");
                    affiche(this);

                    return true;
                }
            }
            PatriciaTrie resval = new PatriciaTrie(endword);
            childs.put(word, resval);
            //System.out.println("final = " + word);
            return true;
        }
    }*/
    
    public boolean insert(PatriciaTrie ptree, String word) {
        if ((word == "") && (childs.containsKey(endword) == true)) {
            return true;
        } else if (word == "") {
            childs.put(endword, null);
            return true;
        } else {
            for (String key : childs.keySet()) {			//TODO
                if (key.startsWith(word.substring(0,1))) {
                    String prefix = getPrefix(key, word);
                    String suffix = word.substring(prefix.length(), word.length());
                    String rest = key.substring(prefix.length(), key.length());
                    System.out.println("psr " + prefix + " " + suffix + " " + rest);

                    HashMap<String, PatriciaTrie> newchild = 
                    		new HashMap<String, PatriciaTrie>();
                    HashMap<String, PatriciaTrie> oldchild = 
                    		new HashMap<String, PatriciaTrie>();

                    PatriciaTrie oldkey = new PatriciaTrie(oldchild);
                    PatriciaTrie newkey = new PatriciaTrie(newchild);
                    PatriciaTrie resval = new PatriciaTrie(endword);
                    
                    oldkey.setChilds(childs.get(key).getChilds());
                    childs.remove(key);

                    newchild.put(rest, oldkey);
                    newchild.put(suffix, resval);
                    childs.put(prefix, newkey);
                    
                    System.out.println("old");
                    affiche(oldkey);
                    System.out.println("new");
                    affiche(newkey);
                    System.out.println("res");
                    affiche(resval);
                    System.out.println("final");
                    
                    System.out.println("test");
                    System.out.println(ptree);
                    System.out.println(childs.get(prefix));
                    System.out.println(childs.get(prefix).getChilds().get(rest));
                    System.out.println(childs.get(prefix).getChilds().get(suffix));
                    System.out.println("fin test");
                    
                    affiche(this);

                    return true;
                }
            }
            PatriciaTrie resval = new PatriciaTrie(endword);
            childs.put(word, resval);
            //System.out.println("final = " + word);
            return true;
        }
    }
}

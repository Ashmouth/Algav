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
    
    public void printPtree(PatriciaTrie pt) {
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	for (String k : tmp.keySet()) {
    		System.out.println("key = " + k);
    		PatriciaTrie child = tmp.get(k);
    		if (child != null) {
    			System.out.println("fils de " + k + " = ");
    			printPtree(child);
    			System.out.println("Fin des fils de " + k);
    		}
    	}
    }
    
    public void cloneAll(PatriciaTrie ptree, PatriciaTrie ntree) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		HashMap<String, PatriciaTrie> ntchilds = ntree.getChilds();
		for (String key : ptchilds.keySet()) {
			ntchilds.put(key, ptchilds.get(key));
			ptchilds.remove(key);
		}
	}

    public boolean search(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
        if ((word.length() == 0) && (ptchilds.containsKey(endword) == true)) {
            return true;
        } else if (word.length() == 0) {
            return false;
        } else {
            for (int i = 1; i < word.length()+1; i++) {
                String tmp = word.substring(0, i);
                if(ptree.getChilds().containsKey(tmp)) {
                    return search(ptchilds.get(tmp),
                    		word.substring(tmp.length(), word.length()));
                }
            }
            System.out.println("FAIL");
            return false;
        }
    }
    
    public PatriciaTrie delete(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
        if ((word.length() == 0) && (ptchilds.containsKey(endword) == true)) {
        	ptchilds.remove(endword);
            return ptree;
        } else if (word.length() == 0) {
            return ptree;
        } else {
            for (int i = 1; i < word.length()+1; i++) {
                String tmp = word.substring(0, i);
                System.out.println("try = " + tmp);
                if(ptree.getChilds().containsKey(tmp)) {
                	System.out.println("find = " + tmp);
                    return delete(ptchilds.get(tmp),
                    		word.substring(tmp.length(), word.length()));
                }
            }
            System.out.println("FAIL");
            return ptree;
        }
    }
    
    public PatriciaTrie insert(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
        if ((word.length() == 0) && (ptchilds.containsKey(endword) == true)) {
            return ptree;
        } else if (word.length() == 0) {
            ptchilds.put(endword, null);
            return ptree;
        } else {
            for (String key : ptchilds.keySet()) {
            	if (word.startsWith(key)) {
            		String suffix = word.substring(key.length(), word.length());
            		PatriciaTrie newkey = insert(ptchilds.get(key), suffix);
            		ptchilds.remove(key);
            		ptchilds.put(key, newkey);
            		return ptree;
            	}
                if (key.startsWith(word.substring(0,1))) {
                    String prefix = getPrefix(key, word);
                    String suffix = word.substring(prefix.length(), word.length());
                    String rest = key.substring(prefix.length(), key.length());

                    HashMap<String, PatriciaTrie> newchild = 
                    		new HashMap<String, PatriciaTrie>();
                    HashMap<String, PatriciaTrie> oldchild = 
                    		new HashMap<String, PatriciaTrie>();

                    PatriciaTrie resval = new PatriciaTrie(endword);
                    
                    PatriciaTrie oldkey = new PatriciaTrie(oldchild);
                    cloneAll(ptchilds.get(key), oldkey);
                    ptchilds.remove(key);
                    
                    newchild.put(rest, oldkey);
                    newchild.put(suffix, resval);
                    PatriciaTrie newkey = new PatriciaTrie(newchild);
                    
                    ptchilds.put(prefix, newkey);
                    return ptree;
                }
            }
            PatriciaTrie resval = new PatriciaTrie(endword);
            ptchilds.put(word, resval);
            return ptree;
        }
    }
    
    public int CountWord(PatriciaTrie ptree, int res) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	for (String key : ptchilds.keySet()) {
    		if(key == endword) {
    			res++;
    		}
            if(ptchilds.get(key) != null) {
                res += CountWord(ptchilds.get(key), 0);
            }
        }
    	return res;
    }
    
    public int CountWord(PatriciaTrie ptree) {
    	return CountWord(ptree, 0);
    }
    
    public void AllWord(PatriciaTrie ptree, String res) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String tmp;
    	for (String key : ptchilds.keySet()) {
    		if(key == endword) {
    			System.out.println(res);
    		}
            if(ptchilds.get(key) != null) {
            	tmp = res.concat(key);
                AllWord(ptchilds.get(key), tmp);
            }
        }
    }
    
    public void AllWord(PatriciaTrie ptree) {
    	AllWord(ptree, "");
    }
}

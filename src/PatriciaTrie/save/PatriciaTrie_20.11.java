package PatriciaTrie;
import java.util.HashMap;

class PatriciaTrie {
    private HashMap<String, PatriciaTrie> childs;
    private String data;
    private final String endword = "@";

    public PatriciaTrie(String data) {
        this.data = data;
        childs = new HashMap<String, PatriciaTrie>();
    }
    
    public PatriciaTrie(HashMap<String, PatriciaTrie> hashmap, String data) {
    	this.data = data;
    	childs = hashmap;
    }

    public HashMap<String, PatriciaTrie> getChilds() {
        return childs;
    }
    
    public void setChilds(HashMap<String, PatriciaTrie> hm) {
        childs = hm;
    }

	public String getData() {
		return data;
	}
	
	public void setData (String d) {
		data = d;
	}

    public String getPrefix(String str1, String str2) {
        for (int i = str2.length(); i > 0; i--) {
            String tmp = str2.substring(0, i);
            System.out.println("test "+ str1 +" with " + tmp);
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
    			System.out.println("fils de " + k + " data = "+ pt.getData() +" = ");
    			printPtree(child);
    			System.out.println("Fin des fils de " + k);
    		}
    	}
    }
    
    public void cloneAll(PatriciaTrie ptree, PatriciaTrie ntree) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		HashMap<String, PatriciaTrie> ntchilds = ntree.getChilds();
		ptchilds.putAll(ntchilds);
		ptchilds.clear();
	}

    public boolean search(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	if ((word == ptdata) && (ptchilds.containsKey(endword) == true)) {
    		return true;
    	} else if (word == ptdata) {
    		return false;
    	} else {
    		String tmp = word.substring(0, 1);
    		if(ptchilds.containsKey(tmp)) {
    			return search(ptchilds.get(tmp),
    					word.substring(1, word.length()));
    		}
    	}
    	System.out.println("FAIL");
    	return false;
    }
    
    public PatriciaTrie delete(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	if ((word == ptdata) && (ptchilds.containsKey(endword) == true)) {
    		ptchilds.remove(endword);
            return ptree;
    	} else if (word == ptdata) {
    		return ptree;
    	} else {
    		String tmp = word.substring(0, 1);
    		if(ptchilds.containsKey(tmp)) {
    			return delete(ptchilds.get(tmp),
                		word.substring(1, word.length()));
    		}
    	}
    	System.out.println("FAIL");
    	return ptree;
    }
    
    public PatriciaTrie insert(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	
    	//Cas de base
    	if ((word.equals(ptdata)) && (ptchilds.containsKey(endword) == true)) {
    		return ptree;
    	} else if (word.equals(ptdata)) {
    		PatriciaTrie endtree = new PatriciaTrie(endword);
    		ptchilds.put(endword, endtree);
    		return ptree;
    	
    	} else {
    		String first = word.substring(0, 1);
    		
    		//Cas racine
    		if (ptdata == endword) {
    			if (ptchilds.containsKey(first)) {
    				insert(ptchilds.get(first), word);
    				return ptree;
    			} else {
    				PatriciaTrie ntree = new PatriciaTrie(word);
    				ptchilds.put(first, ntree);
    				insert(ptchilds.get(first), word);
    				return ptree;
    			}
    			
    		//Cas non-racine
    		} else {
    			
    			
    			//Cas word est une parti de data
    			if(ptdata.contains(word)) {
    				System.out.println("word in data w d "+ word + " " + ptdata);
    				String suffix = ptdata.substring(word.length(), ptdata.length());

    				//TODO ERROR
    				//PatriciaTrie resval = new PatriciaTrie(endword);
    				PatriciaTrie oldkey = new PatriciaTrie(suffix);
    				cloneAll(ptree, oldkey);
    				
    				ptchilds.put(endword, null);
    				ptchilds.put(suffix.substring(0, 1), oldkey);
    				
    				//insert(ptchilds.get(endword), endword);
        			//insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    				
    				//ptree = new PatriciaTrie(word);
    				ptree.setData(word);
    				//insert(ptree, word);
    				insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    				return ptree;
    			}
    			
    			//Cas data est une parti de word
    			if(word.contains(ptdata)) {
    				System.out.println("data in word w d "+ word + " " + ptdata);
    				String suffix = word.substring(ptdata.length(), word.length());
    				if(ptchilds.containsKey(suffix.substring(0, 1))) {
    					System.out.println("if");
    					insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    					return ptree;
    				} else {
    					System.out.println("else");
    					PatriciaTrie resval = new PatriciaTrie(suffix);
    					ptchilds.put(suffix.substring(0, 1), resval);
    					insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    					return ptree;
    				}
    			}
    			
    			//Cas data et word on deux suffix différents
    			System.out.println("word != data w d "+ word + " " + ptdata);
    			String prefix = getPrefix(ptdata, word);
    			String suffixw = word.substring(prefix.length(), word.length());
    			String suffixd = ptdata.substring(prefix.length(), ptdata.length());

    			PatriciaTrie ptsufw = new PatriciaTrie(suffixw);
    			PatriciaTrie ptsufd = new PatriciaTrie(suffixd);
    			//rest disparais
    			cloneAll(ptree, ptsufd);
    			insert(ptsufw, suffixw);
    			insert(ptsufd, suffixd);

    			System.out.println("psr="+prefix+","+suffixw+","+suffixd);
    			//TODO ERROR
    			ptchilds.put(suffixd.substring(0, 1), ptsufd);
    			ptchilds.put(suffixw.substring(0, 1), ptsufw);
    			ptree.setData(prefix);
    			
    			return ptree;
    		}
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
    	return CountWord(ptree, 1);
    }
    
    public void AllWord(PatriciaTrie ptree, String res) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	String tmp = res.concat(ptdata);
    	for (String key : ptchilds.keySet()) {
    		if(key == endword) {
    			System.out.println(tmp);
    		}
            if(ptchilds.get(key) != null) {
                AllWord(ptchilds.get(key), tmp);
            }
        }
    }
    
    public void AllWord(PatriciaTrie ptree) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	System.out.println(ptree.getData());
    	for (String key : ptchilds.keySet()) {
    		if(ptchilds.get(key) != null) {
                AllWord(ptchilds.get(key), "");
            }
    	}
    }

}

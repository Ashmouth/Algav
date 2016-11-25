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
    	String first = word.substring(0, 1);
    	
    	//Cas de base
    	if ((word.equals(ptdata)) && (ptchilds.containsKey(endword) == true)) {
    		return true;
    	} else if (word.equals(ptdata)) {
    		return false;
    		
    	//Cas racine
    	} else if (ptdata.equals(endword)) {
    		if (ptchilds.containsKey(first)) {
    			return search(ptchilds.get(first), word);
    		} else {
    			return false;
    		}
    		
    		//Cas Récursif
    	} else if (word.startsWith(ptdata)) {
    		String nword = word.substring(ptdata.length(), word.length());
    		String tmp = nword.substring(0, 1);
    		if(ptchilds.containsKey(tmp)) {
    			return search(ptchilds.get(tmp), nword);
    		}
    	}
    	System.out.println("FAIL");
    	return false;
    }
    
    public boolean delete(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	String first = word.substring(0, 1);
    	
    	//Cas de base
    	if ((word.equals(ptdata)) && (ptchilds.containsKey(endword) == true)) {
    		ptchilds.remove(endword);
    		return true;
    	} else if (word.equals(ptdata)) {
    		return false;
    		
    	//Cas racine
    	} else if (ptdata.equals(endword)) {
    		if (ptchilds.containsKey(first)) {
    			return delete(ptchilds.get(first), word);
    		} else {
    			return false;
    		}
    		
    		//Cas Récursif
    	} else if (word.startsWith(ptdata)) {
    		String nword = word.substring(ptdata.length(), word.length());
    		String tmp = nword.substring(0, 1);
    		if(ptchilds.containsKey(tmp)) {
    			return delete(ptchilds.get(tmp), nword);
    		}
    	}
    	System.out.println("FAIL");
    	return false;
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
    		if (ptdata.equals(endword)) {
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
    			
    			System.out.println("pt w " + ptdata + " " + word);
    			
    			//Cas word est une parti de data
    			if(ptdata.startsWith(word)) {
    				String suffix = ptdata.substring(word.length(), ptdata.length());
    				
    				PatriciaTrie oldkey = new PatriciaTrie(suffix);
    				cloneAll(ptree, oldkey);
    				
    				ptchilds.put(endword, null);
    				ptchilds.put(suffix.substring(0, 1), oldkey);
    				
    				ptree.setData(word);
    				insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    				return ptree;
    			}
    			
    			//Cas data est une parti de word
    			if(word.startsWith(ptdata)) {
    				System.out.println("Pouet");
    				String suffix = word.substring(ptdata.length(), word.length());
    				if(ptchilds.containsKey(suffix.substring(0, 1))) {
    					insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    					return ptree;
    				} else {
    					PatriciaTrie resval = new PatriciaTrie(suffix);
    					ptchilds.put(suffix.substring(0, 1), resval);
    					insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    					return ptree;
    				}
    			}
    			
    			//Cas data et word on deux suffix différents
    			String prefix = getPrefix(ptdata, word);
    			String suffixw = word.substring(prefix.length(), word.length());
    			String suffixd = ptdata.substring(prefix.length(), ptdata.length());

    			PatriciaTrie ptsufw = new PatriciaTrie(suffixw);
    			PatriciaTrie ptsufd = new PatriciaTrie(suffixd);

    			cloneAll(ptree, ptsufd);
    			insert(ptsufw, suffixw);
    			insert(ptsufd, suffixd);
    			
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
    
    public PatriciaTrie copy(PatriciaTrie ptree) {
    	PatriciaTrie nptree = 
    			new PatriciaTrie(ptree.getChilds(), ptree.getData());
    	if(ptree.getChilds() != null){
    		for(String s : nptree.getChilds().keySet()){
    			nptree.getChilds().put(s, copy(ptree.getChilds().get(s)));
    		}
    	}
    	return nptree;
    }
    
    public PatriciaTrie fusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {
    	PatriciaTrie nptree2 = copy(ptree2);
    	return subfusion(ptree1, nptree2);
    }

    public PatriciaTrie subfusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {
    	HashMap<String, PatriciaTrie> ptchilds1 = ptree1.getChilds();
    	String ptdata1 = ptree1.getData();
    	HashMap<String, PatriciaTrie> ptchilds2 = ptree2.getChilds();
    	String ptdata2 = ptree2.getData();
    	
    	//Cas Initiale
    	if(ptdata1.equals(ptdata2)){
    		
    		//Cas Récurssif
        	for (String s : ptchilds2.keySet()){
        		if(ptchilds1.containsKey(s)){
        			subfusion(ptchilds1.get(s), ptchilds2.get(s));
        		} else {
        			ptchilds1.put(s, ptchilds2.get(s));
        		}
        	}
        	return ptree1;
    	} else {
    	
    		//Cas Récurssif
    		insert(ptree1, ptdata2);
    		insert(ptree2, ptdata1);
    		subfusion(ptree1, ptree2);
    		return ptree1;
    	}
    }

}

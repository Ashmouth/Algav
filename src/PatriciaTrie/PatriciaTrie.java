package PatriciaTrie;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
            if (str1.startsWith(tmp)) {
                return tmp;
            }
        }
        return null;
    }
    
    public void printPtree(PatriciaTrie pt) {
    	displayPtree(pt, 0);
    }
    
    public void displayPtree(PatriciaTrie pt, int deep) {
    	HashMap<String, PatriciaTrie> tmp = pt.getChilds();
    	String space = new String(new char[deep]).replace('\0', ' ');
    	for (String k : tmp.keySet()) {
    		PatriciaTrie ptchild = tmp.get(k);
    		if (ptchild != null) {
    			System.out.println(space + "data = "+ pt.getData() + " fils = " + k);
    			displayPtree(ptchild, (deep + 1));
    		}
    	}
    }
    
    public void cloneAll(PatriciaTrie src, PatriciaTrie dst) {
		HashMap<String, PatriciaTrie> ptchilds = src.getChilds();
		HashMap<String, PatriciaTrie> ntchilds = dst.getChilds();
		
		for (String key : ptchilds.keySet()) {
			ntchilds.put(key, ptchilds.get(key));
		}
		
		ptchilds.clear();
		src.setChilds(ptchilds);
		dst.setChilds(ntchilds);
	}
    
    public boolean search(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String first = word.substring(0, 1);
    	
    	if (ptchilds.containsKey(first)) {
			return subsearch(ptchilds.get(first), word);
		}
    	return false;
    }
    
    public boolean subsearch(PatriciaTrie ptree, String word) {
    	
    	if (ptree == null) {
    		return false;
    	}
    	
    	String ptdata = ptree.getData();
    	
    	if (word.equals(endword) && ptdata.equals(endword)) {
    		return true;
    	} else {
    		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    		if (word.equals(ptdata)) {
    			return subsearch(ptchilds.get(endword), endword);
    		}
    		if (word.startsWith(ptdata)) {
    			String suffix = word.substring(ptdata.length(), word.length());
    			String first = suffix.substring(0, 1);
    			if (ptchilds.containsKey(first)) {
    				return subsearch(ptchilds.get(first), suffix);
    			}
    		}
    		System.out.println("Search didn't find :/");
    		return false;
    	}
    }
    
    public boolean delete(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String first = word.substring(0, 1);
    	
    	if (ptchilds.containsKey(first)) {
			return subdelete(ptchilds.get(first), word);
		}
    	return false;
    }
    
    public boolean subdelete(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	
    	if (word.equals(ptdata)) {
    		if (ptchilds.containsKey(endword)) {
    			ptchilds.remove(endword);
    			return true;
    		} else {
    			return true;
    		}
    	}
    	if (word.startsWith(ptdata)) {
    		String suffix = word.substring(ptdata.length(), word.length());
    		String first = suffix.substring(0, 1);
    		if (ptchilds.containsKey(first)) {
    			return subdelete(ptchilds.get(first), suffix);
    		}
    	}
    	System.out.println("Delete didn't find :/");
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
    				return insert(ptchilds.get(first), word);
    			} else {
    				PatriciaTrie ntree = new PatriciaTrie(word);
    				ptchilds.put(first, ntree);
    				return insert(ptchilds.get(first), word);
    			}
    			
    		//Cas non-racine
    		} else {
    			
    			//Cas word est une parti de data
    			if(ptdata.startsWith(word)) {
    				String suffix = ptdata.substring(word.length(), ptdata.length());
    				
    				PatriciaTrie oldkey = new PatriciaTrie(suffix);
    				cloneAll(ptree, oldkey);
    				
    				ptchilds.put(endword, null);
    				ptchilds.put(suffix.substring(0, 1), oldkey);
    				
    				ptree.setData(word);
    				return insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    			}
    			
    			//Cas data est une parti de word
    			if(word.startsWith(ptdata)) {
    				String suffix = word.substring(ptdata.length(), word.length());
    				if(ptchilds.containsKey(suffix.substring(0, 1))) {
    					return insert(ptchilds.get(suffix.substring(0, 1)), suffix);
    				} else {
    					PatriciaTrie resval = new PatriciaTrie(suffix);
    					ptchilds.put(suffix.substring(0, 1), resval);
    					return insert(ptchilds.get(suffix.substring(0, 1)), suffix);
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
    
    
    public int CountDeep(PatriciaTrie ptree, int size) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	int res = size;
    	for (String key : ptchilds.keySet()) {
            if(ptchilds.get(key) != null) {
            	int tmp = CountDeep(ptchilds.get(key), size + 1);
            	if (tmp > res) {
            		res = tmp;
            	}
            }
        }
    	return res;
    }
    
    public int CountDeep(PatriciaTrie ptree) {
    	if (ptree.getData().equals(endword)) {
    		return CountDeep(ptree, 0);
    	}
    	return CountDeep(ptree, 1);
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
    
    public void split(PatriciaTrie ptree, String word) {
    	
    	String ptdata = ptree.getData();
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	
    	//Cas data est une parti de word
		if(ptdata.startsWith(word)) {
			String suffix = ptdata.substring(word.length(), ptdata.length());
			
			PatriciaTrie oldkey = new PatriciaTrie(suffix);
			cloneAll(ptree, oldkey);
			
			ptchilds.put(suffix.substring(0, 1), oldkey);
			ptree.setData(word);
			return;
		}

		//Cas word est une parti de data
		if(word.startsWith(ptdata)) {
			String suffix = word.substring(ptdata.length(), word.length());
			PatriciaTrie resval = new PatriciaTrie(suffix);
			ptchilds.put(suffix.substring(0, 1), resval);
			return;
		}

    	String prefix = getPrefix(ptdata, word);
		String suffixw = word.substring(prefix.length(), word.length());
		String suffixd = ptdata.substring(prefix.length(), ptdata.length());

		PatriciaTrie ptsufw = new PatriciaTrie(suffixw);
		PatriciaTrie ptsufd = new PatriciaTrie(suffixd);

		cloneAll(ptree, ptsufd);
		
		ptchilds.put(suffixd.substring(0, 1), ptsufd);
		ptchilds.put(suffixw.substring(0, 1), ptsufw);
		ptree.setData(prefix);
    }
    
    
    public PatriciaTrie fusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {
    	PatriciaTrie nptree2 = copy(ptree2);
    	return subfusion(ptree1, nptree2);
    }
    
    public PatriciaTrie subfusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {

    	String ptdata1 = ptree1.getData();
    	HashMap<String, PatriciaTrie> ptchilds1 = ptree1.getChilds();

    	String ptdata2 = ptree2.getData();
    	HashMap<String, PatriciaTrie> ptchilds2 = ptree2.getChilds();
    	
    	//Cas Initiale
    	if(ptdata1.equals(ptdata2)) {
    		//Cas Récurssif
    		for (String s : ptchilds2.keySet()) {
    			if(ptchilds1.containsKey(s)) {
    				subfusion(ptchilds1.get(s), ptchilds2.get(s));
    			} else {
    				ptchilds1.put(s, ptchilds2.get(s));
    			}
    		}
    		return ptree1;
    	} else {
    		
    		split(ptree1, ptdata2);
    		split(ptree2, ptdata1);
    		return subfusion(ptree1, ptree2);
    	}
    }
    
    public void benchmark(File fileEntry) {
    	BufferedReader br = null;
    	PatriciaTrie pt1 = new PatriciaTrie("@");
        PatriciaTrie pt2 = new PatriciaTrie("@");
        long startTime, endTime, duration, total;
		total = 0;
		try {
			br = new BufferedReader(new FileReader(fileEntry));
		
        	String line = br.readLine();

            while (line != null) {
            	startTime = System.nanoTime();
                pt1.insert(pt1, line);
                endTime = System.nanoTime();
                duration = (endTime - startTime);
                //System.out.println(line + " insert in " + duration);
                total+=duration;
                line = br.readLine();
            }
            
		} catch (IOException e) {
			e.printStackTrace();
            
		} finally {
			try {
				br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		long build = total;
		
		startTime = System.nanoTime();
		insert(pt1, "arbre");
        insert(pt1, "arc");
        insert(pt1, "arbuste");
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        long insertime = duration;
        
        startTime = System.nanoTime();
		search(pt1, "arbre");
		search(pt1, "arc");
		search(pt1, "arbuste");
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        long searchtime = duration;
        
        insert(pt2, "artiste");
        insert(pt2, "destin");
        insert(pt2, "magique");
        startTime = System.nanoTime();
        fusion(pt1, pt2);
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        long fusiontime = duration;
        
        startTime = System.nanoTime();
        delete(pt1, "artiste");
        delete(pt1, "destin");
        delete(pt1, "magique");
        endTime = System.nanoTime();
        duration = (endTime - startTime);
        long deletetime = duration;
        
        String value = String.format("%1$-20s | %2$-8s | %3$-6s | %4$-6s | %5$-6s |"
        		+ " %6$-6s | %7$-6s | %8$-6s",
        		fileEntry.getName(), build, insertime/3, searchtime/3, deletetime/3, 
        		fusiontime/3, pt1.CountWord(pt1), pt1.CountDeep(pt1));
	    System.out.println(value);
    }

}

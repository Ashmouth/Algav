import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
        return "";
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
    
    public boolean insert(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	
    	//Cas de base
    	if (word.equals(ptdata)) {
    		if (ptchilds.containsKey(endword) != true) {
    			PatriciaTrie endtree = new PatriciaTrie(endword);
        		ptchilds.put(endword, endtree);
    		}
    		return true;
    	}

    	//Cas récursif
    	if (word.startsWith(ptdata)) {
    		String suffix = word.substring(ptdata.length());
    		String first = suffix.substring(0, 1);
    		if (ptchilds.containsKey(first) != true) {
    			PatriciaTrie tmp = new PatriciaTrie(suffix);
        		ptchilds.put(first, tmp);
    		}
    		return insert(ptchilds.get(first), suffix);
    	}
    	
    	if (data.startsWith(word)) {
    		String suffix = ptdata.substring(word.length());
			PatriciaTrie tmp = new PatriciaTrie(suffix);
			cloneAll(ptree, tmp);
			
			ptchilds.put(suffix.substring(0, 1), tmp);
			ptree.setData(word);
    		return insert(ptree, word);
    	}
    	
    	//Cas racine de l'arbre
    	if (ptdata.equals(endword)) {
    		String first = word.substring(0, 1);
    		if (!ptchilds.containsKey(first)) {
    			PatriciaTrie tmp = new PatriciaTrie(word);
    			ptchilds.put(first, tmp);
    		}
    		return insert(ptchilds.get(first), word);
    	}
    	
    	String prefix = getPrefix(ptdata, word);
    	if (prefix.length() != 0) {
    		String suffix = ptdata.substring(prefix.length());
    		PatriciaTrie tmp = new PatriciaTrie(suffix);
    		cloneAll(ptree, tmp);
			
			ptchilds.put(suffix.substring(0, 1), tmp);
    		
    		ptree.setData(prefix);
    		return insert(ptree, word);
    	}
    	
    	System.out.println("Insert Fail with " +word+ " in "+ptdata);
    	return false;
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
    
    
    public void ArrayWord(PatriciaTrie ptree, String res, ArrayList<String> acc) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String ptdata = ptree.getData();
    	String tmp = res.concat(ptdata);
    	for (String key : ptchilds.keySet()) {
    		if(key == endword) {
    			acc.add(tmp);
    		}
            if(ptchilds.get(key) != null) {
            	ArrayWord(ptchilds.get(key), tmp, acc);
            }
        }
    }
    
    public ArrayList<String> ArrayWord(PatriciaTrie ptree) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	ArrayList<String> acc = new ArrayList<String>();
    	System.out.println(ptree.getData());
    	for (String key : ptchilds.keySet()) {
    		if(ptchilds.get(key) != null) {
    			ArrayWord(ptchilds.get(key), "", acc);
            }
    	}
    	return acc;
    }
    
    public void AllWord(PatriciaTrie ptree) {
    	ArrayList<String> acc = new ArrayList<String>();
    	acc = ArrayWord(ptree);
    	for(String s : acc) {
    		System.out.println(s);
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

		//Cas data et word on deux suffix différents
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
    
    public PatriciaTrie fusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {
    	PatriciaTrie nptree2 = copy(ptree2);
    	return subfusion(ptree1, nptree2);
    }
    
    
    public ArrayList<Integer> patDeep(PatriciaTrie ptree, int deep) {
    	ArrayList<Integer> acc = new ArrayList<Integer>();
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	
    	if(ptchilds.containsKey(endword)) {
    		acc.add(deep);
    	}
    	
    	for (String key : ptchilds.keySet()) {
    		ArrayList<Integer> tmp = new ArrayList<Integer>();
            tmp = patDeep(ptchilds.get(key), deep++);
            acc.addAll(tmp);
        }
    	
    	return acc;
    }
    
    public int mediumDeep(PatriciaTrie ptree) {
    	int res = 0;
    	ArrayList<Integer> tmp = patDeep(ptree, 0);
    	
    	for (int i : tmp) {
    		res += i;
    	}
    	res = res/(tmp.size()-1);
    	
    	return res;
    }
    
    public int subprefix(PatriciaTrie ptree, String word) {
    	String ptdata = ptree.getData();
    	
    	if (word.equals(ptdata)) {
    		return CountWord(ptree, 0);
    	} else {
    		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    		if (word.startsWith(ptdata)) {
    			String suffix = word.substring(ptdata.length(), word.length());
    			String first = suffix.substring(0, 1);
    			if (ptchilds.containsKey(first)) {
    				return subprefix(ptchilds.get(first), suffix);
    			}
    		}
    		return 0;
    	}
    }
    
    public int prefix(PatriciaTrie ptree, String word) {
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	String first = word.substring(0, 1);
    	
    	if(ptchilds.containsKey(first)) {
    		return subprefix(ptchilds.get(first), word);
    	}
    	return 0;
    }
    
    public NoeudTH subpatTohyb(PatriciaTrie ptree) {
    	NoeudTH acc = new NoeudTH();
    	NoeudTH node = acc;
    	NoeudTH last = node;
    	NoeudTH tmp;
    	String ptdata = ptree.getData();
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	
    	//prefix to letter
    	for (int i = 0; i < ptdata.length(); i++) {
    		String s = "" + ptdata.charAt(i);
    		if (i == 0) {
    			node.setLettre(s);
    		} else {
    			node.setFils(new NoeudTH(s, node, false));
    			tmp = node.getFils();
    			node = tmp;
    			last = node;
    		}
    	}
    	
    	//childs
    	for (String key : ptchilds.keySet()) {
    		if (key == endword) {
    			last.setFinMot(true);
    		}
    		node = subpatTohyb(ptchilds.get(key));
    		tmp = node.getFrereDroit();
    		node = tmp;
    	}
    	
    	
    	
    	//TODO Rééiquilibrage
    	
    	return acc;
    }
    	
//    first = second;
//    second = third;
    
    public NoeudTH patTohyb(PatriciaTrie ptree) {
    	NoeudTH acc = null;
    	NoeudTH node = acc;
    	NoeudTH tmp;
    	HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
    	
    	for (String key : ptchilds.keySet()) {
    		if (acc == null) {
    			acc = subpatTohyb(ptchilds.get(key));
    			acc.setFrereDroit(new NoeudTH());
    			node = acc.getFrereDroit();
    		} else {
    			node = subpatTohyb(ptchilds.get(key));
    			System.out.println(node.getLettre());
    			node.setFrereDroit(new NoeudTH());
    			tmp = node.getFrereDroit();			//Hack didn't work ><' 
    			node = tmp;	//Hack for pointer in java
    		}
    	}
    	System.out.println(acc.getLettre());
    	ArrayList<String> as = node.listeMots(node);
        for(String s : as) {
        	System.out.println(s);
        }
    	//TODO Rééiquilibrage
    	
    	return acc;
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
		int cw = pt1.CountWord(pt1);
		int cd = pt1.CountDeep(pt1);
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
        		fusiontime/3, cw, cd);
	    System.out.println(value);
    }

}
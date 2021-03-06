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

	//Permet de récupéré le prefix commun de deux mots
	public String findPrefix(String str1, String str2) {
		for (int i = str2.length(); i > 0; i--) {
			String tmp = str2.substring(0, i);
			if (str1.startsWith(tmp)) {
				return tmp;
			}
		}
		return "";
	}

	//Appel displayPtree pour afficher l'arbre
	public void printPtree(PatriciaTrie pt) {
		displayPtree(pt, 0);
	}

	//Afficher l'arbre
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

	//Recopie la hashmap du PT src dans la PT dst et la supprime
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


	//Sous fonction de la recherche, test si le mots est vide
	//Et si il y a le caractère de fin de mot sinon lance une recursion
	public boolean subsearch(PatriciaTrie ptree, String word) {

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

	//Lance une récursion sur chaque fils du noeud racine
	public boolean search(PatriciaTrie ptree, String word) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		String first = word.substring(0, 1);

		if (ptchilds.containsKey(first)) {
			return subsearch(ptchilds.get(first), word);
		}
		return false;
	}


	//Lance une récursion sur chaque fils du noeud racine
	public boolean delete(PatriciaTrie ptree, String word) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		String first = word.substring(0, 1);

		if (ptchilds.containsKey(first)) {
			return subdelete(ptchilds.get(first), word);
		}
		return false;
	}

	//Une fois le mot vidé, supprime le caractère de fin de mot
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

	//Parcours le Patricia-Tries pour y ajouté un mots
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

		//Cas recursif
		if (word.startsWith(ptdata)) {
			String suffix = word.substring(ptdata.length());
			String first = suffix.substring(0, 1);
			if (ptchilds.containsKey(first) != true) {
				PatriciaTrie tmp = new PatriciaTrie(suffix);
				ptchilds.put(first, tmp);
			}
			return insert(ptchilds.get(first), suffix);
		}

		if (ptdata.startsWith(word)) {
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

		String prefix = findPrefix(ptdata, word);
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

	//Parcours tout les Patricia tries et compte le nombre de mots
	public int countWord(PatriciaTrie ptree, int res) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		for (String key : ptchilds.keySet()) {
			if(key == endword) {
				res++;
			}
			if(ptchilds.get(key) != null) {
				res += countWord(ptchilds.get(key), 0);
			}
		}
		return res;
	}

	//Lance la fonction countWord avec les bon paramètre initial
	public int countWord(PatriciaTrie ptree) {
		return countWord(ptree, 0);
	}


	//Parcours tout les Patricia tries et compte leur profondeur
	public int countDeep(PatriciaTrie ptree, int size) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		int res = size;
		for (String key : ptchilds.keySet()) {
			int tmp = countDeep(ptchilds.get(key), size + 1);
			if (tmp > res) {
				res = tmp;
			}
		}
		return res;
	}

	//Lance la fonction countDeep avec les bon paramètre initial
	public int countDeep(PatriciaTrie ptree) {
		if (ptree.getData().equals(endword)) {
			return countDeep(ptree, 0);
		}
		return countDeep(ptree, 1);
	}

	//Parcours tout les Patricia tries et test si il existe le mot vide, si oui
	// ajoute le mot à l'accumulateur
	public void arrayWord(PatriciaTrie ptree, String res, ArrayList<String> acc) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		String ptdata = ptree.getData();
		String tmp = res.concat(ptdata);
		for (String key : ptchilds.keySet()) {
			if(key == endword) {
				acc.add(tmp);
			}
			arrayWord(ptchilds.get(key), tmp, acc);
		}
	}

	//Lance la fonction arrayWord avec les bon paramètre initial
	public ArrayList<String> arrayWord(PatriciaTrie ptree) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		ArrayList<String> acc = new ArrayList<String>();
		System.out.println(ptree.getData());
		for (String key : ptchilds.keySet()) {
			if(ptchilds.get(key) != null) {

			}
			arrayWord(ptchilds.get(key), "", acc);
		}
		return acc;
	}

	//Parcours tout les Patricia tries et test si il existe le mot vide, si oui
	// affiche le mot
	public void allWord(PatriciaTrie ptree, String res) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		String ptdata = ptree.getData();
		String tmp = res.concat(ptdata);
		for (String key : ptchilds.keySet()) {
			if(key == endword) {
				System.out.println(tmp);
			}
			allWord(ptchilds.get(key), tmp);
		}
	}

	//Lance la fonction allWord avec les bon paramètre initial
	public void allWord(PatriciaTrie ptree) {
		ArrayList<String> acc = new ArrayList<String>();
		acc = arrayWord(ptree);
		for(String s : acc) {
			System.out.println(s);
		}
	}

	//Renvoi une copie du Patricia trie passer en paramètre
	public PatriciaTrie copy(PatriciaTrie ptree) {
		PatriciaTrie nptree = 
				new PatriciaTrie(ptree.getChilds(), ptree.getData());
		for(String s : nptree.getChilds().keySet()){
			nptree.getChilds().put(s, copy(ptree.getChilds().get(s)));
		}
		return nptree;
	}

	//Sépare un PT par rapport à son prefix et le mot donner en argument
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

		//Cas data et word on deux suffix differents
		String prefix = findPrefix(ptdata, word);
		String suffixw = word.substring(prefix.length(), word.length());
		String suffixd = ptdata.substring(prefix.length(), ptdata.length());

		PatriciaTrie ptsufw = new PatriciaTrie(suffixw);
		PatriciaTrie ptsufd = new PatriciaTrie(suffixd);

		cloneAll(ptree, ptsufd);

		ptchilds.put(suffixd.substring(0, 1), ptsufd);
		ptchilds.put(suffixw.substring(0, 1), ptsufw);
		ptree.setData(prefix);
	}


	//Parcours les deux arbres et assemble les préfixes ainsi que les fils
	public PatriciaTrie subfusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {

		String ptdata1 = ptree1.getData();
		HashMap<String, PatriciaTrie> ptchilds1 = ptree1.getChilds();

		String ptdata2 = ptree2.getData();
		HashMap<String, PatriciaTrie> ptchilds2 = ptree2.getChilds();

		//Cas Initiale
		if(ptdata1.equals(ptdata2)) {
			//Cas Recurssif
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

	//Lance la fusion en sauvegardant le second Patricia-Tries
	public PatriciaTrie fusion(PatriciaTrie ptree1, PatriciaTrie ptree2) {
		PatriciaTrie nptree2 = copy(ptree2);
		return subfusion(ptree1, nptree2);
	}


	//Parcours le Patricia-Tries et stock tout les profondeur dans une Array-list
	public ArrayList<Integer> getDeep(PatriciaTrie ptree, int deep) {
		ArrayList<Integer> acc = new ArrayList<Integer>();
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();

		if(ptchilds.containsKey(endword)) {
			acc.add(deep);
		}

		for (String key : ptchilds.keySet()) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp = getDeep(ptchilds.get(key), deep++);
			acc.addAll(tmp);
		}

		return acc;
	}

	//Lance getDeep sur tout les fils et en fait une moyenne
	public int mediumDeep(PatriciaTrie ptree) {
		int res = 0;
		ArrayList<Integer> tmp = getDeep(ptree, 0);

		for (int i : tmp) {
			res += i;
		}
		res = res/(tmp.size()-1);

		return res;
	}

	//Test si le mot est égal au préfix si lance le comptage de mot sinon 
	//continu la recherche
	public int subGetPrefix(PatriciaTrie ptree, String word) {
		String ptdata = ptree.getData();

		if (word.equals(ptdata)) {
			return countWord(ptree, 0);
		} else {
			HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
			if (word.startsWith(ptdata)) {
				String suffix = word.substring(ptdata.length(), word.length());
				String first = suffix.substring(0, 1);
				if (ptchilds.containsKey(first)) {
					return subGetPrefix(ptchilds.get(first), suffix);
				}
			}
			return 0;
		}
	}

	//Lance une récursion sur chaque fils du noeud racine
	public int getPrefix(PatriciaTrie ptree, String word) {
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		String first = word.substring(0, 1);

		if(ptchilds.containsKey(first)) {
			return subGetPrefix(ptchilds.get(first), word);
		}
		return 0;
	}

	//Fonction perso qui prend un Hybrid-Tries et affiche tout les mots existent
	public void displayTH(NoeudTH tree, String acc){

		if(tree.isFinMot()){
			System.out.println(acc);
		}

		if(tree.getFils() != null){
			displayTH(tree.getFils(), acc.concat(tree.getLettre()));
		}
		if(tree.getFrereGauche() != null){
			displayTH(tree.getFrereGauche(), acc);
		}
		if(tree.getFrereDroit() != null){
			displayTH(tree.getFrereDroit(), acc);
		}
	}


	//Prend un noeud de Patricia-Tries et le converti en Hybrid-Tries
	public NoeudTH subConvert(PatriciaTrie ptree) {
		String ptdata = ptree.getData();
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();
		NoeudTH root = new NoeudTH();
		NoeudTH node = root;

		//prefix to letter
		for (int i = 0; i < ptdata.length(); i++) {
			String s = "" + ptdata.charAt(i);

			if (root.getLettre() == null) {	//Work
				root.setLettre(s);
			} else {	//Work
				NoeudTH tmp = new NoeudTH(s, node, false);
				node.setFils(tmp);
				node = tmp;
			}
		}

		//childs
		NoeudTH tmp = new NoeudTH();
		NoeudTH old_r = new NoeudTH();
		node.setFils(old_r);
		for (String key : ptchilds.keySet()) {
			if (key == endword) {
				tmp = new NoeudTH(endword, node, false);
				tmp.setFinMot(true);
				node.setFils(tmp);
			} else {
				tmp = subConvert(ptchilds.get(key));
				old_r.setFrereDroit(tmp);
				old_r = tmp;
			}
		}

		return root;
	}

	//Lance une récursion sur chaque fils du noeud racine
	public NoeudTH convert(PatriciaTrie ptree) {
		NoeudTH root = new NoeudTH();
		NoeudTH node = root;
		HashMap<String, PatriciaTrie> ptchilds = ptree.getChilds();

		for (String key : ptchilds.keySet()) {
			NoeudTH old_r = node;
			node = subConvert(ptchilds.get(key));
			old_r.setFrereDroit(node);
		}

		root = root.getFrereDroit();
		//    	root.reequilibrage2(root);
		//Rééquilibrage won't work for some reason :/

		return root;
	}

	//Benchmark parcours tout les fichiers du dossier et affiche leurs stats sur
	//la structure
	public void benchmark(File f) {

		String clean = new String(new char[80]).replace('\0', '_');
		System.out.println(clean);

		String mean = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
				+ " %6$-6s | %7$-6s | %8$-6s",
				"file", "build", "insert", "search", "delete", "fusion", 
				"nbword", "deep");
		System.out.println(mean);

		long tbtime = 0;
		long titime = 0;
		long tstime = 0;
		long tftime = 0;
		long dltime = 0;

		int tcw = 0;
		int tcd = 0;
		int tf = f.listFiles().length;

		for (final File fileEntry : f.listFiles()) {

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
					insert(pt1, line);
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
			int cw = countWord(pt1);
			tcw+=cw;
			int cd = countDeep(pt1);
			tcd+=cd;

			long build = total;
			tbtime+=build;

			startTime = System.nanoTime();
			insert(pt1, "arbre");
			insert(pt1, "arc");
			insert(pt1, "arbuste");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long insertime = duration;
			titime+=duration;

			startTime = System.nanoTime();
			search(pt1, "arbre");
			search(pt1, "arc");
			search(pt1, "arbuste");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long searchtime = duration;
			tstime+=duration;

			insert(pt2, "artiste");
			insert(pt2, "destin");
			insert(pt2, "magique");
			startTime = System.nanoTime();
			fusion(pt1, pt2);
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long fusiontime = duration;
			tftime+=duration;

			startTime = System.nanoTime();
			delete(pt1, "artiste");
			delete(pt1, "destin");
			delete(pt1, "magique");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long deletetime = duration;
			dltime+=duration;

			String value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
					+ " %6$-6s | %7$-6s | %8$-6s",
					fileEntry.getName(), build, insertime/3, searchtime/3, deletetime/3, 
					fusiontime/3, cw, cd);
			System.out.println(value);
		}

		System.out.println(clean);
		String value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
				+ " %6$-6s | %7$-6s | %8$-6s",
				"Moyen", tbtime/tf, (titime/3)/tf, (tstime/3)/tf, (dltime/3)/tf, 
				(tftime/3)/tf, tcw/tf, tcd/tf);
		System.out.println(value);

		value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
				+ " %6$-6s | %7$-6s | %8$-6s",
				"Total", tbtime, titime/3, tstime/3, dltime/3, 
				tftime/3, tcw, tcd);
		System.out.println(value);
		System.out.println(clean);
	}
}
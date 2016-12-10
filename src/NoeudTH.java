import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//OPERATION IF EQUALS

public class NoeudTH {	
	private String lettre = null;
	
	private boolean finMot = false;
	
	private NoeudTH fils = null;
	private NoeudTH frereDroit = null;
	private NoeudTH frereGauche = null;
	
	public NoeudTH(){// uniquement a la creation
		this.lettre=null;
	}
	
	public NoeudTH(String lettre, NoeudTH pere, boolean b){
		this.lettre=lettre;
		if (b) System.out.println("Ajout de lettre constructeur : "+ lettre);
	}
	
	
	//Getter and Setter
	public String getLettre() {
		return lettre;
	}

	public void setLettre(String lettre) {
		this.lettre = lettre;
	}

	public boolean isFinMot() {
		return finMot;
	}

	public void setFinMot(boolean finMot) {
		this.finMot = finMot;
	}

	public NoeudTH getFils() {
		return fils;
	}

	public void setFils(NoeudTH fils) {
		this.fils = fils;
	}

	public NoeudTH getFrereDroit() {
		return frereDroit;
	}

	public void setFrereDroit(NoeudTH frereDroit) {
		this.frereDroit = frereDroit;
	}

	public NoeudTH getFrereGauche() {
		return frereGauche;
	}

	public void setFrereGauche(NoeudTH frereGauche) {
		this.frereGauche = frereGauche;
	}
	
	//Primitives cle
	public String premLettre(String s){
		return s.substring(0, 1);
	}
	
	public String resteString(String s){
		return s.substring(1, s.length());
	}
	
	public String[] motPhrase(String s){
		return s.split(" |,");
	}
	
	public void ajouterPhrase(NoeudTH arbre, String phrase){
		
		String[] listeMot = motPhrase(phrase);
		for(int i=0; i<listeMot.length;i++){
			ajouterMot(arbre,listeMot[i]);
		}
	}
	
	//Pour la conversion d'un Patricia
	public void ajouterListe(NoeudTH arbre, String[] listeMot){
		for(int i=0; i<listeMot.length;i++){
			ajouterMot(arbre,listeMot[i]);
		}
	}

	//Ajoute un mot
	public void ajouterMot(NoeudTH arbre, String mot){
		
		if(mot==null){
			System.out.println("===================");
			return;
		}
		//Cle vide
		if(mot.equals("")){
			System.out.println("===================");
			return;
		}
		
		//Si 1er mot
		if(arbre.lettre==null){ 
			System.out.println("Nouvel arbre");
			
			System.out.println("Ajout de lettre : "+ premLettre(mot));
			arbre.lettre=premLettre(mot);
			
			//On test si c'est fini
			if(mot.length()==1){
				arbre.finMot=true;
				System.out.println("==================="); //fin
				return;
			}
			else{
				ajouterMot(arbre,mot);
			}
			
		}
		//Cas meme lettre
		if(premLettre(mot).equals(arbre.lettre)){
			//On test si c'est fini
			if(mot.length()==1){
				arbre.finMot=true;
				System.out.println("==================="); //fin
				return;
			}
			//Si pas de fils on en ajoute un
			if(arbre.fils==null){
				//Racine,lettreSuivante,pere
				System.out.print("En dessous de "+arbre.lettre+" : ");
				NoeudTH fi = new NoeudTH(premLettre(resteString(mot)),arbre,true);
				arbre.fils=fi;
			}

			ajouterMot(arbre.fils,resteString(mot));

		}
		//Cas lettre a ajoute plus petit (ajout a gauche)
		else if(premLettre(mot).compareTo(arbre.lettre)<0){
			//Si pas de frereGauche on en ajoute un
			if(arbre.frereGauche==null){
				//Racine,lettre,pere
				System.out.print("A Gauche de "+arbre.lettre+" : ");
				NoeudTH fg = new NoeudTH(premLettre(mot),arbre,true);
				arbre.frereGauche=fg;
			}
			
			ajouterMot(arbre.frereGauche,mot);
		}
		
		else if(premLettre(mot).compareTo(arbre.lettre)>0){
			//Si pas de frereDroit on en ajoute un
			if(arbre.frereDroit==null){
				//Racine,lettre,pere
				System.out.print("A Droite de "+arbre.lettre+" : ");
				NoeudTH fd = new NoeudTH(premLettre(mot),arbre,true);
				arbre.frereDroit=fd;
			}
			
			ajouterMot(arbre.frereDroit,mot);
		}
	}
	
	
	
	//Ajoute un mot (en silence)
		public void ajouterMotSilence(NoeudTH arbre, String mot){
			
			if(mot==null){
				return;
			}
			//Cle vide
			if(mot.equals("")){
				return;
			}
			
			//Si 1er mot
			if(arbre.lettre==null){ 
				arbre.lettre=premLettre(mot);
				
				//On test si c'est fini
				if(mot.length()==1){
					arbre.finMot=true;
					return;
				}
				else{
					ajouterMotSilence(arbre,mot);
				}
				
			}
			//Cas meme lettre
			if(premLettre(mot).equals(arbre.lettre)){
				//On test si c'est fini
				if(mot.length()==1){
					arbre.finMot=true;
					return;
				}
				//Si pas de fils on en ajoute un
				if(arbre.fils==null){
					//Racine,lettreSuivante,pere
					NoeudTH fi = new NoeudTH(premLettre(resteString(mot)),arbre,false);
					arbre.fils=fi;
				}

				ajouterMotSilence(arbre.fils,resteString(mot));

			}
			//Cas lettre a ajoute plus petit (ajout a gauche)
			else if(premLettre(mot).compareTo(arbre.lettre)<0){
				//Si pas de frereGauche on en ajoute un
				if(arbre.frereGauche==null){
					//Racine,lettre,pere
					NoeudTH fg = new NoeudTH(premLettre(mot),arbre,false);
					arbre.frereGauche=fg;
				}
				
				ajouterMotSilence(arbre.frereGauche,mot);
			}
			
			else if(premLettre(mot).compareTo(arbre.lettre)>0){
				//Si pas de frereDroit on en ajoute un
				if(arbre.frereDroit==null){
					//Racine,lettre,pere
					NoeudTH fd = new NoeudTH(premLettre(mot),arbre,false);
					arbre.frereDroit=fd;
				}
				
				ajouterMotSilence(arbre.frereDroit,mot);
			}
		}
	
	
	
	public boolean recherche(NoeudTH arbre, String motCherche){
		//Cle vide
		if(motCherche.equals("")){
			return false;
		}
		
		//Si arbre vide
		if(arbre.lettre==null){
			return false;
		}
		
		//Cas meme lettre
		if(premLettre(motCherche).equals(arbre.lettre)){
			//On test si derniere lettre du mot
			if(motCherche.length()==1){
				return arbre.finMot; //si c'est un mot existant
			}
			else{
				//Si pas de suite a l'arbre
				if(arbre.fils==null){
					return false;
				}
				else return recherche(arbre.fils,resteString(motCherche));
			}
		}
		
		//Cas lettre cherchee plus petite (a gauche)
		else if(premLettre(motCherche).compareTo(arbre.lettre)<0){
			//Si pas de frereGauche
			if(arbre.frereGauche==null){
				return false;
			}
			else return recherche(arbre.frereGauche,motCherche);
		}
		
		
		//Cas lettre cherchee plus petite (a droite)
		else{
			//Si pas de frereDroit
			if(arbre.frereDroit==null){
				return false;
			}
			else return recherche(arbre.frereDroit,motCherche);
		}
	}
	
	
	public int comptageMots(NoeudTH arbre){
		if(arbre==null) return 0;
		if(arbre.finMot) return 1 + comptageMots(arbre.frereGauche) + comptageMots(arbre.fils) + comptageMots(arbre.frereDroit);
		else return comptageMots(arbre.frereGauche) + comptageMots(arbre.fils) + comptageMots(arbre.frereDroit);
	}
	
	
	public ArrayList<String> listeMots(NoeudTH arbre){
		if(arbre.lettre==null) return new ArrayList<String>();
		else{
			ArrayList<String> res = new ArrayList<String>();
			return listeMots2(arbre,res,"");
		}
	}
	
	public ArrayList<String> listeMots2(NoeudTH arbre, ArrayList<String> liste, String strParcourue){
		String nouvelleStrParcourue = strParcourue.concat(arbre.lettre); //on ajoute la lettre actuelle
		if(arbre.finMot){
			liste.add(nouvelleStrParcourue);
		}
		
		if(arbre.fils!=null){
			listeMots2(arbre.fils,liste,nouvelleStrParcourue);
		}
		if(arbre.frereGauche!=null){
			listeMots2(arbre.frereGauche,liste,strParcourue);
		}
		if(arbre.frereDroit!=null){
			listeMots2(arbre.frereDroit,liste,strParcourue);
		}
		
		Collections.sort(liste);
		return liste;
	}
	
	
	public int comptageNil(NoeudTH arbre){
		if(arbre==null) return 1;
		return comptageNil(arbre.frereGauche) + comptageNil(arbre.fils) + comptageNil(arbre.frereDroit);
	}
	
	
	public int hauteur(NoeudTH arbre){
		return hauteur2(arbre)-1; //Car le 1er niveau est 0
	}
	
	private int hauteur2(NoeudTH arbre){
		if(arbre==null) return 0;
		return 1 + Math.max(hauteur2(arbre.frereGauche),Math.max(hauteur2(arbre.fils),hauteur2(arbre.frereDroit)));
	}
	
	
	public int profondeurMoyenne(NoeudTH arbre){
		if(arbre.lettre==null) return 0;
		else{
			ArrayList<Integer> res = new ArrayList<Integer>();
			ArrayList<Integer> profFeuilles = profondeurMoyenne2(arbre,res,0);
			double nbFeuilles = profFeuilles.size();
			double profondeurAdd = 0;
			for(int i = 0; i<nbFeuilles; i++){
				profondeurAdd = profondeurAdd + profFeuilles.get(i);
			}
			return (int)(Math.round(profondeurAdd/nbFeuilles));
		}
	}
	
	private ArrayList<Integer> profondeurMoyenne2(NoeudTH arbre, ArrayList<Integer> liste, int profParcourue){
		profParcourue++;
		if(arbre.fils==null && arbre.frereGauche==null && arbre.frereDroit==null){
			liste.add(profParcourue);
		}
	
		if(arbre.fils!=null){
			profondeurMoyenne2(arbre.fils,liste,profParcourue);
		}
		if(arbre.frereGauche!=null){
			profondeurMoyenne2(arbre.frereGauche,liste,profParcourue);
		}
		if(arbre.frereDroit!=null){
			profondeurMoyenne2(arbre.frereDroit,liste,profParcourue);
		}
		
		return liste;
	}
	
	//On se deplace au bon endroit puis on appelle comptage sur le fils
	public int prefixe(NoeudTH arbre, String motPrefix){
		if(arbre.lettre==null || motPrefix.equals("")) return 0;
		NoeudTH finprefix = deplacePrefixe(arbre,motPrefix);
		if(finprefix == null) return 0;
		else {
			if(finprefix.finMot) return 1 + comptageMots(finprefix.fils);
			else return comptageMots(finprefix.fils);
		}
	}
	
	private NoeudTH deplacePrefixe(NoeudTH arbre, String motPrefix){
		//Cas meme lettre
		if(premLettre(motPrefix).equals(arbre.lettre)){
			//On test si derniere lettre du mot
			if(motPrefix.length()==1){
				return arbre; //On a trouve le prefixe
			}
			else{
				//Si pas de suite a l'arbre
				if(arbre.fils==null){
					return null;
				}
				else return deplacePrefixe(arbre.fils,resteString(motPrefix));
			}
		}
		//Cas lettre cherchee plus petite (a gauche)
		else if(premLettre(motPrefix).compareTo(arbre.lettre)<0){
			//Si pas de frereGauche
			if(arbre.frereGauche==null){
				return null;
			}
			else return deplacePrefixe(arbre.frereGauche,motPrefix);
		}
		
		//Cas lettre cherchee plus petite (a gauche)
		else{
			//Si pas de frereDroit
			if(arbre.frereDroit==null){
				return null;
			}
			else return deplacePrefixe(arbre.frereDroit,motPrefix);
		}
	}
	
	
	public void suppression(NoeudTH arbre, String mot){
		if(arbre.lettre==null) return  ;
		else if (recherche(arbre,mot)==false) return ;
		
		else if(unSeulMot(arbre)){
			arbre = new NoeudTH();//On vide l'arbre
			return ;
		}
		else{
			suppression2(arbre, mot);
		}
	}
	
	private boolean unSeulMot(NoeudTH arbre){
		
		if(arbre.finMot==true) return false;
		if(arbre.frereGauche!=null) return false;
		else if(arbre.frereDroit!=null)return false;
		
		else if(arbre.fils==null)return true;
		
		else return unSeulMot(arbre.fils);
	}
	
	private void suppression2(NoeudTH arbre, String mot){
		
		//Cas meme lettre (cas a traite car on sait que le mot existe)
		if(premLettre(mot).equals(arbre.lettre)){
			//On test si derniere lettre du mot
			if(mot.length()==1){
				arbre.finMot=false;//On supprime le mot ici
			}
			else{
				//Si le fils est un seul mot c'est celui qu'on cherche
				if(unSeulMot(arbre.fils)){
					//Si on a un frere vide on donne sa place a l'autre frere
					if(arbre.frereDroit==null){
						arbre = arbre.frereGauche;
						return ;
					}
					else if(arbre.frereGauche==null){
						arbre = arbre.frereDroit;
						return ;
					}
					//Sinon on coupe juste les lettres vers le bas
					else{
						arbre.fils=null;
						return;
					}
				}
				//Juste un deplacement en bas
				else suppression2(arbre.fils,resteString(mot));
			}
		}
		
		//Juste un deplacement
		else if(premLettre(mot).compareTo(arbre.lettre)<0){
			suppression2(arbre.frereGauche,mot);
		}
		else if(premLettre(mot).compareTo(arbre.lettre)>0){
			suppression2(arbre.frereDroit,mot);
		}
		
	}
	
	
	
	
	public double log2(int n){
	    return (Math.log(n) / Math.log(2));
	}
	
	private int nbDeFrere(NoeudTH arbre){
		if(arbre==null) return 0;
		return 1 + nbDeFrere(arbre.frereGauche) + nbDeFrere(arbre.frereDroit);
	}
	
	private int profondeurMaxFrere(NoeudTH arbre){
		if(arbre==null) return 0;
		return 1 + Math.max(profondeurMaxFrere(arbre.frereGauche),profondeurMaxFrere(arbre.frereDroit));
	}
	
	private ArrayList<NoeudTH> listesFrereSorted(NoeudTH arbre){
		ArrayList<NoeudTH> res = new ArrayList<NoeudTH>();
		res = listesFrere2(arbre,res);
	
		
		//On casse tous les lien de frere pour les refaire ensuite
		for(int i = 0; i<res.size(); i++){
			res.get(i).frereGauche=null;
			res.get(i).frereDroit=null;
		}
		
		
		Collections.sort(res, (a, b) -> a.lettre.compareTo(b.lettre));
		
		
		System.out.print("La liste triee des noeuds : ");
		for(int i = 0; i<res.size(); i++){
			System.out.print("["+res.get(i).lettre+"] ");
		}
		System.out.println();
		
		return res;
	}
	
	private ArrayList<NoeudTH> listesFrere2(NoeudTH arbre, ArrayList<NoeudTH> liste){
		liste.add(arbre);
		if(arbre.frereGauche!=null){
			listesFrere2(arbre.frereGauche,liste);
		}
		if(arbre.frereDroit!=null){
			listesFrere2(arbre.frereDroit,liste);
		}
		return liste;
	}
	
	public NoeudTH reequilibrage(NoeudTH arbre){
		if(arbre==null) return arbre;
		//reequilibre a la racine
		arbre = reequilibrageFrere(arbre);
		
		System.out.println("----------------------------------");
		
		//besoin d'equilibrer que dans les fils
		if(arbre.fils!=null){
			System.out.println(arbre.lettre+" On prend le fils");
			arbre.fils= reequilibrageFrere(arbre.fils);
			reequilibrage2(arbre.fils);
		}
		
		//simple deplacement dans l'arbre
		if(arbre.frereGauche!=null){
			System.out.println();
			System.out.println(arbre.lettre+".frereGauche = "+arbre.frereGauche.lettre);
			System.out.println(arbre.frereGauche.lettre+" lettre actuelle");
			reequilibrage2(arbre.frereGauche);
		}
		if(arbre.frereDroit!=null){
			System.out.println();
			System.out.println(arbre.lettre+".frereDroit = "+arbre.frereDroit.lettre);
			System.out.println(arbre.frereDroit.lettre+" lettre actuelle");
			reequilibrage2(arbre.frereDroit);
		}
		return arbre;
	}
	
	public NoeudTH reequilibrage2(NoeudTH arbre){
		
		//besoin d'equilibrer que dans les fils
		if(arbre.fils!=null){
			arbre.fils= reequilibrageFrere(arbre.fils);
			reequilibrage2(arbre.fils);
		}
		
		//simple deplacement dans l'arbre
		if(arbre.frereGauche!=null){
			System.out.println();
			System.out.println(arbre.lettre+".frereGauche = "+arbre.frereGauche.lettre);
			System.out.println(arbre.frereGauche.lettre+" lettre actuelle");
			reequilibrage2(arbre.frereGauche);
		}
		if(arbre.frereDroit!=null){
			System.out.println();
			System.out.println(arbre.lettre+".frereDroit = "+arbre.frereDroit.lettre);
			System.out.println(arbre.frereDroit.lettre+" lettre actuelle");
			reequilibrage2(arbre.frereDroit);
		}
		return arbre;
	}
	
	private NoeudTH reequilibrageFrere(NoeudTH arbre){
		int nbFrere = nbDeFrere(arbre);
		int profondeurMaxFrere = profondeurMaxFrere(arbre)-1;//On demarre a 0
		
		System.out.print(arbre.lettre+" fait parti de "+nbFrere+" freres avec pour Profondeur Max "+profondeurMaxFrere);
		if(arbre.fils != null) System.out.println(" avec pour fils "+arbre.fils.lettre);
		else System.out.println();
		
		//Si la profondeur max des freres est superieure a la valeure absolue
		//Du log en base 2 du nb de frere (arbre non complet)
		
		if (profondeurMaxFrere>Math.abs(log2(nbFrere))){
			ArrayList<NoeudTH> listeNoeud = listesFrereSorted(arbre);
			return reformeFrere(listeNoeud);
		}
		
		return arbre;
	}
	
	private NoeudTH reformeFrere(ArrayList<NoeudTH> liste){
		int elemMilieu = Math.abs(liste.size()/2);
		
		System.out.println("Le milieu est "+liste.get(elemMilieu).lettre);
		
	    ArrayList<NoeudTH> inf = new ArrayList<NoeudTH>(liste.subList(0, elemMilieu));
	    ArrayList<NoeudTH> sup = new ArrayList<NoeudTH>(liste.subList(elemMilieu+1, liste.size()));
	     
	    if(inf.size()>0){
	    	int elemMilieuInf = Math.abs(inf.size()/2);
	    	System.out.println("Frere Gauche de "+liste.get(elemMilieu).lettre+" est "+ inf.get(elemMilieuInf).lettre);
	    	liste.get(elemMilieu).frereGauche = inf.get(elemMilieuInf);
	    }
	     
	    if(sup.size()>0){
	    	int elemMilieuSup = Math.abs(sup.size()/2);
	    	System.out.println("Frere Droit de "+liste.get(elemMilieu).lettre+" est "+ sup.get(elemMilieuSup).lettre);
	    	liste.get(elemMilieu).frereDroit = sup.get(elemMilieuSup);
	    }
	    
	    if(inf.size()>0){
	    	reformeFrere(inf);
	    }
	    if(sup.size()>0){
	    	reformeFrere(sup);
	    }
	    
	    return liste.get(elemMilieu);
	     
	}


	public void benchmark(File f) {
		BufferedReader br = null;

		long startTime, endTime, duration, total;
		long tbtime = 0;
		long titime = 0;
		long tstime = 0;
		long dltime = 0;
		int cw, cd;

		int tcw = 0;
		int tcd = 0;
		int tf = f.listFiles().length;

		String clean = new String(new char[80]).replace('\0', '_');
		System.out.println(clean);

		String mean = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s | %6$-6s | %7$-6s",
				"file", "build", "insert", "search", "delete", 
				"nbword", "deep");
		System.out.println(mean);

		for (final File fileEntry : f.listFiles()) {
			NoeudTH demo1 = new NoeudTH();
			total = 0;
			cw = 0;
			cd = 0;

			try {
				br = new BufferedReader(new FileReader(fileEntry));

				String line = br.readLine();

				while (line != null) {
					startTime = System.nanoTime();
					demo1.ajouterMotSilence(demo1, line);
					endTime = System.nanoTime();
					duration = (endTime - startTime);
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
			tbtime+=total;

			cw = demo1.comptageMots(demo1)-3;
			tcw+=cw;
			cd = demo1.hauteur(demo1);
			tcd+=cd;

			startTime = System.nanoTime();
			demo1.ajouterMotSilence(demo1, "arbre");
			demo1.ajouterMotSilence(demo1, "arc");
			demo1.ajouterMotSilence(demo1, "arbuste");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long insertime = duration;
			titime+=duration;

			startTime = System.nanoTime();
			demo1.recherche(demo1, "arbre");
			demo1.recherche(demo1, "arc");
			demo1.recherche(demo1, "arbuste");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long searchtime = duration;
			tstime+=duration;

			startTime = System.nanoTime();
			demo1.suppression(demo1, "artiste");
			demo1.suppression(demo1, "destin");
			demo1.suppression(demo1, "magique");
			endTime = System.nanoTime();
			duration = (endTime - startTime);
			long deletetime = duration;
			dltime+=duration;


			String value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s | %6$-6s | %7$-6s",
					fileEntry.getName(), build, insertime/3,  searchtime/3,  deletetime/3, cw, cd);
			System.out.println(value);
		}

		System.out.println(clean);
		String value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
				+ " %6$-6s | %7$-6s",
				"Moyen", tbtime/tf, (titime/3)/tf, (tstime/3)/tf, (dltime/3)/tf, 
				tcw/tf, tcd/tf);
		System.out.println(value);

		value = String.format("%1$-20s | %2$-10s | %3$-6s | %4$-6s | %5$-6s |"
				+ " %6$-6s | %7$-6s",
				"Total", tbtime, titime/3, tstime/3, dltime/3, 
				tcw, tcd);
		System.out.println(value);
		System.out.println(clean);
	}

}



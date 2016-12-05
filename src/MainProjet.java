import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MainProjet {

    private static Scanner scan;
	private static boolean endsoft;

	public static void main(String[] args) {
		
		String filePath = new File("").getAbsolutePath();
    	filePath = filePath+"/src/Shakespeare";
    	final File f = new File(filePath);
    	System.out.println(f);
		
        scan = new Scanner(System.in);
        PatriciaTrie pt1 = new PatriciaTrie("@");
        PatriciaTrie pt2 = new PatriciaTrie("@");
        
        NoeudTH Exemple = new NoeudTH();
        
        System.out.println("Shell de Test");
        String chaine = "A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, "//de 14
        		+ "un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier "//du 19
        		+ "de la machine a ecrire ?"; //de la a 3 = 36mots different
        chaine = chaine.replace(",", "");
        
        //chaine = chaine.toLowerCase();
        String[] tmp = chaine.split(" ");
        
        
		setEndsoft(false);
		
		afficheOpt();
		
		
		do {
			System.out.println();
			System.out.println("help. Afficher Options");
            
            String choice = scan.next();

			switch (choice) {
            
            	case "0" :
            		pt1.printPtree(pt1);
            		break;
            
                case "1" :
                    System.out.println("Entrez le mot a  inserer");
                    scan.nextLine(); //On vide
                    choice = scan.next();
                    System.out.println("Resultat : "+ pt1.insert(pt1, choice));
                    break;

                case "2" :
                    System.out.println("Entrez le mot recherche");
                    scan.nextLine(); //On vide
                    System.out.println("Resultat : "+ pt1.search(pt1, scan.next()));
                    break;

                case "3" :
                    System.out.println("Entrez le mot a  supprimer");
                    scan.nextLine(); //On vide
                    System.out.println("Resultat : "+ pt1.delete(pt1, scan.nextLine()));
                    break;

                case "4" :
                    System.out.println("Lancement de l'auto-test simple");
                    pt1.insert(pt1, "arbre");
                    pt1.insert(pt1, "arc");
                    pt1.insert(pt1, "arbuste");
                    pt2.insert(pt2, "artiste");
                    pt2.insert(pt2, "destin");
                    pt2.insert(pt2, "magique");
                    pt1.fusion(pt1, pt2);
                    pt1.displayPtree(pt1, 0);
                    System.out.println("Resultat : " + pt1.search(pt1, "arbre"));
                    pt1.AllWord(pt1);
                    break;
                    
                case "5" :
                    System.out.println("Question 1.3");
                    for(String s : tmp){
                    	pt1.insert(pt1, s);
                    }
                    System.out.println("Nombre de mots = " + pt1.CountWord(pt1));
                    System.out.println("Mots dans l'arbre = ");
                    pt1.AllWord(pt1);
                    break;
                    
                case "6" :
                	System.out.println("PatriciaTrie Benchmark (en nanoseconde)");
            		
            		String clean = new String(new char[80]).replace('\0', '_');
            		System.out.println(clean);
            		
            		String value = String.format("%1$-20s | %2$-8s | %3$-6s | %4$-6s | %5$-6s |"
                    		+ " %6$-6s | %7$-6s | %8$-6s",
                    		"file", "build", "insert", "search", "delete", "fusion", 
                    		"nbword", "deep");
            	    System.out.println(value);
            		    for (final File fileEntry : f.listFiles()) {
            		    	pt1.benchmark(fileEntry);
            		    }
            		    System.out.println(clean);
				
                    break;

                case "7" :
                	System.out.println("Lancement de l'auto-test avance");
                    pt1.insert(pt1, "arbre");
                    pt1.insert(pt1, "arc");
                    pt1.insert(pt1, "arbuste");
                    pt2.insert(pt2, "artiste");
                    pt2.insert(pt2, "destin");
                    pt2.insert(pt2, "magique");
                    pt1.fusion(pt1, pt2);
                    System.out.println("Medium deep is "+pt1.mediumDeep(pt1));
                    System.out.println("nb ar prefix is "+pt1.prefix(pt1, "ar"));
                    NoeudTH demo1 = pt1.patTohyb(pt1);
                    ArrayList<String> as = demo1.listeMots(demo1);
                    System.out.println("Pat to Hyb");
                    for(String s : as) {
                    	System.out.println(s);
                    }
                    break;
                    
                case "8" :
                	
            		insertOne(f.listFiles()[0]);
				
                    break;
                    
                    
                case "100" :
                	Exemple.ajouterPhrase(Exemple, chaine);
                	break;
                	
                case "101" :
                	System.out.println("Entrez le mot a  inserer");
                	scan.nextLine(); //On vide
                    Exemple.ajouterMot(Exemple, scan.nextLine());
                    System.out.println("Ajout termine");
                    break;
                    
                case "102" :
                	System.out.println("Entrez le mot recherche");
                	scan.nextLine(); //On vide
                    System.out.println("Resultat : "+ Exemple.recherche(Exemple,scan.nextLine()));
                    break;
                    
                case "103" :
                	System.out.println("Entrez le mot e supprimer");
                	scan.nextLine(); //On vide
                	Exemple.suppression(Exemple,scan.nextLine());
                	System.out.println("Supression terminee");
                    break;
                    
                case "104" :
                	System.out.println("Le nombres de mots est de : "+ Exemple.comptageMots(Exemple));
                	break;
                
                case "105" :
                	System.out.println(Exemple.listeMots(Exemple));
                	break;
                	
                case "106" :
                	System.out.println("Le nombres de pointeurs vers Nil est de : "+ Exemple.comptageNil(Exemple));
                	break;
                	
                case "107" :
                	System.out.println("La hauteur de l'arbre est de : "+ Exemple.hauteur(Exemple));
                	break;
                	
                case "108" :
                	System.out.println("La profondeur moyenne des feuilles de l'arbre est de : "+ Exemple.profondeurMoyenne(Exemple));
                	break;
                	
                case "109" :
                	System.out.println("Entrez le prefixe");
                	scan.nextLine(); //On vide
                    choice = scan.nextLine();
                	System.out.println(choice+ " est le prefixe de "+Exemple.prefixe(Exemple,choice)+" mots");
                	break;
                	
                case "110" :
                	ArrayList<String> aInser = Exemple.listeMots(Exemple);
                	
                	pt1 = new PatriciaTrie("@");
                	for(int i = 0; i<aInser.size(); i++){
                		pt1.insert(pt1, aInser.get(i));
                	}
                	System.out.println("Fin de la conversion");
                	break;
                	
                case "111" :
                	
                	System.out.println("Tries Hybrides Benchmark (en nanoseconde)");
            		System.out.println("_____________________________________________________");
            		String value2 = String.format("%1$-20s | %2$-8s | %3$-6s | %4$-6s | %5$-6s | %6$-6s | %7$-6s",
            				"file", "build", "insert", "search", "delete", 
                    		"nbword", "deep");
            	    System.out.println(value2);
            	    
            		    for (final File fileEntry : f.listFiles()) {
            		    	Exemple.benchmark(fileEntry);
            		    }
            		System.out.println("_____________________________________________________");
				
                    break;
                	
                case "112" :
                	break;
                	
                	
                case "help" :
                	afficheOpt();
                	break;
                    
                case "exit" :
                    setEndsoft(true);
                    break;
                    
			default :
                    System.out.println("Echec veuillez entrer un choix valide\n ");
                    break;
            }
            
        } while (!isEndsoft());
    }

	public static boolean isEndsoft() {
		return endsoft;
	}

	public static boolean setEndsoft(boolean endsoft) {
		MainProjet.endsoft = endsoft;
		return endsoft;
	}
	
	public static void afficheOpt(){
		System.out.println();
        System.out.println("Options :");
        System.out.println("exit. Pour quitter le shell");
        System.out.println("0. Patricia : Afficher l'arbre");
        System.out.println("1. Patricia : Inserer mot");
        System.out.println("2. Patricia : Chercher mot");
        System.out.println("3. Patricia : Supprimer mot");
        System.out.println("4. Patricia : Auto-test Fusion");
        System.out.println("5. Patricia : Exemple de base");
        System.out.println("6. Patricia : Benchmark");
        System.out.println("7. Patricia : Auto-test Convertion");
//        System.out.println("8. Patricia : Display lll.txt");	//Work use for test
        System.out.println();
        
        System.out.println("100. Hybrides : Inserer l'exemple de base");
        System.out.println("101. Hybrides : Inserer mot");
        System.out.println("102. Hybrides : Chercher mot");
        System.out.println("103. Hybrides : Supprimer mot");
        System.out.println("104. Hybrides : Compter nombre de mot");
        System.out.println("105. Hybrides : Liste triee des mots");
        System.out.println("106. Hybrides : Compter nombre de pointeurs Nil");
        System.out.println("107. Hybrides : Hauteur de l'arbre");
        System.out.println("108. Hybrides : Profondeur moyenne des feuilles de l'arbre");
        System.out.println("109. Hybrides : Nombre de mot qui contiennent un prefixe");
        System.out.println("110. Hybrides : Conversion Hybrides => Patricia");
        System.out.println("111. Hybrides : Benchmark");
        System.out.println("112. Hybrides : Reequilibrage");
	}
	
	public static void insertOne(File fileEntry) {
    	BufferedReader br = null;
    	PatriciaTrie pt1 = new PatriciaTrie("@");
    	NoeudTH demo1 = new NoeudTH();
    	try {
			br = new BufferedReader(new FileReader(fileEntry));
		
        	String line = br.readLine();

            while (line != null) {
                pt1.insert(pt1, line);
                demo1.ajouterMotSilence(demo1, line);
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
    	System.out.println("PatriciaTrie\n");
    	pt1.AllWord(pt1);

		System.out.println("\n\n\nHybrideTrie\n");
    	ArrayList<String> tmp = demo1.listeMots(demo1);
    	for (String s : tmp) {
    		System.out.println(s);
    	}
    }
	
}

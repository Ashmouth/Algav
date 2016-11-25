package PatriciaTrie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class PatriciaTrieTest {

    private static Scanner scan;
	private static boolean endsoft;

	public static void main(String[] args) {
        scan = new Scanner(System.in);
        PatriciaTrie pt1 = new PatriciaTrie("@");
        PatriciaTrie pt2 = new PatriciaTrie("@");
        
        System.out.println("Patricia Trie Test\n");
        String chaine = 
        		"A quel genial professeur de dactylographie sommes nous redevables de la superbe phrase ci dessous, un modele du genre, que toute dactylo connait par coeur puisque elle fait appel a chacune des touches du clavier de la machine a ecrire ?";
        chaine = chaine.replace(",", "");
        
        chaine = chaine.toLowerCase();
        String[] tmp = chaine.split(" ");
        long startTime, endTime, duration, total;
        
		setEndsoft(false);
		
		do {
            System.out.println("\nPatricia Trie Operations\n");
            System.out.println("n. pour arreter");
            System.out.println("0. Afficher l'arbre");
            System.out.println("1. inserer");
            System.out.println("2. chercher");
            System.out.println("3. supprimer");
            System.out.println("4. auto-test");
            System.out.println("5. Question 1.3)");
            String choice = scan.next();

            switch (choice) {
            
            case "0" :
            	pt1.printPtree(pt1);
                break;
            
                case "1" :
                    System.out.println("Entrée l'élément à inserer");
                    choice = scan.next();
                    System.out.println("Résultat : "+ pt1.insert(pt1, choice));
                    break;

                case "2" :
                    System.out.println("Entrée le mot recherché");
                    System.out.println("Résultat : "+ pt1.search(pt1, scan.next()));
                    break;

                case "3" :
                    System.out.println("Entrée le mot à supprimé");
                    System.out.println("Résultat : "+ pt1.delete(pt1, scan.nextLine()));
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
                    pt1.printPtree(pt1);
                    System.out.println("Résultat : " + pt1.search(pt1, "arbre"));
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
				BufferedReader br;
				total = 0;
				System.out.println("PatriciaTrie Benchmark");
				System.out.println("_____________________________________________________");
				try {
					br = new BufferedReader(new FileReader("./Shakespeare/1henryiv.txt"));
				
                	String line = br.readLine();

                    while (line != null) {
                    	startTime = System.nanoTime();
                        pt1.insert(pt1, line);
                        endTime = System.nanoTime();
                        duration = (endTime - startTime);
                        System.out.println(line + " insert in " + duration);
                        total+=duration;
                        line = br.readLine();
                    }
                    
				} finally {
					br.close();
				}
				System.out.println("total time = " + total);
				System.out.println("_____________________________________________________");
				pt1.AllWord(pt1);
                    break;
                    
                case "n" :
                    setEndsoft(true);
                    break;
                    
			default :
                    System.out.println("Echec veuillez entrée un choix valide\n ");
                    break;
            }
            
        } while (!isEndsoft());
    }

	public static boolean isEndsoft() {
		return endsoft;
	}

	public static boolean setEndsoft(boolean endsoft) {
		PatriciaTrieTest.endsoft = endsoft;
		return endsoft;
	}
}

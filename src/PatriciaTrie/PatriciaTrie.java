package PatriciaTrie;
import java.util.Deque;

class PatriciaTrie {
    private String data;
    private Deque<PatriciaTrie> sous_arbres;

    public PatriciaTrie() {
        data = null;
        sous_arbres = null;
    }

    public PatriciaTrie(String mot) {
        data = mot;
        sous_arbres = null;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrefixe(String str1, String str2){
        for (int i = 0; i < str2.length(); i++) {
            String tmp = str2.substring(0, i);
            if (str1.contains(tmp)) {
                return tmp;
            }
        }
        return null;
    }

    public Deque<PatriciaTrie> getSubTree() {
        return sous_arbres;
    }

    public void addAllSuf(String suffix) {
        for(PatriciaTrie sous_arbre : this.getSubTree()) {
            String gdata = sous_arbre.getData();
            sous_arbre.setData(suffix.concat(gdata));
        }
    }

    public boolean rechercher(PatriciaTrie arbre, String mot) {
        String prefixe = sous_arbres.getFirst().getData();
        if ((mot == null) && (prefixe == " ")) {
            return true;
        } else if ((mot == null) || (prefixe == " ")) {
            return false;
        } else {
            for (PatriciaTrie sous_arbre : sous_arbres) {
                prefixe = sous_arbre.getData();
                if (mot.contains(prefixe)) {
                    return rechercher(sous_arbre,
                            mot.substring(prefixe.length()));
                }
            }
            return false;
        }
    }

    public PatriciaTrie inserer(PatriciaTrie arbre, String mot) {
        String prefixe = sous_arbres.getFirst().getData();
        if ((mot == null) && (prefixe == " ")) {
            return arbre;
        } else if ((mot == null) && (prefixe != " ")) {
            PatriciaTrie pt = new PatriciaTrie(" ");
            sous_arbres.addFirst(pt);
            return arbre;
        } else {
            for (PatriciaTrie sous_arbre : sous_arbres) {
                prefixe = sous_arbre.getData();
                if (mot.contains(prefixe)) {
                    return inserer(sous_arbre,
                            mot.substring(prefixe.length()));
                } else {
                    String prf = getPrefixe(mot, prefixe);
                    if (prf != null){
                        int len = prefixe.length();
                        String racine = prefixe.substring(0, len - prf.length());
                        String suffix = prefixe.substring(len - prf.length(), len);
                        sous_arbre.addAllSuf(suffix);
                        sous_arbre.setData(racine);
                        return inserer(sous_arbre,
                                mot.substring(prefixe.length()));
                    }
                }
            }
            PatriciaTrie pt = new PatriciaTrie(mot);
            sous_arbres.addLast(pt);
            return arbre;
        }
    }

    public PatriciaTrie supprimer(PatriciaTrie arbre, String mot) {
        String prefixe = sous_arbres.getFirst().getData();
        if ((mot == null) && (prefixe == " ")) {
            sous_arbres.removeFirst();
            return arbre;
        } else if ((mot == null) && (prefixe != " ")) {
            return arbre;
        } else {
            for (PatriciaTrie sous_arbre : sous_arbres) {
                prefixe = sous_arbre.getData();
                if (mot.contains(prefixe)) {
                    return supprimer(sous_arbre,
                            mot.substring(prefixe.length()));
                } else {
                    String prf = getPrefixe(mot, prefixe);
                    if (prf != null){
                        int len = prefixe.length();
                        String racine = prefixe.substring(0, len - prf.length());
                        String suffix = prefixe.substring(len - prf.length(), len);
                        sous_arbre.addAllSuf(suffix);
                        sous_arbre.setData(racine);
                        return supprimer(sous_arbre,
                                mot.substring(prefixe.length()));
                    }
                }
            }
            return arbre;
        }
    }
}

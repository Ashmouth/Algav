package PatriciaTrie;
import java.util.List;
import java.nio.charset.Charset;

class PatriciaTrie {
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static byte chars[];
    private byte finmot = (byte)20;
    private PatriciaTrie sous_arbre[];
    private List<String> prefixes;
    private List<PatriciaTrie> prefixes_arbre;

    public PatriciaTrie() {
        int i;
        sous_arbre = new PatriciaTrie[128];
        for(i = 0; i < 128; i++) {
            sous_arbre[i] = null;
        }

        prefixes = null;
        prefixes_arbre = null;
    }

    public void initchars() {
        int i;
        chars = new byte[128];
        for(i = 0; i < 128; i++) {
            chars[i] = (byte)i;
        }
    }

    String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET);
    }

    public boolean rechercher(PatriciaTrie arbre, String mot) {
        if ((mot == null) && (sous_arbre[finmot] != null)) {
            return true;
        } else if ((mot == null) || (sous_arbre[finmot] != null)) {
            return false;
        } else {
            int i = 0;
            for (String prefixe : prefixes) {
                if (mot.contains(prefixe)) {
                    return rechercher(prefixes_arbre.get(i),
                            mot.substring(prefixe.length()));
                }
                i++;
            }
            return false;
        }
    }
}

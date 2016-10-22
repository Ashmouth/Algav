package PatriciaTrie;
import java.util.List;
import java.nio.charset.Charset;

class PatriciaTrie {
    private final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private static byte chars[];
    private PatriciaTrie sousarbre[];
    private List<String> prefixes;

    public PatriciaTrie() {
        int i;
        sousarbre = new PatriciaTrie[128];
        for(i = 0; i < 128; i++) {
            sousarbre[i] = null;
        }

        prefixes = null;
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


}

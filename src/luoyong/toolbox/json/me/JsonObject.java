package luoyong.toolbox.json.me;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonObject {

    private Hashtable hash = new Hashtable();

    public JsonObject(Hashtable hash) {
        this.hash = hash;
    }

    public void put(String key, Object value) {
        hash.put(key, value);
    }

    public Object get(String key) {
        return hash.get(key);
    }

    public Enumeration getKeys() {
        return hash.keys();
    }

    public int getSize() {
        return hash.size();
    }
}

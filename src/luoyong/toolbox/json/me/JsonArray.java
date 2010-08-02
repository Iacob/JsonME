package luoyong.toolbox.json.me;

import java.util.Vector;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonArray {

    private Vector array = new Vector();

    public JsonArray(Vector array) {
        this.array = array;
    }

    public void add(Object ojbect) {
        array.add(ojbect);
    }

    public int getSize() {
        return array.size();
    }

    public Object get(int index) {
        return array.get(index);
    }
}

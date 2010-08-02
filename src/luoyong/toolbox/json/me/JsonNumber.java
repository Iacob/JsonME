package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonNumber {

    private String textString = null;
    private Object number = null;

    public JsonNumber(String textString, Object number) {
        this.textString = textString;
        this.number = number;
    }

    public Object getNumber() {
        return number;
    }

    public void setNumber(Object number) {
        this.number = number;
    }

    public String getTextString() {
        return textString;
    }

    public void setTextString(String textString) {
        this.textString = textString;
    }
}

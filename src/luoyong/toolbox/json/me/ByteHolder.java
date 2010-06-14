package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public interface ByteHolder {

   

   public byte getNextByte() throws EOFException;

   /**
    * Begin cache next bytes that not read.
    */
   public void beginCache();

   /**
    * Cache the byte currently read and begin cache next bytes.
    */
   public void beginCacheAndCacheCurrentByte();

   /**
    * Stop cache bytes.
    */
   public void endCache();

   /**
    * Get cached bytes.
    * @return cached bytes
    */
   public byte[] getCachedBytes();

   /**
    * Get cached bytes in form string.
    * @param charset
    * @return
    */
   public String getCachedBytesAsString(String charset);

   /**
    * Get cached bytes in String form without last character removed.
    * @param charset
    * @return
    */
   public String getCachedBytesAsStringWithoutTrailing(String charset);
}
package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonParser {

   private static class NumberParser {
      public static void matchRestNumberText(ByteHolder byteHolder,
              StringBuffer stringCache)
              throws JsonSyntaxException, EOFException {

         byteHolder.beginCacheAndCacheCurrentByte();
         byte firstByte = byteHolder.getCurrentByte();
         byte currentByte = 0;
         if (firstByte == '-') {
            currentByte = byteHolder.getNextByte();
            if (!JsonParser.isDigit(currentByte)) {
               throw new JsonSyntaxException("Invalid number format.");
            }
            for (;;) {
               currentByte = byteHolder.getNextByte();
               if (JsonParser.isDigit(currentByte)) {
                  continue;
               }else if (currentByte == '.') {
                  // TODO match fractional part.
               }else if (JsonParser.isWhiteSpace(currentByte)
                       || (currentByte == '}')
                       || (currentByte == ']')
                       || (currentByte == ',')) {

                  byteHolder.endCache();
                  stringCache.append(byteHolder
                          .getCachedBytesAsStringWithoutTrailing("iso-8859-1"));
                  return;
               }else {
                  throw new JsonSyntaxException("Invalid number format.");
               }
            }
         }else if (JsonParser.isDigit(currentByte)) {
            // TODO
         }else {
            throw new JsonSyntaxException("Invalid number format.");
         }
      }
   }

   public static boolean isDigit(byte charValue) {
      if ((charValue >= '0') && (charValue <= '9')) {
         return true;
      }else {
         return false;
      }
   }

   public static boolean isWhiteSpace(byte charValue) {
      switch (charValue) {
         case (byte)' ': return true;
         case (byte)'\t': return true;
         case (byte)'\n': return true;
         case (byte)'\r': return true;
         default: return false;
      }
   }
}

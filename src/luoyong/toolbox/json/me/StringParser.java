package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class StringParser {

   private static final String DEFAULT_CHARSET = "UTF-8";

   public static void parseString(
           ByteHolder byteHolder, StringBuffer stringCache)
           throws JsonSyntaxException, EOFException {

         byteHolder.beginCache();
         
         for (;;) {
            byte currentByte = byteHolder.getNextByte();

            if (currentByte == ((byte)'\\')) {
               byteHolder.endCache();
               stringCache.append(byteHolder
                       .getCachedBytesAsStringWithoutTrailing(
                       DEFAULT_CHARSET));
               // Match escaped character then restart cache.
               matchEscapedCharacter(byteHolder, stringCache);
               // Restart the byte cache after the escaped character is handled.
               byteHolder.beginCache();
            }else if (currentByte == ((byte)'\"')) {

               byteHolder.endCache();
               stringCache.append(byteHolder
                       .getCachedBytesAsStringWithoutTrailing(
                       DEFAULT_CHARSET));
               break;
            }
         }
   }

   private static void matchEscapedCharacter(ByteHolder byteHolder,
           StringBuffer stringCache) throws JsonSyntaxException, EOFException {
      
      byte currentByte = byteHolder.getNextByte();

      if (currentByte == (byte)'"') {
         stringCache.append('"');
      }else if (currentByte == (byte)'\\') {
         stringCache.append('\\');
      }else if (currentByte == (byte)'/') {
         stringCache.append('/');
      }else if (currentByte == (byte)'b') {
         stringCache.append('\b');
      }else if (currentByte == (byte)'f') {
         stringCache.append('\f');
      }else if (currentByte == (byte)'n') {
         stringCache.append('\n');
      }else if (currentByte == (byte)'r') {
         stringCache.append('\r');
      }else if (currentByte == (byte)'t') {
         stringCache.append('\t');
      }else if (currentByte == (byte)'u') {

         short unicodeValue = 0;

         // Convert next 4 hex digits to unicode value.

         currentByte = byteHolder.getNextByte();
         if (isValidHexCharValue(currentByte)) {
            unicodeValue += (short)(charValueToHexValue(currentByte)
                    * (16 * 16 * 16));
         }else {
            throw new JsonSyntaxException("Invalid unicode value.");
         }

         currentByte = byteHolder.getNextByte();
         if (isValidHexCharValue(currentByte)) {
            unicodeValue += (short)(charValueToHexValue(currentByte)
                    * (16 * 16));
         }else {
            throw new JsonSyntaxException("Invalid unicode value.");
         }

         currentByte = byteHolder.getNextByte();
         if (isValidHexCharValue(currentByte)) {
            unicodeValue += (short)(charValueToHexValue(currentByte) * 16);
         }else {
            throw new JsonSyntaxException("Invalid unicode value.");
         }

         currentByte = byteHolder.getNextByte();
         if (isValidHexCharValue(currentByte)) {
            unicodeValue += (short)charValueToHexValue(currentByte);
         }else {
            throw new JsonSyntaxException("Invalid unicode value.");
         }

         stringCache.append((char)unicodeValue);
      }
   }

   private static boolean isValidHexCharValue(byte charValue) {
      if (((charValue >= '0') && (charValue <= '9'))
              || ((charValue >= 'A') && (charValue <= 'F'))
              || ((charValue >= 'a') && (charValue <= 'f'))
              ) {
         return true;
      }else {
         return false;
      }
   }

   private static byte charValueToHexValue(byte charValue) {
      switch (charValue) {
         case (byte)'0': return 0;
         case (byte)'1': return 1;
         case (byte)'2': return 2;
         case (byte)'3': return 3;
         case (byte)'4': return 4;
         case (byte)'5': return 5;
         case (byte)'6': return 6;
         case (byte)'7': return 7;
         case (byte)'8': return 8;
         case (byte)'9': return 9;
         case (byte)'A': return 10;
         case (byte)'B': return 11;
         case (byte)'C': return 12;
         case (byte)'D': return 13;
         case (byte)'E': return 14;
         case (byte)'F': return 15;
         case (byte)'a': return 10;
         case (byte)'b': return 11;
         case (byte)'c': return 12;
         case (byte)'d': return 13;
         case (byte)'e': return 14;
         case (byte)'f': return 15;
         default: return 0;
      }
   }
}

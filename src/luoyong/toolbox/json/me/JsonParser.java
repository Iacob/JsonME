package luoyong.toolbox.json.me;

/**
 *
 * @author Luo Yong &lt; luo.yong.name@gmail.com &gt;
 */
public class JsonParser {

   public static class NumberParser {
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
                  matchFractionalAndScientificPart(byteHolder, stringCache);
                  return;
               }else if (isNumberEndByte(currentByte)) {

                  byteHolder.endCache();
                  stringCache.append(byteHolder
                          .getCachedBytesAsStringWithoutTrailing("iso-8859-1"));
                  return;
               }else {
                  throw new JsonSyntaxException("Invalid number format.");
               }
            }
         }else if (JsonParser.isDigit(currentByte)) {
            for (;;) {
               currentByte = byteHolder.getNextByte();
               if (JsonParser.isDigit(currentByte)) {
                  continue;
               } else if (currentByte == '.') {
                  matchFractionalAndScientificPart(byteHolder, stringCache);
                  return;
               } else if (isNumberEndByte(currentByte)) {

                  byteHolder.endCache();
                  stringCache.append(byteHolder
                          .getCachedBytesAsStringWithoutTrailing("iso-8859-1"));
                  return;
               } else {
                  throw new JsonSyntaxException("Invalid number format.");
               }
            }
         }else {
            throw new JsonSyntaxException("Invalid number format.");
         }
      }

      private static void matchFractionalAndScientificPart(ByteHolder byteHolder,
              StringBuffer stringCache)
              throws JsonSyntaxException, EOFException {

         byte currentByte = byteHolder.getNextByte();

         if (!JsonParser.isDigit(currentByte)) {
            throw new JsonSyntaxException("Invalid number format.");
         }

         for (;;) {

            currentByte = byteHolder.getNextByte();

            if (JsonParser.isDigit(currentByte)) {
               continue;
            }else if (isNumberEndByte(currentByte)) {

               byteHolder.endCache();
               stringCache.append(byteHolder
                       .getCachedBytesAsStringWithoutTrailing("iso-8859-1"));
               return;
            }else if ((currentByte == 'e') || (currentByte == 'E')) {
               currentByte = byteHolder.getNextByte();
               if ((currentByte == '+') || (currentByte == '-')) {
                  for (;;) {
                     currentByte = byteHolder.getNextByte();
                     if (!JsonParser.isDigit(currentByte)) {
                        
                     }else if (isNumberEndByte(currentByte)) {
                        byteHolder.endCache();
                        stringCache.append(byteHolder
                                .getCachedBytesAsStringWithoutTrailing(
                                "iso-8859-1"));
                        return;
                     }else {
                        throw new JsonSyntaxException("Invalid number format.");
                     }
                  }
               }else if (JsonParser.isDigit(currentByte)) {
                  for (;;) {
                     currentByte = byteHolder.getNextByte();
                     if (!JsonParser.isDigit(currentByte)) {
                     } else if (isNumberEndByte(currentByte)) {
                        byteHolder.endCache();
                        stringCache.append(byteHolder
                                .getCachedBytesAsStringWithoutTrailing(
                                "iso-8859-1"));
                        return;
                     } else {
                        throw new JsonSyntaxException("Invalid number format.");
                     }
                  }
               }else {
                  throw new JsonSyntaxException("Invalid number format.");
               }
            }else {
               throw new JsonSyntaxException("Invalid number format.");
            }
         }
      }

      /**
       * Examine if the byte is the end of a Json number.
       * @param charValue the byte to examine
       * @return if the byte is the end of a Json number
       */
      private static boolean isNumberEndByte(byte charValue) {
         if (JsonParser.isWhiteSpace(charValue)
                 || (charValue == '}')
                 || (charValue == ']')
                 || (charValue == ',')) {
            return true;
         } else {
            return false;
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

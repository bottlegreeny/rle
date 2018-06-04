/*
Run-length encoding (RLE) is a very simple form of lossless data compression in which runs of data (that is, sequences in which the same data value occurs in many consecutive data elements) are stored as a single data value and count, rather than as the original run. This is most useful on data that contains many such runs. Consider, for example, simple graphic images such as icons, line drawings, and animations. It is not useful with files that don't have many runs as it could greatly increase the file size.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Encode {

    public static String encode(String source) {
        StringBuffer dest = new StringBuffer();
        for (int i = 0; i < source.length(); i++) {
            int runLength = 1;

            while (i + 1 < source.length() &&  source.charAt(i) == source.charAt(i + 1)) {


                runLength++;
                i++;
            }
            if (runLength > 9) {

                int r = runLength - 9;
                dest.append(source.charAt(i)).append(9).append(source.charAt(i)).append(runLength - 9);

            }
            if (runLength < 10) {
                dest.append(source.charAt(i)).append(1).append(runLength);
            }

        }

        return dest.toString();
    }

    public static String decode(String source) {
        StringBuffer dest = new StringBuffer();
        Pattern pattern = Pattern.compile("[0-9]+|[a-zA-Z]");
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group());
            matcher.find();
            while (number-- != 0) {

                dest.append(matcher.group());
            }
        }

        return dest.toString();
    }

    public static void main(String[] args) {
        String example = "11d6g7a";

        System.out.println(decode(example));
        System.out.println(encode(decode(example)));

    }

}

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
        String example = "11d6g7u";

        System.out.println(decode(example));
        System.out.println(encode(decode(example)));

        
    }

}

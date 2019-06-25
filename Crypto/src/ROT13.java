import java.io.*;
import java.util.Scanner;

public class ROT13 {
    Character cs;
    Character cf;
    Integer amountToRotate;



    ROT13(Character cs, Character cf) {
        this.cs = cs;
        this.cf = cf;
        this.amountToRotate = cf.charValue() - cs.charValue();
    }

    ROT13() {
        amountToRotate = 13;
    }


    public String crypt(String text) throws UnsupportedOperationException {
        return encrypt(text);
    }

    public String encrypt(String text) {
        String returnString = "";

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c >= 'a' && c <= 'm') c += amountToRotate;
            else if (c >= 'A' && c <= 'M') c += amountToRotate;
            else if (c >= 'n' && c <= 'z') c -= amountToRotate;
            else if (c >= 'N' && c <= 'Z') c -= amountToRotate;
            returnString += c;
        }
        return returnString;
    }

    public String decrypt(String text) {
        return encrypt(text);

    }

    public String rotate(String s, Character c) {
        Character toThis = Character.toLowerCase(c);
        Character a = 'a';
        Integer toMove =  toThis.charValue() - a.charValue();

        final int length = s.length();
        if( length == 0 ) return "";
        final int offset = ((toMove % length) + length) % length;
        return s.substring( offset, length ) + s.substring( 0, offset );
    }


    public String readfile (String fileInPath){
        Scanner in = null;
        try {
            in = new Scanner(new FileReader(fileInPath));
        } catch (Exception e){}
        StringBuilder sb = new StringBuilder();
        while(in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
       return  sb.toString();
    }


    public void writefile (String fileOutPath , String encryptedSting){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fileOutPath), "utf-8"))) {
            writer.write(encryptedSting);
        } catch (Exception e) {

        }
    }


        public void encriptFile(String fileIn, String fileOut) {
         String toWrite = readfile(fileIn);
         writefile(fileOut, encrypt(toWrite));
        }





}

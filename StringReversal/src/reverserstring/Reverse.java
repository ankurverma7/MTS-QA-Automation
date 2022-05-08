package reverserstring;

public class Reverse {

	public static boolean alphaNumericCheck(char ch) {
		  if ((ch >= 48 && ch <= 57) // Numeric 0 to 9
		    || (ch >= 65 && ch <= 90) // Alphabet A to Z (caps)
		    || (ch >= 97 && ch <= 122)) // Alphabet a to z
		   return true;
		  else
		   return false;

		 }
	
    public static String reverse(String input)
    {
        String[] words = input.split("\\s+");
        String last_str = "";

        for(int j=0;j<words.length;j++){
            char[] str = words[j].toCharArray();
            int r = str.length - 1, l = 0;

            // Traverse string from both ends until
            // 'l' and 'r'
            while (l < r)
            {
                // Ignore special characters
                if (!alphaNumericCheck(str[l]))
                    l++;
                else if(!alphaNumericCheck(str[r]))
                    r--;

                    // Both str[l] and str[r] are not spacial
                else
                {
                    str[l] ^= str[r];//swap using triple XOR
                    str[r] ^= str[l];
                    str[l] ^= str[r];
                    l++;
                    r--;
                }
            }
            last_str = last_str + new String(str) + " ";
        }
        // Initialize left and right pointers

        return last_str;
    }
    
    public static void main(String[] args){
        String s = "String; 2be reversed...";
        String[] words = s.split("\\s+");
        System.out.println(reverse(s));
        }

}
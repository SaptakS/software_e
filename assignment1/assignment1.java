import java.io.*;
import java.util.Scanner;
import java.io.PrintWriter;
class assignment1
{
	public static void main(String args[])throws IOException
	{
		//Creating an object of Scanner Class
		Scanner fin = new Scanner(new File("input.txt"));
		PrintWriter fout = new PrintWriter(new File("output.txt"));
		//Input sentences from the opened file.
		while(fin.hasNext()) {
			String str = fin.nextLine();
			//System.out.println(str);
			String output = "";
			String words[] = str.split(" ");
			for(int i = 0; i < words.length; i++) {
				int last = words[i].length() - 1;
				if(words[i].charAt(last) == ',' || words[i].charAt(last) == '.')
					last = last - 1;
				char ltemp = words[i].charAt(last);
				char sltemp = words[i].charAt(last - 1);
				String newstr = words[i].substring(0, last - 1) + ltemp + sltemp + words[i].substring(last + 1);
//				System.out.println(newstr);
				output = output + newstr + " ";
			}
			fout.println(output);
		}
		fout.close();		
	}
}	

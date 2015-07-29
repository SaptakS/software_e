import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;
class assignment2
{
	public static void main(String args[])throws IOException
	{
		//Creating an object of Scanner Class
		Scanner fin = new Scanner(new File("input.txt"));
		PrintWriter fstrings = new PrintWriter(new File("strings.txt"));
		PrintWriter fnumbers = new PrintWriter(new File("numbers.txt"));
		PrintWriter fcharacters = new PrintWriter(new File("characters.txt"));
		//Input sentences from the opened file.
		while(fin.hasNext()) {
			String str = fin.nextLine();
			String strings = "";
			String numbers = "";
			String characters = "";
			String words[] = str.split(" ");
			for(int i = 0; i < words.length; i++) {
				char first = words[i].charAt(0);
				char last = words[i].charAt(words[i].length() - 1);
				if(first >= '0' && first <= '9') {
					if(!(last >= '0' && first <= '9')){
						characters = characters + last + " ";
						numbers = numbers + words[i].substring(0, words[i].length() - 1) + " ";
					} else {
						numbers = numbers + words[i] + " ";
					}
				}

				else if((first >= 'a' && first <= 'z') || (first >= 'A' && first <= 'Z'))  {
                                        if(!((last >= 'a' && last <= 'z') || (last >= 'A' && last <= 'Z'))){
                                                characters = characters + last + " ";
                                                strings = strings + words[i].substring(0, words[i].length() - 1) + " ";
                                        } else {
                                                strings = strings + words[i] + " ";
                                        }
                                }

			}

			strings = sort(strings);
			numbers = sort(numbers);
			characters = sort(characters);			

			fstrings.println(strings);
			fnumbers.println(numbers);
			fcharacters.println(characters);
				
		}
		fstrings.close();		
		fnumbers.close();
		fcharacters.close();
	}

	public static String sort(String s)
	{
		String words[] = s.split(" ");
		Arrays.sort(words);	
		String newstr = "";
		for(int i = 0; i < words.length; i++) {
			newstr = newstr + words[i] + "\n";
		}
		return newstr;	
	}
}	

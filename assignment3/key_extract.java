import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.io.PrintWriter;
class key_extract
{
	public static void main(String args[])throws IOException
	{
		//Creating an object of Scanner Class
		Scanner fin = new Scanner(new File("input.c"));
		PrintWriter fstrings = new PrintWriter(new File("output.txt"));

		int freq[] = new int[32];
		String keywords[] = {"auto", "break", "case", "char", "const", "continue", "default", "do", "double", "else", "enum", "extern", "float", "for", "goto", "if", "int", "long", "register", "return", "short", "signed", "sizeof", "static", "struct", "switch", "typedef", "union", "unsigned", "void", "volatile", "while"};
		//Input sentences from the opened file.
		while(fin.hasNext()) {
			String str = fin.nextLine();
			int fl = 0, pos = 0;
			String words[] = str.split("\\s*(\\{|\\}|\\(|\\)|\\s|\t)");
			for(int i = 0; i < words.length; i++) {
				fl = 0;
				for(int j = 0; j < keywords.length; j++) {
					if(words[i].equals(keywords[j])) {
						fl = 1;
						pos = j;
						break;
					}
				}
				
				if(fl == 1)
					freq[pos]++;
			}
				

		}

                for(int i = 0; i < keywords.length; i++) {
                        if(freq[i] != 0)
                                fstrings.println(keywords[i] + " has a frequency of " + freq[i]);
                }
	
	
		fstrings.close();		
	}
}	

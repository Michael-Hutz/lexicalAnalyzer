
import java.io.*;
import java.util.Scanner;


public class lexicalAnalyzer {
	
//Variables	
public static int charClass;
public static char[] lexeme = new char[100];
public static char nextC;
public static int lexLen;
public static int token;
public static int nextToken;
public static Scanner scg;
public static String s;
public static int i = 0;


//Constants
public static final int LETTER = 0;
public static final int DIGIT = 1;
public static final int UNKNOWN = 99;
// Students -- Add your constants here
public static final int INT_LIT = 10;
public static final int IDENT = 11;
public static final int ASSIGN_OP = 20;
public static final int ADD_OP = 21;
public static final int SUB_OP = 22;
public static final int MULT_OP = 23;
public static final int DIV_OP = 24;
public static final int LEFT_PAREN = 25;
public static final int RIGHT_PAREN = 26;
public static final int EOF = -1;


	
		public static void main(String[] args) {
		try {
			// do not make any changes to the following TWO lines
			File file = new File(args[0]);		
			Scanner sc = new Scanner(file);	
                        // do not make any other Scanners
			
			do {
                            s += sc.next();
                        }
                        
                        while (sc.hasNext() != false); //Do While loop to convert the input to a single string variable
                        
                        
			
			getChar();          
			do {
				lex();
			}

			while (nextToken != EOF);
			
			
			sc.close();
		} catch (Exception e) {
			System.out.println("ERROR - cannot open front.in \n");
		}
	}
	
	// Students -- add your method declarations here
	static int lookup(char ch) {  //This method is used to take each character and set a value for the Token, this token then determines the behavior in method lex.
		//		printf("  lookup(): %c\n", ch);
			switch (ch) {
				case '(':
					addChar();
					nextToken = LEFT_PAREN;
					break;
				case ')':
					addChar();
					nextToken = RIGHT_PAREN;
					break;
				case '+':
					addChar();
					nextToken = ADD_OP;
					break;
				case '-':
					addChar();
					nextToken = SUB_OP;
					break;
				case '*':
					addChar();
					nextToken = MULT_OP;
					break;
		
				case '/':
					addChar();
					nextToken = DIV_OP;
					break;
                                        
				default: 
                                        addChar();
                                        nextToken = EOF;
                                        break;
        
				}
				return nextToken;
	}

	static void addChar() { //Fills the order of lexemes into the array, ensures that there is not an input beyond the allowed length
        if (lexLen <= 98) {
            lexeme[lexLen++] = nextC;
            lexeme[lexLen] = 0;
			//System.out.printf("  addChar() lexeme[]: %s\n", lexeme);
        } else
            System.out.printf("Error - lexeme is too long \n");
    }  
	
        static void getChar() { //GetChar is used to move one character at a time through the string S (which contains the value from the input), this character is then checked to see if it is a letter, digit, or unknown class.
            if (i < s.length()) { //Validate a getChar to only run inbounds by using length
               nextC = s.charAt(i); //Set next char, using i as a counter to move through the String
               i++;
               
    //			System.out.printf("  getChar(): %c\n", nextChar);
                if (Character.isLetter(nextC))
                    charClass = LETTER;
                else if (Character.isDigit(nextC))
                    charClass = DIGIT;
                else charClass = UNKNOWN;
                
            } 
            
            else {
                charClass = EOF;
                i++; //i++ even if fails to ensure next character is checked
            }
    }    
       
        static void getNonBlank() { //getNonBlank uses a build in method of Character to check if the nextC at the moment is whitespace, if it is, this method calls getChar to skip to the next non-Whitespace
            while (Character.isWhitespace(nextC)) {
		//System.out.printf("  REMOVED SPACE\n");
                getChar();
            }
        }
        
        static int lex() { //lex method is the actual action of compiling, and runs a series of switch statements to retrieve the values, and identifiers of each lexeme as defined in our language. It also is used to determine how each lexeme should behave when breaking down the inputs
            lexLen = 0;
            
            getNonBlank();
            switch(charClass) {
                    case LETTER:	/* charClass after getNonBlank(): LETTER   */	break;
                    case DIGIT:		/* charClass after getNonBlank(): DIGIT    */	break;
                    case UNKNOWN:	/* charClass after getNonBlank(): UNKNOWN  */	break;
                }
            switch (charClass) {
                /* Parse identifiers */
            case LETTER:
                addChar();
                getChar();
                while (charClass == LETTER || charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = IDENT;
                break;
                /* Parse integer literals */
            case DIGIT:
                addChar();
                getChar();
                while (charClass == DIGIT) {
                    addChar();
                    getChar();
                }
                nextToken = INT_LIT;
                break;
                /* Parentheses and operators */
            case UNKNOWN:
                lookup(nextC);
                getChar();
                break;
                /* EOF */
            case EOF:
                lexLen = 3;
                nextToken = -1;
                lexeme[0] = 'E';
                lexeme[1] = 'O';
                lexeme[2] = 'F';
                lexeme[3] = 0;
                break;
            } /* End of switch */
            if (lexeme[0] == 'n' && lexeme[1] == 'u' && lexeme[2] == 'l' && lexeme[3] == 'l'); //Remove null print for initialization
            
            else {
            System.out.print("Next token is: " + nextToken + ", Next lexeme is ");
            for (int i = 0; i < lexLen; i++) {
                if (lexeme[i] != ' '){
                    System.out.print(lexeme[i]);
                }
            }
            System.out.println();
            }
           
            return nextToken;
        
      }       
}

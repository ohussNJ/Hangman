import java.util.ArrayList;
import java.util.Scanner;

public class HangMan {
    private String [][] board;
    private String gameWord;
    private char [] word;
    private ArrayList <String> usedChoices;
    private int letterRemaining;
    private int remainingTries;

    public HangMan(String gameWord){
        board= new String [][]{{"   ","o","   "},{"   ","|","   "},{"---","|","---"},{"   ","|","   "},{"---","|","---"}};
        this.gameWord=gameWord.toLowerCase();
        word=new char [gameWord.length()];
        usedChoices=new ArrayList<String>();
        letterRemaining=gameWord.length();
        remainingTries=8;
    }
    public String getGameWord(){
        return gameWord;
    }
    public int getRemainingTries(){
        return remainingTries;
    }
    public void setRemainingTries(int n){
        remainingTries=n;
    }
    public int getLetterRemaining(){
        return letterRemaining;
    }
    public void setLetterRemaining(int n){
        letterRemaining=n;
    }
    public ArrayList<String> getUsedChoices(){
        return usedChoices;
    }
    public void setUsedChoices(ArrayList <String> a){
        usedChoices=a;
    }
    public char [] getWord(){
        return word;
    }
    public void setWord(char []a){
        word=a;
    }
    public String [][] getBoard(){
        return board;
    }
    public void setBoard(String [][] a){
        board=a;
    }
    public char checkDuplicateMove(char guess){
        ArrayList <String> c=getUsedChoices();
        String s=Character.toString(guess);
        while (c.contains(s)){
            System.out.println();
            System.out.println("You have already used this letter, choose another.");
            s=StdIn.readString();
            while (s.length()>1){
                System.out.println("Error: Type one letter only");
                s=StdIn.readString();
            }
        }
        char newGuess=s.charAt(0);
        return newGuess;
    }
   
    public void wrongGuess(int a, char guess){
        ArrayList <String> c=getUsedChoices();
        String s=Character.toString(guess);
        c.add(s);
        setUsedChoices(c);
        System.out.println("Board:");
        if (a<8){
            System.out.println(board[0][0]+board[0][1]+board[0][2]);
        }
        if (a<7){
            System.out.println(board[1][0]+board[1][1]+board[1][2]);
        }
        if (a<6){
            System.out.print(board[2][0]);
        }
        if (a<5){
            System.out.print(board[2][1]);
        }
        if (a<4){
            System.out.println(board[2][2]);
        }
        if (a<3){
            System.out.println(board[3][0]+board[3][1]+board[3][2]);
        }
        if (a<2){
            System.out.print(board[4][0]+board[4][1]);
        }
        if (a<1){
            System.out.println(board[4][2]);
        }
        System.out.println();
        System.out.println("Used Choices:");
        System.out.println(usedChoices);
        return;
    }
    public boolean checkMultipleOccurence(char guess, int r){
        boolean multiple=false;
        int count=0;
        String word=getGameWord();
        for (int i=0; i<gameWord.length(); i++){
            if (gameWord.charAt(i)==guess){
                count++;
            }
        } 
        if (count>=2) {
                char [] a=getWord();
            for (int j=0; j<gameWord.length(); j++){ 
                if (gameWord.charAt(j)==guess){    
                a[j]=word.charAt(j);
                setWord(a);
            }}
        } else {
            return multiple;
        }   
        System.out.println("Word: ");
        System.out.println( getWord());
        if (count>=2)
            multiple=true;
            r=getLetterRemaining()-count;
            setLetterRemaining(r);
        return multiple;
    }
    public void rightGuess(char c, int r){
        boolean multiple=checkMultipleOccurence(c,r);
        ArrayList <String> d=getUsedChoices();
        String s=Character.toString(c);
        d.add(s);
        setUsedChoices(d);
        if(multiple)
                return;
        String word=getGameWord();
        int n=word.indexOf(c);
        char [] a=getWord();
        a[n]=word.charAt(n);
        setWord(a);
        System.out.println("Word: ");
        System.out.println( getWord());
        r=getLetterRemaining()-1;
        setLetterRemaining(r);
        return;
    }
    public boolean solvePuzzle(){
        int a=getRemainingTries();
        System.out.println("Would you like to solve the puzzle?(Yes or No)");
        String answer=StdIn.readString();
        answer=answer.toLowerCase();
        boolean x=false;
            while ( !answer.equals("yes") && !answer.equals("no") ){
                System.out.println("Error: Choose 'yes' or 'no' ");
                    answer=StdIn.readString();
                    answer=answer.toLowerCase();
            }
            if (answer.equals("no"))
                x=false;
            if (answer.equals("yes")){
                System.out.println("What is your guess?");
                String wordGuess=StdIn.readString();
                String puzzleWord=getGameWord();
                    if (wordGuess.equals(puzzleWord)){
                        x=true;
                    } else{
                        a--;
                        setRemainingTries(getRemainingTries()-1);
                        System.out.println("Board:");
                             if (a<8){
                                 System.out.println(board[0][0]+board[0][1]+board[0][2]);
                             }
                            if (a<7){
                                System.out.println(board[1][0]+board[1][1]+board[1][2]);
                            }
                            if (a<6){
                                System.out.print(board[2][0]);
                            }
                            if (a<5){
                                System.out.print(board[2][1]);
                            }
                            if (a<4){
                                System.out.println(board[2][2]);
                            }
                            if (a<3){
                                System.out.println(board[3][0]+board[3][1]+board[3][2]);
                            }
                            if (a<2){
                                System.out.print(board[4][0]+board[4][1]);
                            }
                            if (a<1){
                                System.out.println(board[4][2]);
                            }
                        System.out.println();
                        System.out.println("Incorrect, you have "+ getRemainingTries()+ " tries left.");
                        x=false;
                    }
            }    
            return x;
    }
    public boolean checkWinner(int r, int rem){
        boolean gameOver=false;
        if (r==0){
            gameOver=true;
            System.out.println("Game Over: You Won");
        }
        if (rem==0){
            gameOver=true;
            System.out.println("Game Over: You Lost");
        }
            return gameOver;
    }
    public void gameSetup(){
        Scanner scan=new Scanner(System.in);
        char [] a=getWord();
        int lr=getLetterRemaining();
        String word=getGameWord();
        for (int j=0; j<word.length(); j++){
            a[j]=' ';
        }
        setWord(a);
        for (int i=0; i<(8+word.length()); i++){
            int tries=getRemainingTries();
        System.out.println("Choose a letter");
        String letter=scan.next();
        while (letter.length()>1){
            System.out.println("Error: Type one letter only");
            letter=scan.next();
        }
        char guess=letter.charAt(0);
        char checkedGuess=checkDuplicateMove(guess);
        int n=word.indexOf(checkedGuess);
        if (n>=0) {
            rightGuess(checkedGuess, lr);
            System.out.println("Correct, you have "+getLetterRemaining()+" letters left");
                if (getLetterRemaining()>0){
                boolean solved=solvePuzzle();
                 if (solved){
                     System.out.println("Correct! You guessed the word!");
                     System.out.println("Game Over: You Won");
                     break;
                 }
                 }
        } else {
            tries--;
            setRemainingTries(tries);
            wrongGuess(getRemainingTries(), checkedGuess);
            System.out.println();
            System.out.println("Wrong, you have "+getRemainingTries()+" tries remaining.");
        }
        boolean gameEnd=checkWinner(getLetterRemaining(),getRemainingTries());
        if (gameEnd)
            break;
       }
        scan.close();
    
}
    public String toString(){
        return "Thanks for playing! The word was '"+ getGameWord()+"'.";
    }
    public static void main (String [] args){
        HangMan h=new HangMan("Titan");
        h.gameSetup();
        System.out.println(h);
    }
}

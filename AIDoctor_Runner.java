import java.util.*;
import java.io.*;
public class AIDoctor_Runner {
	 public static void main(String[] args) throws IOException {
	 Scanner console = new Scanner(System.in);
	 System.out.println("welcome to the doctor \nlets try to diagnose your illness");
	 System.out.println("what doctor would you like to use");
	 String filename = console.nextLine().trim();//gets the name of the doctor file they wana use
	 File questionsFile = new File(filename);
     if (!questionsFile.exists()) {
     	questionsFile.createNewFile();
     
     }
     Scanner questions = new Scanner(questionsFile);
     AIDoctor_code doc = createGame(questions, console);
     PrintStream f = new PrintStream(filename);
     do {
         System.out.println("Press Enter when you're ready to begin!");
         doc.read(questions);
         console.nextLine();
         doc.Diagnose(console,f);
         System.out.println();
//         game.saveQuestions(new PrintStream(questionsFile));
         System.out.print("Do you have any other things you want diagnosed (y/n)? ");
     } while (console.nextLine().trim().toLowerCase().startsWith("y"));
     
}
	 
	 
     public static String GetInitialIllness(Scanner console) {
    	 System.out.println("There are no diagnoses in that AI doctor.");
    	 System.out.print("Can you provide me with an initial illness? ");
    	 return console.nextLine().toLowerCase().trim();
     }
     
     
     public static AIDoctor_code createGame(Scanner questions, Scanner console) {
         /* Check if the file has anything in it.  If it does, use it.
          * Otherwise, initialize a new game. */
         if (!questions.hasNext()) {
             return new AIDoctor_code(GetInitialIllness(console));
         }
         else {
             return new AIDoctor_code(questions);
         }
     }
   
     
	 
}

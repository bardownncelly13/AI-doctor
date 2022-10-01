import java.util.*;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.*;
import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class AIDoctor_Runner {
	 public AIDoctor_Runner() {
			JFrame frame = new JFrame();
			JPanel panel = new JPanel();
			panel.setBorder((BorderFactory.createEmptyBorder(300, 300, 300, 300)));
			panel.setLayout(new GridLayout(0,1));
			frame.add(panel,BorderLayout.CENTER);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("the GUI");
			frame.pack();
			frame.setVisible(true);
		}
	 public static void main(String[] args) throws IOException {
		 new firstGUI();
		 // put code for the GUI here
		 
		 
		 
		 
		 
		 
		 Scanner console = new Scanner(System.in);
		 System.out.println("welcome to the doctor \nlets try to diagnose your illness");
		 System.out.println("what doctor would you like to use");
		 String filename = console.nextLine().trim();//gets the name of the doctor file they wana use
		 File questionsFile = new File(filename);
	     if (!questionsFile.exists()) {
	     	questionsFile.createNewFile();
	     
	     }
	     Scanner file = new Scanner(new File("1234.csv"));
	     AIdoc.csvRead(file);
	     Scanner questions = new Scanner(questionsFile);
	     AIdoc doc = createGame(questions, console);
	     PrintStream f = new PrintStream(filename);
	     do {
	         System.out.println("Press Enter when you're ready to begin!");
	         doc.read(questions);
	         console.nextLine();
	         doc.Diagnose(console,f);
	         System.out.println();
//	         game.saveQuestions(new PrintStream(questionsFile));
	         System.out.print("Do you have any other things you want diagnosed (y/n)? ");
	     } while (console.nextLine().trim().toLowerCase().startsWith("y"));
	     
	}
		 
		 
	     public static String GetInitialIllness(Scanner console) {
	    	 System.out.println("There are no diagnoses in that AI doctor.");
	    	 System.out.print("Can you provide me with an initial illness? ");
	    	 return console.nextLine().toLowerCase().trim();
	     }
	     
	     
	     public static AIdoc createGame(Scanner questions, Scanner console) {
	         /* Check if the file has anything in it.  If it does, use it.
	          * Otherwise, initialize a new game. */
	         if (!questions.hasNext()) {
	             return new AIdoc(GetInitialIllness(console));
	         }
	         else {
	             return new AIdoc(questions);
	         }
	     }
	   
}

import java.util.*;
import java.io.*;

public class AIDoctor_code {
	private DocNode root;
	public DocNode computer;
	public Scanner sc;
	PrintStream f;
	
	//creating a Node with a left node, right node, and a string representing the data and a 
	//boolean depicting weather it is a answer
	private static class DocNode {
		DocNode left;
		DocNode right;
		String data;
		boolean ans;
		private DocNode(DocNode l, DocNode r, String d, boolean a) {
			left = l;
			right = r;
			data = d;
			ans = a;
		}
	}
	//sets the root computer node
	public AIDoctor_code() {
		computer = new DocNode(null,null,"computer",false);
	}
	//sets the root node to the first illness
	public AIDoctor_code(String first_illness) {
		root = new DocNode(null,null,first_illness,true);
		try {
			f = new PrintStream(new File(first_illness));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public AIDoctor_code(Scanner questions) {
		
	}
	public boolean answerYorN(String user_answer) {
		if(user_answer.toLowerCase().equals("y")) {
			return true;
		}
		else if (user_answer.toLowerCase().equals("n")) {
			return false;
		}
		else System.out.println("it says type a y/n answer");
		return (Boolean) null;
	}
	public void add(DocNode n,String newquestion,String newanswer) {
		if(n.data == null) {
			n = new DocNode(null,null,newquestion,false);
		}
		else {
			String previousAnswer = n.data;
			n.data = newquestion;
			n.ans = false;
			n.left = new DocNode(null,null,newanswer,true);
			n.right = new DocNode(null,null,previousAnswer,true);
		}
	}
	 private DocNode add(String data,DocNode n,int ac,boolean tf) {
	    	if(n == null) {
	    		 n = new DocNode(null,null,data,tf);// if there is no node there then we add one because its an empty space
	    	}
	    	else if(ac == 0) {
	    		ac = 1;								//we go to the right first just because there is no answer to the question
	    		n.right = add(data,n.right,ac,tf);	//ac represents that we are on a question not an answer
	    	}
	    	else if(n.left != null && n.left.ans) { //the node on the left is an answer and the node on the right is null
	    		n.right = add(data,n.right,ac,tf);	// so we move to the right node
	    	}
	    	else  {
	    		n.left = add(data,n.left,ac,tf);	//only thing left is left
	    	}	
	    	return n;								//we need to rebuild the tree as we go and only change it at the end so we return 
	    											//each node when we go through
	    }
	 public void read(Scanner sc) {//read the file that we put in and creates the tree for us to go through and ask questions
	    	int ac = 0;
	    	int boo = 0;
	    	int count = 0;
	    	while (sc.hasNext()) {
	    		boolean tf = false;
	    		String data = sc.nextLine();
	        	if(data.equals("A:")) {
	        		ac++;
	        		tf = true;
	        	}
	        	else ac --;					//this is an absolute monstrosity where I used ints and not booleans but 
	        	if(ac == 0) boo = 1;		//I am not changing it now cause it took a bit of time so deal with it
	        	if(boo == 1) ac = 0;		//scanner here refers to the file and not the console btw
	        	if(ac == 0) count ++;
	        	if(count == 1) ac = 1;
	        	data = sc.nextLine();
	    		root = add(data,root,ac,tf);
	    	}
	    }
	private void saveQuestions( DocNode t,PrintStream p) {
			if(t != null){
			if(t.ans){					//when we edit the text file we need a uniform way to store questions and answers
				p.println("A:");		//if the node is an answer we put A: in front else we put Q:
			}
			else
				p.println("Q:");
			
			p.println(t.data);			
			
			saveQuestions(t.right, p);	//goes to the left and right next and until t is null and the tree is empty
			saveQuestions(t.left, p);
			}
		}
	public void saveQuestions(PrintStream printStream) {
		if(root != null) {
		saveQuestions(root,printStream);
		}
		}
		
	public void Diagnose(Scanner sc,PrintStream f) {
		DocNode n = root;
		while (n != null){
			if(n.ans){
				System.out.println("I believe you have "+ n.data);	
				System.out.println("check with a real doctor and tell me if is was right(y/n)");
				String PersonsAnswer = sc.nextLine();
				if(answerYorN(PersonsAnswer)){
					System.out.println("Glad i could help diagnose you");
					break;
				}
				else{
					System.out.println("what is the name of your illness");
					String nans = sc.nextLine();
					System.out.println("please give me a yes/no question to determine your illness");
					String d = sc.nextLine();
					add(n,d,nans);
					saveQuestions(f);
					break;
				}
				}
			else {
			System.out.println(n.data + " (y/n)");
			String p = sc.nextLine();
			if(answerYorN(p) && root.left != null) n = n.left;
			else if (n.right != null && !answerYorN(p)){
				 n = n.right;
			}
			}
		}
    }
	
}
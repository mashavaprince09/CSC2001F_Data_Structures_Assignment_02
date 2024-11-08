import java.io.*;
import java.util.*;
//import java.math.*;
public class GenericsKbAVLApp {
        /**
        * Main method for testing AVLTree. It takes a command line argument as an argument
        * 
        * @param args - the command line arguments
        */
        public static void main(String[] args){
                Scanner sc = new Scanner(System.in);
                int choice =-1;
                AVLTree AVL = new AVLTree();
		String[] arrQuery = new String[5000];
                String[] arrQueryTest = new String[10];
                int iQuery = 0;
                
                // Main entry point for the menu.
                while (choice!=5){
                System.out.println("Choose an action from the menu: ");
                System.out.println("1. Load the knowledge bases");
                System.out.println("2. Search items in the query");
                System.out.println("3. Test application using query file");
                System.out.println("4. Experiment");                
                System.out.println("5. Quit");
                System.out.println();
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                System.out.println();
		// Load the Kb tree and return the AVL tree.
		if (choice == 1){
			try {				
                        	String fileName = "GenericsKB.txt";                     
                                String abspath = new File("").getAbsolutePath();                                
				String filepath = abspath + "/data/"+fileName;
				File file_1 = new File(filepath);
                                Scanner x = new Scanner(file_1);                                
				// Insert all the characters in the input stream.
				while (x.hasNext()){
                                        String line = x.nextLine();
                                        Kb kb = new Kb(line);
                                          AVL.insert(kb);                         

                                }
                                x.close();
                                System.out.println("GenericsKB.txt loaded successfully.");
                                System.out.println("Number of insertions into AVL Tree: "+AVL.insert);                                                                
                        } catch (Exception e){System.out.println("File not found");}			               		
			try {

                        	String fileName = "GenericsKB-queries.txt";
                                String abspath = new File("").getAbsolutePath();
                                String filepath = abspath + "/data/"+fileName;
                                File file_1 = new File(filepath);
                                Scanner x = new Scanner(file_1);	   			
                                // This method is used to iterate over the next line in the query.
                                while (x.hasNext()){
                                        String line = x.nextLine();
                                        arrQuery[iQuery] = line;
                                        iQuery++;
                                       }
                                x.close();
                                System.out.println("GenericsKB-queries.txt loaded successfully.");
                         
                        } catch (Exception e){System.out.println("File not found");}                          
                }
                // This method is used to find the best and average search results.
                else if (choice == 2){
                        // prints all the terms in the query
                        for (int i =0; i<iQuery;i++){
                           // Find the term in the term array.
                           if (AVL.findTerm(arrQuery[i]) == null){
                              System.out.println("Term not found: "+arrQuery[i]);
                           }
                           else {
                              Kb kb = AVL.findTerm(arrQuery[i]).getData();
                              System.out.println(kb.getTerm()+": "+kb.getSentence()+" ("+kb.getConfidence()+")");
                           }     
                        }
                        System.out.println();
			System.out.println("Number of searches performed: "+AVL.search);			
                }
                // choice is the choice of the query test.
                else if (choice == 3){
		     try {
                    	String fileName = "Query-Test.txt";
                     	String abspath = new File("").getAbsolutePath();
                     	String filepath = abspath + "/data/"+fileName;
                     	File file_1 = new File(filepath);
                     	Scanner x = new Scanner(file_1);                        
			int c=0;                               
                        // This method is used to iterate over the next line in the input stream and store the results in the arrQueryTest array.
                        while (x.hasNext()){
                           String line = x.nextLine();
                           arrQueryTest[c] = line;
                           c++;
                        }
                        x.close();
                        System.out.println("Query-Test.txt loaded successfully.");   
                        System.out.println();                      
                     } catch (Exception e){System.out.println("File not found");}
                     
		     // This method is used to find all terms in the query.
		     for (int i =0; i<arrQueryTest.length;i++){
                        // Find term in the array of terms.
                        if (AVL.findTerm(arrQueryTest[i]) == null){
                              System.out.println("Term not found: "+arrQueryTest[i]);
                        }
                        else {
                              Kb kb = AVL.findTerm(arrQueryTest[i]).getData();
                              System.out.println(kb.getTerm()+": "+kb.getSentence()+" ("+kb.getConfidence()+")");
                        }                              
                      }                  
                }
                // This method is called by the main program.
                else if (choice == 4){
		     int[] n = {10,50,100,500,1000,5000,10000,20000,35000,50000};
                        for (int j : n){
			   int bestCaseInsert=0;
                           int worstCaseInsert = -1;
                           int averageCaseInsert = -1;				
                           int bestCaseSearch=0;
                           int worstCaseSearch=-1;
                           int averageCaseSearch = -1;			   
			   System.out.println("When n= "+j);
                           // This method is used to compute the average number of cases for each instrumentation.
                           for (int k=0;k<10;k++){
				   String instrum = instrumentation(j);
				   int insert = Integer.valueOf(instrum.substring(0,instrum.indexOf(",")));
				   instrum = instrum.substring(instrum.indexOf(",")+1);
				   int search = Integer.valueOf(instrum);
				   
				   // Set the best case insert search.
				   if (k<1){
					bestCaseInsert = insert;
					bestCaseSearch = search;
				   }
				   
				   bestCaseInsert = Math.min(bestCaseInsert,insert);
                                   worstCaseInsert = Math.max(worstCaseInsert,insert);		
				   averageCaseInsert +=insert;

                                   bestCaseSearch = Math.min(bestCaseSearch,search);
                                   worstCaseSearch = Math.max(worstCaseSearch,search);
                                   averageCaseSearch +=search;				   
			   }
			   averageCaseInsert = averageCaseInsert/10;
			   averageCaseSearch = averageCaseSearch/10;
			   System.out.println("Insertions:");
			   System.out.println("Best Case: "+bestCaseInsert+"\t"+"Average Case: "+averageCaseInsert+"\t"+"Worst Case: "+worstCaseInsert);
                           System.out.println("Searches:");
                           System.out.println("Best Case: "+bestCaseSearch+"\t"+"Average Case: "+averageCaseSearch+"\t"+"Worst Case: "+worstCaseSearch);  			   
                           System.out.println();
 			}
                        }                                    
                           
               // This method is called when the user clicks on the program.
               else if (choice ==5){
                     System.out.println("Exiting the program");
                     break;
               }
		else {
            System.out.println("This is an invalid option. Please select an option from the menu above.");}
      		System.out.println();
        } // while
    } //main

    /**
    // Creates an AVL Tree with size random KB objects and returns the search and insertion counters 
    * 
    * 
    * @param size the size of the AVL Tree
    *
    * @return a string containing the insert and search counter, separated by a comma
    */
public static String instrumentation(int size){
                        AVLTree avlExperiment = new AVLTree();
                        Kb[] arrKB = new Kb[50000];
                        int s = 0;
			try {
                 	        String fileName = "GenericsKB.txt";
				String abspath = new File("").getAbsolutePath();
                        	String filepath = abspath + "/data/"+fileName;
                        	File file_1 = new File(filepath);
                        	Scanner x = new Scanner(file_1);
                                // Get the next Kb in the input stream.
                                while (x.hasNext()){
                                        String line = x.nextLine();
                                        Kb kb = new Kb(line);
                                        arrKB[s] = kb;
                                        s++;
                                }
                                x.close();
                                                                                          
                        } catch (Exception e) {System.out.println("File not found");}                          
                                Random random = new Random();
                                // Insert a random number of random bytes in the experiment.
                                for (int i=0; i<size;i++){
                                          int randomNumber = random.nextInt(50000);
                                          avlExperiment.insert(arrKB[randomNumber]);
                                 }			       	
                                 String[] arrQueryTest = new String[10];
				 AVLTree avlTree2 = avlExperiment;
				 avlTree2.search = 0;
                                try {
                                        String fileName = "Query-Test.txt";
                                        String abspath = new File("").getAbsolutePath();
                                        String filepath = abspath + "/data/"+fileName;
                                        File file_1 = new File(filepath);
                                        Scanner x = new Scanner(file_1);
                                        int c=0;
                                        // This method is used to iterate over the next line in the input stream and store the results in the arrQueryTest array.
                                        while (x.hasNext()){
                                                String line = x.nextLine();
                                                arrQueryTest[c] = line;
                                                c++;
                                        }
                                        x.close();
                                } catch (Exception e){System.out.println("File not found");}
				// Find the terms in the tree.
				for (int i =0; i<10;i++){
					 // Find the term in the tree.
					 if (avlTree2.findTerm(arrQueryTest[i]) == null){
					 }
					 else {}
                                } 
                              return avlExperiment.insert+","+avlTree2.search;                                                                                                                                                                                  
} //instrumentation

} //class


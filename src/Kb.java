/**
* A method that takes a string and returns a Kb. The string must be of the form, Term \ t Sentence \ t Confidence
*/
public class Kb  implements Comparable<Kb>  {
    private  String term;
    private String sentence;
    private double confidence;

    /**
    * Creates an instance of Kb from a string. The string must be of the form term sentence
    * @param entry this is a string containing a term, description and confidence score all separated by tabs. The entry is split into the 3 different instance variables and assigned to its respective variable
    */
    public Kb(String entry){
            term = entry.substring(0,entry.indexOf("\t"));
            entry = entry.substring(entry.indexOf("\t")+1);
            sentence = entry.substring(0,entry.indexOf("\t"));
            entry = entry.substring(entry.indexOf("\t")+1);
            confidence = Double.valueOf(entry);
    }

    /**
    * Returns the term associated with this query. This is used to distinguish queries that have multiple terminals in their query string.
    * 
    * 
    * @return the term associated with this query or null if there is no term associated with this query ( in which case the query is a no - op
    */
    public String getTerm(){
            return term;
    }
    /**
    * Returns the sentence associated with this event. This is used to generate error messages when there is no sentence associated with the event.
    * 
    * 
    * @return the sentence associated with this event or null if there is no sentence associated with the event or if the event does not
    */
    public String getSentence(){
            return sentence;
    }
    /**
    * Returns the confidence of this distribution. This is an estimate of the number of successes that have been made with the same classifiers and are more likely to be used for a particular set of classifiers.
    * 
    * 
    * @return the confidence of this distribution as a double between 0 and 1. 0 or Double. NaN if there are no
    */
    public double  getConfidence(){
            return confidence;
    }

    /**
    * Matches a term against the term field. This is used to determine if an item is part of the search terms
    * 
    * @param item - the item to be matched against the term field
    *
    * 
    * @return true if the term matches false if it doesn't or if the term is not in the search
    */
    public boolean matchTerm(String item){
            // Checks if the item is case insensitive.
            if (item.equalsIgnoreCase(term) ){
                    return true;
            }
            else {
                    return false;
            }
    }
    /**
    * Matches the term and sentence of a sentence. This is used to determine if an item is part of the sentence or not
    * 
    * @param item - Item to be matched against
    *
    *
    * @param sen - Sentence to be matched against
    *
    * 
    * @return true if match false if not match or no match is found in the sentence ( case insensitive match is used
    */
    public boolean matchTermSentence(String item, String sen){
            // Checks if the item is a term sentence or term
            if ( (item.equalsIgnoreCase(term) ) && sen.equalsIgnoreCase(sentence) ){
                    return true;
            }
            else {
                    return false;
            }
    }

    /**
    * Matches a partial term against a string. This is used to determine if a search term has been created with a partial term
    * 
    * @param item - The string to search for
    * 
    * @return true if the string matches false if it doesn't ( case insensitive match is used ) Note : The comparison is case
    */
    public boolean matchTermPartial(String item){
            //boolean bMatch = false;
            String[] arrTerm = term.split(" ");
            String[] arrItem = item.split(" ");            
            for (String ele1: arrTerm){
               for (String ele2: arrItem){
                  // match the first element of the list
                  if (ele1.toLowerCase().equals(ele2.toLowerCase())){
                     
                     return true;
                     
                  }
               }
            }  
            return false;
    }
    /**
    * Match SENTENCE part of sentence with item. Case insensitive. This is used to determine if item is part of sentence
    * 
    * @param item - item to be checked for match
    * 
    * @return true if match false if not match or item doesn't exist in sentence ( case insensitive ). 
    */
    public boolean matchSenPartial(String item){
            //boolean bMatch = false;
            String[] arrSen = sentence.split(" ");
            String[] arrItem = item.split(" ");            
            for (String ele1: arrSen){
               for (String ele2: arrItem){
                  // Match the first element of the two elements in the string.
                  if (ele1.toLowerCase().equals(ele2.toLowerCase())){
                     return true;
                  }
               }
            }  
            return false;
    }
    
    /**
    * Modifies the sentence of the current Kb object
    * 
    * @param t - the term to be inserted
    */
    public void setTerm(String t){
            term = t;
    }    
    /**
    * Modifies the sentence of the current Kb object
    * 
    * @param sen the description to be inserted
    */
    public void setSentence(String sen){
            sentence = sen;
    }
    /**
    * Sets the confidence level. This is used to determine how good the model is based on the data that was read from the file.
    * 
    * @param cs - Confidence level of the model in the range 0. 0 - 1.
    */
    public void setConfidence(double cs){
            confidence = cs;
    }
    /**
    * Returns a string representation of this term. The string is formatted as term sentence confidence. For example " T_BANGER " is returned as " T_BANGER ".
    * 
    * 
    * @return a string representation of this term and confidence in the form " Term \ t Sentence \ t Confidence
    */
    public String toString(){
            return term +"\t"+ sentence +"\t"+ String.valueOf(confidence);
    }
    
    /**
    * Replaces the knowledge base with another knowledge base. This is useful for testing and to avoid having to copy the knowledge base every time it is used.
    * 
    * @param kb - the knowledge base to use as a replacement for the current one
    */
    public void replace(Kb kb){
         this.term = kb.getTerm();
         this.sentence = kb.getSentence();
         this.confidence = kb.getConfidence();
    }
    
    /**
    * Compares this Kb to another for order. Returns a negative integer zero or a positive integer as this object is less than equal to or greater than the specified object.
    * 
    * @param kb - the object to be compared. Must not be null.
    * 
    * @return a negative integer zero or a positive integer as this object is less than equal to or greater than the specified object
    */
    @Override
    public int compareTo(Kb kb ){
        return this.term.compareTo(kb.getTerm());
    }
}

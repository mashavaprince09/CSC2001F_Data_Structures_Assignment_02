
# CSC2001F Data Structures Assignment 02 README

## Overview
This project provides an implementation of a **Knowledge Base** using an **AVL Tree** (balanced binary search tree) and various other supporting classes such as **BinaryTree** and **BTQueue**. The system is designed to manage knowledge entries efficiently with methods for insertion, deletion, searching, and sorting based on key terms.

The **Kb** class represents an individual knowledge base entry with a `term`, `sentence`, and `confidence`. The project includes methods for:
- Managing knowledge base entries in an AVL tree.
- Performing binary tree operations.
- Using queues and nodes for tree management.

The **GenericsKbAVLApp** is the main application that interacts with these classes to manage the AVL tree of knowledge base entries.

## Table of Contents
1. [Class Descriptions](#class-descriptions)
2. [Methods](#methods)
3. [Makefile](#makefile)
4. [Usage Example](#usage-example)

---

## Class Descriptions

### **Kb Class**
The **Kb** class represents a single knowledge entry. Each entry contains:
- `term`: A keyword or identifier for the knowledge entry.
- `sentence`: A description or detailed text about the term.
- `confidence`: A confidence score (between 0 and 1) representing the certainty of the knowledge.

### **AVLTree Class**
The **AVLTree** class implements a self-balancing binary search tree. It ensures that the tree remains balanced after insertions and deletions, providing efficient operations for searching, adding, and removing nodes.

### **BTQueue Class**
The **BTQueue** class implements a queue data structure used to manage **BinaryTreeNode** elements. This queue is typically used for level-order traversal of binary trees.

### **BTQueueNode Class**
The **BTQueueNode** class represents a node in the **BTQueue**, storing a **BinaryTreeNode** and a reference to the next node in the queue.

### **BinaryTree Class**
The **BinaryTree** class is a general binary tree implementation that uses **BinaryTreeNode** objects. It provides methods for tree operations like traversal and searching.

### **BinaryTreeNode Class**
The **BinaryTreeNode** class represents a single node in the **BinaryTree**, storing a reference to its left and right child nodes as well as the data.

### **GenericsKbAVLApp Class**
This is the main application class that ties all the components together. It provides the user interface for interacting with the knowledge base stored in the AVL tree.

---

## Methods

### **Kb Methods**
- **Constructor**: `Kb(String entry)`
  - Creates a knowledge base entry from a string in the format `term \t sentence \t confidence`.

- **getTerm()**: Returns the term.
- **getSentence()**: Returns the sentence.
- **getConfidence()**: Returns the confidence score.
- **setTerm(String term)**: Sets the term.
- **setSentence(String sentence)**: Sets the sentence.
- **setConfidence(double confidence)**: Sets the confidence score.
- **matchTerm(String item)**: Matches the term with an input string.
- **matchTermSentence(String item, String sen)**: Matches both the term and the sentence.
- **matchTermPartial(String item)**: Partial match for the term.
- **matchSenPartial(String item)**: Partial match for the sentence.
- **toString()**: Returns a string representation of the `Kb` object.

### **AVLTree Methods**
- **insert(Kb entry)**: Inserts a knowledge base entry into the AVL tree.
- **delete(Kb entry)**: Deletes a knowledge base entry from the tree.
- **search(String term)**: Searches for a `Kb` entry by term.
- **balance()**: Balances the tree after insertions and deletions.
- **inOrderTraversal()**: Performs an in-order traversal of the tree.

### **BTQueue Methods**
- **enqueue(BinaryTreeNode node)**: Adds a node to the queue.
- **dequeue()**: Removes and returns the node from the front of the queue.
- **isEmpty()**: Checks if the queue is empty.

### **BinaryTree Methods**
- **addNode(Kb entry)**: Adds a new node to the binary tree.
- **searchNode(String term)**: Searches for a node with the specified term.
- **preOrderTraversal()**: Traverses the tree in pre-order.
- **inOrderTraversal()**: Traverses the tree in in-order.
- **postOrderTraversal()**: Traverses the tree in post-order.

### **GenericsKbAVLApp Methods**
- **addKbToAVL(Kb kb)**: Adds a `Kb` entry to the AVL tree.
- **removeKbFromAVL(String term)**: Removes a `Kb` entry from the AVL tree by term.
- **searchKbInAVL(String term)**: Searches for a `Kb` entry by term in the AVL tree.
- **printAllKbEntries()**: Prints all `Kb` entries in the AVL tree.

---

## Makefile

### **Makefile Contents**

```Makefile
# Makefile for compiling the Kb project with AVL Tree and Binary Tree

# Compiler and flags
JAVAC = javac
JFLAGS = -g

# Directories
SRC_DIR = src
BIN_DIR = bin
INPUT_FILE = input/2000_by_2000_All_1.csv
OUTPUT_FILE = output/c.png

# Java source files
SOURCES = $(SRC_DIR)/Kb.java \
          $(SRC_DIR)/AVLTree.java \
          $(SRC_DIR)/BTQueue.java \
          $(SRC_DIR)/BTQueueNode.java \
          $(SRC_DIR)/BinaryTree.java \
          $(SRC_DIR)/BinaryTreeNode.java \
          $(SRC_DIR)/GenericsKbAVLApp.java

# Default target to compile the project
all: $(BIN_DIR)/Kb.class $(BIN_DIR)/AVLTree.class $(BIN_DIR)/BTQueue.class \
    $(BIN_DIR)/BTQueueNode.class $(BIN_DIR)/BinaryTree.class $(BIN_DIR)/BinaryTreeNode.class \
    $(BIN_DIR)/GenericsKbAVLApp.class

# Compile the source files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	$(JAVAC) $(JFLAGS) -d $(BIN_DIR) $(SRC_DIR)/$*.java

# Run the project with the input and output files
run: all
	java -cp $(BIN_DIR) GenericsKbAVLApp $(INPUT_FILE) $(OUTPUT_FILE)

# Clean up the compiled files
clean:
	rm -f $(BIN_DIR)/*.class
```

### **Makefile Instructions**
1. **Compile**: Run `make` to compile the project and generate class files.
2. **Run**: Use `make run` to execute the project with the provided input and output files.
3. **Clean**: Run `make clean` to remove all compiled class files.

---

## Usage Example

### **Example Code**

```java
public class Main {
    public static void main(String[] args) {
        // Create an instance of GenericsKbAVLApp
        GenericsKbAVLApp app = new GenericsKbAVLApp();

        // Create new Kb entries
        Kb kb1 = new Kb("T_A\tTerm A description.\t0.7");
        Kb kb2 = new Kb("T_B\tTerm B description.\t0.8");

        // Add entries to AVL tree
        app.addKbToAVL(kb1);
        app.addKbToAVL(kb2);

        // Search for an entry by term
        Kb searchResult = app.searchKbInAVL("T_A");
        System.out.println(searchResult); // Output: T_A\tTerm A description.\t0.7

        // Remove an entry from the AVL tree
        app.removeKbFromAVL("T_B");

        // Print all entries
        app.printAllKbEntries(); // Output: T_A\tTerm A description.\t0.7
    }
}
```

### **Expected Output**
```
T_A\tTerm A description.\t0.7
```

---

## Conclusion

This project offers a comprehensive solution for managing a knowledge base using an AVL tree data structure. It allows for efficient storage and retrieval of knowledge entries, with all entries organized by their term. The system supports various tree operations and makes use of supporting structures like **BinaryTree**, **BTQueue**, and **BTQueueNode**. The **GenericsKbAVLApp** class ties everything together and offers a user interface for interacting with the tree.

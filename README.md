Cartesian Product Generator
A Java application that generates the Cartesian product of two sets.We devlope this project by using only java for semester one as Discrete Theory project. 

Mathematical Definition
For two sets A and B, the Cartesian product A × B is defined as:
A × B = {(a, b) | a ∈ A and b ∈ B}

Project Structure
Cartesian/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── Cartesian/
│                   ├── Main.java                          # Entry point
│                   ├── Model/
│                   │   ├── SetElement.java               # Set element representation
│                   │   └── CartesianPair.java            # Ordered pair (a,b)
│                   ├── Service/
│                   │   └── CartesianProductService.java  # Core logic
│                   ├── Util/
│                   │   └── InputParser.java              # Input parsing utilities
│                   └── Exception/
│                       └── InvalidSetException.java      # Custom exception
│
├── README.md
└── .gitignore

Features
•	Generate Cartesian products of two sets
•	Support for comma-separated input
•	Input validation and error handling
•	Clean object-oriented design
•	Formatted output display

How to Run

Prerequisites
•	Java 8 or higher
•	JDK installed

Compilation
javac -d bin src/main/java/com/cartesianproduct/**/*.java

Execution
java -cp bin com.cartesianproduct.Main

Usage Example
=== Cartesian Product Generator ===

Enter elements of Set A (comma-separated): 1, 2, 3
Enter elements of Set B (comma-separated): x, y

Set A: [1, 2, 3]
Set B: [x, y]

Cartesian Product A × B:
{ (1, x), (1, y), (2, x), (2, y), (3, x), (3, y) }

Total pairs: 6
Mathematical Properties
•	Cardinality: |A × B| = |A| * |B|
•	Non-commutative: A × B ≠ B × A (in general)
•	Empty set: A × ∅ = ∅
Classes Overview
Main.java
Entry point of the application. Handles user interaction and coordinates the generation process.
SetElement.java
Represents an individual element within a set with validation.
CartesianPair.java
Represents an ordered pair (a, b) in the Cartesian product.
CartesianProductService.java
Contains the core logic for generating and formatting Cartesian products.
InputParser.java
Utility class for parsing and validating user input.
InvalidSetException.java
Custom exception for handling invalid set operations.
Input Formats Supported
•	Comma-separated values: 1, 2, 3
•	Set notation: {a, b, c}
•	Mixed types: apple, banana, cherry
Error Handling
The application handles:
•	Empty sets
•	Null inputs
•	Invalid formats
•	Whitespace issues
License
This project is open-source and available for educational purposes.


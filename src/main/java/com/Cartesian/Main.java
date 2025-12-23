
package com.Cartesian;

import com.Cartesian.Model.CartesianPair;
import com.Cartesian.Model.SetElement;
import com.Cartesian.Service.CartesianProductService;
import com.Cartesian.Util.InputParser;
import com.Cartesian.exception.InvalidSetException;

import java.util.List;
import java.util.Scanner;

/**
 * Main class for Cartesian Product Generator
 * Generates cartesian products of two sets provided by user
 */
public class Main {
    
    private static final CartesianProductService productService = new CartesianProductService();
    private static final InputParser inputParser = new InputParser();
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   CARTESIAN PRODUCT GENERATOR");
        System.out.println("===========================================\n");
        
        try {
            // Get first set from user
            System.out.println("Enter elements of Set A (comma-separated):");
            System.out.print("Set A: ");
            String setAInput = scanner.nextLine();
            List<SetElement> setA = inputParser.parseSet(setAInput, "A");
            
            // Get second set from user
            System.out.println("\nEnter elements of Set B (comma-separated):");
            System.out.print("Set B: ");
            String setBInput = scanner.nextLine();
            List<SetElement> setB = inputParser.parseSet(setBInput, "B");
            
            // Display parsed sets
            displaySet("Set A", setA);
            displaySet("Set B", setB);
            
            // Generate cartesian product
            System.out.println("\nGenerating Cartesian Product A × B...\n");
            List<CartesianPair> cartesianProduct = productService.generateCartesianProduct(setA, setB);
            
            // Display results
            displayCartesianProduct(cartesianProduct, setA.size(), setB.size());
            
            // Offer to continue
            offerToContinue();
            
        } catch (InvalidSetException e) {
            System.err.println("\nError: " + e.getMessage());
            System.out.println("\nPlease try again with valid input.");
        } catch (Exception e) {
            System.err.println("\nAn unexpected error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
    
    /**
     * Display a set in formatted manner
     */
    private static void displaySet(String setName, List<SetElement> elements) {
        System.out.print("\n" + setName + " = { ");
        for (int i = 0; i < elements.size(); i++) {
            System.out.print(elements.get(i).getValue());
            if (i < elements.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" }");
        System.out.println("Cardinality: " + elements.size());
    }
    
    /**
     * Display the cartesian product result
     */
    private static void displayCartesianProduct(List<CartesianPair> pairs, int sizeA, int sizeB) {
        System.out.println("===========================================");
        System.out.println("CARTESIAN PRODUCT RESULT");
        System.out.println("===========================================");
        System.out.println("Total pairs: " + pairs.size() + " (expected: " + sizeA + " × " + sizeB + " = " + (sizeA * sizeB) + ")");
        System.out.println();
        
        System.out.println("A × B = {");
        for (int i = 0; i < pairs.size(); i++) {
            System.out.print("  " + pairs.get(i));
            if (i < pairs.size() - 1) {
                System.out.println(",");
            } else {
                System.out.println();
            }
        }
        System.out.println("}");
    }
    
    /**
     * Offer user to generate another cartesian product
     */
    private static void offerToContinue() {
        System.out.print("\nGenerate another Cartesian Product? (y/n): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("y") || response.equals("yes")) {
            System.out.println("\n");
            main(new String[]{});
        } else {
            System.out.println("\nThank you for using Cartesian Product Generator!");
        }
    }
}
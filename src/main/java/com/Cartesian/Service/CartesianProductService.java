package com.Cartesian.Service;



import com.Cartesian.Model.CartesianPair;
import com.Cartesian.Model.SetElement;
import com.Cartesian.exception.InvalidSetException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* Service class for generating Cartesian products
* Provides various methods to compute and manipulate Cartesian products
*/
public class CartesianProductService {
  
  /**
   * Generate Cartesian product A × B
   * For each element a in A and each element b in B, create pair (a, b)
   * 
   * @param setA First set
   * @param setB Second set
   * @return List of CartesianPair representing A × B
   * @throws InvalidSetException if either set is null or empty
   */
  public List<CartesianPair> generateCartesianProduct(List<SetElement> setA, List<SetElement> setB) 
          throws InvalidSetException {
      
      validateSets(setA, setB);
      
      List<CartesianPair> product = new ArrayList<>();
      
      // For each element in A
      for (SetElement elementA : setA) {
          // Pair it with each element in B
          for (SetElement elementB : setB) {
              product.add(new CartesianPair(elementA, elementB));
          }
      }
      
      return product;
  }
  
  /**
   * Generate Cartesian product using recursive approach
   * Alternative implementation for educational purposes
   * 
   * @param setA First set
   * @param setB Second set
   * @return List of CartesianPair
   * @throws InvalidSetException if sets are invalid
   */
  public List<CartesianPair> generateCartesianProductRecursive(List<SetElement> setA, List<SetElement> setB) 
          throws InvalidSetException {
      
      validateSets(setA, setB);
      return generateRecursive(setA, setB, 0, new ArrayList<>());
  }
  
  /**
   * Recursive helper method
   */
  private List<CartesianPair> generateRecursive(List<SetElement> setA, List<SetElement> setB, 
                                                 int indexA, List<CartesianPair> result) {
      if (indexA >= setA.size()) {
          return result;
      }
      
      SetElement currentA = setA.get(indexA);
      for (SetElement elementB : setB) {
          result.add(new CartesianPair(currentA, elementB));
      }
      
      return generateRecursive(setA, setB, indexA + 1, result);
  }
  
  /**
   * Generate Cartesian product using Java Streams
   * Modern functional approach
   * 
   * @param setA First set
   * @param setB Second set
   * @return List of CartesianPair
   * @throws InvalidSetException if sets are invalid
   */
  public List<CartesianPair> generateCartesianProductStream(List<SetElement> setA, List<SetElement> setB) 
          throws InvalidSetException {
      
      validateSets(setA, setB);
      
      return setA.stream()
              .flatMap(a -> setB.stream()
                      .map(b -> new CartesianPair(a, b)))
              .collect(Collectors.toList());
  }
  
  /**
   * Filter cartesian product to only include numeric pairs
   * 
   * @param product The cartesian product
   * @return Filtered list containing only numeric pairs
   */
  public List<CartesianPair> filterNumericPairs(List<CartesianPair> product) {
      return product.stream()
              .filter(CartesianPair::isNumericPair)
              .collect(Collectors.toList());
  }
  
  /**
   * Get cardinality (size) of the cartesian product
   * |A × B| = |A| × |B|
   * 
   * @param setA First set
   * @param setB Second set
   * @return Expected cardinality
   */
  public int getCardinality(List<SetElement> setA, List<SetElement> setB) {
      if (setA == null || setB == null) {
          return 0;
      }
      return setA.size() * setB.size();
  }
  
  /**
   * Check if a pair exists in the cartesian product
   * 
   * @param product The cartesian product
   * @param first First element value
   * @param second Second element value
   * @return true if pair exists, false otherwise
   */
  public boolean containsPair(List<CartesianPair> product, String first, String second) {
      return product.stream()
              .anyMatch(pair -> pair.getFirst().getValue().equals(first) && 
                               pair.getSecond().getValue().equals(second));
  }
  
  /**
   * Validate that sets are not null or empty
   * 
   * @param setA First set
   * @param setB Second set
   * @throws InvalidSetException if validation fails
   */
  private void validateSets(List<SetElement> setA, List<SetElement> setB) 
          throws InvalidSetException {
      
      if (setA == null || setB == null) {
          throw new InvalidSetException("Sets cannot be null");
      }
      
      if (setA.isEmpty()) {
          throw new InvalidSetException("Set A cannot be empty");
      }
      
      if (setB.isEmpty()) {
          throw new InvalidSetException("Set B cannot be empty");
      }
  }
  
  /**
   * Generate statistics about the cartesian product
   * 
   * @param product The cartesian product
   * @return Statistics string
   */
  public String generateStatistics(List<CartesianPair> product) {
      long numericPairs = product.stream()
              .filter(CartesianPair::isNumericPair)
              .count();
      
      StringBuilder stats = new StringBuilder();
      stats.append("Total pairs: ").append(product.size()).append("\n");
      stats.append("Numeric pairs: ").append(numericPairs).append("\n");
      stats.append("Non-numeric pairs: ").append(product.size() - numericPairs);
      
      return stats.toString();
  }
}

package com.Cartesian.Model;

import java.util.Objects;

/**
* Represents an ordered pair (a, b) in the Cartesian product A × B
* An ordered pair consists of two elements where order matters
*/
public class CartesianPair {
  
  private final SetElement first;
  private final SetElement second;
  
  /**
   * Constructor
   * @param first The first element from set A
   * @param second The second element from set B
   */
  public CartesianPair(SetElement first, SetElement second) {
      if (first == null || second == null) {
          throw new IllegalArgumentException("Pair elements cannot be null");
      }
      this.first = first;
      this.second = second;
  }
  
  /**
   * Get the first element of the pair
   * @return first element
   */
  public SetElement getFirst() {
      return first;
  }
  
  /**
   * Get the second element of the pair
   * @return second element
   */
  public SetElement getSecond() {
      return second;
  }
  
  /**
   * Check if both elements in the pair are numeric
   * @return true if both numeric, false otherwise
   */
  public boolean isNumericPair() {
      return first.isNumeric() && second.isNumeric();
  }
  
  /**
   * Get the sum if both elements are numeric
   * @return sum or null if not numeric
   */
  public Double getSum() {
      if (isNumericPair()) {
          return first.getNumericValue() + second.getNumericValue();
      }
      return null;
  }
  
  /**
   * Get the product if both elements are numeric
   * @return product or null if not numeric
   */
  public Double getProduct() {
      if (isNumericPair()) {
          return first.getNumericValue() * second.getNumericValue();
      }
      return null;
  }
  
  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      CartesianPair that = (CartesianPair) obj;
      return Objects.equals(first, that.first) && 
             Objects.equals(second, that.second);
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(first, second);
  }
  
  @Override
  public String toString() {
      return "(" + first.getValue() + ", " + second.getValue() + ")";
  }
  
  /**
   * Get detailed string representation with set information
   * @return detailed string
   */
  public String toDetailedString() {
      return "(" + first.getValue() + " ∈ " + first.getSetName() + 
             ", " + second.getValue() + " ∈ " + second.getSetName() + ")";
  }
}

package com.Cartesian.Model;

import java.util.Objects;

/**
* Represents a single element in a set
* Can hold any type of value (String, Integer, etc.)
*/
public class SetElement {
  
  private final String value;
  private final String setName;
  
  /**
   * Constructor
   * @param value The value of the element
   * @param setName The name of the set this element belongs to
   */
  public SetElement(String value, String setName) {
      this.value = value != null ? value.trim() : "";
      this.setName = setName;
  }
  
  /**
   * Get the value of this element
   * @return element value
   */
  public String getValue() {
      return value;
  }
  
  /**
   * Get the set name this element belongs to
   * @return set name
   */
  public String getSetName() {
      return setName;
  }
  
  /**
   * Check if this element is numeric
   * @return true if numeric, false otherwise
   */
  public boolean isNumeric() {
      try {
          Double.parseDouble(value);
          return true;
      } catch (NumberFormatException e) {
          return false;
      }
  }
  
  /**
   * Get numeric value if element is numeric
   * @return numeric value or null
   */
  public Double getNumericValue() {
      if (isNumeric()) {
          return Double.parseDouble(value);
      }
      return null;
  }
  
  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      SetElement that = (SetElement) obj;
      return Objects.equals(value, that.value);
  }
  
  @Override
  public int hashCode() {
      return Objects.hash(value);
  }
  
  @Override
  public String toString() {
      return value;
  }
}

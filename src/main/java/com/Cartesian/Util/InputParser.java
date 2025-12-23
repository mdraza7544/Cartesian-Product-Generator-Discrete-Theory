package com.Cartesian.Util;


import com.Cartesian.Model.SetElement;
import com.Cartesian.exception.InvalidSetException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* Utility class for parsing user input into sets
* Handles various input formats and validates data
*/
public class InputParser {
  
  private static final String DELIMITER = ",";
  private static final String SET_NOTATION_START = "{";
  private static final String SET_NOTATION_END = "}";
  
  /**
   * Parse a comma-separated string into a list of SetElements
   * Supports various formats:
   * - Simple: "1, 2, 3"
   * - Set notation: "{1, 2, 3}"
   * - Mixed: "apple, banana, cherry"
   * 
   * @param input The input string
   * @param setName The name of the set being parsed
   * @return List of SetElement objects
   * @throws InvalidSetException if input is invalid
   */
  public List<SetElement> parseSet(String input, String setName) throws InvalidSetException {
      if (input == null || input.trim().isEmpty()) {
          throw new InvalidSetException("Input cannot be null or empty for set " + setName);
      }
      
      // Remove set notation if present
      String cleanedInput = removeSetNotation(input.trim());
      
      // Split by delimiter
      String[] elements = cleanedInput.split(DELIMITER);
      
      if (elements.length == 0) {
          throw new InvalidSetException("No elements found in set " + setName);
      }
      
      List<SetElement> setElements = new ArrayList<>();
      
      for (String element : elements) {
          String trimmed = element.trim();
          
          if (trimmed.isEmpty()) {
              continue; // Skip empty elements
          }
          
          // Validate element
          if (!isValidElement(trimmed)) {
              throw new InvalidSetException(
                  "Invalid element '" + trimmed + "' in set " + setName
              );
          }
          
          setElements.add(new SetElement(trimmed, setName));
      }
      
      if (setElements.isEmpty()) {
          throw new InvalidSetException("Set " + setName + " contains no valid elements");
      }
      
      // Check for duplicates and warn (but allow them)
      long distinctCount = setElements.stream()
              .map(SetElement::getValue)
              .distinct()
              .count();
      
      if (distinctCount < setElements.size()) {
          System.out.println("Warning: Set " + setName + " contains duplicate elements. " +
                           "In mathematical sets, duplicates are typically ignored.");
      }
      
      return setElements;
  }
  
  /**
   * Remove set notation braces from input
   * Example: "{1, 2, 3}" -> "1, 2, 3"
   * 
   * @param input Input string
   * @return Cleaned string
   */
  private String removeSetNotation(String input) {
      String result = input;
      
      if (result.startsWith(SET_NOTATION_START)) {
          result = result.substring(1);
      }
      
      if (result.endsWith(SET_NOTATION_END)) {
          result = result.substring(0, result.length() - 1);
      }
      
      return result.trim();
  }
  
  /**
   * Validate if an element is valid
   * Elements should not contain special characters that might cause issues
   * 
   * @param element The element to validate
   * @return true if valid, false otherwise
   */
  private boolean isValidElement(String element) {
      if (element == null || element.isEmpty()) {
          return false;
      }
      
      // Allow alphanumeric, spaces, dots, hyphens, underscores
      return element.matches("[a-zA-Z0-9\\s._-]+");
  }
  
  /**
   * Parse a set from array of strings
   * 
   * @param elements Array of element strings
   * @param setName Name of the set
   * @return List of SetElement objects
   * @throws InvalidSetException if input is invalid
   */
  public List<SetElement> parseSetFromArray(String[] elements, String setName) 
          throws InvalidSetException {
      
      if (elements == null || elements.length == 0) {
          throw new InvalidSetException("Element array cannot be null or empty for set " + setName);
      }
      
      return Arrays.stream(elements)
              .map(String::trim)
              .filter(s -> !s.isEmpty())
              .map(s -> new SetElement(s, setName))
              .collect(Collectors.toList());
  }
  
  /**
   * Parse integer range into a set
   * Example: parseRange(1, 5, "A") creates {1, 2, 3, 4, 5}
   * 
   * @param start Start value (inclusive)
   * @param end End value (inclusive)
   * @param setName Name of the set
   * @return List of SetElement objects
   * @throws InvalidSetException if range is invalid
   */
  public List<SetElement> parseRange(int start, int end, String setName) 
          throws InvalidSetException {
      
      if (start > end) {
          throw new InvalidSetException(
              "Invalid range for set " + setName + ": start must be <= end"
          );
      }
      
      List<SetElement> elements = new ArrayList<>();
      for (int i = start; i <= end; i++) {
          elements.add(new SetElement(String.valueOf(i), setName));
      }
      
      return elements;
  }
  
  /**
   * Remove duplicate elements from a set
   * 
   * @param elements List of elements
   * @return List without duplicates
   */
  public List<SetElement> removeDuplicates(List<SetElement> elements) {
      return elements.stream()
              .distinct()
              .collect(Collectors.toList());
  }
  
  /**
   * Format a set for display
   * 
   * @param elements List of elements
   * @return Formatted string representation
   */
  public String formatSet(List<SetElement> elements) {
      if (elements == null || elements.isEmpty()) {
          return "∅"; // Empty set symbol
      }
      
      String content = elements.stream()
              .map(SetElement::getValue)
              .collect(Collectors.joining(", "));
      
      return "{ " + content + " }";
  }
}

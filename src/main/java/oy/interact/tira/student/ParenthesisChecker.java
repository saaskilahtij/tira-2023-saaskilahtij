package oy.interact.tira.student;


import oy.interact.tira.util.StackInterface;

public class ParenthesisChecker {

   private ParenthesisChecker() {
   }

   /**
    * TODO: Student: Implement this method which checks if the given string has matching opening and closing
    * parentheses. It should check for matching parentheses:

    *   Lorem ipsum ( dolor sit {  amet, [ consectetur adipiscing ] elit, sed } do eiusmod tempor ) incididunt ut...,
    * 
    * that can be found for example in Java source code and JSON structures.
    * 
    * If the string has issues with parentheses, you should throw a {@code ParenthesisException} with a
    * descriptive message and error code. Error codes are already defined for you in the ParenthesesException
    * class to be used.
    * 
    * NOTE: If the string contains quotation marks ("like this"), the text between quotation marks 
    * MUST be ignored. Why? In structured text, the rule of the parentheses applies only to the structured
    * text but not the text in quotation marks. It is totally valid to have JSON:
    * 
    * {
    *    "somekey": "Some value [ with that opening bracket only" 
    * }
    *
    * and that is perfectly ok and acceptable, also in source code like Java.
    *
    * Note that the exception thrown must include correct line and column numbers of the
    * place where it became obvious that there are incorrect parenthesis in the input text.
    *
    * What is to be tested about the incoming string:
    *
    * - If a quotation mark was found, all characters until the next quotation mark must be ignored.
    *   And obviously, before and after, all charactes must be checked if they are parenthesis chars.
    * - When an opening parenthesis is found in the string, it is successfully pushed to the stack (push may fail).
    * - When a closing parenthesis is found in the string, chech that a matching opening parenthesis is popped from the stack.
    * - If the stack was empty, this indicates an error, too many opening parentheses (or too few closing ones).
    *   Note that you can check if the stack is empty before calling pop() and throw an exception.
    * - When the string has been handled, and if the stack still has parentheses, there are too few closing parentheses.
    * 
    * @param stack The stack object used in checking the parentheses from the string.
    * @param fromString A string containing parentheses to check if it is valid.
    * @return Returns the number of parentheses found from the input in total (both opening and closing).
    * @throws ParenthesesException if the parentheses did not match as intended.
    * @throws StackAllocationException If the stack cannot be allocated or reallocated if necessary.
    */
    public static int checkParentheses(StackInterface<Character> stack, String fromString) throws ParenthesesException {
      
      int rowNum = 1;
      int columnNum = 0;
      int parentheses = 0;
      boolean inQuote = false;

      for (int letter = 0; letter < fromString.length(); letter++) {
        columnNum++;
        char current = fromString.charAt(letter);

        if (current == '\n') {
          ++rowNum;
          columnNum = 0;
          continue;
        }

        if (current == '"') {
          inQuote =! inQuote;
          continue;
        }

        if (!inQuote) {
          if (current == '(' || current == '[' || current == '{' ) {
            try {
              stack.push(current);
              parentheses += 2;
            } catch (Exception e) {
              throw new ParenthesesException("Failed pushing parenthesis to the stack", 
                rowNum, columnNum, 
                ParenthesesException.STACK_FAILURE);
            }
          } else if (current == ')' || current == ']' || current == '}') {
            try {
              char openingParentheses = stack.pop();
              if (!isMatching(openingParentheses, current)) {
                throw new ParenthesesException("Parenthesis didn't match", 
                  rowNum, columnNum, 
                  ParenthesesException.PARENTHESES_IN_WRONG_ORDER);
              }
            } catch (Exception e) {
              // TODO: handle null
            }
          }
        }
      }
      
      if (!stack.isEmpty()) {
        throw new ParenthesesException("Too many opening parentheses", 
          rowNum, columnNum, 
          ParenthesesException.TOO_MANY_OPENING_PARENTHESES);
        // Throw error of more opening parentheses than closing ones
      }

      return parentheses;
      // check the popped opening parenthesis against the closing parenthesis read from the string
      // if they do not match -- opening was { but closing was ], for example.
      // throw an exception, wrong kind of parenthesis were in the text (e.g. "asfa ( asdf } sadf")

      
      // TODO:
      // for each character in the input string
      //   if in between of quotes
      //      ignore this character (but count column numbers)
      //   if character is an opening parenthesis -- one of "([{"
      //      push it into the stack (check for failure and throw an exception if so)

      //   else if character is a closing parenthesis -- one of ")]}"
      //      pop the latest opening parenthesis from the stack
      //      if the popped item is null
      //         throw an exception, there are too many closing parentheses 

      //      check the popped opening parenthesis against the closing parenthesis read from the string
      //      if they do not match -- opening was { but closing was ], for example.
      //         throw an exception, wrong kind of parenthesis were in the text (e.g. "asfa ( asdf } sadf")
      
      // if the stack is not empty after all the characters have been handled
      //   throw an exception since the string has more opening than closing parentheses.
    }
    
    private static boolean isMatching(char open, char close) {
      return (open == '(' && close == ')') || (open == '{' && close == '}') || (open == '[' && close == ']');
    } 

  }

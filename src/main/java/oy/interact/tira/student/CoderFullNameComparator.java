package oy.interact.tira.student;
import oy.interact.tira.model.Coder;
import java.util.Comparator;

public class CoderFullNameComparator implements Comparator<Coder>{

  @Override
  public int compare(Coder c1, Coder c2) {

    // Compares coder full names and returns 1 if c1 full name comes first
    // and -1 if c2 full name comes first
    // If the full names are the same returns 0
    if (c1.getLastName().compareTo(c2.getLastName()) < 0) {
      return 1;
    } else if (c1.getLastName().compareTo(c2.getLastName()) > 0) {
      return -1;
    } else {
      if (c1.getFirstName().compareTo(c2.getFirstName()) < 0) {
        return 1;
      } else if (c1.getFirstName().compareTo(c2.getFirstName()) > 0) {
        return -1;
      } else {
        return 0;
      }
    }
  }
}

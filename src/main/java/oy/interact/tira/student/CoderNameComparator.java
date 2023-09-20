package oy.interact.tira.student;
import oy.interact.tira.model.Coder;
import java.util.Comparator;

public class CoderNameComparator implements Comparator<Coder> {

  // Compares coder names and returns 1 if c1 name comes first
  // and -1 if c2 name comes first
  // If the names are the same returns 0
  @Override
  public int compare(Coder c1, Coder c2) {
    if (c1.getCoderName().compareTo(c2.getCoderName()) > 0) {
      return 1;
    } else if (c1.getCoderName().compareTo(c2.getCoderName()) < 0) {
      return -1;
    }
    return 0;
  }
}

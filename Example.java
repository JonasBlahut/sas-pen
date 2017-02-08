
/**
 * Beschreiben Sie hier die Klasse SchwarzesQuadrat.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
import sas.*;
public class Example
{
  public static void main(String[] args)
  {
    new View(600,600);
    Pen p = new Pen();
    p.down();
    p.moveTo(300, 300);
    p.moveFor(150);
    p.turnBy(90);
    p.moveFor(150);
    new Rectangle(25,25, 150, 150);
  }
}


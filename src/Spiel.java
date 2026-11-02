import java.util.Scanner;
public class Spiel {
 private static Scanner sc=new Scanner(System.in);
 public static Spieler spieler;
 public static Spielfeld sf;
 private static boolean abgebrochen;
 public static void main(String[] args){
  System.out.println("Herzlich Willkommen zu Sudoku");
  System.out.println("Dein Name:");
  spieler=new Spieler(sc.nextLine());
  int eingabe;
  do{
   eingabe=menu();
   if(eingabe<4){sf=new Spielfeld(eingabe);spielen();}
  }while(eingabe!=4);
  sc.close();
 }
 public static int menu(){
  int eingabe;
  do{
   System.out.println("1 - Leicht, 2 - Mittel, 3 - Schwer, 4 - Beenden");
   eingabe=liesZahl();
  }while(!checkEingabeMenu(eingabe));
  return eingabe;
 }
 public static boolean checkEingabeMenu(int eingabe){return eingabe>=1&&eingabe<=4;}
 public static void spielen(){
  abgebrochen=false;
  while(!sf.istVoll()&&!abgebrochen){System.out.println(sf);spielzug();}
  System.out.println(sf);
  if(abgebrochen)System.out.println("Zurueck zum Menue.");
  else System.out.println(auswertung());
 }
 public static void spielzug(){
  while(true){
   System.out.println(spieler.getName()+": Zeile (ab 0, -1 = Menue):");
   int zeile=liesZahl();
   if(zeile==-1){abgebrochen=true;return;}
   System.out.println("Spalte (ab 0):");int spalte=liesZahl();
   System.out.println("Zahl:");int wert=liesZahl();
   if(sf.gueltigeEingabe(zeile,spalte,wert)){sf.setStelle(zeile,spalte,wert);return;}
   System.out.println("Ungueltige Eingabe oder vorgegebenes Feld.");
  }
 }
 public static String auswertung(){
  return sf.geloest()?"Herzlichen Glueckwunsch! Du hast das Sudoku korrekt geloest!":
   "Schade! Da ist was falsch. Naechstes Mal klappt es bestimmt besser.";
 }
 private static int liesZahl(){
  while(true)try{return Integer.parseInt(sc.nextLine().trim());}
  catch(NumberFormatException e){System.out.println("Bitte eine ganze Zahl eingeben.");}
 }
}
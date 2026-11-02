public class Spielfeld {
 private int[][] feld;
 private boolean[][] gegebeneStellen;
 private int n;
 public Spielfeld(int level){
  switch(level){
   case 1:
    n=2;
    feld=new int[][]{{1,2,3,4},{3,4,1,2},{2,1,4,0},{4,3,0,1}};
    break;
   case 2:
    n=2;
    feld=new int[][]{{1,0,0,4},{0,4,1,0},{2,0,4,3},{4,3,0,1}};
    break;
   case 3:
    n=3;
    feld=new int[][]{{5,0,4,6,7,8,9,1,0},{6,7,2,0,9,5,3,0,8},{1,9,8,3,0,2,5,6,7},
     {8,5,0,7,6,1,4,2,3},{4,2,6,8,0,3,7,9,1},{7,1,3,9,2,0,0,5,6},
     {9,6,1,5,3,7,2,0,4},{2,0,7,4,1,9,6,3,5},{0,4,5,2,8,6,1,7,0}};
    break;
   default:throw new IllegalArgumentException("Unknown difficulty");
  }
  initialisiereGegebeneStellen();
 }
 public Spielfeld(int[][] feld){this.feld=feld;n=(int)Math.sqrt(feld.length);initialisiereGegebeneStellen();}
 public void initialisiereGegebeneStellen(){
  gegebeneStellen=new boolean[n*n][n*n];
  for(int i=0;i<n*n;i++)for(int j=0;j<n*n;j++)gegebeneStellen[i][j]=feld[i][j]>0;
 }
 public boolean getGegebeneStellen(int zeile,int spalte){return gegebeneStellen[zeile][spalte];}
 public int getStelle(int zeile,int spalte){return feld[zeile][spalte];}
 public void setStelle(int zeile,int spalte,int wert){feld[zeile][spalte]=wert;}
 public boolean gueltigeEingabe(int zeile,int spalte,int wert){
  return zeile>=0&&zeile<n*n&&spalte>=0&&spalte<n*n&&wert>=1&&wert<=n*n&&!gegebeneStellen[zeile][spalte];
 }
 public boolean istVoll(){
  for(int i=0;i<feld.length;i++)for(int j=0;j<feld.length;j++)if(feld[i][j]==0)return false;
  return true;
 }
 public boolean enthaeltJedeZahl(int[] feld){
  for(int zahl=1;zahl<=n*n;zahl++){
   boolean gefunden=false;
   for(int i=0;i<feld.length;i++)if(zahl==feld[i])gefunden=true;
   if(!gefunden)return false;
  }
  return true;
 }
 public boolean checkZeilen(){
  for(int i=0;i<feld.length;i++)if(!enthaeltJedeZahl(feld[i]))return false;
  return true;
 }
 public boolean checkSpalten(){
  for(int j=0;j<feld.length;j++){
   int[] spalte=new int[feld.length];
   for(int i=0;i<feld.length;i++)spalte[i]=feld[i][j];
   if(!enthaeltJedeZahl(spalte))return false;
  }
  return true;
 }
 public boolean checkBloecke(){
  int[] block=new int[n*n];
  for(int zeile=0;zeile<n*n;zeile+=n)
   for(int spalte=0;spalte<n*n;spalte+=n){
    int index=0;
    for(int i=0;i<n;i++)for(int j=0;j<n;j++)block[index++]=feld[zeile+i][spalte+j];
    if(!enthaeltJedeZahl(block))return false;
   }
  return true;
 }
 public boolean geloest(){return istVoll()&&checkZeilen()&&checkSpalten()&&checkBloecke();}
 public String toString(){
  String ausgabe="";
  int groesse=n*n;
  ausgabe+=String.format("%4s","");
  for(int j=0;j<groesse;j++){
   ausgabe+=String.format(" %2d ",j);
   if((j+1)%n==0)ausgabe+=" ";
  }
  ausgabe+="\n";
  for(int i=0;i<groesse;i++){
   if(i%n==0){
    ausgabe+=String.format("%3s","")+"+";
    for(int j=0;j<groesse;j++){ausgabe+="----";if((j+1)%n==0)ausgabe+="+";}
    ausgabe+="\n";
   }
   ausgabe+=String.format("%2d ",i);
   for(int j=0;j<groesse;j++){
    if(j%n==0)ausgabe+="|";
    ausgabe+=feld[i][j]==0?String.format(" %2s ","-"):String.format(" %2d ",feld[i][j]);
   }
   ausgabe+="|\n";
  }
  ausgabe+=String.format("%3s","")+"+";
  for(int j=0;j<groesse;j++){ausgabe+="----";if((j+1)%n==0)ausgabe+="+";}
  return ausgabe+"\n";
 }
}
class Counter{
  private static int nosCounters =0;
  private int value;
  public Counter(){
    value = 0;
    nosCounters ++;
  }
  public void count(){
    value = value + 1;
  }
  public int getValue(){
    return value;
  }
  public static void main(String[] args){
    Counter boardingCounter = new Counter();
    boardingCounter.count();
    int tall = boardingCounter.getValue();
    System.out.println("svar: " + tall);
  }
}

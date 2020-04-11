public class Ol extends Drikke{
    
    public Ol(int rom){
        super(rom);
    }
    
    @Override
    public void drikkGlass(){
        if(dlIFlaska < 5){
            dlIFlaska = 0;
        }
        dlIFlaska -= 5;
    }

    @Override
    public String toString(){
        return "saa mye igjen av oelen: " + dlIFlaska;
    }
}
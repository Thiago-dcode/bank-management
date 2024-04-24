package utils;

public class NumberGame {
    private int number;
    public NumberGame(int number) {
        this.number = number;
    }
    public NumberGame() {

    }

    public void setNumber(final int number) {
        this.number = number;
    }
    public String[] start(){
        String[] result = new String[number];
        for (int i = 1; i <= number; i++) {
            int index = i -1;

                if(i%3 == 0 && i%5 == 0){
                    result[index] = "fish-bus";
                }
                else if(i%3 == 0){
                    result[index] = "fish";

                } else if (i%5 == 0) {
                    result[index] = "bus";

                }else{
                    result[index] = Integer.toString(i);
                }

        }
        return result;

    }

}

import java.util.Random;

public class RandomGenerator {
private String randomNum;
    
    public void randomIDGen() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));

        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        setRandomNum(randomID);
    }

   

    /*Generates the Random Number of Seats for each flight*/
    public int randomNumOfSeats() {
        Random random = new Random();
        int numOfSeats = random.nextInt(500);
        while (numOfSeats < 75) {
            numOfSeats = random.nextInt(500);
        }
        return numOfSeats;
    }

    /*Generates the Unique Flight Number....*/
    public String randomFlightNumbGen(int uptoHowManyLettersRequired, int divisible) {
        Random random = new Random();
        StringBuilder randomAlphabets = new StringBuilder();
        for (int i = 0; i < uptoHowManyLettersRequired; i++) {
            randomAlphabets.append((char) (random.nextInt(26) + 'a'));
        }
        randomAlphabets.append("-").append(randomNumOfSeats() / divisible);
        return randomAlphabets.toString();
    }

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    public String getRandomNumber() {
        return randomNum;
    }
}

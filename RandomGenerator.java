import java.util.Random;

public class RandomGenerator {

    //        ************************************************************ Fields ************************************************************

    private String randomNum;
    /*  City name is at the 0-index, its latitude is on the 1-index and longitude on the 2-index*/
    private static final String[][] destinations = {
            {"Lucknow", "26.850000", "80.949997"}, {"Chennai", "13.067439", "80.237617"}, {"Jaisalmer", "26.911661", "70.922928"},
            {"Mumbai","19.076090", "72.877426"}, {"Mysore", "12.311827", "76.652985"}, {"Jammu", "32.732998", "74.864273"},
            {"Ranchi", "23.344315", "85.296013"}, {"Coimbatore", "11.004556", "76.961632"}, {"Jodhpur", "26.263863", "73.008957"}, {"New Delhi", "28.555764", "77.096520"},
            {"Chandigarh", "30.667767", "76.786232"}, {"Daman", "20.397373", "72.832802"}, {"Kanpur", "26.449923", "80.331871"}, {"Udiapur", "24.571270", "73.691544"},
            {"Diu", "20.714273", "70.921616"}, {"Darjeeling", "27.036007", "88.262672"}, {"Varanasi", "25.321684", "82.987289"},
            {"Patna", "26.996279", "84.820374"}, {"Dehradun", "30.316496", "78.032188"}, {"Kota", "25.18254", "75.83907"},
            {"Visakhapatnam", "17.728647", "83.223549"}, {"Dhanbad", "23.795399", "86.427040"}, {"Leh", "34.152588", "77.577049"}, {"Vellore", "12.934968", "79.146881"},
            {"Bhubaneshwar", "20.256399", "77.710136"}, {"Gaya", "24.780010", "84.981827"}, {"Nagpur", "21.146633", "79.088860"}, 
            {"Bangalore", "12.971599", "77.594566"}, {"Gwalior", "26.218287", "78.182831"}, {"Madurai", "9.939093", "78.121719"}, 
            {"Agra", "27.176670", "78.008072"}, {"Pune", "18.579019", "73.908572"}, {"Goa", "15.496777", "73.827827"},
            {"Aligarh", "27.897394", "78.088013"}, {"Guwahati", "23.847177", "90.404133"}, {"Pondicherry", "11.916064", "79.812325"}, 
            {"Amritsar", "31.633980", "74.872261"}, {"Raipur", "21.2513844", "81.6296413"}, {"Noida", "28.535517", "77.391029"},
            {"Bhopal", "23.259933", "77.412613"}, {"Hyderabad", "17.387140", "78.491684"}, {"Srinagar", "34.083656", "74.797371"},
            {"Kolkata", "22.572645", "88.363892"}, {"Indore", "22.719568", "75.857727"}, {"Surat", "21.170240", "72.831062"},
            {"Cochin", "9.939093", "76.270523"}, {"Jaipur", "26.922070", "75.778885"}, {"Tirupati", "13.629065", "79.424446"}
    };

    //        ************************************************************ Behaviours/Methods ************************************************************


    /* Generates Random ID for the Customers....*/
    public void randomIDGen() {
        Random rand = new Random();
        String randomID = Integer.toString(rand.nextInt(1000000));

        while (Integer.parseInt(randomID) < 20000) {
            randomID = Integer.toString(rand.nextInt(1000000));
        }
        setRandomNum(randomID);
    }

    /*This method sets the destinations for each of the flights from the above destinations randomly.....*/
    public String[][] randomDestinations() {
        Random rand = new Random();
        int randomCity1 = rand.nextInt(destinations.length);
        int randomCity2 = rand.nextInt(destinations.length);
        String fromWhichCity = destinations[randomCity1][0];
        String fromWhichCityLat = destinations[randomCity1][1];
        String fromWhichCityLong = destinations[randomCity1][2];
        while (randomCity2 == randomCity1) {
            randomCity2 = rand.nextInt(destinations.length);
        }
        String toWhichCity = destinations[randomCity2][0];
        String toWhichCityLat = destinations[randomCity2][1];
        String toWhichCityLong = destinations[randomCity2][2];
        String[][] chosenDestinations = new String[2][3];
        chosenDestinations[0][0] = fromWhichCity;
        chosenDestinations[0][1] = fromWhichCityLat;
        chosenDestinations[0][2] = fromWhichCityLong;
        chosenDestinations[1][0] = toWhichCity;
        chosenDestinations[1][1] = toWhichCityLat;
        chosenDestinations[1][2] = toWhichCityLong;
        return chosenDestinations;
    }
    // public 

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

    //        ************************************************************ Setters & Getters ************************************************************

    public void setRandomNum(String randomNum) {
        this.randomNum = randomNum;
    }

    public String getRandomNumber() {
        return randomNum;
    }
}

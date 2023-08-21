import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

class FlightSchedule {

    private static int nextFlightDay = 0;
    static final HashMap<String, ArrayList<Flight>> flightMap = new HashMap<>();
    private static final String[][] destinations = {
            { "Lucknow", "26.850000", "80.949997" }, { "Chennai", "13.067439", "80.237617" },
            { "Jaisalmer", "26.911661", "70.922928" },
            { "Mumbai", "19.076090", "72.877426" }, { "Mysore", "12.311827", "76.652985" },
            { "Jammu", "32.732998", "74.864273" },
            { "Ranchi", "23.344315", "85.296013" }, { "Coimbatore", "11.004556", "76.961632" },
            { "Jodhpur", "26.263863", "73.008957" }, { "New Delhi", "28.555764", "77.096520" },
            { "Chandigarh", "30.667767", "76.786232" }, { "Daman", "20.397373", "72.832802" },
            { "Kanpur", "26.449923", "80.331871" }, { "Udiapur", "24.571270", "73.691544" },
            { "Diu", "20.714273", "70.921616" }, { "Darjeeling", "27.036007", "88.262672" },
            { "Varanasi", "25.321684", "82.987289" },
            { "Patna", "26.996279", "84.820374" }, { "Dehradun", "30.316496", "78.032188" },
            { "Kota", "25.18254", "75.83907" },
            { "Visakhapatnam", "17.728647", "83.223549" }, { "Dhanbad", "23.795399", "86.427040" },
            { "Leh", "34.152588", "77.577049" }, { "Vellore", "12.934968", "79.146881" },
            { "Bhubaneshwar", "20.256399", "77.710136" }, { "Gaya", "24.780010", "84.981827" },
            { "Nagpur", "21.146633", "79.088860" },
            { "Bangalore", "12.971599", "77.594566" }, { "Gwalior", "26.218287", "78.182831" },
            { "Madurai", "9.939093", "78.121719" },
            { "Agra", "27.176670", "78.008072" }, { "Pune", "18.579019", "73.908572" },
            { "Goa", "15.496777", "73.827827" },
            { "Aligarh", "27.897394", "78.088013" }, { "Guwahati", "23.847177", "90.404133" },
            { "Pondicherry", "11.916064", "79.812325" },
            { "Amritsar", "31.633980", "74.872261" }, { "Raipur", "21.2513844", "81.6296413" },
            { "Noida", "28.535517", "77.391029" },
            { "Bhopal", "23.259933", "77.412613" }, { "Hyderabad", "17.387140", "78.491684" },
            { "Srinagar", "34.083656", "74.797371" },
            { "Kolkata", "22.572645", "88.363892" }, { "Indore", "22.719568", "75.857727" },
            { "Surat", "21.170240", "72.831062" },
            { "Cochin", "9.939093", "76.270523" }, { "Jaipur", "26.922070", "75.778885" },
            { "Tirupati", "13.629065", "79.424446" }
    };
    public FlightSchedule() {
        for(int i = 0;i<destinations.length;i++) {
            flightMap.put(destinations[i][0] , new ArrayList());
        }
    } 
    public void flights() {
        RandomGenerator r1 = new RandomGenerator();
        for (int i = 0; i < destinations.length; i++) {
            for (int j = 0; j < destinations.length; j++) {
                if(i == j) {
                    continue;
                }
                String chosenDestinations[][] = new String[2][3];
                chosenDestinations[0] = destinations[i];
                chosenDestinations[1] = destinations[j];
                String[] distanceBetweenTheCities = calculateDistance(Double.parseDouble(chosenDestinations[0][1]),
                        Double.parseDouble(chosenDestinations[0][2]), Double.parseDouble(chosenDestinations[1][1]),
                        Double.parseDouble(chosenDestinations[1][2]));
                String flightSchedule = createNewFlightsAndTime();
                String flightNumber = r1.randomFlightNumbGen(2, 1).toUpperCase();
                int numOfSeatsInTheFlight = r1.randomNumOfSeats();
                String gate = r1.randomFlightNumbGen(1, 30);
                flightMap.get(chosenDestinations[0][0]).add(new Flight(flightSchedule, flightNumber, numOfSeatsInTheFlight, chosenDestinations,
                distanceBetweenTheCities, gate.toUpperCase()));
            }
        }
       
    }
    public void DisplayCities() {
        System.out.println();
        System.out.print(
                "+-----------------------+\n");
        System.out.printf(
                "| \tCities          |%n");
        System.out.print(
                "+-----------------------+\n");
        for(int i = 0;i<destinations.length;i++) {
            System.out.println(destinations[i][0]);
            System.out.print(
                "+-----------------------+\n");
        }
        
    }
    public boolean validSourceCity(String city) {
        if(!flightMap.containsKey(city)) {
            System.out.printf("%-50sEnter Valid City Name!!!\n", " ");
            return false;
        }
        return true;
    }
    public void showFlights(String city) {
        int count = 0;
        System.out.println();
        System.out.print(
                "+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        System.out.printf(
                "| Num  | FLIGHT SCHEDULE\t\t\t   | FLIGHT NO | Available Seats  | \tFROM ====>>       | \t====>> TO\t   | \t    ARRIVAL TIME       | FLIGHT TIME |  GATE  |   DISTANCE(MILES/KMS)  |%n");
        System.out.print(
                "+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        for(Flight flight : flightMap.get(city)) {
            System.out.println(flight.toString(++count));
             System.out.print(
                    "+------+-------------------------------------------+-----------+------------------+-----------------------+------------------------+---------------------------+-------------+--------+------------------------+\n");
        }
        
    }
    void deleteFlight(String flightNumber , String sourceCity) {
        boolean isFound = false;
        int index = 0;
        for(Flight  e : flightMap.get(sourceCity)) {
            if(e.getFlightNumber().equals(flightNumber)) {
                isFound = true;
                break;
            }
            index++;
        }
        if (isFound) {
            flightMap.get(sourceCity).remove(index);
        } else {
            System.out.println("Flight with given Number not found...");
        }
    }
    public String createNewFlightsAndTime() {

        Calendar c = Calendar.getInstance();
        nextFlightDay += Math.random() * 7;
        c.add(Calendar.DATE, nextFlightDay);
        c.add(Calendar.HOUR, nextFlightDay);
        c.set(Calendar.MINUTE, ((c.get(Calendar.MINUTE) * 3) - (int) (Math.random() * 45)));
        Date myDateObj = c.getTime();
        LocalDateTime date = Instant.ofEpochMilli(myDateObj.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
        date = getNearestHourQuarter(date);
        return date.format(DateTimeFormatter.ofPattern("EEEE, dd MMMM yyyy, HH:mm a "));
    }
    public LocalDateTime getNearestHourQuarter(LocalDateTime datetime) {
        int minutes = datetime.getMinute();
        int mod = minutes % 15;
        LocalDateTime newDatetime;
        if (mod < 8) {
            newDatetime = datetime.minusMinutes(mod);
        } else {
            newDatetime = datetime.plusMinutes(15 - mod);
        }
        newDatetime = newDatetime.truncatedTo(ChronoUnit.MINUTES);
        return newDatetime;
    }
    public String calculateFlightTime(double distanceBetweenTheCities) {
        double groundSpeed = 450;
        double time = (distanceBetweenTheCities / groundSpeed);
        String timeInString = String.format("%.4s", time);
        String[] timeArray = timeInString.replace('.', ':').split(":");
        int hours = Integer.parseInt(timeArray[0]);
        int minutes = Integer.parseInt(timeArray[1]);
        int modulus = minutes % 5;
        // Changing flight time to make minutes near/divisible to 5.
        if (modulus < 3) {
            minutes -= modulus;
        } else {
            minutes += 5 - modulus;
        }
        if (minutes >= 60) {
            minutes -= 60;
            hours++;
        }
        if (hours <= 9 && Integer.toString(minutes).length() == 1) {
            return String.format("0%s:%s0", hours, minutes);
        } else if (hours <= 9 && Integer.toString(minutes).length() > 1) {
            return String.format("0%s:%s", hours, minutes);
        } else if (hours > 9 && Integer.toString(minutes).length() == 1) {
            return String.format("%s:%s0", hours, minutes);
        } else {
            return String.format("%s:%s", hours, minutes);
        }
    }
    private double degreeToRadian(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radianToDegree(double rad) {
        return (rad * 180.0 / Math.PI);
    }
    public String[] calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double distance = Math.sin(degreeToRadian(lat1)) * Math.sin(degreeToRadian(lat2))
                + Math.cos(degreeToRadian(lat1)) * Math.cos(degreeToRadian(lat2)) * Math.cos(degreeToRadian(theta));
        distance = Math.acos(distance);
        distance = radianToDegree(distance);
        distance = distance * 60 * 1.1515;
        
        String[] distanceString = new String[3];
        distanceString[0] = String.format("%.2f", distance * 0.8684);
        distanceString[1] = String.format("%.2f", distance * 1.609344);
        distanceString[2] = Double.toString(Math.round(distance * 100.0) / 100.0);
        return distanceString;
    }
}
package Tasks2;

import java.util.Scanner;

public class Main {

    private final static int MAX_COUNT_TESTS = 10;
    private final static int MAX_COUNT_CITY = 10_000;
    private final static int MAX_LENGTH_CITY_NAME = 10;
    private final static int MIN_COUNT_NEIGHBORS = 1;
    private final static int MAX_COUNT_NEIGHBORS = Integer.MAX_VALUE;
    private final static int MAX_COUNT_PATH = 100;
    private final static int MAX_COST_CITY = 200_000;


    private static City cityes[];
    private static Route routsForTest[];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int test = scannerInt(scanner, "Enter test count:", MAX_COUNT_TESTS);
        int countCity = scannerInt(scanner, "Enter count City:", MAX_COUNT_CITY);
        cityes = new City[countCity];
        for (int i = 0; i < cityes.length; i++) {
            String nameCity = scannerString(scanner, "Enter " + (i+1) + " city name: ", MAX_LENGTH_CITY_NAME);
            int countNeighbors = scannerInt(scanner, "Enter count neighbors for " + nameCity + ": ", MIN_COUNT_NEIGHBORS, MAX_COUNT_NEIGHBORS);
            cityes[i] = new City(nameCity, i+1, countNeighbors);
            for (int j = 0; j < cityes[i].getNeighbors(); j++) {
                cityes[i].insertNeighbors(scannerRouteCost(scanner, "Enter number route and cost:", cityes[i]), j);
            }
        }

        int paths = scannerPath(scanner, "Enter count path", MAX_COUNT_PATH );
        routsForTest = new Route[paths];
        for (int j = 0; j < paths; j++) {
            routsForTest[j] = scannerCitysForTest(scanner, "Enter paths for test");
        }

        System.out.println("\n-------------------------------------\n");

        for (Route route : routsForTest) {
            getPriceForRoute(route);
            route.setPrice(optimalPrice);
            optimalPrice = 0;
            tempPrice = 0;
        }

    }



    static int optimalPrice = 0;
    static int tempPrice = 0;

    private static void getPriceForRoute (Route route) {
        City fromCity = cityes[route.getIndexFrom() - 1];
        for (Route cityRoute : fromCity.getRoutes()) {
            tempPrice += cityRoute.getPrice();
            if (cityRoute.getIndexWhere() == route.getIndexWhere()) {
                if (optimalPrice == 0 || tempPrice < optimalPrice) {
                    optimalPrice = tempPrice;
                    continue;
                }
            } else {
                getPriceForRoute(new Route(cityRoute.getIndexFrom(), route.getIndexWhere()));
            }
        }
    }

    private static int scannerPath (Scanner scanner, String message, int max) {
        int temp;
        while (true) {
            System.out.println(message);
            temp = scanner.nextInt();

            if (temp > max) {
                System.out.println("Path has been >=100");
            }else {
                break;
            }
        }
        return temp;
    }

    private static Route scannerRouteCost(Scanner scanner, String message, City city) {
        Route temp = new Route();
        while (true) {
            System.out.println(message);
            String route = scanner.nextLine();
            String tempRouts[] = route.split(" ");
            if (tempRouts.length != 2) {
                System.out.println("Input error");
                continue;
            }

            int routeNumber = integerInputValidator(tempRouts[0], 1, cityes.length);
            int costRoute = integerInputValidator(tempRouts[1], 1, MAX_COST_CITY);
            if (routeNumber != -1 || costRoute != -1) {
                temp.setPrice(costRoute);
                temp.setIndexWhere(routeNumber);
                temp.setIndexFrom(city.getID());
                boolean isNewRoute = true;
                for (Route rt : city.getRoutes()) {
                    if (temp.equals(rt)) {
                        isNewRoute = false;
                        System.out.println("Такой маршрут уже задан для этого города");
                        break;
                    }
                }
                if (isNewRoute) {
                    break;
                } else {
                    temp = new Route();
                }
            }
        }
        return temp;
    }

    private static Route scannerCitysForTest (Scanner scanner, String message) {
        Route temp = new Route();
        while (true) {
            System.out.println(message);
            String route = scanner.nextLine();
            String tempRouts[] = route.split(" ");
            if (tempRouts.length != 2) {
                System.out.println("Input error");
                continue;
            }
            for (City city : cityes) {
                if (tempRouts[0].equals(city.getName())) {
                    temp.setIndexFrom(city.getID());
                } else if (tempRouts[1].equals(city.getName())) {
                    temp.setIndexWhere(city.getID());
                }
            }
            if (temp.getIndexWhere() < 0 || temp.getIndexFrom() < 0) {
                System.out.println("Такие города не найдены в списке");
            } else {
                break;
            }
        }
        return temp;
    }

    public static int scannerInt (Scanner scanner, String message, int maxValue) {
        return scannerInt(scanner, message, 0, maxValue);
    }

    public static int scannerInt (Scanner scanner, String message, int minValue, int maxValue) {
        int temp = -1;
        while (true) {
            System.out.println(message);
            String tempStr = scanner.nextLine();
            temp = integerInputValidator(tempStr, minValue, maxValue);
            if (temp > 0) {
                break;
            }
        }
        return temp;
    }

    private static int integerInputValidator(String strToParse, int minValue, int maxValue) {
        int temp = -1;
        try {
            temp = Integer.parseInt(strToParse);
            if (temp < minValue || temp > maxValue) {
                temp = -1;
                System.out.println("Число должно ыбть меньше " + maxValue + " и больше " + minValue);
            }
        } catch (NumberFormatException ex) {
            System.out.println("Вводите только цифры");
        }
        return temp;
    }

    public static String scannerString (Scanner scanner, String message, int length) {
        String temp;
        while (true) {
            System.out.println(message);
            temp = scanner.nextLine();
            boolean isValidString = true; //TODO Add validator (only [a...z])
            if (temp.length() > length || temp.length() == 0) {
                System.out.println("Число должно ыбть больше " + length);
            } else if (!isValidString) {
                System.out.println("Строка должна сождержать только символы [a...z] " );
            } else {
                break;
            }
        }
        return temp;
    }
}

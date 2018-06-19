package Tasks2;

public class Route_Search {
    private int numberOfSearchPaths;
    private String name1;
    private String name2;

    Route_Search(int numberOfSearchPaths, String name1, String name2) {
        this.numberOfSearchPaths = numberOfSearchPaths;
        this.name1 = name1;
        this.name2 = name2;
    }

    public int getNumberOfSearchPaths() {
        return numberOfSearchPaths;
    }

    public void setNumberOfSearchPaths(int numberOfSearchPaths) {
        if (numberOfSearchPaths >= 1) {
            this.numberOfSearchPaths = numberOfSearchPaths;
        } else {
            System.out.println("Некоректно задано количество путей для поиска");
        }
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }
}

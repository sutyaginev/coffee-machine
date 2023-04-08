package machine;
import java.util.Scanner;

class CoffeeMachine {
    CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }
    static final int ESPRESSO_WATER = 250;
    static final int ESPRESSO_MILK = 0;
    static final int ESPRESSO_BEANS = 16;
    static final int ESPRESSO_COST = 4;

    static final int LATTE_WATER = 350;
    static final int LATTE_MILK = 75;
    static final int LATTE_BEANS = 20;
    static final int LATTE_COST = 7;

    static final int CAPPUCCINO_WATER = 200;
    static final int CAPPUCCINO_MILK = 100;
    static final int CAPPUCCINO_BEANS = 12;
    static final int CAPPUCCINO_COST = 6;

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    private State state = State.HOME_SCREEN;

    State getState() {
        return state;
    }

    void setCurrentState(State state) {
        this.state = state;
    }
    static Scanner scanner = new Scanner(System.in);

    //метод по отображению домашнего экрана
    void printHomeScreen() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String actionChoice = scanner.nextLine();

        for (State state : State.values()) {
            if (state.name().equals(actionChoice.toUpperCase())) {
                this.state = State.valueOf(actionChoice.toUpperCase());
            }
        }
    }

    //метод по выводу состояния на экран
    void printRemaining() {
        System.out.printf("""
                The coffee machine has:
                %d ml of water
                %d ml of milk
                %d g of coffee beans
                %d disposable cups
                $%d of money%n""", water, milk, beans, cups, money);

        state = State.HOME_SCREEN;
    }

    //метод по проверке ресурсов
    void checkAndBuy(int waterConst, int milkConst, int beansConst, int costConst) {
        if (water < waterConst) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < milkConst) {
            System.out.println("Sorry, not enough coffee milk!");
        } else if (beans < beansConst) {
            System.out.println("Sorry, not enough coffee beans!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            water -= waterConst;
            milk -= milkConst;
            beans -= beansConst;
            cups--;
            money += costConst;
        }
    }

    //метод по покупке кофе
    void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String coffeeChoice = scanner.nextLine();
        switch (coffeeChoice) {
            case "1":
                checkAndBuy(ESPRESSO_WATER, ESPRESSO_MILK, ESPRESSO_BEANS, ESPRESSO_COST);
                state = State.HOME_SCREEN;
                break;
            case "2":
                checkAndBuy(LATTE_WATER, LATTE_MILK, LATTE_BEANS, LATTE_COST);
                state = State.HOME_SCREEN;
                break;
            case "3":
                checkAndBuy(CAPPUCCINO_WATER, CAPPUCCINO_MILK, CAPPUCCINO_BEANS, CAPPUCCINO_COST);
                state = State.HOME_SCREEN;
                break;
            case "back":
                state = State.HOME_SCREEN;
                break;
        }
    }

    //метод по заполнению кофемашины
    void fillCoffeeMachine() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups you want to add:");
        cups += scanner.nextInt();
        state = State.HOME_SCREEN;
    }

    //метод по забору денег
    void takeMoney() {
        System.out.printf("I gave you $%d%n", money);
        money = 0;
        state = State.HOME_SCREEN;
    }
}
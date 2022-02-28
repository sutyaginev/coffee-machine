import java.util.Scanner;

public class CoffeeMachine {
    private Scanner scanner = new Scanner(System.in);
    private final int WATER_FOR_ESPRESSO_CUP = 250;
    private final int WATER_FOR_LATTE_CUP = 350;
    private final int WATER_FOR_CAPPUCCINO_CUP = 200;
    private final int MILK_FOR_LATTE_CUP = 75;
    private final int MILK_FOR_CAPPUCCINO_CUP = 100;
    private final int BEANS_FOR_ESPRESSO_CUP = 16;
    private final int BEANS_FOR_LATTE_CUP = 20;
    private final int BEANS_FOR_CAPPUCCINO_CUP = 12;
    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int disposableCups = 9;
    private int money = 550;
    public Status status = Status.SHOW;

    public void performAction(String choice) {
        status = Status.valueOf(choice.toUpperCase());

        switch (status) {
            case BUY:
                buyCoffee();
                status = Status.SHOW;
                break;
            case FILL:
                fillCoffeeMachine();
                status = Status.SHOW;
                break;
            case TAKE:
                takeMoney();
                status = Status.SHOW;
                break;
            case REMAINING:
                printRemaining();
                status = Status.SHOW;
                break;
            case EXIT:
                break;
            default:
                System.out.println("Unsupported enum constant.");
        }
    }

    private void buyCoffee() {
        System.out.printf("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:\n");
        String variantOfCoffee = scanner.nextLine();

        switch (variantOfCoffee) {
            case "1":
                if (checkResources(WATER_FOR_ESPRESSO_CUP, 0, BEANS_FOR_ESPRESSO_CUP)) {
                    water -= WATER_FOR_ESPRESSO_CUP;
                    beans -= BEANS_FOR_ESPRESSO_CUP;
                    disposableCups--;
                    money += 4;
                }
                break;
            case "2":
                if (checkResources(WATER_FOR_LATTE_CUP, MILK_FOR_LATTE_CUP, BEANS_FOR_LATTE_CUP)) {
                    water -= WATER_FOR_LATTE_CUP;
                    milk -= MILK_FOR_LATTE_CUP;
                    beans -= BEANS_FOR_LATTE_CUP;
                    disposableCups--;
                    money += 7;
                }
                break;
            case "3":
                if (checkResources(WATER_FOR_CAPPUCCINO_CUP, MILK_FOR_CAPPUCCINO_CUP, BEANS_FOR_CAPPUCCINO_CUP)) {
                    water -= WATER_FOR_CAPPUCCINO_CUP;
                    milk -= MILK_FOR_CAPPUCCINO_CUP;
                    beans -= BEANS_FOR_CAPPUCCINO_CUP;
                    disposableCups--;
                    money += 6;
                }
                break;
            case "back":
                break;
            default:
                System.out.println("Unsupported variant.");
        }
    }

    private void fillCoffeeMachine() {
        System.out.println("\nWrite how many ml of water you want to add:");
        water += Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many ml of milk you want to add:");
        milk += Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans you want to add:");
        beans += Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many disposable cups of coffee you want to add:");
        disposableCups += Integer.parseInt(scanner.nextLine());
    }

    private void takeMoney() {
        System.out.printf("\nI gave you $%d\n", money);
        money = 0;
    }

    private void printRemaining() {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", beans);
        System.out.printf("%d disposable cups\n", disposableCups);
        System.out.printf("$%d of money\n", money);
    }

    private boolean checkResources(int necessaryWater, int necessaryMilk, int necessaryBeans) {
        if (water - necessaryWater < 0) {
            System.out.println("Sorry, not enough water!");
            return false;
        } else if (milk - necessaryMilk < 0) {
            System.out.println("Sorry, not enough milk!");
            return false;
        } else if (beans - necessaryBeans < 0) {
            System.out.println("Sorry, not enough beans!");
            return false;
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            return true;
        }
    }
}

class CoffeeMachineDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();

        while (coffeeMachine.status == Status.SHOW) {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            String choice = scanner.nextLine();
            coffeeMachine.performAction(choice);
        }
    }
}

enum Status {
    SHOW,
    BUY,
    FILL,
    TAKE,
    REMAINING,
    EXIT
}
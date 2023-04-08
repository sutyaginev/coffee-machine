package machine;

public class Main {

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine1 = new CoffeeMachine(400, 540, 120, 9, 550);

        while (coffeeMachine1.getState() != State.EXIT) {

            switch (coffeeMachine1.getState()) {
                case HOME_SCREEN:
                    coffeeMachine1.printHomeScreen();
                    break;
                case BUY:
                    coffeeMachine1.buyCoffee();
                    break;
                case FILL:
                    coffeeMachine1.fillCoffeeMachine();
                    break;
                case TAKE:
                    coffeeMachine1.takeMoney();
                    break;
                case REMAINING:
                    coffeeMachine1.printRemaining();
                    break;
                case EXIT:
                    coffeeMachine1.setCurrentState(State.EXIT);
                    break;
            }
        }
    }
}

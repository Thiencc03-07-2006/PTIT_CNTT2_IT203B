package bai5;

interface UserActions {

    default void logActivity(String activity) {
        System.out.println("User log: " + activity);
    }

}
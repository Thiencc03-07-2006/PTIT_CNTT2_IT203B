package bai5;

class SuperAdmin implements UserActions, AdminActions {

    @Override
    public void logActivity(String activity) {
        UserActions.super.logActivity(activity);
    }

}
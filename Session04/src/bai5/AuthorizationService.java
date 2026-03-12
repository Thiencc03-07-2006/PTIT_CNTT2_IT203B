package bai5;

public class AuthorizationService {

    public boolean canPerformAction(User user, Action action) {
        Role role = user.getRole();
        switch (role) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action == Action.LOCK_USER
                        || action == Action.VIEW_PROFILE;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}
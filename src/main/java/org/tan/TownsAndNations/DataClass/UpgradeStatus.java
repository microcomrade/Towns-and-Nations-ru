package org.tan.TownsAndNations.DataClass;

public class UpgradeStatus {
    private boolean unlocked;
    private boolean activated;

    public UpgradeStatus(boolean unlocked, boolean activated) {
        this.unlocked = unlocked;
        this.activated = activated;
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public boolean isActivated() {
        if(!unlocked)
            return true;
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
package me.tofpu.lockeddimension.modules.dimension.values;

import me.tofpu.lockeddimension.config.Values;
import me.tofpu.lockeddimension.modules.action.actions.Denied;
import me.tofpu.lockeddimension.modules.action.actions.Locked;
import me.tofpu.lockeddimension.modules.action.actions.Succeed;

public class DimensionValues {
    private Succeed succeed;
    private Denied denied;
    private Locked locked;

    private boolean isLocked;
    private String permission;

    public boolean isLocked() {
        return isLocked;
    }
    
    public String getPermission() {
        return permission;
    }

    public Succeed getSucceed() {
        return succeed;
    }

    public Denied getDenied() {
        return denied;
    }

    public Locked getLocked() {
        return locked;
    }

    public void setValues(Values values) {
        permission = String.format("lockeddimension.%s", values.getWorld());
        isLocked = values.isLocked();

        succeed = new Succeed(values.getFileConfiguration(), values.getWorld());
        denied = new Denied(values.getFileConfiguration(), values.getWorld());
        locked = new Locked(values.getFileConfiguration(), values.getWorld());
    }
}

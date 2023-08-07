package appeng.helpers;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import appeng.api.networking.IGridHost;
import appeng.api.util.DimensionalCoord;
import appeng.parts.AEBasePart;

public interface IInterfaceTerminalSupport extends IGridHost {

    class PatternsConfiguration {

        public int offset;
        public int size;

        public PatternsConfiguration(int offset, int size) {
            this.offset = offset;
            this.size = size;
        }
    }

    default ForgeDirection getDirection() {
        if (this instanceof AEBasePart) {
            return ((AEBasePart) this).getSide();
        } else {
            return ForgeDirection.UNKNOWN;
        }
    }

    DimensionalCoord getLocation();

    PatternsConfiguration[] getPatternsConfigurations();

    IInventory getPatterns(int index);

    String getName();

    TileEntity getTileEntity();

    default long getSortValue() {
        var te = getTileEntity();
        return ((long) te.zCoord << 24) ^ ((long) te.xCoord << 8) ^ te.yCoord;
    }

    default boolean shouldDisplay() {
        return true;
    }
}

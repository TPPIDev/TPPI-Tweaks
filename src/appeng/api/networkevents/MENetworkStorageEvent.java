package appeng.api.networkevents;

import appeng.api.IItemList;
import appeng.api.me.tiles.IGridTileEntity;

public class MENetworkStorageEvent extends MENetworkEvent
{

	public final IItemList currentItems;

	public MENetworkStorageEvent(IGridTileEntity t, IItemList o)
	{
		super(t);
		currentItems = o;
	}

}

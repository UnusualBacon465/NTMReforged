package api.hbm.fluid;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import com.hbm.inventory.fluid.FluidType;

public interface IPipeNet {
	
	public void joinNetworks(IPipeNet network);

	public List<IFluidConductor> getLinks();
	public Set<IFluidConnector> getSubscribers();

	public IPipeNet joinLink(IFluidConductor conductor);
	public void leaveLink(IFluidConductor conductor);

	public void subscribe(IFluidConnector connector);
	public void unsubscribe(IFluidConnector connector);
	public boolean isSubscribed(IFluidConnector connector);

	public void destroy();
	
	public boolean isValid();
	
	public long transferFluid(long fill, int pressure);
	public FluidType getType();
	public BigInteger getTotalTransfer();
}

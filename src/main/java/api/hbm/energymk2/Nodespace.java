package api.hbm.energymk2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.hbm.util.fauxpointtwelve.DirPos;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

/**
 * The "Nodespace" is an intermediate, "ethereal" layer of abstraction that tracks nodes (i.e. cables)
 * even when they are no longer loaded, allowing continued operation even when unloaded.
 */
public class Nodespace {

	/**
	 * Contains all "NodeWorld" instances, i.e., lists of nodes existing per world.
	 */
	public static final Map<Level, NodeWorld> worlds = new HashMap<>();
	public static final Set<PowerNetMK2> activePowerNets = new HashSet<>();

	public static PowerNode getNode(Level level, BlockPos pos) {
		NodeWorld nodeWorld = worlds.get(level);
		return nodeWorld != null ? nodeWorld.nodes.get(pos) : null;
	}

	public static void createNode(Level level, PowerNode node) {
		NodeWorld nodeWorld = worlds.computeIfAbsent(level, l -> new NodeWorld());
		nodeWorld.pushNode(node);
	}

	public static void destroyNode(Level level, BlockPos pos) {
		PowerNode node = getNode(level, pos);
		if (node != null) {
			worlds.get(level).popNode(node);
		}
	}

	/**
	 * Goes over each node and manages connections.
	 */
	public static void updateNodespace(MinecraftServer server) {
		for (ServerLevel level : server.getAllLevels()) {
			NodeWorld nodes = worlds.get(level);
			if (nodes == null) continue;

			for (Entry<BlockPos, PowerNode> entry : nodes.nodes.entrySet()) {
				PowerNode node = entry.getValue();
				if (!node.hasValidNet() || node.recentlyChanged) {
					checkNodeConnection(level, node);
					node.recentlyChanged = false;
				}
			}
		}

		updatePowerNets();
	}

	private static void updatePowerNets() {
		for (PowerNetMK2 net : activePowerNets) net.resetEnergyTracker(); // Reset before transfer
		for (PowerNetMK2 net : activePowerNets) net.transferPower();
	}

	/**
	 * Connects nodes and joins networks.
	 */
	private static void checkNodeConnection(Level level, PowerNode node) {
		for (DirPos con : node.connections) {
			PowerNode conNode = getNode(level, con.getBlockPos());
			if (conNode != null) {
				if (conNode.hasValidNet() && conNode.net == node.net) continue;

				if (checkConnection(conNode, con, false)) {
					connectToNode(node, conNode);
				}
			}
		}

		if (node.net == null || !node.net.isValid()) new PowerNetMK2().joinLink(node);
	}

	public static boolean checkConnection(PowerNode connectsTo, DirPos connectFrom, boolean skipSideCheck) {
		for (DirPos revCon : connectsTo.connections) {
			if (revCon.getBlockPos().equals(connectFrom.getBlockPos().relative(connectFrom.getDir().getOpposite()))
					&& (revCon.getDir() == connectFrom.getDir().getOpposite() || skipSideCheck)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Links two nodes with different or potentially no networks.
	 */
	private static void connectToNode(PowerNode origin, PowerNode connection) {
		if (origin.hasValidNet() && connection.hasValidNet()) {
			if (origin.net.links.size() > connection.net.links.size()) {
				origin.net.joinNetworks(connection.net);
			} else {
				connection.net.joinNetworks(origin.net);
			}
		} else if (!origin.hasValidNet() && connection.hasValidNet()) {
			connection.net.joinLink(origin);
		} else if (origin.hasValidNet() && !connection.hasValidNet()) {
			origin.net.joinLink(connection);
		}
	}

	public static PowerNode getNode(Level level, int x, int y, int z) {
		return null;
	}

	public static class NodeWorld {
		/**
		 * Tracks nodes by position.
		 */
		public final Map<BlockPos, PowerNode> nodes = new HashMap<>();

		/**
		 * Adds a node to all positions in the nodespace.
		 */
		public void pushNode(PowerNode node) {
			for (BlockPos pos : node.positions) {
				nodes.put(pos, node);
			}
		}

		/**
		 * Removes the specified node from the nodespace.
		 */
		public void popNode(PowerNode node) {
			if (node.net != null) node.net.destroy();
			for (BlockPos pos : node.positions) {
				nodes.remove(pos);
				node.expired = true;
			}
		}
	}

	public static class PowerNode {

		public BlockPos[] positions = new BlockPos[0];
		public DirPos[] connections;
		public PowerNetMK2 net;
		public boolean expired = false;
		public boolean recentlyChanged = true;

		public PowerNode(BlockPos... positions) {
			this.positions = positions;
		}

		public PowerNode(com.hbm.util.fauxpointtwelve.BlockPos blockPos) {

		}

		public PowerNode setConnections(DirPos... connections) {
			this.connections = connections;
			return this;
		}

		public boolean hasValidNet() {
			return this.net != null && this.net.isValid();
		}

		public void setNet(PowerNetMK2 net) {
			this.net = net;
			this.recentlyChanged = true;
		}
	}
}
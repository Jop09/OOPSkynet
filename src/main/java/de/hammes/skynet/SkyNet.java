package de.hammes.skynet;

import skynet.SubnetBackdoor;

public class SkyNet {

	private IStrategy strategy = new DeleteGatewayLinkStrategy();

	public void killAgent(SubnetBackdoor sb) {

		while (sb.isAgentStillMoving()) {
			int[] disconnectingNodes = strategy.chooseDisconnectingNodes(sb);
			sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
		}
	}

	public void setStrategy(IStrategy strategy) {
		this.strategy = strategy;
	}

}

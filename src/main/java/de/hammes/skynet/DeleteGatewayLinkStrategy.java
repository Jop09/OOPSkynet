package de.hammes.skynet;

import skynet.SubnetBackdoor;

public class DeleteGatewayLinkStrategy implements IStrategy{

	public int[] chooseDisconnectingNodes(SubnetBackdoor sb) {

		int[] disconnectingNodes = new int[2];
		int[][] links = sb.getNodeLinks();
		for (int[] link : links) {

			if (linkContainsNodeFromArray(link, sb.getGatewayNodes())) {
				disconnectingNodes = link;
				if (isNodeInLink(link, sb.getAgentPosition())) {
					break;
				}
			}
		}
		return disconnectingNodes;
	}

	public static boolean linkContainsNodeFromArray(int[] link, int[] nodesArray) {
		for (int node : nodesArray) {
			if (isNodeInLink(link, node)) {
				return true;
			}
		}
		return false;
	}

	public static boolean isNodeInLink(int[] link, int wantedNode) {

		return (link[0] == wantedNode) || (link[1] == wantedNode);
	}
}

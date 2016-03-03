package de.hammes.skynet;

import skynet.SubnetBackdoor;

public class SkynetProblem {

	public static int[] chooseDisconnectingNodes(SubnetBackdoor sb) {

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

	public static void killAgent(SubnetBackdoor sb) {
		while (sb.isAgentStillMoving()) {
			int[] disconnectingNodes = chooseDisconnectingNodes(sb);
			sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
			trace(sb);
		}
	}

	private static void trace(SubnetBackdoor sb) {
		System.out.println("NEXT STEP!!");
		int[][] links2 = sb.getNodeLinks();
		for (int[] link2 : links2) {
			System.out.println("" + link2[0] + " " + link2[1] + " " + sb.getAgentPosition());
		}
	}
}

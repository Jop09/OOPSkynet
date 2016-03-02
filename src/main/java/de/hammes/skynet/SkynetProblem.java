package de.hammes.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class SkynetProblem {

	public static int[] chooseDisconnectingNodes(SubnetBackdoor sb) {

		int[] disconnectingLink = new int[2];
		int[] gatewayNodes = sb.getGatewayNodes();
		int[][] links = sb.getNodeLinks();
		int row = 0;
		boolean searchForAgentGatewayLink = true;
		while (searchForAgentGatewayLink && links.length > row) {

			if (isLinkAnAgentGatewayConnection(links[row], sb.getAgentPosition(), gatewayNodes, sb)) {
				searchForAgentGatewayLink = false;
				disconnectingLink[0] = links[row][0];
				disconnectingLink[1] = links[row][1];
			}
			row++;
		}
		if (!searchForAgentGatewayLink) {
			return disconnectingLink;
		} else {
			return chooseAlternativeDisconnectingNodes(sb);
		}
	}

	public static int[] chooseAlternativeDisconnectingNodes(SubnetBackdoor sb) {

		int[] disconnectingLink = new int[2];
		int[] gatewayNodes = sb.getGatewayNodes();
		int[][] links = sb.getNodeLinks();
		int row = 0;
		boolean searchForGatewayLink = true;
		while (searchForGatewayLink && links.length > row) {

			if (containsLinkANodeFromArray(links[row], gatewayNodes)) {
				searchForGatewayLink = false;
				disconnectingLink[0] = links[row][0];
				disconnectingLink[1] = links[row][1];
			}
			row++;
		}
		return disconnectingLink;
	}

	public static boolean isLinkAnAgentGatewayConnection(int[] link, int agentNode, int[] gatewayNodes, SubnetBackdoor sb) {

		return (link[0] == agentNode || link[1] == agentNode) && containsLinkANodeFromArray(link, gatewayNodes) && link[0] != link[1] && sb.isAgentOnAGateway() == false;
	}

	public static boolean containsLinkANodeFromArray(int[] link, int[] nodesArray) {
		boolean nodeFound = false;
		int numNode = 0;
		int arrayLength = nodesArray.length;
		while (!nodeFound && arrayLength > numNode) {
			if (isNodeInLink(link, nodesArray[numNode])) {
				nodeFound = true;
			}
			numNode++;
		}
			return nodeFound;
	}

	public static boolean isNodeInLink(int[] link, int wantedNode) {

		return (link[0] == wantedNode) || (link[1] == wantedNode);
	}

	public static void killAgent(SubnetBackdoor sb) {

		while (sb.isAgentStillMoving()) {
			System.out.println("NEXT STEP!!!!!");
			int[] disconnectingNodes = chooseDisconnectingNodes(sb);
			sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
			int[][] links2 = sb.getNodeLinks();

			for (int[] link2 : links2) {
				System.out.println("" + link2[0] + " " + link2[1] + " " + sb.getAgentPosition());
			}
		}
	}


//
//	public static int[][] getAgentLinks(SubnetBackdoor sb) {
//		int links[][] = sb.getNodeLinks();
//		List test = new ArrayList();
//		int agentLinks[][];
//		int agentLinksCount = 0;
//		int row = 0;
//		boolean searchForAgentGatewayLink = true;
//		while (searchForAgentGatewayLink && links.length > row) {
//			if (links[row][0] == sb.getAgentPosition() || links[row][1] == sb.getAgentPosition()) {
//				agentLinks[agentLinksCount] = links[row];
//			}
//		}
//	}
//
//
//	public static List<List<Integer>> convertLinksToList(SubnetBackdoor sb) {
//		List<List<Integer>> listLinks = new ArrayList<List<Integer>>();
//		int links[][] = sb.getNodeLinks();
//		for (int i = 0; i < links.length; i++) {
//			listLinks.iterator();
//			listLinks.add(links[i][]);
//
//		}
//	}

//	public boolean isGatewayNextToAgent(SubnetBackdoor sb) {
//
//
//	}


	public static void main(String[] args) {

		// SubnetBackdoor sb = SkynetSubnet.createRandomSubnet(2);
		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(2);
		killAgent(sb);
	}
}

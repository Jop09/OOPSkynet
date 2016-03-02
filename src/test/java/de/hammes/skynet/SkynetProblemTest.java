package de.hammes.skynet;

import static org.junit.Assert.*;

import org.junit.Test;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class SkynetProblemTest {

	static int subnet = 2;
	@Test
	public void testChooseDisconnectingNodes() {
		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(subnet);
    	int disconnectingNodes[] = SkynetProblem.chooseDisconnectingNodes(sb);
    	sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
		assertFalse(sb.isAgentOnAGateway());
	}

	@Test
	public void testKillAgent() {
		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(subnet);
		SkynetProblem.killAgent(sb);
		assertFalse(sb.isAgentOnAGateway());
	}

	@Test
	public void testIsNodeInLink() {
		int[] testLink = {1, 5};
		assertTrue(SkynetProblem.isNodeInLink(testLink, 1));
		assertTrue(SkynetProblem.isNodeInLink(testLink, 5));
		assertFalse(SkynetProblem.isNodeInLink(testLink, 7));
	}

	@Test
	public void testContainsLinkANodeFromArray() {
		int[] testLink = {3, 5};
		int[] testArrayTrue = {1, 3, 4, 7, 9};
		int[] testArrayFalse = {1, 2, 4, 7, 9};
		assertTrue(SkynetProblem.containsLinkANodeFromArray(testLink, testArrayTrue));
		assertFalse(SkynetProblem.containsLinkANodeFromArray(testLink, testArrayFalse));
	}

	@Test
	public void testIsLinkAnAgentGatewayConnection() {

		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(2);
		int[][] links = sb.getNodeLinks();

		int[] testLinkFalse = links[0];
		int testNodeFalse = sb.getAgentPosition();
		int[] testArrayFalse = sb.getGatewayNodes();
		assertFalse(SkynetProblem.isLinkAnAgentGatewayConnection(testLinkFalse, testNodeFalse, testArrayFalse, sb));

		sb.disconnectNodesBeforeAgentMovesOn(links[0][0], links[0][1]);
		int[] testLinkTrue = links[3];
		int testNodeTrue = sb.getAgentPosition();
		int[] testArrayTrue = sb.getGatewayNodes();
		assertTrue(SkynetProblem.isLinkAnAgentGatewayConnection(testLinkTrue, testNodeTrue, testArrayTrue, sb));
	}

//	@Test
//	public void testAgentHasGatewayConnection() {
//		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(subnet);
//		SkynetProblem.hasAgentGatewayConnection(sb);
//		assertFalse(sb.isAgentOnAGateway());
//	}

}

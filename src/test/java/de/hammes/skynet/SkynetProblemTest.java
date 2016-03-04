package de.hammes.skynet;

import static org.junit.Assert.*;

import org.junit.Test;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class SkynetProblemTest {

	static int subnet = 2;
	@Test
	public void testChooseDisconnectingNodes() {
		IStrategy strategy = new DeleteGatewayLinkStrategy();
		SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(subnet);
    	int disconnectingNodes[] = strategy.chooseDisconnectingNodes(sb);
    	sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
		assertFalse(sb.isAgentOnAGateway());
	}

	@Test
	public void testKillAgent() {
		for (int i = 1; i < 50; i++) {
			SubnetBackdoor sb = SkynetSubnet.createRandomSubnet(i);
			new SkyNet().killAgent(sb);
			assertFalse(sb.isAgentOnAGateway());
		}
	}

	@Test
	public void testIsNodeInLink() {
		int[] testLink = {1, 5};
		assertTrue(DeleteGatewayLinkStrategy.isNodeInLink(testLink, 1));
		assertTrue(DeleteGatewayLinkStrategy.isNodeInLink(testLink, 5));
		assertFalse(DeleteGatewayLinkStrategy.isNodeInLink(testLink, 7));
	}

	@Test
	public void testContainsLinkANodeFromArray() {
		int[] testLink = {3, 5};
		int[] testArrayTrue = {1, 3, 4, 7, 9};
		int[] testArrayFalse = {1, 2, 4, 7, 9};
		assertTrue(DeleteGatewayLinkStrategy.linkContainsNodeFromArray(testLink, testArrayTrue));
		assertFalse(DeleteGatewayLinkStrategy.linkContainsNodeFromArray(testLink, testArrayFalse));
	}

}

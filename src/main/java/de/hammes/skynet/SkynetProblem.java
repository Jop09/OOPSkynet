package de.hammes.skynet;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

public class SkynetProblem {

    public static int[] chooseDisconnectingNodes(SubnetBackdoor sb, boolean primaryStrategy) {

        int[] disconnectingNodes = new int[2];
        int[][] links = sb.getNodeLinks();
        for (int row = 0; row < links.length; row++) {

            if (linkContainsNodeFromArray(links[row], sb.getGatewayNodes())) {
                if ((primaryStrategy && isNodeInLink(links[row], sb.getAgentPosition())) || !primaryStrategy) {
                    disconnectingNodes[0] = links[row][0];
                    disconnectingNodes[1] = links[row][1];
                    return disconnectingNodes;
                }
            }
        }
        disconnectingNodes = chooseDisconnectingNodes(sb, false);
        return disconnectingNodes;
    }

    public static boolean linkContainsNodeFromArray(int[] link, int[] nodesArray) {
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
            System.out.println("NEXT STEP!!");
            int[] disconnectingNodes = chooseDisconnectingNodes(sb, true);
            sb.disconnectNodesBeforeAgentMovesOn(disconnectingNodes[0], disconnectingNodes[1]);
            int[][] links2 = sb.getNodeLinks();

            for (int[] link2 : links2) {
                System.out.println("" + link2[0] + " " + link2[1] + " " + sb.getAgentPosition());
            }
        }
    }

    public static void main(String[] args) {

        // SubnetBackdoor sb = SkynetSubnet.createRandomSubnet(2);
        SubnetBackdoor sb = SkynetSubnet.createBackdoorToExistingSubnet(2);
        killAgent(sb);
    }
}

package de.hammes.skynet;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import skynet.SkynetSubnet;
import skynet.SubnetBackdoor;

@RunWith(Parameterized.class)
public class SkynetProblemTestParameterized {

	private SubnetBackdoor sb;

	@Parameters
    public static Collection<SubnetBackdoor> sbData() {
		SubnetBackdoor[] testSb = new SubnetBackdoor[50];
		for (int i = 0; i < 50; i++) {
			testSb[i] = SkynetSubnet.createRandomSubnet(i+1);
		}
        return Arrays.asList(testSb);
    }

    public SkynetProblemTestParameterized(SubnetBackdoor sbInput) {
		sb = sbInput;
	}

	@Test
	public void testKillAgent() {
			new SkyNet().killAgent(sb);
			assertFalse(sb.isAgentOnAGateway());
	}

}
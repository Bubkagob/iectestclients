import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;
import org.json.JSONObject;
import java.net.UnknownHostException;

public class RudeDisconnectTest {

  private ConsoleClient850 client1;
  private ConsoleClient850 client2;
  private ConsoleClient850 client3;
  private ConsoleClient850 client4;
  private ConsoleClient850 client5;
  private ConsoleClient850 client6;
  private ConsoleClient850 client7;
  private ConsoleClient850 client8;
  private Requester requester;
  private String host;
  private String port;



  @DataProvider(name = "Floats")
  public Object[][] getFloats() {
    return new Object[][]{
        {"Test850.Dev1.Currents.I1", "VolcanoMicrologic1/MMXU1.A.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Currents.I2", "VolcanoMicrologic1/MMXU1.A.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Currents.I3", "VolcanoMicrologic1/MMXU1.A.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Currents.IN", "VolcanoMicrologic1/MMXU1.A.neut.cVal.mag.f", "MX"},
        {"Test850.Dev1.Currents.Imax", "VolcanoMicrologic1/MMXU1.A.zavg.cVal.mag.f", "MX"},
        {"Test850.Dev1.CurrMax.I1max", "VolcanoMicrologic1/MSTA1.MaxAmps1.mag.f", "MX"},
        {"Test850.Dev1.CurrMax.I2max", "VolcanoMicrologic1/MSTA1.MaxAmps2.mag.f", "MX"},
        {"Test850.Dev1.CurrMax.I3max", "VolcanoMicrologic1/MSTA1.MaxAmps3.mag.f", "MX"},
        {"Test850.Dev1.CurrMax.INmax", "VolcanoMicrologic1/MSTA1.MaxAmps4.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V12", "VolcanoMicrologic1/MMXU1.PPV.phsAB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V23", "VolcanoMicrologic1/MMXU1.PPV.phsBC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V31", "VolcanoMicrologic1/MMXU1.PPV.phsCA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V1N", "VolcanoMicrologic1/MMXU1.PhV.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V2N", "VolcanoMicrologic1/MMXU1.PhV.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Voltages.V3N", "VolcanoMicrologic1/MMXU1.PhV.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Frequencies.F", "VolcanoMicrologic1/MMXU1.Hz.mag.f", "MX"},
        {"Test850.Dev1.Power.P1", "VolcanoMicrologic1/MMXU1.W.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.P2", "VolcanoMicrologic1/MMXU1.W.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.P3", "VolcanoMicrologic1/MMXU1.W.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.Ptot", "VolcanoMicrologic1/MMXU1.TotW.mag.f", "MX"},
        {"Test850.Dev1.Power.Q1", "VolcanoMicrologic1/MMXU1.VAr.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.Q2", "VolcanoMicrologic1/MMXU1.VAr.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.Q3", "VolcanoMicrologic1/MMXU1.VAr.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.Qtot", "VolcanoMicrologic1/MMXU1.TotVAr.mag.f", "MX"},
        {"Test850.Dev1.Power.S1", "VolcanoMicrologic1/MMXU1.VA.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.S2", "VolcanoMicrologic1/MMXU1.VA.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.S3", "VolcanoMicrologic1/MMXU1.VA.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Power.Stot", "VolcanoMicrologic1/MMXU1.TotVA.mag.f", "MX"},
        {"Test850.Dev1.Factors.PF1", "VolcanoMicrologic1/MMXU1.PF.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev1.Factors.PF2", "VolcanoMicrologic1/MMXU1.PF.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev1.Factors.PF3", "VolcanoMicrologic1/MMXU1.PF.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev1.Factors.PFtot", "VolcanoMicrologic1/MMXU1.TotPF.mag.f", "MX"},
        {"Test850.Dev2.Currents.I1", "VolcanoMicrologic2/MMXU1.A.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Currents.I2", "VolcanoMicrologic2/MMXU1.A.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Currents.I3", "VolcanoMicrologic2/MMXU1.A.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Currents.IN", "VolcanoMicrologic2/MMXU1.A.neut.cVal.mag.f", "MX"},
        {"Test850.Dev2.Currents.Imax", "VolcanoMicrologic2/MMXU1.A.zavg.cVal.mag.f", "MX"},
        {"Test850.Dev2.CurrMax.I1max", "VolcanoMicrologic2/MSTA1.MaxAmps1.mag.f", "MX"},
        {"Test850.Dev2.CurrMax.I2max", "VolcanoMicrologic2/MSTA1.MaxAmps2.mag.f", "MX"},
        {"Test850.Dev2.CurrMax.I3max", "VolcanoMicrologic2/MSTA1.MaxAmps3.mag.f", "MX"},
        {"Test850.Dev2.CurrMax.INmax", "VolcanoMicrologic2/MSTA1.MaxAmps4.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V12", "VolcanoMicrologic2/MMXU1.PPV.phsAB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V23", "VolcanoMicrologic2/MMXU1.PPV.phsBC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V31", "VolcanoMicrologic2/MMXU1.PPV.phsCA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V1N", "VolcanoMicrologic2/MMXU1.PhV.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V2N", "VolcanoMicrologic2/MMXU1.PhV.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Voltages.V3N", "VolcanoMicrologic2/MMXU1.PhV.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Frequencies.F", "VolcanoMicrologic2/MMXU1.Hz.mag.f", "MX"},
        {"Test850.Dev2.Power.P1", "VolcanoMicrologic2/MMXU1.W.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.P2", "VolcanoMicrologic2/MMXU1.W.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.P3", "VolcanoMicrologic2/MMXU1.W.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.Ptot", "VolcanoMicrologic2/MMXU1.TotW.mag.f", "MX"},
        {"Test850.Dev2.Power.Q1", "VolcanoMicrologic2/MMXU1.VAr.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.Q2", "VolcanoMicrologic2/MMXU1.VAr.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.Q3", "VolcanoMicrologic2/MMXU1.VAr.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.Qtot", "VolcanoMicrologic2/MMXU1.TotVAr.mag.f", "MX"},
        {"Test850.Dev2.Power.S1", "VolcanoMicrologic2/MMXU1.VA.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.S2", "VolcanoMicrologic2/MMXU1.VA.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.S3", "VolcanoMicrologic2/MMXU1.VA.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Power.Stot", "VolcanoMicrologic2/MMXU1.TotVA.mag.f", "MX"},
        {"Test850.Dev2.Factors.PF1", "VolcanoMicrologic2/MMXU1.PF.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev2.Factors.PF2", "VolcanoMicrologic2/MMXU1.PF.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev2.Factors.PF3", "VolcanoMicrologic2/MMXU1.PF.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev2.Factors.PFtot", "VolcanoMicrologic2/MMXU1.TotPF.mag.f", "MX"},
        {"Test850.Dev3.Currents.I1", "VolcanoMicrologic3/MMXU1.A.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Currents.I2", "VolcanoMicrologic3/MMXU1.A.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Currents.I3", "VolcanoMicrologic3/MMXU1.A.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Currents.IN", "VolcanoMicrologic3/MMXU1.A.neut.cVal.mag.f", "MX"},
        {"Test850.Dev3.Currents.Imax", "VolcanoMicrologic3/MMXU1.A.zavg.cVal.mag.f", "MX"},
        {"Test850.Dev3.CurrMax.I1max", "VolcanoMicrologic3/MSTA1.MaxAmps1.mag.f", "MX"},
        {"Test850.Dev3.CurrMax.I2max", "VolcanoMicrologic3/MSTA1.MaxAmps2.mag.f", "MX"},
        {"Test850.Dev3.CurrMax.I3max", "VolcanoMicrologic3/MSTA1.MaxAmps3.mag.f", "MX"},
        {"Test850.Dev3.CurrMax.INmax", "VolcanoMicrologic3/MSTA1.MaxAmps4.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V12", "VolcanoMicrologic3/MMXU1.PPV.phsAB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V23", "VolcanoMicrologic3/MMXU1.PPV.phsBC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V31", "VolcanoMicrologic3/MMXU1.PPV.phsCA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V1N", "VolcanoMicrologic3/MMXU1.PhV.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V2N", "VolcanoMicrologic3/MMXU1.PhV.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Voltages.V3N", "VolcanoMicrologic3/MMXU1.PhV.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Frequencies.F", "VolcanoMicrologic3/MMXU1.Hz.mag.f", "MX"},
        {"Test850.Dev3.Power.P1", "VolcanoMicrologic3/MMXU1.W.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.P2", "VolcanoMicrologic3/MMXU1.W.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.P3", "VolcanoMicrologic3/MMXU1.W.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.Ptot", "VolcanoMicrologic3/MMXU1.TotW.mag.f", "MX"},
        {"Test850.Dev3.Power.Q1", "VolcanoMicrologic3/MMXU1.VAr.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.Q2", "VolcanoMicrologic3/MMXU1.VAr.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.Q3", "VolcanoMicrologic3/MMXU1.VAr.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.Qtot", "VolcanoMicrologic3/MMXU1.TotVAr.mag.f", "MX"},
        {"Test850.Dev3.Power.S1", "VolcanoMicrologic3/MMXU1.VA.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.S2", "VolcanoMicrologic3/MMXU1.VA.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.S3", "VolcanoMicrologic3/MMXU1.VA.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Power.Stot", "VolcanoMicrologic3/MMXU1.TotVA.mag.f", "MX"},
        {"Test850.Dev3.Factors.PF1", "VolcanoMicrologic3/MMXU1.PF.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev3.Factors.PF2", "VolcanoMicrologic3/MMXU1.PF.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev3.Factors.PF3", "VolcanoMicrologic3/MMXU1.PF.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev3.Factors.PFtot", "VolcanoMicrologic3/MMXU1.TotPF.mag.f", "MX"},
        {"Test850.Dev4.TestBranch.Reg1", "VolcanoZelio/MMXU1.A.phsA.cVal.mag.f", "MX"},
        {"Test850.Dev4.TestBranch.Reg2", "VolcanoZelio/MMXU1.A.phsB.cVal.mag.f", "MX"},
        {"Test850.Dev4.TestBranch.Reg3", "VolcanoZelio/MMXU1.A.phsC.cVal.mag.f", "MX"},
        {"Test850.Dev4.TestBranch.Reg4", "VolcanoZelio/MMXU1.A.neut.cVal.mag.f", "MX"}
    };
  }

  @BeforeMethod
  @Parameters({"host", "port"})
  public void setUp(String host, String port) throws UnknownHostException {
    this.port = port;
    this.host = host;
    client1 = new ConsoleClient850();
    client2 = new ConsoleClient850();
    client3 = new ConsoleClient850();
    client4 = new ConsoleClient850();
    client5 = new ConsoleClient850();
    client6 = new ConsoleClient850();
    client7 = new ConsoleClient850();
    client8 = new ConsoleClient850();
    requester = new Requester();
    client1.connect(host);
    client2.connect(host);
    client3.connect(host);
    client4.connect(host);
    client5.connect(host);
    client6.connect(host);
    client7.connect(host);
    client8.connect(host);
    client1.retrieveServerModelFromServer();
    client2.retrieveServerModelFromServer();
    client3.retrieveServerModelFromServer();
    client4.retrieveServerModelFromServer();
    client5.retrieveServerModelFromServer();
    client6.retrieveServerModelFromServer();
    client7.retrieveServerModelFromServer();
    client8.retrieveServerModelFromServer();
    System.out.println("Client HASH " + client1.hashCode() + " ---- " + client1.getClass());
    System.out.println("Client HASH " + client2.hashCode() + " ---- " + client2.getClass());
    System.out.println("Client HASH " + client3.hashCode() + " ---- " + client3.getClass());
    System.out.println("Client HASH " + client4.hashCode() + " ---- " + client4.getClass());
    System.out.println("Client HASH " + client5.hashCode() + " ---- " + client5.getClass());
    System.out.println("Client HASH " + client6.hashCode() + " ---- " + client6.getClass());
    System.out.println("Client HASH " + client7.hashCode() + " ---- " + client7.getClass());
    System.out.println("Client HASH " + client8.hashCode() + " ---- " + client8.getClass());
  }

  @AfterMethod
  public void tearDown() {
    client1.disconnect();
    client2.disconnect();
    client3.disconnect();
    client4.disconnect();
    client8.disconnect();
    client5.disconnect();
    client6.disconnect();
    client7.disconnect();
  }

  @Test(dataProvider = "Floats")
  public void readFloatsMeasuresTest(String dbVarRef, String iecRef, String fc) {
    Random rand = new Random();
    float randomFloat = (rand.nextFloat() * 999999F) + 1000F;
    requester.setValue(host, port, dbVarRef, randomFloat);
    try {
      Thread.sleep(100);
      JSONObject actualVar = requester.getJson(host, port, dbVarRef);
      Float iecValue1 = (Float) client1.readFloat(iecRef, fc);
      Float iecValue2 = (Float) client2.readFloat(iecRef, fc);
      Float iecValue3 = (Float) client3.readFloat(iecRef, fc);
      Float iecValue4 = (Float) client4.readFloat(iecRef, fc);
      Float iecValue5 = (Float) client5.readFloat(iecRef, fc);
      Float iecValue6 = (Float) client6.readFloat(iecRef, fc);
      Float iecValue7 = (Float) client7.readFloat(iecRef, fc);
      Float iecValue8 = (Float) client8.readFloat(iecRef, fc);
      Float restValue = actualVar.getFloat("v");
      Assert.assertEquals(restValue, iecValue1);
      Assert.assertEquals(restValue, iecValue2);
      Assert.assertEquals(restValue, iecValue3);
      Assert.assertEquals(restValue, iecValue4);
      Assert.assertEquals(restValue, iecValue5);
      Assert.assertEquals(restValue, iecValue6);
      Assert.assertEquals(restValue, iecValue7);
      Assert.assertEquals(restValue, iecValue8);
      System.out.println("#########FLOAT###############" + iecValue1 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue2 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue3 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue4 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue5 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue6 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue7 + " ---- " + restValue);
      System.out.println("#########FLOAT###############" + iecValue8 + " ---- " + restValue);
      for (int i = 0; i < 100; i++){
        randomFloat = (rand.nextFloat() * 999999F) + 1000F;
        requester.setValue(host, port, dbVarRef, randomFloat);
      }
    } catch (Exception ex) {
      System.out.println("Exception ex " + ex);
    }
  }
}



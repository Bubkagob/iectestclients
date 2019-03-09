import org.testng.Assert;
import org.testng.annotations.*;
import java.util.Random;
import org.json.JSONObject;
import java.net.UnknownHostException;

public class ConsoleClient850Test {

  private ConsoleClient850 client;
  private Requester requester;
  private String host;
  private String port;

  @DataProvider(name = "Int128s")
  public Object[][] getInt128s() {
    return new Object[][] {
      {"Test850.Dev1.Energy.Ep", "VolcanoMicrologic1/MMTR1.TotWh.actVal", "ST"},
      {"Test850.Dev1.Energy.Eq", "VolcanoMicrologic1/MMTR1.TotVArh.actVal", "ST"},
      {"Test850.Dev1.Energy.Es", "VolcanoMicrologic1/MMTR1.TotVAh.actVal", "ST"},
      {"Test850.Dev1.Energy.EpOut", "VolcanoMicrologic1/MMTR1.SupWh.actVal", "ST"},
      {"Test850.Dev1.Energy.EqOut", "VolcanoMicrologic1/MMTR1.SupVArh.actVal", "ST"},
      {"Test850.Dev1.Energy.EpIn", "VolcanoMicrologic1/MMTR1.DmdWh.actVal", "ST"},
      {"Test850.Dev1.Energy.EqIn", "VolcanoMicrologic1/MMTR1.DmdVArh.actVal", "ST"},
      {"Test850.Dev2.Energy.Ep", "VolcanoMicrologic2/MMTR1.TotWh.actVal", "ST"},
      {"Test850.Dev2.Energy.Eq", "VolcanoMicrologic2/MMTR1.TotVArh.actVal", "ST"},
      {"Test850.Dev2.Energy.Es", "VolcanoMicrologic2/MMTR1.TotVAh.actVal", "ST"},
      {"Test850.Dev2.Energy.EpOut", "VolcanoMicrologic2/MMTR1.SupWh.actVal", "ST"},
      {"Test850.Dev2.Energy.EqOut", "VolcanoMicrologic2/MMTR1.SupVArh.actVal", "ST"},
      {"Test850.Dev2.Energy.EpIn", "VolcanoMicrologic2/MMTR1.DmdWh.actVal", "ST"},
      {"Test850.Dev2.Energy.EqIn", "VolcanoMicrologic2/MMTR1.DmdVArh.actVal", "ST"},
      {"Test850.Dev3.Energy.Ep", "VolcanoMicrologic3/MMTR1.TotWh.actVal", "ST"},
      {"Test850.Dev3.Energy.Eq", "VolcanoMicrologic3/MMTR1.TotVArh.actVal", "ST"},
      {"Test850.Dev3.Energy.Es", "VolcanoMicrologic3/MMTR1.TotVAh.actVal", "ST"},
      {"Test850.Dev3.Energy.EpOut", "VolcanoMicrologic3/MMTR1.SupWh.actVal", "ST"},
      {"Test850.Dev3.Energy.EqOut", "VolcanoMicrologic3/MMTR1.SupVArh.actVal", "ST"},
      {"Test850.Dev3.Energy.EpIn", "VolcanoMicrologic3/MMTR1.DmdWh.actVal", "ST"},
      {"Test850.Dev3.Energy.EqIn", "VolcanoMicrologic3/MMTR1.DmdVArh.actVal", "ST"}
    };
  }

  @DataProvider(name = "Floats")
  public Object[][] getFloats() {
    return new Object[][] {
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
    client = new ConsoleClient850();
    requester = new Requester();
    client.connect(host);
    client.retrieveServerModelFromServer();
  }

  @AfterMethod
  public void tearDown() {
    client.disconnect();
  }

  @Test(dataProvider = "Floats")
  public void readFloatsMeasuresTest(String dbVarRef, String iecRef, String fc) {
    Random rand = new Random();
    float randomFloat = (rand.nextFloat() * 999999F) + 1000F;
    requester.setValue(host, port, dbVarRef, randomFloat);
    try {
      Thread.sleep(100);
      JSONObject actualVar = requester.getJson(host, port, dbVarRef);
      Float iecValue = (Float) client.readFloat(iecRef, fc);
      Float restValue = actualVar.getFloat("v");
      System.out.println("#########FLOAT###############" + iecValue + " ---- " + restValue);
      Assert.assertEquals(actualVar.getFloat("v"), client.readFloat(iecRef, fc));
    } catch (Exception ex) {
      System.out.println("Exception ex " + ex);
    }
  }

  @Test(dataProvider = "Int128s")
  public void readInt128sMeasuresTest(String dbVarRef, String iecRef, String fc) {
    Random rand = new Random();
    int randomInt = (rand.nextInt() * 999999) + 1000;
    requester.setValue(host, port, dbVarRef, randomInt);
    try {
      Thread.sleep(100);
      JSONObject actualVar = requester.getJson(host, port, dbVarRef);
      Long iecValue = (Long) client.readFloat(iecRef, fc);
      Assert.assertEquals((long) iecValue, 0);
    } catch (Exception ex) {
      System.out.println("Exception ex " + ex);
    }
  }
}

import java.util.Collection;
import org.openmuc.openiec61850.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.openmuc.openiec61850.internal.cli.ActionException;

class ConsoleClient850 {

  private static volatile ClientAssociation association;
  private static ServerModel serverModel;
  private static Report report;

  ServerModel getServerModel() {
    return serverModel;
  }

  static Report getReport() {
    return report;
  }

  boolean enableReport(String reference) {
    System.out.println("Enabling Report!");
    Urcb urcb = serverModel.getUrcb(reference);
    if (urcb == null) {
      Brcb brcb = serverModel.getBrcb(reference);
      if (brcb != null) {
        try {
          association.enableReporting(brcb);
          System.out.println(brcb.getRptEna());
          return true;
        } catch (Exception ex) {
          System.out.println(ex);
        }

        return true;
      }
      return false;
    }

    return true;
  }

  boolean retrieveServerModelFromFile(String sclFilePath) {
    try {
      System.out.println(association.getClass());
      serverModel = association.getModelFromSclFile(sclFilePath);
    } catch (SclParseException e1) {
      System.out.println("Error parsing SCL file: " + e1.getMessage());
      return false;
    }

    System.out.println("successfully read model from file");
    return true;
  }

  void retrieveServerModelFromServer() {
    try {
      serverModel = association.retrieveModel();
    } catch (ServiceError e) {
      System.out.println("Service error: " + e.getMessage());
      return;
    } catch (IOException e) {
      System.out.println("Fatal error: " + e.getMessage());
      return;
    }

    System.out.println("successfully read model from server");
  }

  private FcModelNode getFcModelNode(String reference, String fcString) {

    Fc fc = Fc.fromString(fcString);
    if (fc == null) {
      System.out.println("Unknown functional constraint.");
    }
    ModelNode modelNode = serverModel.findModelNode(reference, Fc.fromString(fcString));
    if (modelNode == null) {
      System.out.println(
          "A model node with the given reference and functional constraint could not be found.");
    }

    if (!(modelNode instanceof FcModelNode)) {
      System.out.println("The given model node is not a functionally constraint model node.");
    }

    return (FcModelNode) modelNode;
  }

  boolean readStatusVar(String variable) {
    String fcString = "ST";

    Fc fc = Fc.fromString(fcString);
    if (fc == null) {
      System.out.println("Unknown functional constraint.");
      return false;
    }

    FcModelNode fcModelNode = getFcModelNode(variable, "ST");

    try {
      association.getDataValues(fcModelNode);
    } catch (ServiceError e) {
      System.out.println("Service error: " + e.getMessage());
      return false;
    } catch (IOException e) {
      System.out.println("Fatal error: " + e.getMessage());
      return false;
    }

    BdaBoolean bdaBool = (BdaBoolean) fcModelNode;
    return bdaBool.getValue();
  }

  Object readFloat(String variable, String fcString) {

    Fc fc = Fc.fromString(fcString);

    if (fc == null) {
      System.out.println("Unknown functional constraint.");
    }

    FcModelNode fcModelNode = getFcModelNode(variable, fcString);

    try {
      association.getDataValues(fcModelNode);
    } catch (ServiceError e) {
      System.out.println("Service error: " + e.getMessage());
    } catch (IOException e) {
      System.out.println("Fatal error: " + e.getMessage());
    }

    if (fcModelNode instanceof BdaInt128) {
      BdaInt128 bdaInt128 = (BdaInt128) fcModelNode;
      return bdaInt128.getValue();
    } else if (fcModelNode instanceof BdaFloat32) {
      BdaFloat32 bdaFloat32 = (BdaFloat32) fcModelNode;
      return bdaFloat32.getFloat();
    } else {
      return 0.0f;
    }
  }

  void connect(String host) throws UnknownHostException {
    ClientSap clientSap = new ClientSap();
    InetAddress address = InetAddress.getByName(host);
    try {
      association = clientSap.associate(address, 102, null, new EventListener());
    } catch (IOException e) {
      System.out.println("Unable to connect to remote host.");
      return;
    }
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread() {
              @Override
              public void run() {
                association.close();
              }
            });
    System.out.println("successfully connected");
  }

  void disconnect() {
    association.close();
    association.disconnect();
  }

  private static class EventListener implements ClientEventListener {

    @Override
    public void newReport(Report r) {
      System.out.println("\n----------------");
      System.out.println("Received report: ");
      System.err.println(r.getEntryId());
      System.err.println(r.getSqNum());
      System.err.println(r.getDataSetRef());
      report = r;
      System.out.println("------------------");
    }

    @Override
    public void associationClosed(IOException e) {
      System.out.print("Received connection closed signal. Reason: ");
      if (!e.getMessage().isEmpty()) {
        System.out.println(e.getMessage());
      } else {
        System.out.println("unknown");
      }
    }
  }
}

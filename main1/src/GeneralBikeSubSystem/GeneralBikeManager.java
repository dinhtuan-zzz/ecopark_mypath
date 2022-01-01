package GeneralBikeSubSystem;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import static GeneralBikeSubSystem.GeneralBikeConnector.connect;

public class GeneralBikeManager implements IGeneralBike{
	private static Connection connection;
	public GeneralBikeManager() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean checkBikeExist(String bikeCode)  {
		// TODO Auto-generated method stub
		boolean isExist = true;
        try {
            connection = connect();
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM GeneralBike WHERE licensePlate = " + "'" + bikeCode + "'");
            if (!resultSet.next()) isExist = false;
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return isExist;
	}

	@Override
	public boolean checkBikeRent(String bikeCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> getBikeDetail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createBike(HashMap<String, String> BikeInfo) throws ParseException  {
		// TODO Auto-generated method stub
		try {
            connection = connect();
            
            //insert to general bike table
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO GeneralBike VALUES ( "
            		+ "'" + BikeInfo.get("License Plate") + "',"
            		+ "'" + BikeInfo.get("Name") + "',"
            		+ Double.parseDouble(BikeInfo.get("Weight")) + ","
            		+ "'" + BikeInfo.get("Manufactured Date") + "',"
            		+ "'" + BikeInfo.get("Type") + "',"
            		+ "'" + BikeInfo.get("DockId") + "'"
            		+ ")");	
            if(BikeInfo.get("Type") == "EBike") {
            	SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
    			java.util.Date dt2 = dt.parse(BikeInfo.get("Estimate time left"));
    			Timestamp timestamp = new java.sql.Timestamp(dt2.getTime());
	           statement.executeUpdate("INSERT INTO EBike VALUES ("
	        		   + "'" + BikeInfo.get("License Plate") + "',"
	        		   + Double.parseDouble(BikeInfo.get("Battery Percent")) + ","
	        		   + Integer.parseInt(BikeInfo.get("Load Cycle")) + ","
	        		   + "'" + timestamp +"'"
	        		   + ")");
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
		
	}
	

}

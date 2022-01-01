package GeneralBikeSubSystem;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public interface IGeneralBike {
	 public boolean checkBikeExist(String bikeCode) ;
	 public boolean checkBikeRent(String bikeCode);
	 public Map<String, String> getBikeDetail();
	 public void createBike(HashMap<String, String> BikeInfo) throws ParseException ;
}
